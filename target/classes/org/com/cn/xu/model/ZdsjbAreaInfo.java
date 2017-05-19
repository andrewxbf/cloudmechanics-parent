package org.com.cn.xu.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by xbf on 2017/5/11.
 */
public class ZdsjbAreaInfo implements Serializable {
    private static final long serialVersionUID = -1055518913580444280L;

    private String areaCode;
    private String areaName;
    private Integer areaNo;
    private String areaRemark;
    private String areaToken;
    private Integer invokeStatus;
    private Integer invokeResult;
    private Date invokeStartTime;
    private Date invokeEndTime;
    private Integer isSelected;

    public Integer getInvokeResult() {
        return invokeResult;
    }

    public void setInvokeResult(Integer invokeResult) {
        this.invokeResult = invokeResult;
    }

    public Integer getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(Integer isSelected) {
        this.isSelected = isSelected;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Integer getAreaNo() {
        return areaNo;
    }

    public void setAreaNo(Integer areaNo) {
        this.areaNo = areaNo;
    }

    public String getAreaRemark() {
        return areaRemark;
    }

    public void setAreaRemark(String areaRemark) {
        this.areaRemark = areaRemark;
    }

    public Integer getInvokeStatus() {
        return invokeStatus;
    }

    public void setInvokeStatus(Integer invokeStatus) {
        this.invokeStatus = invokeStatus;
    }

    public Date getInvokeStartTime() {
        return invokeStartTime;
    }

    public void setInvokeStartTime(Date invokeStartTime) {
        this.invokeStartTime = invokeStartTime;
    }

    public Date getInvokeEndTime() {
        return invokeEndTime;
    }

    public void setInvokeEndTime(Date invokeEndTime) {
        this.invokeEndTime = invokeEndTime;
    }

    public String getAreaToken() {
        return areaToken;
    }

    public void setAreaToken(String areaToken) {
        this.areaToken = areaToken;
    }
}
