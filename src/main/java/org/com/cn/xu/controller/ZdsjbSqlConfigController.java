package org.com.cn.xu.controller;

import org.com.cn.xu.exception.BizException;
import org.com.cn.xu.service.ZdsjbSqlConfigService;
import org.com.cn.xu.util.JSONUtils;
import org.com.cn.xu.util.JsonPage;
import org.com.cn.xu.util.Response;
import org.com.cn.xu.util.ResponseObject;
import org.com.cn.xu.vo.SqlVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by xbf on 2017/5/5.
 */
@Controller
@RequestMapping("zdsjbSqlConfig/")
public class ZdsjbSqlConfigController extends BaseController {
    @Autowired
    ZdsjbSqlConfigService service;

    /**
     * 获取Download服务配置列表
     *
     * @Return
     */
    @ResponseBody
    @RequestMapping(value = "getPtSqlServerConfigList.do")
    public Response getPtSqlServerConfigList() {
        JsonPage<SqlVO> jsonPage = service.getPtSqlServerConfigList();

        ResponseObject<List<SqlVO>> res = new ResponseObject<List<SqlVO>>();

        res.setRows(jsonPage.getRows());

        return res;
    }

    /**
     * 保存Download服务配置列表
     *
     * @Return
     */
    @ResponseBody
    @RequestMapping(value = "savePtSqlServerConfig.do")
    public Response savePtSqlServerConfig(String sqlVO) {
        List<SqlVO> list = null;
        try {
            list = JSONUtils.toBeanList(sqlVO, SqlVO.class);
        } catch (Exception e) {
            throw new BizException("对象转换错误", null);
        }

        service.savePtSqlServerConfig(list);

        return new Response();
    }


    /**
     * 获取Download服务配置列表
     *
     * @Return
     */
    @ResponseBody
    @RequestMapping(value = "getLocalSqlServerConfigList.do")
    public Response getLocalSqlServerConfigList() {
        JsonPage<SqlVO> jsonPage = service.getLocalSqlServerConfigList();

        ResponseObject<List<SqlVO>> res = new ResponseObject<List<SqlVO>>();

        res.setRows(jsonPage.getRows());

        return res;
    }

    /**
     * 保存Download服务配置列表
     *
     * @Return
     */
    @ResponseBody
    @RequestMapping(value = "saveLocalSqlServerConfig.do")
    public Response saveLocalSqlServerConfig(String sqlVO) {
        List<SqlVO> list = null;
        try {
            list = JSONUtils.toBeanList(sqlVO, SqlVO.class);
        } catch (Exception e) {
            throw new BizException("对象转换错误", null);
        }

        service.saveLocalSqlServerConfig(list);

        return new Response();
    }
}
