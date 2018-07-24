package com.navi.util;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class FileUtil {

    /**
     *
     * @param src 源文件url
     * @param dest 本地文件路径, dest需要以"／"结尾 todo:这个是不是得用separator来写。。应对不同系统的区别。。查一查这个separator的用法
     * @param name 新文件名字
     * @param suffix 文件后缀
     * @throws Exception
     */
    public static void downloadFile(String src, String dest, String name, String suffix) throws Exception{
        URL url = new URL(src);
        URLConnection con = url.openConnection();
        FileOutputStream out = new FileOutputStream(dest + name + suffix);
        InputStream ins = con.getInputStream();
        byte[] b = new byte[1024];
        int i=0;
        while((i=ins.read(b))!=-1){
            out.write(b, 0, i);
        }
        ins.close();
        out.close();
    }

    public static void main(String args[]){

    }
}
