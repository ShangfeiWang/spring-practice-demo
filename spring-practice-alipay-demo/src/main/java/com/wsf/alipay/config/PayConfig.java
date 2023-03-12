package com.wsf.alipay.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "alipay")
@Data
public class PayConfig {

    /**
     * appId应用id
     */
    private String appId;

    /**
     * 应用公钥
     */
    private String publicKey;

    /**
     * 应用私钥
     */
    private String privateKey;
}
