package com.jumaojiang.controller;

import com.jumaojiang.pojo.Template;
import com.jumaojiang.service.TemplateServiceImp;
import com.jumaojiang.vo.AjaxResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * api
 *
 * @author wuhanwei
 * @version 1.0
 * @date 2021/10/16
 */
@Controller
public class TemplateController {

    @Autowired
    private TemplateServiceImp templateServiceImp;

    @ResponseBody
    @RequestMapping(value = "template", method = RequestMethod.POST)
    public AjaxResultVo insert(@RequestParam(value = "styleId", required = false) Integer styleId,
                               @RequestParam(value = "typeName", required = false) String typeName){
        Template template = new Template();
        template.setStyleId(styleId);
        template.setTypeName(typeName);
        Integer insert = templateServiceImp.insert(template);
        AjaxResultVo ajaxResultVo = new AjaxResultVo();
        if(insert == 0){
            ajaxResultVo.setCode(500);
            ajaxResultVo.setMsg("添加失败");
        }
        return ajaxResultVo;
    }

    @ResponseBody
    @RequestMapping(value = "template", method = RequestMethod.GET)
    public AjaxResultVo queryAll(){
        List<Template> templates = templateServiceImp.queryAll();
        AjaxResultVo ajaxResultVo = new AjaxResultVo();
        if(ajaxResultVo == null){
            ajaxResultVo.setCode(400);
            ajaxResultVo.setMsg("查询失败");
        }
        ajaxResultVo.setObj(templates);
        return ajaxResultVo;
    }

    @ResponseBody
    @RequestMapping(value = "template/delete")
    public AjaxResultVo deleteById(@RequestParam("tempLateId") Integer tempLateId){
        Integer integer = templateServiceImp.deleteById(tempLateId);
        if(integer > 0)
            return new AjaxResultVo(200, "删除成功", null);
        else
            return new AjaxResultVo(500, "删除失败", null);
    }
}
