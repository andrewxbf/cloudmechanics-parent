package org.com.cn.xu.vo;

import java.io.Serializable;

public class SqlVO implements Serializable {

    private static final long serialVersionUID = 4725298156072836312L;

    private String downHostIp;
    private int downPort;
    private String downUserName;
    private String downPassword;
    private String downFilePath;
    private String downFileSavePath;

    public String getDownHostIp() {
        return downHostIp;
    }

    public void setDownHostIp(String downHostIp) {
        this.downHostIp = downHostIp;
    }

    public int getDownPort() {
        return downPort;
    }

    public void setDownPort(int downPort) {
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
}
