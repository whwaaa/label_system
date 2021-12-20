package com.jumaojiang.utils;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * api
 *
 * @author wuhanwei
 * @version 1.0
 * @date 2021/10/14
 */
public class Base64Util {

    public static final Base64.Decoder decoder = Base64.getDecoder();
    public static final Base64.Encoder encoder = Base64.getEncoder();

    /**
     * Base64解码
     * @param str
     * @param coding
     * @return
     */
    public static String decoderString(String str, String ...coding){
        if(coding.length == 0)
            coding = new String[]{"utf8"};
        try {
            return new String(decoder.decode(str), coding[0]);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Base64编码
     * @param str
     * @param coding
     * @return
     */
    public static String encoderString(String str, String ...coding){
        if(coding.length == 0)
            coding = new String[]{"utf8"};
        try {
            return new String(encoder.encode(str.getBytes("utf-8")), coding[0]);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String s = encoderString("33%氨纶 33.8%聚酯纤维 33.2%腈纶","utf-8");
        System.out.println(s);
    }
}
