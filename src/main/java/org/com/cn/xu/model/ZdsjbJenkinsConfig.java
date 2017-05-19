package org.com.cn.xu.model;

import java.io.Serializable;

/**
 * Created by xbf on 2017/5/15.
 */
public class ZdsjbJenkinsConfig implements Serializable {
    private static final long serialVersionUID = 1980165213250504257L;

    private String jksHostIp;
    private Integer jksPort;
    private String jksUserName;
    private String jksPassword;
    private String jksPackageUrl;
    private Integer jksConfigNo;
    private String areaCode;

    public String getJksHostIp() {
        return jksHostIp;
    }

    public void setJksHostIp(String jksHostIp) {
        this.jksHostIp = jksHostIp;
    }

    public Integer getJksPort() {
        return jksPort;
    }

    public void setJksPort(Integer jksPort) {
        this.jksPort = jksPort;
    }

    public String getJksUserName() {
        return jksUserName;
    }

    public void setJksUserName(String jksUserName) {
        this.jksUserName = jksUserName;
    }

    public String getJksPassword() {
        return jksPassword;
    }

    public void setJksPassword(String jksPassword) {
        this.jksPassword = jksPassword;
    }

    public String getJksPackageUrl() {
        return jksPackageUrl;
    }

    public void setJksPackageUrl(String jksPackageUrl) {
        this.jksPackageUrl = jksPackageUrl;
    }

    public Integer getJksConfigNo() {
        return jksConfigNo;
    }

    public void setJksConfigNo(Integer jksConfigNo) {
        this.jksConfigNo = jksConfigNo;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }
}
