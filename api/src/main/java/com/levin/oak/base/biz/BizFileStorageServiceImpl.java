package com.levin.oak.base.biz;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.levin.oak.base.autoconfigure.FrameworkProperties;
import com.levin.oak.base.entities.Setting;
import com.levin.oak.base.services.setting.SettingService;
import com.levin.oak.base.services.setting.info.SettingInfo;
import com.levin.oak.base.services.setting.req.CreateSettingReq;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.dromara.x.file.storage.core.FileStorageProperties;
import org.dromara.x.file.storage.core.FileStorageService;
import org.dromara.x.file.storage.core.FileStorageServiceBuilder;
import org.dromara.x.file.storage.core.platform.FileStorage;
import org.dromara.x.file.storage.spring.file.MultipartFileWrapperAdapter;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Role;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.levin.oak.base.ModuleOption.PLUGIN_PREFIX;


@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
@Service(PLUGIN_PREFIX + "BizFileStorageService")
@ConditionalOnClass({FileStorageService.class})

@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "BizFileStorageService", havingValue = "true", matchIfMissing = true)
@Slf4j
//@Valid只能用在controller，@Validated可以用在其他被spring管理的类上。
//@Validated
@Tag(name = "文件存储服务", description = "支持OSS，COS，S3等云服务")
public class BizFileStorageServiceImpl
        implements BizFileStorageService {

    @Autowired
    RedissonClient redissonClient;

    @Autowired
    FrameworkProperties frameworkProperties;

    @Autowired //@DubboReference
    SettingService settingService;

    @Autowired //@DubboReference
    BizSettingService bizSettingService;

    private static Gson gson = new GsonBuilder().setPrettyPrinting().setLenient().create();

    public static final String CFG_CODE = "文件存储配置";

    static final Map<String, Class<? extends FileStorage>> fileStorageClassMap = new ConcurrentHashMap<>();

    {
        ClassUtil.scanPackage(FileStorage.class.getPackage().getName(),
                        type -> FileStorage.class.isAssignableFrom(type) && !Modifier.isAbstract(type.getModifiers()))
                .stream().forEach(type -> fileStorageClassMap.put(type.getSimpleName().substring(0, type.getSimpleName().indexOf("FileStorage")), (Class<? extends FileStorage>) type));
    }

    @PostConstruct
    public void init() {
        log.info("文件存储服务初始完成");
    }

    protected Map<String, Object> getSetting(String tenantId, String appId) {

        String value = bizSettingService.getValue(tenantId, getSettingCode(tenantId, appId));

        return (Map<String, Object>) (StringUtils.hasText(value) ? gson.fromJson(value, Map.class) : Collections.emptyMap());
    }

    private String getSettingCode(String tenantId, String appId) {
        return CFG_CODE + (StringUtils.hasText(appId) ? "|" + appId : "");
    }

    /**
     * 获取文件存储器
     *
     * @param tenantId
     * @param appId
     * @return
     */
    protected FileStorageService getFileStorageService(String tenantId, String appId) {

        Map<String, Object> setting = getSetting(tenantId, appId);

        if (setting == null || setting.isEmpty()) {
            //创建默认配置
            String settingCode = getSettingCode(tenantId, appId);
            settingService.create(BeanUtil.copyProperties(newDefaultConfig(settingCode), CreateSettingReq.class).setId(settingCode + "@" + (StringUtils.hasText(tenantId) ? tenantId : "")));
            setting = getSetting(tenantId, appId);
        }

        String fileStorageType = (String) setting.get("fileStorageType");

        Assert.notBlank(fileStorageType, "系统设置[{}]，fileStorageType属性必须指定", CFG_CODE);

        Class<? extends FileStorage> implType = fileStorageClassMap.get(fileStorageType);

        // Assert.notNull(implType, "未知的存储类型：fileStorageType={}", fileStorageType);

        Assert.notNull(implType, "不支持的文件存储类型{}", fileStorageType);

        FileStorageProperties fileStorageProperties = new FileStorageProperties();

        //配置类
        FileStorageProperties.BaseConfig baseConfig = BeanUtil.copyProperties(setting, ClassUtil.loadClass(FileStorageProperties.class.getName() + "." + fileStorageType + "Config"));

        List configList = (List) BeanUtil.getFieldValue(fileStorageProperties, StrUtil.lowerFirst(fileStorageType));

        //加入配置
        configList.add(0, baseConfig);

        log.info("*** Build FileStorageService , fileStorageType:{},configList:{}", fileStorageType, configList);

        FileStorageServiceBuilder builder = FileStorageServiceBuilder.create(fileStorageProperties).useDefault();


        builder.addFileWrapperAdapter(new MultipartFileWrapperAdapter());

        FileStorageService fileStorageService = builder.build();

        fileStorageService.setDefaultPlatform(baseConfig.getPlatform());

        return fileStorageService;
    }


    private static SettingInfo newDefaultConfig(String code) {

        Map<String, Object> config = MapUtil.builder("配置参考文档", (Object) "Json格式，具体配置参考文档：https://spring-file-storage.xuyanwu.cn/")
                .put("fileStorageTypeList", fileStorageClassMap.keySet().stream().collect(Collectors.toList()))
                .put("fileStorageType", "LocalPlus")
                .put("platform", "LocalPlus-1")
                .put("domain", "https://xxx.doamin.com")
                .put("storagePath", "/home/dev")
                .put("basePath", "pic/")
                .put("配置说明", "LocalPlus 是本地存储方案，可以指定Spring mvc资源路径作为[storagePath]，basePath为访问路径")
                .build();

        return new SettingInfo()
                .setCategoryName(CFG_CODE)
                .setCode(code)
                .setValueType(Setting.ValueType.Json)
                .setInputPlaceholder("Json格式,fileStorageType属性配置通道类型")
                .setValueContent(gson.toJson(config))
                .setName(CFG_CODE)
                .setRemark("内容必须Json格式，fileStorageType 属性必须指定，可选择包括："
                        + fileStorageClassMap.keySet()
                        + "\n具体配置参考文档：https://spring-file-storage.xuyanwu.cn/");
    }

    /**
     * 存储文件
     *
     * @param tenantId
     * @param appId
     * @return URL
     */
    @Override
    public String upload(String tenantId, String appId, MultipartFile multipartFile) {

        Assert.notNull(multipartFile, "MultipartFile 上传内容没有指定");

        FileStorageService fileStorageService = getFileStorageService(tenantId, appId);

        try {
            return fileStorageService.of(multipartFile).upload().getUrl();
        } finally {
            fileStorageService.destroy();
        }
    }


    /**
     * 存储文件
     *
     * @param tenantId
     * @param appId
     * @param fileName       文件名
     * @param sourceSupplier
     * @param sourceExtInfo
     * @return URL
     */
    @Override
    public String upload(String tenantId, String appId, String fileName, Supplier<InputStream> sourceSupplier, Map<String, Object> sourceExtInfo) {
        FileStorageService fileStorageService = getFileStorageService(tenantId, appId);
        try {
            return fileStorageService.of(sourceSupplier.get()).upload().getUrl();
        } finally {
            fileStorageService.destroy();
        }
    }

}
