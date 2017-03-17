package com.xie.java.constant;


import org.springframework.beans.factory.annotation.Value;

/**
 * Created by xieyang on 16/12/11.
 */
public final class AppConfig {

    @Value("${package_env}")
    private String packageEnviroment;

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


}
