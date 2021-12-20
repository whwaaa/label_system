package com.jumaojiang.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.Iterator;

/**
 * api
 *
 * @author wuhanwei
 * @version 1.0
 * @date 2021/10/14
 */
public class Dom4jtUtil {

    /**
     * 加载xml
     * @param file
     * @return
     */
    public static Document Dom4jGetDoc(File file){
        try {
            // 创建一个SAXReader解析器
            SAXReader reader = new SAXReader();
            // 读取xml文件,转换成Document结点
            return reader.read(file);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 通过指定的节点名称获取节点
     * @param element : 修改的内容对象
     * @param eleName : 保存后的文件
     * @return
     */
    public static Element getElementByName(Element element, String eleName) {
        Iterator<Node> iterator = element.nodeIterator();
        while (iterator.hasNext()) {
            Node node = iterator.next();
            if (node instanceof Element) {
                if (node.getName().equals(eleName))
                    return (Element) node;
            }
        }
        return null;
    }
}
