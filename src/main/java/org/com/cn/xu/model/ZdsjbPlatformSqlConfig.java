package org.com.cn.xu.model;

import java.io.Serializable;

/**
 * Created by xbf on 2017/5/15.
 */
public class ZdsjbPlatformSqlConfig implements Serializable {
    private static final long serialVersionUID = -3570791138828911807L;

    private String ptSqlHostIp;
    private Integer ptSqlPort;
    private String ptSqlUserName;
    private String ptSqlPassword;
    private String ptSqlFilePath;
    private String ptSqlFileSavePath;
    private Integer ptSqlConfigNo;
    private String areaCode;

    public String getPtSqlHostIp() {
        return ptSqlHostIp;
    }

    public void setPtSqlHostIp(String ptSqlHostIp) {
        this.ptSqlHostIp = ptSqlHostIp;
    }

    public Integer getPtSqlPort() {
        return ptSqlPort;
    }

    public void setPtSqlPort(Integer ptSqlPort) {
        this.ptSqlPort = ptSqlPort;
    }

    public String getPtSqlUserName() {
        return ptSqlUserName;
    }

    public void setPtSqlUserName(String ptSqlUserName) {
        this.ptSqlUserName = ptSqlUserName;
    }

    public String getPtSqlPassword() {
        return ptSqlPassword;
    }

    public void setPtSqlPassword(String ptSqlPassword) {
        this.ptSqlPassword = ptSqlPassword;
    }

    public String getPtSqlFilePath() {
        return ptSqlFilePath;
    }

    public void setPtSqlFilePath(String ptSqlFilePath) {
        this.ptSqlFilePath = ptSqlFilePath;
    }

    public String getPtSqlFileSavePath() {
        return ptSqlFileSavePath;
    }

    public void setPtSqlFileSavePath(String ptSqlFileSavePath) {
        this.ptSqlFileSavePath = ptSqlFileSavePath;
    }

    public Integer getPtSqlConfigNo() {
        return ptSqlConfigNo;
    }

    public void setPtSqlConfigNo(Integer ptSqlConfigNo) {
        this.ptSqlConfigNo = ptSqlConfigNo;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }
}
