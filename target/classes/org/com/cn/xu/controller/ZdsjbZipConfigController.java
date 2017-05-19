package org.com.cn.xu.controller;

import org.com.cn.xu.exception.BizException;
import org.com.cn.xu.service.ZdsjbZipConfigService;
import org.com.cn.xu.util.JSONUtils;
import org.com.cn.xu.util.JsonPage;
import org.com.cn.xu.util.Response;
import org.com.cn.xu.util.ResponseObject;
import org.com.cn.xu.vo.ZipVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by xbf on 2017/5/5.
 */
@Controller
@RequestMapping("zdsjbZipConfig/")
public class ZdsjbZipConfigController extends BaseController {
    @Autowired
    ZdsjbZipConfigService service;

    /**
     * 获取Download服务配置列表
     *
     * @Return
     */
    @ResponseBody
    @RequestMapping(value = "getZipServerConfigList.do")
    public Response getZipServerConfigList() {
        JsonPage<ZipVO> jsonPage = service.getZipServerConfigList();

        ResponseObject<List<ZipVO>> res = new ResponseObject<List<ZipVO>>();

        res.setRows(jsonPage.getRows());

        return res;
    }

    /**
     * 保存Download服务配置列表
     *
     * @Return
     */
    @ResponseBody
    @RequestMapping(value = "saveZipServerConfig.do")
    public Response saveZipServerConfig(String zipVO) {
        List<ZipVO> list = null;
        try {
            list = JSONUtils.toBeanList(zipVO, ZipVO.class);
        } catch (Exception e) {
            throw new BizException("对象转换错误", null);
        }

        service.saveZipServerConfig(list);

        return new Response();
    }
}
