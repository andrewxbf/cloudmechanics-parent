package org.com.cn.xu.controller;

import org.apache.log4j.Logger;
import org.com.cn.xu.exception.BizException;
import org.com.cn.xu.model.ZdsjbAreaInfo;
import org.com.cn.xu.model.ZdsjbJenkinsConfig;
import org.com.cn.xu.service.ZdsjbJenkinsConfigService;
import org.com.cn.xu.util.JSONUtils;
import org.com.cn.xu.util.JsonPage;
import org.com.cn.xu.util.Response;
import org.com.cn.xu.util.ResponseObject;
import org.com.cn.xu.util.ibatis.plugin.util.Utils;
import org.com.cn.xu.vo.JenkinsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by xbf on 2017/5/5.
 */
@Controller
@RequestMapping("zdsjbJenkinsConfig/")
public class ZdsjbJenkinsConfigController extends BaseController {
    private static final Logger LOG = Logger.getLogger(ZdsjbJenkinsConfigController.class);

    @Autowired
    ZdsjbJenkinsConfigService service;

    /**
     * 获取Jenkins服务配置列表
     *
     * @Return
     */
    @ResponseBody
    @RequestMapping(value = "getJenkinsServerConfigList.do")
    public Response getJenkinsServerConfigList(ZdsjbAreaInfo zdsjbAreaInfo) {
        JsonPage<ZdsjbJenkinsConfig> jsonPage = service.getJenkinsServerConfigList(zdsjbAreaInfo.getAreaCode());

        ResponseObject<List<ZdsjbJenkinsConfig>> res = new ResponseObject<List<ZdsjbJenkinsConfig>>();

        res.setRows(jsonPage.getRows());

        return res;
    }

    @ResponseBody
    @RequestMapping(value = "getJenkinsServerConfigListSec.do")
    public Response getJenkinsServerConfigListSec() {
        JsonPage<JenkinsVO> jsonPage = service.getJenkinsServerConfigListSec();

        ResponseObject<List<JenkinsVO>> res = new ResponseObject<List<JenkinsVO>>();

        res.setRows(jsonPage.getRows());

        return res;
    }

    /**
     * 保存Jenkins服务配置列表
     *
     * @Return
     */
    @ResponseBody
    @RequestMapping(value = "saveJenkinsServerConfig.do")
    public Response saveJenkinsServerConfig(String zdsjbJenkinsConfig, String areaCode) {
        List<ZdsjbJenkinsConfig> jksList = null;
        try {
            jksList = (List<ZdsjbJenkinsConfig>)Utils.toBean(zdsjbJenkinsConfig, ZdsjbJenkinsConfig.class);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            throw new BizException("对象转换错误", null);
        }

        service.saveJenkinsServerConfig(jksList, areaCode);

        return new Response();
    }

    @ResponseBody
    @RequestMapping(value = "saveJenkinsServerConfigSec.do")
    public Response saveJenkinsServerConfigSec(String jenkinsVO) {
        List<JenkinsVO> jksList = null;
        try {
            jksList = JSONUtils.toBeanList(jenkinsVO, JenkinsVO.class);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            throw new BizException("对象转换错误", null);
        }

        service.saveJenkinsServerConfigSec(jksList);

        return new Response();
    }

    @ResponseBody
    @RequestMapping(value = "deleteZdsjbJenkinsConfig.do")
    public Response deleteZdsjbJenkinsConfig(String zdsjbJenkinsConfig) {
        List<ZdsjbJenkinsConfig> jksConfigList = null;
        try {
            jksConfigList = JSONUtils.toBeanList(zdsjbJenkinsConfig, ZdsjbJenkinsConfig.class);
        }catch (Exception e) {
            LOG.error(e.getMessage());
            throw new BizException("对象转换错误", null);
        }

        service.deleteZdsjbJenkinsConfig(jksConfigList);

        return new Response();
    }

}
