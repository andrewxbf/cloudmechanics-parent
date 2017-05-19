package org.com.cn.xu.service;

import org.com.cn.xu.util.JsonPage;
import org.com.cn.xu.vo.UploadVO;
import org.com.cn.xu.vo.ZipVO;

import java.util.List;

/**
 * Created by xbf on 2017/5/8.
 */
public interface ZdsjbZipConfigService {
    public JsonPage<ZipVO> getZipServerConfigList();

    public void saveZipServerConfig(List<ZipVO> zipList);
}
