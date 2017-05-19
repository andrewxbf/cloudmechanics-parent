package org.com.cn.xu.controller;

import org.com.cn.xu.exception.BizException;
import org.com.cn.xu.service.ZdsjbDownloadConfigService;
import org.com.cn.xu.util.JSONUtils;
import org.com.cn.xu.util.JsonPage;
import org.com.cn.xu.util.Response;
import org.com.cn.xu.util.ResponseObject;
import org.com.cn.xu.vo.DownloadVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by xbf on 2017/5/5.
 */
@Controller
@RequestMapping("zdsjbDownloadConfig/")
public class ZdsjbDownloadConfigController extends BaseController {
    @Autowired
    ZdsjbDownloadConfigService service;

    /**
     * 获取Download服务配置列表
     *
     * @Return
     */
    @ResponseBody
    @RequestMapping(value = "getDownloadServerConfigList.do")
    public Response getDownloadServerConfigList() {
        System.out.println("------- getDownloadServerConfigList -------");
        JsonPage<DownloadVO> jsonPage = service.getDownloadServerConfigList();

        ResponseObject<List<DownloadVO>> res = new ResponseObject<List<DownloadVO>>();

        res.setRows(jsonPage.getRows());

        return res;
    }

    /**
     * 保存Download服务配置列表
     *
     * @Return
     */
    @ResponseBody
    @RequestMapping(value = "saveDownloadServerConfig.do")
    public Response saveDownloadServerConfig(String downloadVO) {
        List<DownloadVO> list = null;
        try {
            list = JSONUtils.toBeanList(downloadVO, DownloadVO.class);
        } catch (Exception e) {
            throw new BizException("对象转换错误", null);
        }

        service.saveDownloadServerConfig(list);

        return new Response();
    }
}
