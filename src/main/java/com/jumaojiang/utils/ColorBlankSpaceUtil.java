package com.jumaojiang.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * api
 *
 * @author wuhanwei
 * @version 1.0
 * @date 2021/10/20
 */
public class ColorBlankSpaceUtil {

    /**
     * 颜色中的字母数字和汉字空格, 汉字间也空格
     * @param str
     * @return
     */
    public static String blankSpace(String str){
        // 预处理去除所有空格
        str = str.replaceAll(" ","");
        // 每个字符间加入分隔符下划线_
        String str2 = "";
        for(int i=0; i<str.length(); i++){
            str2 += str.substring(i,i+1) + "_";
        }
        // 去除数字字母间的下划线
        boolean pre = false;
        String newStr = "";
        for(int i=0; i<str2.length(); i++){
            if(str2.substring(i,i+1).equals("_")){
                newStr += str2.substring(i,i+1);
            }else{
                if(pre){
                    if(Pattern.matches("[A-Za-z0-9]", str2.substring(i,i+1))){
                        // 去除数字字母间下划线(当前字符串最后一个)
                        newStr = newStr.substring(0,newStr.length()-1);
                    }else{
                        pre = false;
                    }
                }else{
                    if(Pattern.matches("[A-Za-z0-9]", str2.substring(i,i+1))){
                        pre = true;
                    }
                }
                newStr += str2.substring(i,i+1);
            }
        }
        // 去掉最后的下划线
        newStr = newStr.substring(0,newStr.length()-1);
        // 下划线替换为空格
        newStr = newStr.replaceAll("_"," ");
        return newStr;
    }
}
