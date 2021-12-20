package com.jumaojiang.vo;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * api
 *
 * @author wuhanwei
 * @version 1.0
 * @date 2021/10/19
 */
public class LabelMapVO {

    private Map<String,Integer> zxbzMap;
    private Map<String,Integer> cplbMap;
    private Map<String,Integer> mianliaoMap;
    private Map<String,Integer> liliaoMap;
    private Map<String,Integer> fillerMap;
    private Map<String,Integer> kuanHaoMap;
    private Map<String,Integer> buyPriceMap;
    private Map<String,Integer> sellPriceMap;
    private Map<String,Integer> guidePriceMap;
    private Map<String,Integer> colorMap;

    {
        zxbzMap = new TreeMap<>();
        cplbMap = new TreeMap<>();
        mianliaoMap = new TreeMap<>();
        liliaoMap = new TreeMap<>();
        fillerMap = new TreeMap<>();
        kuanHaoMap = new TreeMap<>();
        buyPriceMap = new TreeMap<>();
        sellPriceMap = new TreeMap<>();
        guidePriceMap = new TreeMap<>();
        colorMap = new TreeMap<>();
    }

    public LabelMapVO() {
    }

    public Map<String, Integer> getZxbzMap() {
        return zxbzMap;
    }

    public void setZxbzMap(Map<String, Integer> zxbzMap) {
        this.zxbzMap = zxbzMap;
    }

    public Map<String, Integer> getCplbMap() {
        return cplbMap;
    }

    public void setCplbMap(Map<String, Integer> cplbMap) {
        this.cplbMap = cplbMap;
    }

    public Map<String, Integer> getMianliaoMap() {
        return mianliaoMap;
    }

    public void setMianliaoMap(Map<String, Integer> mianliaoMap) {
        this.mianliaoMap = mianliaoMap;
    }

    public Map<String, Integer> getLiliaoMap() {
        return liliaoMap;
    }

    public void setLiliaoMap(Map<String, Integer> liliaoMap) {
        this.liliaoMap = liliaoMap;
    }

    public Map<String, Integer> getFillerMap() {
        return fillerMap;
    }

    public void setFillerMap(Map<String, Integer> fillerMap) {
        this.fillerMap = fillerMap;
    }

    public Map<String, Integer> getKuanHaoMap() {
        return kuanHaoMap;
    }

    public void setKuanHaoMap(Map<String, Integer> kuanHaoMap) {
        this.kuanHaoMap = kuanHaoMap;
    }

    public Map<String, Integer> getBuyPriceMap() {
        return buyPriceMap;
    }

    public void setBuyPriceMap(Map<String, Integer> buyPriceMap) {
        this.buyPriceMap = buyPriceMap;
    }

    public Map<String, Integer> getSellPriceMap() {
        return sellPriceMap;
    }

    public void setSellPriceMap(Map<String, Integer> sellPriceMap) {
        this.sellPriceMap = sellPriceMap;
    }

    public Map<String, Integer> getGuidePriceMap() {
        return guidePriceMap;
    }

    public void setGuidePriceMap(Map<String, Integer> guidePriceMap) {
        this.guidePriceMap = guidePriceMap;
    }

    public Map<String, Integer> getColorMap() {
        return colorMap;
    }

    public void setColorMap(Map<String, Integer> colorMap) {
        this.colorMap = colorMap;
    }
}
