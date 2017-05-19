package org.com.cn.xu.service.impl;

import org.apache.log4j.Logger;
import org.com.cn.xu.AutoUpgradePackageService;
import org.com.cn.xu.exception.BizException;
import org.com.cn.xu.model.ZdsjbAreaInfo;
import org.com.cn.xu.service.ZdsjbAreaInfoService;
import org.com.cn.xu.util.FileUtils;
import org.com.cn.xu.util.JSonHandlerUtils;
import org.com.cn.xu.util.dao.GeneralDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.List;

/**
 * Created by xbf on 2017/5/10.
 */
@Service
public class ZdsjbAreaInfoServiceImpl implements ZdsjbAreaInfoService {
    private final static Logger LOG = Logger.getLogger(ZdsjbAreaInfoServiceImpl.class);

    @Autowired
    GeneralDAO dao;


    public List<ZdsjbAreaInfo> getZdsjbAreaInfoList(){
            return dao.queryForList("ZdsjbAreaInfoMapper.getZdsjbAreaInfoList", null);
    }

    @Transactional
    public void saveZdsjbAreaInfo(List<ZdsjbAreaInfo> zdsjbAreaInfoList){
        dao.deleteObject("ZdsjbAreaInfoMapper.deleteZdsjbAreaInfo", null);

        for (ZdsjbAreaInfo zdsjbAreaInfo : zdsjbAreaInfoList) {
            if(StringUtils.isEmpty(zdsjbAreaInfo.getAreaCode()) || StringUtils.isEmpty(zdsjbAreaInfo.getAreaName())){
                throw new BizException("地区代码和地区名称都不能为空！", null);
            }

            if(((List)dao.queryForList("ZdsjbAreaInfoMapper.getZdsjbAreaInfoList", zdsjbAreaInfo)).size() > 0){
                throw new BizException("存在重复的地区代码【"+zdsjbAreaInfo.getAreaCode()+"】", null);
            }

            dao.insertObject("ZdsjbAreaInfoMapper.insertZdsjbAreaInfo", zdsjbAreaInfo);
        }
    }

    public void deleteZdsjbAreaInfo(ZdsjbAreaInfo zdsjbAreaInfo){
        dao.deleteObject("ZdsjbAreaInfoMapper.deleteZdsjbAreaInfo", zdsjbAreaInfo);
    }

    public void startInvokeMethod() {
        mergeJsonFile();
        try {
            AutoUpgradePackageService.invokeMain();
        }catch (Exception e){
            LOG.error(e.getMessage());
            throw new BizException("制作升级包失败，清查看日志信息！", null);
        }
    }

    public void mergeJsonFile() {
        OutputStreamWriter osw = null;

        try {
            StringBuffer jsonStr = new StringBuffer("");
            jsonStr.append(JSonHandlerUtils.getJsonFileAsString(new FileUtils().getFilePath("JenkinsServerConfig.json")));
            jsonStr.append(JSonHandlerUtils.getJsonFileAsString(new FileUtils().getFilePath("DownloadServerConfig.json")));
            jsonStr.append(JSonHandlerUtils.getJsonFileAsString(new FileUtils().getFilePath("UploadServerConfig.json")));
            jsonStr.append(JSonHandlerUtils.getJsonFileAsString(new FileUtils().getFilePath("ZipServerConfig.json")));
            jsonStr.append(JSonHandlerUtils.getJsonFileAsString(new FileUtils().getFilePath("LocalSqlServerConfig.json")));
            jsonStr.append(JSonHandlerUtils.getJsonFileAsString(new FileUtils().getFilePath("PtSqlServerConfig.json")));

            String strTemp = jsonStr.toString().replaceAll("[}][{]", ",");

            //替换拼接JSON文件中的“}{”为“，”
            String filePath = new FileUtils().getFilePath("JsonConfig.json");
            LOG.info("filePath====>>" + filePath);
            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            osw = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
            osw.write(strTemp, 0, strTemp.length());
            osw.flush();

        } catch (IOException ioe) {
            LOG.error(ioe.getMessage());
            throw new BizException("配置信息存储失败！", null);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            throw new BizException("执行出现异常！", null);
        } finally {
            try {
                if (null != osw) {
                    osw.close();
                }
            } catch (IOException ioe) {
                LOG.error(ioe.getMessage());
                throw new BizException("文件流操作失败！", null);
            }
        }
    }
}
