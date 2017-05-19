package org.com.cn.xu.service.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.com.cn.xu.exception.BizException;
import org.com.cn.xu.service.ZdsjbZipConfigService;
import org.com.cn.xu.util.FileUtils;
import org.com.cn.xu.util.JSonHandlerUtils;
import org.com.cn.xu.util.JsonPage;
import org.com.cn.xu.vo.ZipVO;
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
public class ZdsjbZipServiceConfigImpl implements ZdsjbZipConfigService {
    private final static Logger LOG = Logger.getLogger(ZdsjbZipServiceConfigImpl.class);

    public JsonPage<ZipVO> getZipServerConfigList() {
        JsonPage<ZipVO> jsonpage = new JsonPage<ZipVO>();

        try {
            String filePath = new FileUtils().getFilePath("ZipServerConfig.json");
            LOG.info("filePath====>>" + filePath);
            JsonArray jsonArray = JSonHandlerUtils.getJsonArray(filePath, "zipServerConfig");
            List<ZipVO> voList = new ArrayList<ZipVO>();
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject obj = jsonArray.get(i).getAsJsonObject();
                ZipVO vo = new ZipVO();
                vo.setZipFileFrom(obj.get("zipFileFrom").getAsString());
                vo.setZipFileTo(obj.get("zipFileTo").getAsString());

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
     * @param zipList
     */
    public void saveZipServerConfig(List<ZipVO> zipList) {
        OutputStreamWriter fos = null;

        try {
            JSONObject jsonStr = new JSONObject();
            jsonStr.put("zipServerConfig", zipList);
            jsonStr.put("sqlConfig", zipList.get(0).getZipFileFrom()+"/sql_update/");
            LOG.info(jsonStr.toString());

            String filePath = new FileUtils().getFilePath("ZipServerConfig.json");
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
