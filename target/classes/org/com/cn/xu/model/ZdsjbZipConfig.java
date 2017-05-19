package org.com.cn.xu.model;

import java.io.Serializable;

/**
 * Created by xbf on 2017/5/15.
 */
public class ZdsjbZipConfig implements Serializable {
    private static final long serialVersionUID = -7361249032881782533L;

    private String zipFileName;
    private String zipFileTo;
    private String zipFileFrom;
    private Integer zipConfigNo;
    private String areaCode;

    public String getZipFileName() {
        return zipFileName;
    }

    public void setZipFileName(String zipFileName) {
        this.zipFileName = zipFileName;
    }

    public String getZipFileTo() {
        return zipFileTo;
    }

    public void setZipFileTo(String zipFileTo) {
        this.zipFileTo = zipFileTo;
    }

    public String getZipFileFrom() {
        return zipFileFrom;
    }

    public void setZipFileFrom(String zipFileFrom) {
        this.zipFileFrom = zipFileFrom;
    }

    public Integer getZipConfigNo() {
        return zipConfigNo;
    }

    public void setZipConfigNo(Integer zipConfigNo) {
        this.zipConfigNo = zipConfigNo;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }
}
