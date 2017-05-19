package org.com.cn.xu.service;

import org.com.cn.xu.util.JsonPage;
import org.com.cn.xu.vo.SqlVO;
import org.com.cn.xu.vo.ZipVO;

import java.util.List;

/**
 * Created by xbf on 2017/5/8.
 */
public interface ZdsjbSqlConfigService {
    public JsonPage<SqlVO> getPtSqlServerConfigList();

    public void savePtSqlServerConfig(List<SqlVO> zipList);

    public JsonPage<SqlVO> getLocalSqlServerConfigList();

    public void saveLocalSqlServerConfig(List<SqlVO> zipList);
}
