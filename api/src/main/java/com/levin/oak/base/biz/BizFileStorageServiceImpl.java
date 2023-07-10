package com.levin.oak.base.biz;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.xuyanwu.spring.file.storage.FileInfo;
import cn.xuyanwu.spring.file.storage.FileStorageProperties;
import cn.xuyanwu.spring.file.storage.FileStorageService;
import cn.xuyanwu.spring.file.storage.UploadPretreatment;
import cn.xuyanwu.spring.file.storage.aspect.FileStorageAspect;
import cn.xuyanwu.spring.file.storage.aspect.UploadAspectChain;
import cn.xuyanwu.spring.file.storage.exception.FileStorageRuntimeException;
import cn.xuyanwu.spring.file.storage.platform.FileStorage;
import cn.xuyanwu.spring.file.storage.recorder.DefaultFileRecorder;
import cn.xuyanwu.spring.file.storage.recorder.FileRecorder;
import cn.xuyanwu.spring.file.storage.tika.DefaultTikaFactory;
import cn.xuyanwu.spring.file.storage.tika.TikaFactory;
import com.google.gson.Gson;
import com.levin.oak.base.autoconfigure.FrameworkProperties;
import com.levin.oak.base.entities.Setting;
import com.levin.oak.base.services.setting.SettingService;
import com.levin.oak.base.services.setting.info.SettingInfo;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

import static com.levin.oak.base.ModuleOption.PLUGIN_PREFIX;


@Service(PLUGIN_PREFIX + "BizFileStorageService")
//@ConditionalOnClass({FileStorageService.class})
//@ConditionalOnMissingBean({BizFileStorageService.class}) //默认只有在无对应服务才启用
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "BizFileStorageService", matchIfMissing = true)
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

    @DubboReference
    BizSettingService bizSettingService;

    @Autowired(required = false)
    List<FileStorageAspect> aspectList;

    @Autowired(required = false)
    FileRecorder fileRecorder;

    @Autowired(required = false)
    TikaFactory tikaFactory;

    private static Gson gson = new Gson();

    final FileStorageService fileStorageService = new FileStorageService();

    public static final String CFG_CODE = "文件存储配置";

    static final Map<String, Class<? extends FileStorage>> fileStorageClassMap = new ConcurrentHashMap<>();

    {
        ClassUtil.scanPackage(FileStorage.class.getPackage().getName(),
                        type -> FileStorage.class.isAssignableFrom(type) && !Modifier.isAbstract(type.getModifiers()))
                .stream().forEach(type -> fileStorageClassMap.put(type.getSimpleName(), (Class<? extends FileStorage>) type));

    }

    @PostConstruct
    public void init() {

        if (fileRecorder == null) {
            fileRecorder = new DefaultFileRecorder();
        }
        if (tikaFactory == null) {
            tikaFactory = new DefaultTikaFactory();
        }

        fileStorageService.setProperties(new FileStorageProperties());
        fileStorageService.setFileRecorder(fileRecorder);
        fileStorageService.setTikaFactory(tikaFactory);

        log.info("文件存储服务初始完成");

    }


    /**
     * 获取文件存储器
     *
     * @param tenantId
     * @param appId
     * @return
     */
    protected FileStorage getFileStorage(String tenantId, String appId) {

        Map<String, Object> setting = getSetting(tenantId, appId);

        String fileStorageType = (String) setting.get("fileStorageType");

        Assert.notBlank(fileStorageType, "系统设置中[{}]，fileStorageType属性必须指定", CFG_CODE);

        Class<? extends FileStorage> implType = fileStorageClassMap.get(fileStorageType);

        if (implType == null && !fileStorageType.endsWith("FileStorage")) {
            implType = fileStorageClassMap.get(fileStorageType + "FileStorage");
        }

        Assert.notNull(implType, "不支持的文件存储类型{}", fileStorageType);

        return BeanUtil.copyProperties(setting, implType);
    }

    /**
     * 获取配置信息
     *
     * @param tenantId
     * @param appId
     * @return
     */
    protected Map<String, Object> getSetting(String tenantId, String appId) {

        if (!StringUtils.hasText(appId)) {
            appId = "";
        }

        String code = CFG_CODE + "|" + appId;

        String value = bizSettingService.getValue(tenantId, code, () -> {
                    Map<String, Object> config = MapUtil.builder("ref_doc", (Object) "Json格式，具体配置参考文档：https://spring-file-storage.xuyanwu.cn/")
                            .put("fileStorageType", "AliyunOss")
                            .build();
                    return new SettingInfo().setCategoryName(CFG_CODE)
                            .setCode(code)
                            .setValueType(Setting.ValueType.Json)
                            .setInputPlaceholder("Json格式,fileStorageType属性配置通道类型")
                            .setValueContent(gson.toJson(config))
                            .setName(CFG_CODE)
                            .setRemark("内容必须Json格式，fileStorageType 属性必须指定，可选择包括："
                                    + fileStorageClassMap.keySet()
                                    + "\n具体配置参考文档：https://spring-file-storage.xuyanwu.cn/");
                }
        );

        return (Map<String, Object>) (StringUtils.hasText(value) ? gson.fromJson(value, Map.class) : Collections.emptyMap());
    }


    /**
     * 上传文件，成功返回文件信息，失败返回 null
     */
    public FileInfo upload(UploadPretreatment pre, FileStorage fileStorage) {

        //FileStorage fileStorage = getFileStorage(pre.getPlatform());

        if (fileStorage == null)
            throw new FileStorageRuntimeException("没有找到对应的存储平台！");

        MultipartFile file = pre.getFileWrapper();

        if (file == null)
            throw new FileStorageRuntimeException("文件不允许为 null ！");

        if (pre.getPlatform() == null)
            throw new FileStorageRuntimeException("platform 不允许为 null ！");

        FileInfo fileInfo = new FileInfo();
        fileInfo.setCreateTime(new Date());
        fileInfo.setSize(file.getSize());
        fileInfo.setOriginalFilename(file.getOriginalFilename());
        fileInfo.setExt(FileNameUtil.getSuffix(file.getOriginalFilename()));
        fileInfo.setObjectId(pre.getObjectId());
        fileInfo.setObjectType(pre.getObjectType());
        fileInfo.setPath(pre.getPath());
        fileInfo.setPlatform(pre.getPlatform());
        fileInfo.setAttr(pre.getAttr());

        if (StrUtil.isNotBlank(pre.getSaveFilename())) {
            fileInfo.setFilename(pre.getSaveFilename());
        } else {
            fileInfo.setFilename(IdUtil.objectId() + (StrUtil.isEmpty(fileInfo.getExt()) ? StrUtil.EMPTY : "." + fileInfo.getExt()));
        }
        if (pre.getContentType() != null) {
            fileInfo.setContentType(pre.getContentType());
        } else if (pre.getFileWrapper().getContentType() != null) {
            fileInfo.setContentType(pre.getFileWrapper().getContentType());
        } else {
            fileInfo.setContentType(tikaFactory.getTika().detect(fileInfo.getFilename()));
        }

        byte[] thumbnailBytes = pre.getThumbnailBytes();
        if (thumbnailBytes != null) {
            fileInfo.setThSize((long) thumbnailBytes.length);
            if (StrUtil.isNotBlank(pre.getSaveThFilename())) {
                fileInfo.setThFilename(pre.getSaveThFilename() + pre.getThumbnailSuffix());
            } else {
                fileInfo.setThFilename(fileInfo.getFilename() + pre.getThumbnailSuffix());
            }
            fileInfo.setThContentType(tikaFactory.getTika().detect(thumbnailBytes, fileInfo.getThFilename()));
        }

        //处理切面
        return new UploadAspectChain(aspectList, (_fileInfo, _pre, _fileStorage, _fileRecorder) -> {
            //真正开始保存
            if (_fileStorage.save(_fileInfo, _pre)) {
                if (_fileRecorder.record(_fileInfo)) {
                    return _fileInfo;
                }
            }
            return null;
        }).next(fileInfo, pre, fileStorage, fileRecorder);
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

        FileStorage fileStorage = getFileStorage(tenantId, appId);

        try {
            FileInfo fileInfo = upload(fileStorageService.of(multipartFile), fileStorage);

            Assert.notNull(fileInfo, "文件({})上传失败", multipartFile.getOriginalFilename());

            return fileInfo.getUrl();

        } finally {
            fileStorage.close();
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

        FileStorage fileStorage = getFileStorage(tenantId, appId);

        InputStream inputStream = sourceSupplier.get();

        try {

            UploadPretreatment pretreatment = fileStorageService.of(inputStream);

            pretreatment.getAttr().putAll(sourceExtInfo);

            FileInfo fileInfo = upload(pretreatment, fileStorage);

            Assert.notNull(fileInfo, "文件({})上传失败", fileName);

            return fileInfo.getUrl();

        } finally {
            fileStorage.close();
        }
    }

}
