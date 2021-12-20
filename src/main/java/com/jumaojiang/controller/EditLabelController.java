package com.jumaojiang.controller;

import com.jumaojiang.pojo.Label;
import com.jumaojiang.service.EditLabelServiceImp;
import com.jumaojiang.utils.ChineseCharToEn;
import com.jumaojiang.utils.ColorBlankSpaceUtil;
import com.jumaojiang.utils.FileZipUtils;
import com.jumaojiang.vo.AjaxResultVo;
import com.jumaojiang.vo.LabelListVO;
import com.jumaojiang.vo.LabelVO;
import com.jumaojiang.vo.ListVo;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * api
 *
 * @author wuhanwei
 * @version 1.0
 * @date 2021/10/18
 */
@Controller
@RequestMapping("editLabel")
public class EditLabelController {

    @Resource
    private EditLabelServiceImp editLabelServiceImp;

    @ResponseBody
    @RequestMapping(value = "label/{kuanhao}", method = RequestMethod.GET)
    public AjaxResultVo getKuanhaoMsg(@PathVariable(value = "kuanhao", required = false) String kuanhao){
        if(kuanhao==null || "".equals(kuanhao.trim())){
            return new AjaxResultVo(404, "查询的款号信息不存在");
        }
        List<Label> labels = editLabelServiceImp.queryByKuanHao(kuanhao.trim());
        return new AjaxResultVo(200, "ok", labels);
    }

    @ResponseBody
    @RequestMapping(value = "font", method = RequestMethod.GET)
    public AjaxResultVo getFirstLetter(@RequestParam("nameType") String nameType){
        String firstLetter;
        if(nameType.length() > 0){
            firstLetter = ChineseCharToEn.getFirstLetter(nameType.substring(nameType.length()-1));
        }else{
            firstLetter = ChineseCharToEn.getFirstLetter(nameType);
        }
        AjaxResultVo resultVo = new AjaxResultVo();
        resultVo.setObj(firstLetter);
        return resultVo;
    }

    @ResponseBody
    @RequestMapping(value = "label", method = RequestMethod.POST)
    public AjaxResultVo insert(@RequestParam(value = "saveNum", required = false) Integer saveNum, Label label,
                               @RequestParam(value = "deletekuanhao", required = false) String kuanhao){
        // 重录先删除
        if(kuanhao!=null && !"".equals(kuanhao))
            editLabelServiceImp.deleteByKuanhao(kuanhao);
        // 预处理
        if(label.getColor().equals(""))
            label.setColor(null);
        // 颜色值间空格
        if(label.getColor() != null)
            label.setColor(ColorBlankSpaceUtil.blankSpace(label.getColor()));
        if(label.getZxbz().equals(""))
            label.setZxbz(null);
        if(label.getCplb().equals(""))
            label.setCplb(null);
        if(label.getOutFabric().equals(""))
            label.setOutFabric(null);
        if(label.getIntFabric().equals(""))
            label.setIntFabric(null);
        if(label.getFiller().equals(""))
            label.setFiller(null);
        if(label.getCplb().equals(""))
            label.setCplb(null);

        for(int i=0; i<saveNum; i++){
            // 标签信息存入数据库
            boolean insert = editLabelServiceImp.insert(label);
            if(!insert)
                return new AjaxResultVo(500, "数据添加失败");
        }

        return new AjaxResultVo(200, "数据添加成功", label);
    }

    @ResponseBody
    @RequestMapping(value = "label", method = RequestMethod.GET)
    public AjaxResultVo list(@RequestParam(value = "pageNum", required = false) Integer pageNum,
                             @RequestParam(value = "pageSize", required = false) Integer pageSize,
                             ListVo vo){
        LabelVO labelVO = editLabelServiceImp.list(pageNum, pageSize, vo);
        return new AjaxResultVo(200, "ok", labelVO);
    }

    @ResponseBody
    @RequestMapping(value = "label/update", method = RequestMethod.POST)
    public AjaxResultVo updateById(Label label){
        // 颜色值间空格
        if(label.getColor() != null)
            label.setColor(ColorBlankSpaceUtil.blankSpace(label.getColor()));
        // 更新数据库
        Integer integer = editLabelServiceImp.updateById(label.getId(), label);
        if(integer > 0){
            return new AjaxResultVo(200, "更新成功", null);
        }else{
            return new AjaxResultVo(500, "服务器内部出错", null);
        }
    }

    @ResponseBody
    @RequestMapping(value = "label/delete", method = RequestMethod.POST)
    public AjaxResultVo deleteById(Label label){
        Integer integer = editLabelServiceImp.deleteById(label.getId());
        if(integer > 0){
            return new AjaxResultVo(200, "删除成功", null);
        }else{
            return new AjaxResultVo(500, "服务器内部出错", null);
        }
    }


    @ResponseBody
    @RequestMapping(value = "label/history", method = RequestMethod.GET)
    public AjaxResultVo getHistoryQuery(){
        LabelListVO historyQuery = editLabelServiceImp.getHistoryQuery();
        return new AjaxResultVo(200, "ok", historyQuery);
    }


    @RequestMapping("label/download")
    public ResponseEntity<byte[]> download(HttpServletRequest request) throws IOException {
        String fileName = request.getParameter("fileName").replaceAll(" ","");
        // 生成打印标签
        String kuanHao = fileName.split("_")[1];
        List<Label> labels = editLabelServiceImp.queryByKuanHao(kuanHao);
        // 修改下载属性为已下载 blank1 -> 1
        editLabelServiceImp.updateBlank(labels);
        // 生成.Lsdx文件
        editLabelServiceImp.createLsdxs(labels);
        // 打包目录为zip文件
        String zipName = editLabelServiceImp.createZIP(fileName);
        String showName = zipName.replace(" ", "");
        // 1.创建响应头信息对象
        HttpHeaders httpHeaders = new HttpHeaders();
        // 2.标记以流的方式做出相应
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        // 3.以附件的形式响应给用户
        httpHeaders.setContentDispositionFormData(showName, URLEncoder.encode(showName, "utf-8"));
        // 4.指定文件的路径
        String path = FileZipUtils.FILE_ROOTPATH + File.separator + zipName;
        // 5.创建ResponseEntity对象
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(
                FileUtils.readFileToByteArray(new File(path)), httpHeaders, HttpStatus.CREATED);
        // 6.返回ResponseEntiey对象
        return responseEntity;
    }
}
