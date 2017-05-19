package org.com.cn.xu.controller;

import org.apache.log4j.Logger;
import org.com.cn.xu.exception.BizException;
import org.com.cn.xu.model.ZdsjbAreaInfo;
import org.com.cn.xu.service.ZdsjbAreaInfoService;
import org.com.cn.xu.util.JSONUtils;
import org.com.cn.xu.util.Response;
import org.com.cn.xu.util.ResponseObject;
import org.com.cn.xu.util.ibatis.plugin.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by xbf on 2017/5/5.
 */
@Controller
@RequestMapping("zdsjbAreaInfo/")
public class ZdsjbAreaInfoController extends BaseController {
    private static final Logger LOG = Logger.getLogger(ZdsjbAreaInfoController.class);
    @Autowired
    ZdsjbAreaInfoService service;

    /**
     * 升级地区选择
     *
     * @return
     */
    @RequestMapping(value = "choiceLoginArea.do")
    public String choiceLoginArea() {

        return "choiceLoginArea";
    }

    @ResponseBody
    @RequestMapping(value = "getZdsjbAreaInfoList.do")
    public Response getZdsjbAreaInfoList() {
        List<ZdsjbAreaInfo> areaInfoList = service.getZdsjbAreaInfoList();

        ResponseObject<List<ZdsjbAreaInfo>> res = new ResponseObject<List<ZdsjbAreaInfo>>();
        res.setRows(areaInfoList);

        return res;
    }

    @ResponseBody
    @RequestMapping(value = "saveZdsjbAreaInfo.do")
    public Response saveZdsjbAreaInfo(String zdsjbAreaInfo) {
        List<ZdsjbAreaInfo> paramList;
        try {
            paramList = JSONUtils.toBeanList(zdsjbAreaInfo, ZdsjbAreaInfo.class);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            throw new BizException("保存失败！", null);
        }
        service.saveZdsjbAreaInfo(paramList);

        return new Response();
    }

    @ResponseBody
    @RequestMapping(value = "deleteZdsjbAreaInfo.do")
    public Response deleteZdsjbAreaInfo(String zdsjbAreaInfo) {
        ZdsjbAreaInfo param;
        try {
            param = JSONUtils.toBean(zdsjbAreaInfo, ZdsjbAreaInfo.class);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            throw new BizException("删除异常！", null);
        }
        service.deleteZdsjbAreaInfo(param);

        return new Response();
    }

    @RequestMapping(value = "showDetailConfig.do")
    public String showDetailConfig(ModelMap modelMap, String zdsjbAreaInfo) {
        ZdsjbAreaInfo areainfo;
        try {
            areainfo = JSONUtils.toBean(zdsjbAreaInfo, ZdsjbAreaInfo.class);
        }catch (Exception e){
            LOG.error(e.getMessage());
            throw new BizException("对象转换错误！", null);
        }
            modelMap.put("zdsjbAreaInfo", areainfo);

        return "mainConfig";
    }

    /**
     * 升级包主页面配置
     *
     * @return
     */
    @RequestMapping(value = "showMainConfig.do")
    public String showMainConfig() {
        return "mainConfig";
    }

    /**
     * Jenkins服务配置项
     *
     * @return
     */
    @RequestMapping(value = "showJenkinsServerConfig.do")
    public String showJenkinsServerConfig() {
        return "jenkins";
    }

    /**
     * 下载服务器服务配置项
     *
     * @return
     */
    @RequestMapping(value = "showDownloadServer.do")
    public String showDownloadServer() {
        return "downloadServer";
    }

    /**
     * 上传服务器服务配置项
     *
     * @return
     */
    @RequestMapping(value = "showUploadServer.do")
    public String showUploadServer() {
        return "uploadServer";
    }

    /**
     * 开始执行Controller
     */
    @ResponseBody
    @RequestMapping(value = "startInvokeMethod")
    public Response startInvokeMethod() {

        service.startInvokeMethod();

        return new Response();
    }

}
