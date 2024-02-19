package com.levin.oak.base.codegen.model;

import cn.hutool.core.io.FileUtil;
import cn.hutool.crypto.SecureUtil;
import com.levin.commons.utils.ExpressionUtils;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.Template;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;
import java.util.stream.Collectors;


@Slf4j
public class TemplateUtils {

    static final String TEMPLATE_PATH = "/templates/com.levin.oak.base/V1/";

    /**
     * 生成文件，如果文件存在已经被修改，则直接返回。
     *
     * @param templatePath
     * @param params
     * @param fileName
     * @throws Exception
     */

    @SneakyThrows
    public static void genFileByTemplate(File baseDir, String fileName, final String templatePath, Map<String, Object> params) {

        if (baseDir == null) {
            baseDir = new File("./");
        }

        //复制
        params = new LinkedHashMap<>(params);

        //自动生成文件名
        if (!StringUtils.hasText(fileName)) {
            fileName = ExpressionUtils.evalGroovy("\"" + templatePath + "\"", null, params).toString();
        }

        File file = new File(baseDir, fileName);

        String path = file.getAbsoluteFile().getCanonicalPath().substring(baseDir.getCanonicalPath().length());

        final String prefix = "代码生成哈希校验码：[";

        final String keyword = "@author Auto gen by simple-dao-codegen, @time:";

        //内容过滤器
        final Function<List<String>, String> linesFilter = lines -> lines.stream()
                //去除空行
                .filter(StringUtils::hasText)
                //不包含生成标记行，里面有动态时间
                .filter(line -> !line.contains(keyword) && !line.contains(prefix))
                //去除空格
                .map(StringUtils::trimWhitespace)
                .collect(Collectors.joining());

        final AtomicBoolean skip = new AtomicBoolean(false);

        final String fileOldCompactContent = getCompactContent(file, skip, prefix, linesFilter);

        if (skip.get()) {
            return;
        }

        file.getParentFile().mkdirs();

        //文件名
        params.put("fileName", file.getName());
        params.put("templateFileName", templatePath.replace("\\", "/"));

        StringWriter stringWriter = new StringWriter();

        getTemplate(templatePath).process(params, stringWriter);

        //文件内容
        String fileContent = stringWriter.toString();


        final int startIdx = fileContent.indexOf(prefix);

        String newMd5 = "";

        String newCompactContent = "";

        if (startIdx != -1) {

            //需要hash的部分
            newCompactContent = fileContent;

            newCompactContent = linesFilter.apply(Arrays.asList(newCompactContent.split("[\r\n]")));

            //如果文件内容相同，没有变化，则直接返回
            if (newCompactContent.contentEquals(fileOldCompactContent)) {
                log.debug("目标文件：" + path + " 已经存在，新生成的代码内容和旧内容相同，跳过。");
                return;
            }

            newMd5 = SecureUtil.md5(newCompactContent);

            int endIndex = fileContent.indexOf("]", startIdx);

            fileContent = fileContent.substring(0, startIdx + prefix.length()) + newMd5 + fileContent.substring(endIndex);
        }

        //写入文件
        FileUtil.writeString(fileContent, file, StandardCharsets.UTF_8);

        log.info("目标文件：{} 写入成功，新内容压缩后的MD5：<{}>。", path, newMd5);

    }


    /**
     * 获取未变更的内容
     *
     * @param file
     * @param skip
     * @param prefix
     * @param linesFilter
     * @return
     */
    @SneakyThrows
    public static String getCompactContent(File file, AtomicBoolean skip, String prefix, Function<List<String>, String> linesFilter) {

        if (file == null || !file.exists()) {
            return "";
        }

        //读取旧文件内容
        List<String> lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);

        final String md5Line = lines.stream().filter(StringUtils::hasText)
                .filter(line -> line.contains(prefix))
                .findFirst()
                .orElse(null);

        int startIdx = StringUtils.hasText(md5Line) ? md5Line.indexOf(prefix) : -1;

        if (startIdx == -1) {
            skip.set(true);
            log.warn("目标文件：" + file + " 已经存在，但没有发现生成关键字<<<{}>>>, {}, 将被忽略。", prefix, startIdx);
            return null;
        }

        StringBuilder info = new StringBuilder();

        //提取md5
        final String md5 = md5Line.substring(startIdx, md5Line.indexOf("]", startIdx))
                .substring(prefix.length());

        String fileOldCompactContent = linesFilter.apply(lines);

        //1、去除空行，trim 去除关键字后的文件内容
        if (md5.equals(SecureUtil.md5(fileOldCompactContent))) {
            return fileOldCompactContent;
        } else {
            info.append("去除空行 -> 裁剪空字符串");
        }

        skip.set(true);

        log.warn("目标文件：{}已经存在，并且被修改过，跳过。校验md5：{}，内容逐步校验逻辑：{}。", file, md5, info);

        return null;
    }


    private static Template getTemplate(String templatePath) throws IOException {

        //freemark 模板路径只支持正斜杠
        templatePath = templatePath.replace("\\", "/").replace("//", "/");

        //创建一个合适的Configuration对象
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);
        DefaultObjectWrapper objectWrapper = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_28).build();
        configuration.setObjectWrapper(objectWrapper);

        //这个一定要设置，不然在生成的页面中 会乱码
        configuration.setDefaultEncoding("UTF-8");

        //支持从jar中加载模板
        configuration.setClassForTemplateLoading(TemplateUtils.class, "/");

        if (!templatePath.startsWith(TEMPLATE_PATH)) {
            templatePath = TEMPLATE_PATH + templatePath;
        }

        //获取页面模版。
        return configuration.getTemplate(templatePath);
    }
}
