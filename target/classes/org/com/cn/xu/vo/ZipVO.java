package org.com.cn.xu.vo;

import java.io.Serializable;

public class ZipVO implements Serializable {

    private static final long serialVersionUID = -546691361891092646L;

    private String zipFileFrom;
    private String zipFileTo;

    public String getZipFileFrom() {
        return zipFileFrom;
    }

    public void setZipFileFrom(String zipFileFrom) {
        this.zipFileFrom = zipFileFrom;
    }

    public String getZipFileTo() {
        return zipFileTo;
    }

    public void setZipFileTo(String zipFileTo) {
        this.zipFileTo = zipFileTo;
    }
}
