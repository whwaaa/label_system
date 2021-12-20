package com.jumaojiang.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Label {
    private Integer id;

    private Byte isDel;

    private Integer userId;

    private Date createTime;

    private Date updateTime;

    private String typeName;

    private String kuanHao;

    private String color;

    private String size;

    private String zxbz;

    private String cplb;

    private String outFabric;

    private String intFabric;

    private String filler;

    private String checker;

    private String level;

    private String barCode;

    private BigDecimal guidePrice;

    private BigDecimal sellPrice;

    private BigDecimal buyPrice;

    private Integer isSelled;

    private Integer templateId;

    private String blank1;

    private String blank2;

    {
        isDel = (byte)0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Byte getIsDel() {
        return isDel;
    }

    public void setIsDel(Byte isDel) {
        this.isDel = isDel;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    public String getKuanHao() {
        return kuanHao;
    }

    public void setKuanHao(String kuanHao) {
        this.kuanHao = kuanHao == null ? null : kuanHao.trim();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color == null ? null : color.trim();
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size == null ? null : size.trim();
    }

    public String getZxbz() {
        return zxbz;
    }

    public void setZxbz(String zxbz) {
        this.zxbz = zxbz == null ? null : zxbz.trim();
    }

    public String getCplb() {
        return cplb;
    }

    public void setCplb(String cplb) {
        this.cplb = cplb == null ? null : cplb.trim();
    }

    public String getOutFabric() {
        return outFabric;
    }

    public void setOutFabric(String outFabric) {
        this.outFabric = outFabric == null ? null : outFabric.trim();
    }

    public String getIntFabric() {
        return intFabric;
    }

    public void setIntFabric(String intFabric) {
        this.intFabric = intFabric == null ? null : intFabric.trim();
    }

    public String getFiller() {
        return filler;
    }

    public void setFiller(String filler) {
        this.filler = filler == null ? null : filler.trim();
    }

    public String getChecker() {
        return checker;
    }

    public void setChecker(String checker) {
        this.checker = checker == null ? null : checker.trim();
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode == null ? null : barCode.trim();
    }

    public BigDecimal getGuidePrice() {
        return guidePrice;
    }

    public void setGuidePrice(BigDecimal guidePrice) {
        this.guidePrice = guidePrice;
    }

    public BigDecimal getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(BigDecimal sellPrice) {
        this.sellPrice = sellPrice;
    }

    public BigDecimal getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(BigDecimal buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Integer getIsSelled() {
        return isSelled;
    }

    public void setIsSelled(Integer isSelled) {
        this.isSelled = isSelled;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public String getBlank1() {
        return blank1;
    }

    public void setBlank1(String blank1) {
        this.blank1 = blank1 == null ? null : blank1.trim();
    }

    public String getBlank2() {
        return blank2;
    }

    public void setBlank2(String blank2) {
        this.blank2 = blank2 == null ? null : blank2.trim();
    }
}