package com.hk.wwz.controller;

import com.alibaba.fastjson.JSON;
import com.hk.wwz.pojo.ResultBean;
import com.hk.wwz.service.KeyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/hk/wwz/key")

@RestController
@CrossOrigin
@Api(value = "部门分页查询")
public class KeyController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    KeyService keyService;

    @ApiOperation(value = "字符串加密接口", notes = "字符串加密")
    @RequestMapping(value = "/addKey", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "value", value = "加密的字符串", paramType = "String", required = true),
            @ApiImplicitParam(name = "name", value = "字符串名字", paramType = "String", required = true)
    })

    public ResultBean addKeyToStr(@RequestBody Map<String, String> params) {
        logger.info("需要加密的字符串是：" + JSON.toJSONString(params));
        String str = params.get("value");
        String name = params.get("name");
        return keyService.addKeyToStr(str, name);
    }

    @ApiOperation(value = "字符串解密接口", notes = "字符串解密")
    @RequestMapping(value = "/removeKey/{name}", method = RequestMethod.GET)
    @ApiImplicitParam(name = "name", value = "解密的字符串", paramType = "String", required = true)
    public ResultBean removeKeyToStr(@PathVariable String name) {
        logger.info("需要解密的字符串是：" + name);
        return keyService.removeKeyToStr(name);
    }
}
