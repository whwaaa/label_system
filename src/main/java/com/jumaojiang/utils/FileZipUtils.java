package com.jumaojiang.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
/**
 * @author Nemo
 * @version 1.0
 * @date 2019/11/5
 */
@Component
public class FileZipUtils {
    private static final int BUFFER_SIZE = 2 * 1024;

    public static String FILE_ROOTPATH; // 文件存储路径
    public static Boolean IS_KEEP_FILE;  // 是否保留历史生成打包的.zip文件

    @Autowired
    public void setFileRootpath(String savePath){
        FileZipUtils.FILE_ROOTPATH = savePath;
    }

    @Autowired
    public void setIS_KEEP_FILE(Boolean isKeepFile) {
        FileZipUtils.IS_KEEP_FILE = isKeepFile;
    }

    /**
     * 删除指定路径下的文件
     * @param path
     * @return
     */
    public static boolean deleteDir(String path, boolean... isRecursive){
        File file = new File(path);
        if(!file.exists()){//判断是否待删除目录是否存在
            System.err.println("The dir are not exists!");
            return false;
        }
        String[] content = file.list();//取得当前目录下所有文件和文件夹
        for(String name : content){
            File temp = new File(path, name);
            if(temp.isDirectory()){//判断是否是目录
                deleteDir(temp.getAbsolutePath(),true);//递归调用，删除目录里的内容
                temp.delete();//删除空目录
            }else{
                if(isRecursive.length!=0 && isRecursive[0]){
                    // 递归调用, 文件夹里的文件直接删除
                    if(!temp.delete()){ // 删除文件
                        System.err.println("Failed to delete " + name);
                    }
                }else{
                    // 非递归调用,由IS_KEEP_FILE决定是否删除文件
                    if(!IS_KEEP_FILE){
                        if(!temp.delete()){// 删除文件
                            System.err.println("Failed to delete " + name);
                        }
                    }
                }

            }
        }
        return true;
    }

    /**
     * 获取存储目录下.zip文件名称集合
     * @return
     */
    public static Map<String, String> getAlreadyDownFileName(){
        Map<String, String> map = new HashMap<>();
        File dirFile = new File(FILE_ROOTPATH);
        if(dirFile.exists()){
            File[] files = dirFile.listFiles();
            for (File file : files) {
                if(file.getName().endsWith(".zip")){
                    map.put(file.getName(), file.getName());
                }
            }
        }
        return map;
    }

    /**
     * 压缩成ZIP 方法1
     * @param sourceFile 压缩文件夹路径
     * @param out  压缩文件输出流
     * @param KeepDirStructure 是否保留原来的目录结构,true:保留目录结构;
     *             false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
     * @throws RuntimeException 压缩失败会抛出运行时异常
     */
    public static void toZip(File sourceFile, OutputStream out, boolean KeepDirStructure)
            throws RuntimeException{
        ZipOutputStream zos = null ;
        try {
            zos = new ZipOutputStream(out);
            compress(sourceFile,zos,sourceFile.getName(),KeepDirStructure);
        } catch (Exception e) {
            throw new RuntimeException("zip error from ZipUtils",e);
        }finally{
            if(zos != null){
                try {
                    zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /**
     * 压缩成ZIP 方法2
     * @param srcFiles 需要压缩的文件列表
     * @param out      压缩文件输出流
     * @throws RuntimeException 压缩失败会抛出运行时异常
     */
    public static void toZip(List<File> srcFiles , OutputStream out)throws RuntimeException {
        long start = System.currentTimeMillis();
        ZipOutputStream zos = null ;
        try {
            zos = new ZipOutputStream(out);
            for (File srcFile : srcFiles) {
                byte[] buf = new byte[BUFFER_SIZE];
                zos.putNextEntry(new ZipEntry(srcFile.getName()));
                int len;
                FileInputStream in = new FileInputStream(srcFile);
                while ((len = in.read(buf)) != -1){
                    zos.write(buf, 0, len);
                }
                zos.closeEntry();
                in.close();
            }
            long end = System.currentTimeMillis();
            System.out.println("压缩完成，耗时：" + (end - start) +" ms");
        } catch (Exception e) {
            throw new RuntimeException("zip error from ZipUtils",e);
        }finally{
            if(zos != null){
                try {
                    zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /**
     * 递归压缩方法
     * @param sourceFile 源文件
     * @param zos    zip输出流
     * @param name    压缩后的名称
     * @param KeepDirStructure 是否保留原来的目录结构,true:保留目录结构;
     *             false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
     * @throws Exception
     */
    private static void compress(File sourceFile, ZipOutputStream zos, String name,
                                 boolean KeepDirStructure) throws Exception{
        byte[] buf = new byte[BUFFER_SIZE];
        if(sourceFile.isFile()){
            // 向zip输出流中添加一个zip实体，构造器中name为zip实体的文件的名字
            zos.putNextEntry(new ZipEntry(name));
            // copy文件到zip输出流中
            int len;
            FileInputStream in = new FileInputStream(sourceFile);
            while ((len = in.read(buf)) != -1){
                zos.write(buf, 0, len);
            }
            // Complete the entry
            zos.closeEntry();
            in.close();
        } else {
            File[] listFiles = sourceFile.listFiles();
            if(listFiles == null || listFiles.length == 0){
                // 需要保留原来的文件结构时,需要对空文件夹进行处理
                if(KeepDirStructure){
                    // 空文件夹的处理
                    zos.putNextEntry(new ZipEntry(name + "/"));
                    // 没有文件，不需要文件的copy
                    zos.closeEntry();
                }
            }else {
                for (File file : listFiles) {
                    // 判断是否需要保留原来的文件结构
                    if (KeepDirStructure) {
                        // 注意：file.getName()前面需要带上父文件夹的名字加一斜杠,
                        // 不然最后压缩包中就不能保留原来的文件结构,即：所有文件都跑到压缩包根目录下了
                        compress(file, zos, name + "/" + file.getName(),KeepDirStructure);
                    } else {
                        compress(file, zos, file.getName(),KeepDirStructure);
                    }
                }
            }
        }
    }
}