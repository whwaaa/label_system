package com.jumaojiang.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.math.BigDecimal;

/**
 * api
 *
 * @author wuhanwei
 * @version 1.0
 * @date 2021/10/14
 */
@Component
public class XMLMapping {

    public static String TEMPLATE_DIR;

    @Autowired
    public void setTemplateDir(String templateDir){
        XMLMapping.TEMPLATE_DIR = templateDir;
    }

    public static final String TYPENAME = "446";
    public static final String KUANHAO = "448";
    public static final String COLOR = "450";
    public static final String SIZE = "466";
    public static final String ZXBZ = "452";
    public static final String CPLB = "454";
    public static final String OUTFABRIC = "1092";
    public static final String INTFABRIC = "702";
    public static final String FILLER = "3106";
    public static final String BARCODE = "403";
    public static final String GUIDEPRICE = "1120";

    public static final String SELLPRICE = "402"; // 工厂直销价 : 345.00  /  零售价 : 345.00
    public static final String LEVEL = "698";
    public static final String CHECKER = "700";

    public static String getModelXML(Integer tempLateId){
        String filePath = null;
        switch (tempLateId){
            case 0:
                filePath = TEMPLATE_DIR + File.separator + "0.lsdx";
                break;
            case 1:
                filePath = TEMPLATE_DIR + File.separator + "1.lsdx";
                break;
            case 4:
                filePath = TEMPLATE_DIR + File.separator + "100.lsdx";
                break;
            case 5:
                filePath = TEMPLATE_DIR + File.separator + "101.lsdx";
                break;
            case 6:
                filePath = TEMPLATE_DIR + File.separator + "110.lsdx";
                break;
            case 7:
                filePath = TEMPLATE_DIR + File.separator + "111.lsdx";
                break;
            case 8:
                filePath = TEMPLATE_DIR + File.separator + "1000.lsdx";
                break;
            case 9:
                filePath = TEMPLATE_DIR + File.separator + "1001.lsdx";
                break;
            case 12:
                filePath = TEMPLATE_DIR + File.separator + "1100.lsdx";
                break;
            case 13:
                filePath = TEMPLATE_DIR + File.separator + "1101.lsdx";
                break;
            case 14:
                filePath = TEMPLATE_DIR + File.separator + "1110.lsdx";
                break;
            case 15:
                filePath = TEMPLATE_DIR + File.separator + "1111.lsdx";
                break;
        }
        return filePath;
    }
}
