package com.jumaojiang.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * api
 *
 * @author wuhanwei
 * @version 1.0
 * @date 2021/10/19
 */
public class LabelListVO {

    private List<String> zxbzList;
    private List<String> cplbList;
    private List<String> mianliaoList;
    private List<String> liliaoList;
    private List<String> fillerList;
    private List<String> kuanHaoList;
    private List<String> buyPriceList;
    private List<String> sellPriceList;
    private List<String> guidePriceList;
    private List<String> colorList;

    {
        zxbzList = new ArrayList<>();
        cplbList = new ArrayList<>();
        mianliaoList = new ArrayList<>();
        liliaoList = new ArrayList<>();
        fillerList = new ArrayList<>();
        kuanHaoList = new ArrayList<>();
        buyPriceList = new ArrayList<>();
        sellPriceList = new ArrayList<>();
        guidePriceList = new ArrayList<>();
        colorList = new ArrayList<>();
    }

    public List<String> getZxbzList() {
        return zxbzList;
    }

    public void setZxbzList(List<String> zxbzList) {
        this.zxbzList = zxbzList;
    }

    public List<String> getCplbList() {
        return cplbList;
    }

    public void setCplbList(List<String> cplbList) {
        this.cplbList = cplbList;
    }

    public List<String> getMianliaoList() {
        return mianliaoList;
    }

    public void setMianliaoList(List<String> mianliaoList) {
        this.mianliaoList = mianliaoList;
    }

    public List<String> getLiliaoList() {
        return liliaoList;
    }

    public void setLiliaoList(List<String> liliaoList) {
        this.liliaoList = liliaoList;
    }

    public List<String> getFillerList() {
        return fillerList;
    }

    public void setFillerList(List<String> fillerList) {
        this.fillerList = fillerList;
    }

    public List<String> getKuanHaoList() {
        return kuanHaoList;
    }

    public void setKuanHaoList(List<String> kuanHaoList) {
        this.kuanHaoList = kuanHaoList;
    }

    public List<String> getBuyPriceList() {
        return buyPriceList;
    }

    public void setBuyPriceList(List<String> buyPriceList) {
        this.buyPriceList = buyPriceList;
    }

    public List<String> getSellPriceList() {
        return sellPriceList;
    }

    public void setSellPriceList(List<String> sellPriceList) {
        this.sellPriceList = sellPriceList;
    }

    public List<String> getGuidePriceList() {
        return guidePriceList;
    }

    public void setGuidePriceList(List<String> guidePriceList) {
        this.guidePriceList = guidePriceList;
    }

    public List<String> getColorList() {
        return colorList;
    }

    public void setColorList(List<String> colorList) {
        this.colorList = colorList;
    }

    public LabelListVO() {
    }
}
