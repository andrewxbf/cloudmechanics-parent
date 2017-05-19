package org.com.cn.xu.model;

import java.io.Serializable;

/**
 * Created by xbf on 2017/5/15.
 */
public class ZdsjbUploadConfig implements Serializable {
    private static final long serialVersionUID = 7444918422268118919L;

    private String upHostIp;
    private Integer upPort;
    private String upUserName;
    private String upPassword;
    private String upFileName;
    private String upFilePath;
    private String upSourceFilePath;
    private Integer upConfigNo;
    private String areaCode;

    public String getUpHostIp() {
        return upHostIp;
    }

    public void setUpHostIp(String upHostIp) {
        this.upHostIp = upHostIp;
    }

    public Integer getUpPort() {
        return upPort;
    }

    public void setUpPort(Integer upPort) {
        this.upPort = upPort;
    }

    public String getUpUserName() {
        return upUserName;
    }

    public void setUpUserName(String upUserName) {
        this.upUserName = upUserName;
    }

    public String getUpPassword() {
        return upPassword;
    }

    public void setUpPassword(String upPassword) {
        this.upPassword = upPassword;
    }

    public String getUpFileName() {
        return upFileName;
    }

    public void setUpFileName(String upFileName) {
        this.upFileName = upFileName;
    }

    public String getUpFilePath() {
        return upFilePath;
    }

    public void setUpFilePath(String upFilePath) {
        this.upFilePath = upFilePath;
    }

    public String getUpSourceFilePath() {
        return upSourceFilePath;
    }

    public void setUpSourceFilePath(String upSourceFilePath) {
        this.upSourceFilePath = upSourceFilePath;
    }

    public Integer getUpConfigNo() {
        return upConfigNo;
    }

    public void setUpConfigNo(Integer upConfigNo) {
        this.upConfigNo = upConfigNo;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }
}
