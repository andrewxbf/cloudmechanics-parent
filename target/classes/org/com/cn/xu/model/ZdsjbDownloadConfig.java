package org.com.cn.xu.model;

import java.io.Serializable;

/**
 * Created by xbf on 2017/5/15.
 */
public class ZdsjbDownloadConfig implements Serializable {
    private static final long serialVersionUID = -3861820795051256596L;

    private String downHostIp;
    private Integer downPort;
    private String downUserName;
    private String downPassword;
    private String downFileName;
    private String downFilePath;
    private String downFileSavePath;
    private Integer downConfigNo;
    private String areaCode;
    private Integer isSelected;

    public Integer getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(Integer isSelected) {
        this.isSelected = isSelected;
    }

    public String getDownHostIp() {
        return downHostIp;
    }

    public void setDownHostIp(String downHostIp) {
        this.downHostIp = downHostIp;
    }

    public Integer getDownPort() {
        return downPort;
    }

    public void setDownPort(Integer downPort) {
        this.downPort = downPort;
    }

    public String getDownUserName() {
        return downUserName;
    }

    public void setDownUserName(String downUserName) {
        this.downUserName = downUserName;
    }

    public String getDownPassword() {
        return downPassword;
    }

    public void setDownPassword(String downPassword) {
        this.downPassword = downPassword;
    }

    public String getDownFileName() {
        return downFileName;
    }

    public void setDownFileName(String downFileName) {
        this.downFileName = downFileName;
    }

    public String getDownFilePath() {
        return downFilePath;
    }

    public void setDownFilePath(String downFilePath) {
        this.downFilePath = downFilePath;
    }

    public String getDownFileSavePath() {
        return downFileSavePath;
    }

    public void setDownFileSavePath(String downFileSavePath) {
        this.downFileSavePath = downFileSavePath;
    }

    public Integer getDownConfigNo() {
        return downConfigNo;
    }

    public void setDownConfigNo(Integer downConfigNo) {
        this.downConfigNo = downConfigNo;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }
}
