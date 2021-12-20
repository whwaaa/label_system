package com.jumaojiang.service;

import com.jumaojiang.mapper.LabelMapper;
import com.jumaojiang.mapper.TemplateMapper;
import com.jumaojiang.pojo.Label;
import com.jumaojiang.pojo.LabelExample;
import com.jumaojiang.pojo.Template;
import com.jumaojiang.utils.*;
import com.jumaojiang.vo.LabelListVO;
import com.jumaojiang.vo.LabelMapVO;
import com.jumaojiang.vo.LabelVO;
import com.jumaojiang.vo.ListVo;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.XMLWriter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.*;

/**
 * api
 *
 * @author wuhanwei
 * @version 1.0
 * @date 2021/10/18
 */
@Service
public class EditLabelServiceImp {

    @Resource
    private LabelMapper labelMapper;
    @Resource
    private TemplateMapper templateMapper;

    /**
     * 打包目录为zip文件
     * @param fileName
     * @return
     */
    public String createZIP(String fileName){
        // 打包生成文件
        String filePath1 = FileZipUtils.FILE_ROOTPATH + File.separator + fileName;
        String filePath2 = FileZipUtils.FILE_ROOTPATH + File.separator + fileName + ".zip";
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(filePath2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        FileZipUtils.toZip(new File(filePath1), fileOutputStream, true);
        return fileName + ".zip";
    }

    /**
     * 删除原.Lsdx文件
     * @param label
     * @return
     */
    public boolean deleteLsdx(Label label) {
        String dir = label.getTypeName().replaceAll(" ","") + "_" + label.getKuanHao();
        String fileName =  label.getTypeName().replaceAll(" ","") + "_" +
                label.getKuanHao() + "_" +
                label.getColor().replaceAll(" ","") + "_" +
                label.getSize() + "_" +
                label.getBuyPrice().intValue() + "_" +
                label.getSellPrice().intValue() + "_id" +
                label.getId() + ".lsdx";
        File file = new File(FileZipUtils.FILE_ROOTPATH + File.separator + dir + File.separator + fileName);
        if(!file.exists()){
            return false;
        }
        file.delete();
        return true;
    }

    /**
     * 生成.Lsdx文件
     * @param labelList
     */
    public void createLsdxs(List<Label> labelList) {
        // 先清空文件夹
        FileZipUtils.deleteDir(FileZipUtils.FILE_ROOTPATH);

        // 生成标签文件
        for (Label label : labelList) {
            // fileDir: 品名_款号
            String fileDir = label.getTypeName().replaceAll(" ","") + "_" + label.getKuanHao();
            // fileName: 品名_款号_颜色_尺寸_进价_售价
            String fileName =  label.getTypeName().replaceAll(" ","") + "_" +
                    label.getKuanHao() + "_" +
                    label.getColor().replaceAll(" ","") + "_" +
                    label.getSize() + "_" +
                    label.getBuyPrice().intValue() + "_" +
                    label.getSellPrice().intValue() + "_id" +
                    label.getId() + ".lsdx";
            File dir;
            File file;
            try {
                dir = new File(FileZipUtils.FILE_ROOTPATH + File.separator + fileDir);
                if(!dir.exists()){
                    dir.mkdirs();
                }
                String absolutePath = dir.getAbsolutePath();
                System.out.println(absolutePath);
                file = new File(dir,fileName);

                if(!file.exists()){
                    file.createNewFile();
                }
                // 生成单个.Lsdx文件
                createLsdx(label, file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 生成单个.Lsdx文件
     * @param label
     * @param file
     */
    private void createLsdx(Label label, File file) {
        Template template = templateMapper.selectByPrimaryKey(label.getTemplateId());
        // 加载xml模板
        Document doc = Dom4jtUtil.Dom4jGetDoc(new File(XMLMapping.getModelXML(template.getStyleId())));
        Element rootElement = doc.getRootElement();
        Element labelform = Dom4jtUtil.getElementByName(rootElement, "labelform");

        // 根据面料,里料的内容修改最大长度
        String outfabric = label.getOutFabric();
        Integer outfabricWidth = FontWidthUtil.XMLWidth_MianLiao_LiLiao(outfabric);
        String iutfabric = label.getIntFabric();
        Integer iutfabricWidth = FontWidthUtil.XMLWidth_MianLiao_LiLiao(iutfabric);

        // 遍历xml节点
        Element labellayer = Dom4jtUtil.getElementByName(labelform, "labellayer");
        Element labelobjects = Dom4jtUtil.getElementByName(labellayer, "labelobjects");
        Iterator<Node> it0 = labelobjects.nodeIterator();
        while (it0.hasNext()) {
            Node node = it0.next();
            if (node instanceof Element) {
                // 457: 面料(outfabricWidth)文字框节点的id
                // 703: 里料(iutfabricWidth)文字框节点的id
                switch (((Element) node).attribute("id").getValue()) {
                    case "457":
                        Element outfabricWidthFont = Dom4jtUtil.getElementByName(((Element) node), "font");
                        outfabricWidthFont.attribute("width").setValue(outfabricWidth.toString());
                        break;
                    case "703":
                        Element iutfabricWidthFont = Dom4jtUtil.getElementByName(((Element) node), "font");
                        iutfabricWidthFont.attribute("width").setValue(iutfabricWidth.toString());
                        break;
                }
            }
        }
        // 修改标签具体内容
        DecimalFormat df = new DecimalFormat("#.00");
        Element variables = Dom4jtUtil.getElementByName(labelform, "variables");
        Iterator<Node> iterator = variables.nodeIterator();
        while (iterator.hasNext()) {
            Node node = iterator.next();
            if (node instanceof Element) {
                // 更新指定节点
                switch(((Element) node).attribute("id").getValue()){
                    case XMLMapping.SELLPRICE:
                        if((Integer.parseInt(template.getStyleId().toString()) & 1) == 0){
                            // 无指导价
                            ((Element) node).attribute("data").setValue(Base64Util.encoderString("零售价 : " + df.format(label.getSellPrice()),"utf-8"));
                            ((Element) node).attribute("value").setValue("零售价 : " + df.format(label.getSellPrice()));
                        }else{
                            // 有指导价
                            ((Element) node).attribute("data").setValue(Base64Util.encoderString("工厂直销价 : " + df.format(label.getSellPrice()),"utf-8"));
                            ((Element) node).attribute("value").setValue("工厂直销价 : " + df.format(label.getSellPrice()));
                        }
                        break;
                    case XMLMapping.BARCODE:
                        ((Element) node).attribute("data").setValue(Base64Util.encoderString(label.getBarCode(),"utf-8"));
                        ((Element) node).attribute("value").setValue(label.getBarCode());
                        break;
                    case XMLMapping.TYPENAME:
                        ((Element) node).attribute("data").setValue(Base64Util.encoderString(label.getTypeName(),"utf-8"));
                        ((Element) node).attribute("value").setValue(label.getTypeName());
                        break;
                    case XMLMapping.KUANHAO:
                        ((Element) node).attribute("data").setValue(Base64Util.encoderString(label.getKuanHao(),"utf-8"));
                        ((Element) node).attribute("value").setValue(label.getKuanHao());
                        break;
                    case XMLMapping.COLOR:
                        ((Element) node).attribute("data").setValue(Base64Util.encoderString(label.getColor(),"utf-8"));
                        ((Element) node).attribute("value").setValue(label.getColor());
                        break;
                    case XMLMapping.ZXBZ:
                        ((Element) node).attribute("data").setValue(Base64Util.encoderString(label.getZxbz(),"utf-8"));
                        ((Element) node).attribute("value").setValue(label.getZxbz());
                        break;
                    case XMLMapping.CPLB:
                        ((Element) node).attribute("data").setValue(Base64Util.encoderString(label.getCplb(),"utf-8"));
                        ((Element) node).attribute("value").setValue(label.getCplb());
                        break;
                    case XMLMapping.OUTFABRIC:
                        ((Element) node).attribute("data").setValue(Base64Util.encoderString(label.getOutFabric(),"utf-8"));
                        ((Element) node).attribute("value").setValue(label.getOutFabric());
                        break;
                    case XMLMapping.SIZE:
                        ((Element) node).attribute("data").setValue(Base64Util.encoderString(label.getSize(),"utf-8"));
                        ((Element) node).attribute("value").setValue(label.getSize());
                        break;
                    case XMLMapping.FILLER:
                        ((Element) node).attribute("data").setValue(Base64Util.encoderString(label.getFiller(),"utf-8"));
                        ((Element) node).attribute("value").setValue(label.getFiller());
                        break;
                    case XMLMapping.GUIDEPRICE:
                        ((Element) node).attribute("data").setValue(Base64Util.encoderString(df.format(label.getGuidePrice()),"utf-8"));
                        ((Element) node).attribute("value").setValue(df.format(label.getGuidePrice()));
                        break;
                    case XMLMapping.INTFABRIC:
                        ((Element) node).attribute("data").setValue(Base64Util.encoderString(label.getIntFabric(),"utf-8"));
                        ((Element) node).attribute("value").setValue(label.getIntFabric());
                        break;
                    case XMLMapping.LEVEL:
                        ((Element) node).attribute("data").setValue(Base64Util.encoderString("合格品","utf-8"));
                        ((Element) node).attribute("value").setValue("合格品");
                        break;
                    case XMLMapping.CHECKER:
                        ((Element) node).attribute("data").setValue(Base64Util.encoderString("08","utf-8"));
                        ((Element) node).attribute("value").setValue("08");
                        break;
                }
            }
        }
        // 更新完毕, 保存xml
        XMLWriter writer = null;
        FileOutputStream fileOutputStream = null;
        try {
            if(!file.exists())
                file.createNewFile();
            fileOutputStream = new FileOutputStream(file);
            writer = new XMLWriter(fileOutputStream);
            writer.write(doc);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                writer.close();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 根据款号删除标签
     * @param kuanhao
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer deleteByKuanhao(String kuanhao){
        LabelExample labelExample = new LabelExample();
        labelExample.createCriteria().andKuanHaoEqualTo(kuanhao);
        return labelMapper.deleteByExample(labelExample);
    }

    /**
     * 标签信息存入数据库
     * @param label
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = {Exception.class})
    public boolean insert(Label label){
        label.setCreateTime(new Timestamp(new Date().getTime()));
        String nativeSize = label.getSize();
        String[] sizes = nativeSize.split(",");
        int length = sizes.length;
        for(int i=0; i<length; i++){
            label.setId(null);
            label.setBlank1("0");
            label.setSize(sizes[i]);
            if(labelMapper.insertSelective(label) == 0)
                return false;
        }
        label.setSize(nativeSize);
        return true;
    }

    /**
     * 删除一个标签(逻辑删除)
     * @param labelId
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = {Exception.class})
    public Integer deleteById(Integer labelId) {
        Label label = new Label();
        label.setId(labelId);
        label.setIsDel((byte)1);
        label.setUpdateTime(new Timestamp(new Date().getTime()));
        int res = labelMapper.updateByPrimaryKeySelective(label);
        return res;
    }

    /**
     * 修改一个标签
     * @param labelId
     * @param label
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = {Exception.class})
    public Integer updateById(Integer labelId, Label label) {
        label.setId(labelId);
        label.setUpdateTime(new Timestamp(new Date().getTime()));
        int res = labelMapper.updateByPrimaryKeySelective(label);
        return res;
    }

    /**
     * 通过款号查询标签
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public List<Label> queryByKuanHao(String kuanHao){
        // 查询数据库
        LabelExample labelExample = new LabelExample();
        LabelExample.Criteria criteria = labelExample.createCriteria();
        criteria.andKuanHaoEqualTo(kuanHao);
        return labelMapper.selectByExample(labelExample);
    }

    /**
     * 查询标签列表
     * @param pageNum
     * @param pageSize
     * @param vo
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public LabelVO list(Integer pageNum, Integer pageSize, ListVo vo){
        // 查询数据库
        LabelVO labelVO = new LabelVO();
        labelVO.setKuanHaoMap(new HashMap<>());
        Map<String, List<Label>> kuanHaoMap = labelVO.getKuanHaoMap();

        LabelExample labelExample = new LabelExample();
        LabelExample.Criteria criteria = labelExample.createCriteria();
        if(vo.getKuanHao()!=null && !"".equals(vo.getKuanHao().trim()))
            criteria.andKuanHaoLike("%"+vo.getKuanHao().trim()+"%");
        if(vo.getTypeName()!=null && !"".equals(vo.getTypeName().trim()))
            criteria.andTypeNameLike("%"+vo.getTypeName().trim()+"%");
        if(vo.getBlank1()!=null && !"".equals(vo.getBlank1().trim()) && !"-1".equals(vo.getBlank1().trim()))
            criteria.andBlank1EqualTo(vo.getBlank1().trim());
        if(vo.getCreateTime() != null){
            criteria.andCreateTimeGreaterThanOrEqualTo(vo.getCreateTime());
            Date createTime = vo.getCreateTime();
            long l = createTime.getTime() + 86400000;
            criteria.andCreateTimeLessThanOrEqualTo( new Date(l));
        }
        criteria.andIsDelEqualTo("0");
        List<Label> labels = labelMapper.selectByExample(labelExample);
        labels.forEach(label -> {
            List<Label> mapLabels = kuanHaoMap.get(label.getKuanHao());
            if(mapLabels != null){ // 存在这个集合,直接向list中添加
                mapLabels.add(label);
            }else{  // 不存在,创建list,添加进map
                List<Label> mapLabelsTemp = new ArrayList<>();
                mapLabelsTemp.add(label);
                kuanHaoMap.put(label.getKuanHao(), mapLabelsTemp);
            }
        });
        labelVO.setKuanHaoName(kuanHaoMap.keySet());
        // 查询已存在的文件名
        labelVO.setAlreadyDownMap(FileZipUtils.getAlreadyDownFileName());
        return labelVO;
    }

    /**
     * 查询历史数据记录
     * @return
     */
    public LabelListVO getHistoryQuery(){
        LabelListVO labelListVO = new LabelListVO();
        LabelMapVO labelMapVO = new LabelMapVO();
        List<Label> labels = labelMapper.selectAll();
        labels.forEach(label -> {
            labelMapVO.getZxbzMap().merge(label.getZxbz(), 1, Integer::sum);
            labelMapVO.getCplbMap().merge(label.getCplb(), 1, Integer::sum);
            labelMapVO.getMianliaoMap().merge(label.getOutFabric(), 1, Integer::sum);
            if(label.getIntFabric() != null)
                labelMapVO.getLiliaoMap().merge(label.getIntFabric(), 1, Integer::sum);
            if(label.getFiller() != null)
                labelMapVO.getFillerMap().merge(label.getFiller(), 1, Integer::sum);
            if(label.getKuanHao() != null)
                labelMapVO.getKuanHaoMap().merge(label.getKuanHao().replaceAll("^[0-9]*[A-z]", ""), 1, Integer::sum);
            if(label.getBuyPrice() != null)
                labelMapVO.getBuyPriceMap().merge(String.valueOf(label.getBuyPrice().intValue()), 1, Integer::sum);
            if(label.getSellPrice() != null)
                labelMapVO.getSellPriceMap().merge(String.valueOf(label.getSellPrice().intValue()), 1, Integer::sum);
            if(label.getGuidePrice() != null)
                labelMapVO.getGuidePriceMap().merge(String.valueOf(label.getGuidePrice().intValue()), 1, Integer::sum);
            if(label.getColor() != null)
                labelMapVO.getColorMap().merge(label.getColor(), 1, Integer::sum);
        });
        // 1：把map转换成entryset，再转换成保存Entry对象的list
        ArrayList<Map.Entry<String, Integer>> entriesZxbzMap = new ArrayList<>(labelMapVO.getZxbzMap().entrySet());
        ArrayList<Map.Entry<String, Integer>> entriesCplbMap = new ArrayList<>(labelMapVO.getCplbMap().entrySet());
        ArrayList<Map.Entry<String, Integer>> entriesMianliaoMap = new ArrayList<>(labelMapVO.getMianliaoMap().entrySet());
        ArrayList<Map.Entry<String, Integer>> entriesLiliaoMap = new ArrayList<>(labelMapVO.getLiliaoMap().entrySet());
        ArrayList<Map.Entry<String, Integer>> entriesFillerMap = new ArrayList<>(labelMapVO.getFillerMap().entrySet());
        ArrayList<Map.Entry<String, Integer>> entriesKuanHaoMap = new ArrayList<>(labelMapVO.getKuanHaoMap().entrySet());
        ArrayList<Map.Entry<String, Integer>> entriesBuyPriceMap = new ArrayList<>(labelMapVO.getBuyPriceMap().entrySet());
        ArrayList<Map.Entry<String, Integer>> entriesSellPriceMap = new ArrayList<>(labelMapVO.getSellPriceMap().entrySet());
        ArrayList<Map.Entry<String, Integer>> entriesGuidePriceMap = new ArrayList<>(labelMapVO.getGuidePriceMap().entrySet());
        ArrayList<Map.Entry<String, Integer>> entriesColorMap = new ArrayList<>(labelMapVO.getColorMap().entrySet());
        // 2：调用Collections.sort(list,comparator)方法把Entry-list排序
        Collections.sort(entriesZxbzMap, new MyComparator());
        Collections.sort(entriesCplbMap, new MyComparator());
        Collections.sort(entriesMianliaoMap, new MyComparator());
        Collections.sort(entriesLiliaoMap, new MyComparator());
        Collections.sort(entriesFillerMap, new MyComparator());
        Collections.sort(entriesKuanHaoMap, new MyComparator());
        Collections.sort(entriesBuyPriceMap, new MyComparator());
        Collections.sort(entriesSellPriceMap, new MyComparator());
        Collections.sort(entriesGuidePriceMap, new MyComparator());
        Collections.sort(entriesColorMap, new MyComparator());
        // 3：遍历排好序的Entry-list，可得到按顺序输出的结果
        mapSaveToLists(entriesZxbzMap, labelListVO.getZxbzList());
        mapSaveToLists(entriesCplbMap, labelListVO.getCplbList());
        mapSaveToLists(entriesMianliaoMap, labelListVO.getMianliaoList());
        mapSaveToLists(entriesLiliaoMap, labelListVO.getLiliaoList());
        mapSaveToLists(entriesFillerMap, labelListVO.getFillerList());
        mapSaveToLists(entriesKuanHaoMap, labelListVO.getKuanHaoList());
        mapSaveToLists(entriesBuyPriceMap, labelListVO.getBuyPriceList());
        mapSaveToLists(entriesSellPriceMap, labelListVO.getSellPriceList());
        mapSaveToLists(entriesGuidePriceMap, labelListVO.getGuidePriceList());
        mapSaveToLists(entriesColorMap, labelListVO.getColorList());
        return labelListVO;
    }

    // Map转存List工具方法
    private void mapSaveToLists(ArrayList<Map.Entry<String, Integer>> entriesMapList, List<String> labelxxList){
        int i=0;
        for (Map.Entry<String, Integer> entrieMapList : entriesMapList) {
            if(++i > 3)
                break;
            labelxxList.add(entrieMapList.getKey());
        }
    }


    /**
     * 更新blanck为已下载状态:1
     * @param list
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateBlank(List<Label> list){
        List<Integer> ids = new ArrayList<>();
        for (Label label : list){
            label.setBlank1("1");
            labelMapper.updateByPrimaryKeySelective(label);
        }
    }

    /**
     * 查询一个标签
     * @param id
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public Label queryById(Integer id){
        return labelMapper.selectByPrimaryKey(id);
    }


    static class MyComparator implements Comparator<Map.Entry>{
        @Override
        public int compare(Map.Entry o1, Map.Entry o2) {
            return ((Integer)o2.getValue()).compareTo((Integer)o1.getValue());
        }
    }
}




















