package org.com.cn.xu.service.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.com.cn.xu.exception.BizException;
import org.com.cn.xu.service.ZdsjbDownloadConfigService;
import org.com.cn.xu.util.FileUtils;
import org.com.cn.xu.util.JSonHandlerUtils;
import org.com.cn.xu.util.JsonPage;
import org.com.cn.xu.vo.DownloadVO;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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
public class ZdsjbDownloadConfigServiceImpl implements ZdsjbDownloadConfigService {
    private final static Logger LOG = Logger.getLogger(ZdsjbDownloadConfigServiceImpl.class);

    public JsonPage<DownloadVO> getDownloadServerConfigList() {
        JsonPage<DownloadVO> jsonpage = new JsonPage<DownloadVO>();

        try {
            String filePath = new FileUtils().getFilePath("DownloadServerConfig.json");
            LOG.info("filePath====>>" + filePath);
            JsonArray jsonArray = JSonHandlerUtils.getJsonArray(filePath, "downloadServerConfig");
            List<DownloadVO> voList = new ArrayList<DownloadVO>();
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject obj = jsonArray.get(i).getAsJsonObject();
                DownloadVO vo = new DownloadVO();
                vo.setDownHostIp(obj.get("downHostIp").getAsString());
                vo.setDownPort(obj.get("downPort").getAsInt());
                vo.setDownUserName(obj.get("downUserName").getAsString());
                vo.setDownPassword(obj.get("downPassword").getAsString());
                vo.setDownFileName(obj.get("downFileName").getAsString());
                vo.setDownFilePath(obj.get("downFilePath").getAsString());
                vo.setDownFileSavePath(obj.get("downFileSavePath").getAsString());

                voList.add(vo);
            }

            jsonpage.setRows(voList);
        }catch (Exception e){
            LOG.error(e.getMessage());
            throw new BizException("获取配置信息失败！",null);
        }
        return jsonpage;
    }

    /**
     * 将Jenkins配置信息存储到指定文件中
     * @param downList
     */
    public void saveDownloadServerConfig(List<DownloadVO> downList) {
        OutputStreamWriter fos = null;

        try {
            JSONObject jsonStr = new JSONObject();
            jsonStr.put("downloadServerConfig", downList);
            LOG.info(jsonStr.toString());

            String filePath = new FileUtils().getFilePath("DownloadServerConfig.json");
            LOG.info("filePath====>>" + filePath);

            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            String str = jsonStr.toString();

            fos = new OutputStreamWriter(new FileOutputStream(file),"UTF-8");
            fos.write(str, 0, str.length());
            fos.flush();
        } catch (IOException ioe) {
            LOG.error(ioe.getMessage());
            throw new BizException("配置信息存储失败！", null);
        } finally {
            new FileUtils().closeResource(fos);
        }
    }
}
