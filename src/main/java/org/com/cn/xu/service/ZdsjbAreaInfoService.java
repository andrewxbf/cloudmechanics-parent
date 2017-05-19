package org.com.cn.xu.service;

import org.com.cn.xu.model.ZdsjbAreaInfo;

import java.util.List;

/**
 * Created by xbf on 2017/5/10.
 */
public interface ZdsjbAreaInfoService {
    List<ZdsjbAreaInfo> getZdsjbAreaInfoList();
    void saveZdsjbAreaInfo(List<ZdsjbAreaInfo> zdsjbAreaInfoList);
    void deleteZdsjbAreaInfo(ZdsjbAreaInfo zdsjbAreaInfo);
    void startInvokeMethod();
}
