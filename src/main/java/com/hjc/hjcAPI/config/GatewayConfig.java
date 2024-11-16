package com.hjc.hjcAPI.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Data
@Configuration
@ConfigurationProperties(prefix = "interface.gateway")
public class GatewayConfig {
    private String gatewayHost;

    private int gatewayPort;
    public String getGatewayHost() {
        return this.gatewayHost;
    }
    public void setGatewayHost(String gatewayHost) {
        this.gatewayHost = gatewayHost;
    }
    public int getGatewayPort() {
        return this.gatewayPort;
    }
    public void setGatewayPort(int gatewayPort) {
        this.gatewayPort = gatewayPort;
    }
    @Bean
    public String getGatewayConfig() {
        return this.gatewayHost
                 + ":" + this.gatewayPort;
    }
}
