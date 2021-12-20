package com.jumaojiang.vo;

import com.jumaojiang.pojo.Label;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * api
 *
 * @author wuhanwei
 * @version 1.0
 * @date 2021/10/19
 */
public class LabelVO {

    private Set<String> kuanHaoName;

    // 下载过的文件夹名称
    private Map<String, String> alreadyDownMap;

    // 所有款号集合
    private Map<String, List<Label>> kuanHaoMap;

    public LabelVO() {
    }

    public LabelVO(Set<String> kuanHaoName, Map<String, List<Label>> kuanHaoMap, Map<String, String> alreadyDownMap) {
        this.kuanHaoName = kuanHaoName;
        this.kuanHaoMap = kuanHaoMap;
        this.alreadyDownMap = alreadyDownMap;
    }

    public Map<String, String> getAlreadyDownMap() {
        return alreadyDownMap;
    }

    public void setAlreadyDownMap(Map<String, String> alreadyDownMap) {
        this.alreadyDownMap = alreadyDownMap;
    }

    public Set<String> getKuanHaoName() {
        return kuanHaoName;
    }

    public void setKuanHaoName(Set<String> kuanHaoName) {
        this.kuanHaoName = kuanHaoName;
    }

    public Map<String, List<Label>> getKuanHaoMap() {
        return kuanHaoMap;
    }

    public void setKuanHaoMap(Map<String, List<Label>> kuanHaoMap) {
        this.kuanHaoMap = kuanHaoMap;
    }
}
