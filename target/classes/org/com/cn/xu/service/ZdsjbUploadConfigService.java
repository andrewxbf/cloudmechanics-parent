package org.com.cn.xu.service;

import org.com.cn.xu.util.JsonPage;
import org.com.cn.xu.vo.DownloadVO;
import org.com.cn.xu.vo.UploadVO;

import java.util.List;

/**
 * Created by xbf on 2017/5/8.
 */
public interface ZdsjbUploadConfigService {
    public JsonPage<UploadVO> getUploadServerConfigList();

    public void saveUploadServerConfig(List<UploadVO> upList);
}
