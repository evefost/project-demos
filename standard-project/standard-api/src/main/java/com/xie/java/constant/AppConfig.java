package com.xie.java.constant;


import org.springframework.beans.factory.annotation.Value;

/**
 * Created by xieyang on 16/12/11.
 */
public final class AppConfig {

    @Value("${package_enviroment}")
    private String packageEnviroment;

    @Value("${white_list}")
    private String whileList;

    @Value("${config_debug}")
    private Boolean configDebug;

    public Boolean getConfigDebug() {
        return configDebug;
    }

    public void setConfigDebug(Boolean configDebug) {
        this.configDebug = configDebug;
    }

    public String getPackageEnviroment() {
        return packageEnviroment;
    }

    public String getWhileList() {
        return whileList;
    }
}
