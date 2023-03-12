package com.wsf.alipay.config;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConfig;
import com.alipay.api.DefaultAlipayClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Autowired
    private PayConfig payConfig;

    @Bean
    public AlipayConfig alipayConfig() {
        AlipayConfig config = new AlipayConfig();
        config.setServerUrl("https://openapi.alipaydev.com/gateway.do");
        config.setAppId(payConfig.getAppId());
        config.setAlipayPublicKey(payConfig.getPublicKey());
        config.setPrivateKey(payConfig.getPrivateKey());
        return config;
    }

    @Bean
    public AlipayClient alipayClient(AlipayConfig alipayConfig) {
        try {
            return new DefaultAlipayClient(alipayConfig);
        } catch (AlipayApiException e) {
            e.printStackTrace();
            return null;
        }
    }

}
