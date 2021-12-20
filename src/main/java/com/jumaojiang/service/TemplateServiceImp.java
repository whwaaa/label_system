package com.jumaojiang.service;

import com.jumaojiang.mapper.TemplateMapper;
import com.jumaojiang.pojo.Template;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * api
 *
 * @author wuhanwei
 * @version 1.0
 * @date 2021/10/16
 */
@Service
public class TemplateServiceImp {

    @Resource
    private TemplateMapper templateMapper;

    /**
     * 添加一个模板
     * @param template
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = {Exception.class})
    public Integer insert(Template template) {
        int insert = templateMapper.insert(template);
        return insert;
    }

    /**
     * 修改模板
     * @param templateId:要修改的模板id
     * @param template:修改为的数据
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = {Exception.class})
    public Integer updateById(Integer templateId, Template template) {
        template.setTemplateId(templateId);
        int res = templateMapper.updateByPrimaryKeySelective(template);
        return res;
    }

    /**
     * 删除模板(逻辑删除)
     * @param templateId
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = {Exception.class})
    public Integer deleteById(Integer templateId) {
        Template template = new Template();
        template.setTemplateId(templateId);
        template.setIsDel((byte)1);
        int res = templateMapper.updateByPrimaryKeySelective(template);
        return res;
    }

    /**
     * 查询所有模板
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public List<Template> queryAll(){
        return templateMapper.selectAll();
    }
}
