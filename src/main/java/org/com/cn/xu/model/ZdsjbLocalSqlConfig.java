package org.com.cn.xu.model;

import java.io.Serializable;

/**
 * Created by xbf on 2017/5/15.
 */
public class ZdsjbLocalSqlConfig implements Serializable {
    private static final long serialVersionUID = 2862973605411664689L;

    private String localSqlHostIp;
    private Integer localSqlPort;
    private String localSqlUserName;
    private String localSqlPassword;
    private String localSqlFilePath;
    private String localSqlFileSavePath;
    private Integer localSqlConfigNo;
    private String areaCode;

    public String getLocalSqlHostIp() {
        return localSqlHostIp;
    }

    public void setLocalSqlHostIp(String localSqlHostIp) {
        this.localSqlHostIp = localSqlHostIp;
    }

    public Integer getLocalSqlPort() {
        return localSqlPort;
    }

    public void setLocalSqlPort(Integer localSqlPort) {
        this.localSqlPort = localSqlPort;
    }

    public String getLocalSqlUserName() {
        return localSqlUserName;
    }

    public void setLocalSqlUserName(String localSqlUserName) {
        this.localSqlUserName = localSqlUserName;
    }

    public String getLocalSqlPassword() {
        return localSqlPassword;
    }

    public void setLocalSqlPassword(String localSqlPassword) {
        this.localSqlPassword = localSqlPassword;
    }

    public String getLocalSqlFilePath() {
        return localSqlFilePath;
    }

    public void setLocalSqlFilePath(String localSqlFilePath) {
        this.localSqlFilePath = localSqlFilePath;
    }

    public String getLocalSqlFileSavePath() {
        return localSqlFileSavePath;
    }

    public void setLocalSqlFileSavePath(String localSqlFileSavePath) {
        this.localSqlFileSavePath = localSqlFileSavePath;
    }

    public Integer getLocalSqlConfigNo() {
        return localSqlConfigNo;
    }

    public void setLocalSqlConfigNo(Integer localSqlConfigNo) {
        this.localSqlConfigNo = localSqlConfigNo;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }
}
