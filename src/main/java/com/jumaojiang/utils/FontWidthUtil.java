package com.jumaojiang.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * api
 * 计算字体,空格,数字,符号的长度
 * @author wuhanwei
 * @version 1.0
 * @date 2021/10/14
 */
public class FontWidthUtil {
    // 需要修改标签文字的最大宽度
    private static final float DOUBLECOAT_MIANLIAO_LILIAO_WIDTH_MAX = 24.8f;
    private static final float CHINESE = 4.6f;
    private static final float SYMBOL = 4.004f;
    private static final float BLANK = 1.252f;
    private static final float DIGITAL = 2.502f;


    public static Integer XMLWidth_MianLiao_LiLiao(String str) {
        if(str == null)
            return 0;
        //  计算
        Matcher digital = Pattern.compile("\\d").matcher(str);
        Matcher symbol = Pattern.compile("[%]").matcher(str);
        Matcher chinese = Pattern.compile("[\u4e00-\u9fa5]").matcher(str);
        Matcher blank = Pattern.compile("[ ]").matcher(str);
        int digital_count = 0;
        while(digital.find())
            digital_count++;
        int symbol_count = 0;
        while(symbol.find())
            symbol_count++;
        int chinese_count = 0;
        while(chinese.find())
            chinese_count++;
        int blank_count = 0;
        while(blank.find())
            blank_count++;

        float temp = chinese_count*CHINESE + symbol_count*SYMBOL + blank_count*BLANK + digital_count*DIGITAL;
        float width = temp * 0.5f;
        System.out.println("width: " + width);
        if(width > DOUBLECOAT_MIANLIAO_LILIAO_WIDTH_MAX){
            Integer jiamju = (int)Math.floor((DOUBLECOAT_MIANLIAO_LILIAO_WIDTH_MAX / temp) * 10000);
            System.out.println("宽度超出最大, 计算间距, 换算xml值: " + jiamju);
            return jiamju;
        }
        return 5000;
    }
}
