package org.com.cn.xu.controller;

import org.com.cn.xu.exception.BizException;
import org.com.cn.xu.service.ZdsjbUploadConfigService;
import org.com.cn.xu.util.JSONUtils;
import org.com.cn.xu.util.JsonPage;
import org.com.cn.xu.util.Response;
import org.com.cn.xu.util.ResponseObject;
import org.com.cn.xu.vo.UploadVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by xbf on 2017/5/5.
 */
@Controller
@RequestMapping("zdsjbUploadConfig/")
public class ZdsjbUploadConfigController extends BaseController {
    @Autowired
    ZdsjbUploadConfigService service;

    /**
     * 获取Download服务配置列表
     *
     * @Return
     */
    @ResponseBody
    @RequestMapping(value = "getUploadServerConfigList.do")
    public Response getUploadServerConfigList() {
        JsonPage<UploadVO> jsonPage = service.getUploadServerConfigList();

        ResponseObject<List<UploadVO>> res = new ResponseObject<List<UploadVO>>();

        res.setRows(jsonPage.getRows());

        return res;
    }

    /**
     * 保存Download服务配置列表
     *
     * @Return
     */
    @ResponseBody
    @RequestMapping(value = "saveUploadServerConfig.do")
    public Response saveUploadServerConfig(String uploadVO) {
        List<UploadVO> list = null;
        try {
            list = JSONUtils.toBeanList(uploadVO, UploadVO.class);
        } catch (Exception e) {
            throw new BizException("对象转换错误", null);
        }

        service.saveUploadServerConfig(list);

        return new Response();
    }
}
