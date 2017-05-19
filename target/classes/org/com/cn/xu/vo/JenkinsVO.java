package org.com.cn.xu.vo;

import java.io.Serializable;

public class JenkinsVO implements Serializable {
    private static final long serialVersionUID = -5252806577149671055L;

    private String jksHostIp;
    private int jksPort;
    private String jksUserName;
    private String jksPassword;
    private String jksUrl;

    public String getJksHostIp() {
        return jksHostIp;
    }

    public void setJksHostIp(String jksHostIp) {
        this.jksHostIp = jksHostIp;
    }

    public int getJksPort() {
        return jksPort;
    }

    public void setJksPort(int jksPort) {
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

    public String getJksUrl() {
        return jksUrl;
    }

    public void setJksUrl(String jksUrl) {
        this.jksUrl = jksUrl;
    }
}
