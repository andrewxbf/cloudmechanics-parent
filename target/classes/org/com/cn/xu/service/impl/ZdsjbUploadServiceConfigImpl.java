package org.com.cn.xu.service.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.com.cn.xu.exception.BizException;
import org.com.cn.xu.service.ZdsjbUploadConfigService;
import org.com.cn.xu.util.FileUtils;
import org.com.cn.xu.util.JSonHandlerUtils;
import org.com.cn.xu.util.JsonPage;
import org.com.cn.xu.vo.UploadVO;
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
public class ZdsjbUploadServiceConfigImpl implements ZdsjbUploadConfigService {
    private final static Logger LOG = Logger.getLogger(ZdsjbUploadServiceConfigImpl.class);

    public JsonPage<UploadVO> getUploadServerConfigList() {
        JsonPage<UploadVO> jsonpage = new JsonPage<UploadVO>();

        try {
            String filePath = new FileUtils().getFilePath("UploadServerConfig.json");
            LOG.info("filePath====>>" + filePath);
            JsonArray jsonArray = JSonHandlerUtils.getJsonArray(filePath, "uploadServerConfig");
            List<UploadVO> voList = new ArrayList<UploadVO>();
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject obj = jsonArray.get(i).getAsJsonObject();
                UploadVO vo = new UploadVO();
                vo.setUpHostIp(obj.get("upHostIp").getAsString());
                vo.setUpPort(obj.get("upPort").getAsInt());
                vo.setUpUserName(obj.get("upUserName").getAsString());
                vo.setUpPassword(obj.get("upPassword").getAsString());
                vo.setUpFileName(obj.get("upFileName").getAsString());
                vo.setUpFilePath(obj.get("upFilePath").getAsString());
                vo.setUpSourceFilePath(obj.get("upSourceFilePath").getAsString());

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
     * @param upList
     */
    public void saveUploadServerConfig(List<UploadVO> upList) {
        OutputStreamWriter fos = null;

        try {
            JSONObject jsonStr = new JSONObject();
            jsonStr.put("uploadServerConfig", upList);
            LOG.info(jsonStr.toString());

            String filePath = new FileUtils().getFilePath("UploadServerConfig.json");
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
