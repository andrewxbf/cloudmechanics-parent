package org.com.cn.xu.vo;

import java.io.Serializable;

public class UploadVO implements Serializable{

    private static final long serialVersionUID = -8170967069880231998L;
    
    private String upHostIp;
    private int upPort;
    private String upUserName;
    private String upPassword;
    private String upFileName;
    private String upFilePath;
    private String upSourceFilePath;


    public String getUpHostIp() {
        return upHostIp;
    }

    public void setUpHostIp(String upHostIp) {
        this.upHostIp = upHostIp;
    }

    public int getUpPort() {
        return upPort;
    }

    public void setUpPort(int upPort) {
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
}
