package com.jumaojiang.vo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description:
 * @Auther: whw
 * @Date: 2021/12/19
 * @Week: 星期日
 */
public class ListVo {

    // 创建日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    // 品名
    private String typeName;

    // 款号
    private String kuanHao;

    // 是否下载过 0未下载 1下载过
    private String blank1;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getKuanHao() {
        return kuanHao;
    }

    public void setKuanHao(String kuanHao) {
        this.kuanHao = kuanHao;
    }

    public String getBlank1() {
        return blank1;
    }

    public void setBlank1(String blank1) {
        this.blank1 = blank1;
    }
}
