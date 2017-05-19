package org.com.cn.xu.service.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.com.cn.xu.exception.BizException;
import org.com.cn.xu.model.ZdsjbJenkinsConfig;
import org.com.cn.xu.service.ZdsjbJenkinsConfigService;
import org.com.cn.xu.util.*;
import org.com.cn.xu.util.dao.GeneralDAO;
import org.com.cn.xu.vo.JenkinsVO;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xbf on 2017/5/8.
 */
@Service
public class ZdsjbJenkinsConfigServiceImpl implements ZdsjbJenkinsConfigService {
    private final static Logger LOG = Logger.getLogger(ZdsjbJenkinsConfigServiceImpl.class);

    @Autowired
    GeneralDAO dao;


    public JsonPage<ZdsjbJenkinsConfig> getJenkinsServerConfigList(String areaCode) {
        JsonPage<ZdsjbJenkinsConfig> jsonpage = new JsonPage<ZdsjbJenkinsConfig>();

        List<ZdsjbJenkinsConfig> voList = dao.queryForList("ZdsjbJenkinsConfigMapper.getZdsjbJenkinsConfigList", areaCode);

        jsonpage.setRows(voList);

        return jsonpage;
    }

    public JsonPage<JenkinsVO> getJenkinsServerConfigListSec() {
        JsonPage<JenkinsVO> jsonpage = new JsonPage<JenkinsVO>();

        try {
            String filePath = new FileUtils().getFilePath("JenkinsServerConfig.json");
            LOG.info("filePath====>>" + filePath);
            JsonArray jsonArray = JSonHandlerUtils.getJsonArray(filePath, "jenkinsServerConfig");
            List<JenkinsVO> voList = new ArrayList<JenkinsVO>();
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject obj = jsonArray.get(i).getAsJsonObject();
                JenkinsVO vo = new JenkinsVO();
                vo.setJksHostIp(obj.get("jksHostIp").getAsString());
                vo.setJksPort(obj.get("jksPort").getAsInt());
                vo.setJksUserName(obj.get("jksUserName").getAsString());
                vo.setJksPassword(obj.get("jksPassword").getAsString());
                if (null != obj.get("jksUrl")) {
                    vo.setJksUrl(obj.get("jksUrl").getAsString());
                }

                voList.add(vo);
            }

            jsonpage.setRows(voList);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            throw new BizException("获取配置信息失败！", null);
        }
        return jsonpage;
    }

    /**
     * 将Jenkins配置信息存储到指定文件中
     *
     * @param jksList
     */

    @Transactional
    public void saveJenkinsServerConfig(List<ZdsjbJenkinsConfig> jksList, String areaCode) {
        dao.deleteObject("ZdsjbJenkinsConfigMapper.deleteZdsjbJenkinsConfigByKey", areaCode);

        for (ZdsjbJenkinsConfig jks : jksList) {
            if(StringUtils.isEmpty(jks.getAreaCode())){
                jks.setAreaCode(areaCode);
            }
            dao.insertObject("ZdsjbJenkinsConfigMapper.insertZdsjbJenkinsConfig", jks);
        }
    }

    public void saveJenkinsServerConfigSec(List<JenkinsVO> jksList) {
        OutputStreamWriter fos = null;

        try {
            JSONObject jsonStr = new JSONObject();
            jsonStr.put("jenkinsServerConfig", jksList);
            LOG.info(jsonStr.toString());

            String filePath = new FileUtils().getFilePath("JenkinsServerConfig.json");
            LOG.info("filePath====>>" + filePath);

            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            String str = jsonStr.toString();

            fos = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
            fos.write(str, 0, str.length());
            fos.flush();
        } catch (IOException ioe) {
            LOG.error(ioe.getMessage());
            throw new BizException("配置信息存储失败！", null);
        } finally {
            new FileUtils().closeResource(fos);
        }
    }

    @Transactional
    public void deleteZdsjbJenkinsConfig(List<ZdsjbJenkinsConfig> jksConfigList) {
        for (ZdsjbJenkinsConfig jksConfig : jksConfigList) {
            dao.deleteObject("ZdsjbJenkinsConfigMapper.deleteZdsjbJenkinsConfig", jksConfig);
        }
    }
}
