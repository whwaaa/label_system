package com.jumaojiang.controller;

import com.jumaojiang.vo.AjaxResultVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * api
 *
 * @author wuhanwei
 * @version 1.0
 * @date 2021/10/20
 */
@Controller
public class PingTestController {
    @ResponseBody
    @RequestMapping(value = "pingTest")
    public AjaxResultVo pingTest(){
        return new AjaxResultVo();
    }
}
