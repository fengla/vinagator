package com.navi.util;

import com.yidian.push.lib.core.util.HttpConnectionUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadFromFileApps {

    public static void main(String args[]) throws Exception{

        //read form file
        File fileInput = new File("/Users/admin/spring/navigator/src/main/resources/damengxiang.me");

        BufferedReader reader = null;
        List<String> appList = new ArrayList<String>();
        try {
            reader = new BufferedReader(new FileReader(fileInput));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                tempString = tempString.trim();
                if(!tempString.startsWith("#") && !"".equals(tempString)){//跳过注释行
                    appList.add(tempString);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        System.out.println("总APP数：" + appList.size());

        //request localhost/crawler
        for(String app : appList){
            //System.out.println(app);
            Map<String, Object> params = new HashMap<String, Object>();//目前对于damengxiang.me这个网站的请求，这个参数没有用处
            params.put("url", app);
            String response = HttpConnectionUtils.getGetResult( "http://localhost:8080/crawler", params);
            System.out.println("response:" + response);

        }

        //不要异步处理



        //写一个清理程序。。。每天标记清理一下子我们的数据库

    }
}
