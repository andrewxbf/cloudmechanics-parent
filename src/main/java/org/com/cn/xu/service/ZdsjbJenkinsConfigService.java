package org.com.cn.xu.service;

import org.com.cn.xu.model.ZdsjbJenkinsConfig;
import org.com.cn.xu.util.JsonPage;
import org.com.cn.xu.vo.JenkinsVO;

import java.util.List;

/**
 * Created by xbf on 2017/5/8.
 */
public interface ZdsjbJenkinsConfigService {
    JsonPage<ZdsjbJenkinsConfig> getJenkinsServerConfigList(String areaCode);
    void saveJenkinsServerConfig(List<ZdsjbJenkinsConfig> jksList, String areaCode);
    void deleteZdsjbJenkinsConfig(List<ZdsjbJenkinsConfig> jksConfigList);

    JsonPage<JenkinsVO> getJenkinsServerConfigListSec();
    void saveJenkinsServerConfigSec(List<JenkinsVO> jksList);
}
