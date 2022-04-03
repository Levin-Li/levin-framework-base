package com.levin.oak.base.autoconfigure;

import com.levin.oak.base.ModuleOption;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Data
@Accessors(chain = true)
@FieldNameConstants

@ConfigurationProperties(prefix = ModuleOption.PACKAGE_NAME)
public class FrameworkBaseProperties implements Serializable {

    /**
     * 是否开启API签名验证
     */
    private boolean enableApiSign = false;

    /**
     * 访问控制
     */
    @NestedConfigurationProperty
    private final AccessController accessController = new AccessController();

    /**
     * 访问日志
     */
    @NestedConfigurationProperty
    private final AccessLog accessLog = new AccessLog();

    private ServerProperties serverProperties;

    @Autowired
    void setServerProperties(ObjectProvider<ServerProperties> serverProperties) {
        this.serverProperties = serverProperties.getIfUnique();
    }

    @Data
    @Accessors(chain = true)
    public static class AccessController
            implements Serializable {

        List<String> excludePathPatterns = Collections.emptyList();

    }


    @Data
    @Accessors(chain = true)
    public static class AccessLog
            implements Serializable {

        boolean enable = true;

        String name;

        List<String> ignorePackages = Collections.emptyList();

        List<String> ignorePaths = Collections.emptyList();

        List<String> ignoreKeywords = Collections.emptyList();

    }

}
