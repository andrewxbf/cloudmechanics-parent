package org.com.cn.xu.service;

import org.com.cn.xu.util.JsonPage;
import org.com.cn.xu.vo.DownloadVO;
import org.com.cn.xu.vo.JenkinsVO;

import java.util.List;

/**
 * Created by xbf on 2017/5/8.
 */
public interface ZdsjbDownloadConfigService {
    public JsonPage<DownloadVO> getDownloadServerConfigList();

    public void saveDownloadServerConfig(List<DownloadVO> downList);
}
