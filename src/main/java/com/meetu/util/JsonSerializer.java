package com.meetu.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *  【工具类】Json序列化与反序列化
 */

public class JsonSerializer {

    public ObjectMapper mapper = new ObjectMapper();

    public String toJson(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }



    public static void main(String args[]) throws IOException{

        List<String> in = new ArrayList<>();
        in.add("/this/url1");
        in.add("/this/url2");
        in.add("/this/url3");

        JsonSerializer jsonFilter = new JsonSerializer();
        String out = jsonFilter.toJson(in);//序列化

        System.out.println("out:"+out);

        List<String> in2 = jsonFilter.mapper.readValue(out, List.class);//反序列化。。。todo: 可以将这个序列化，反序列化封装成独立的工具类

        System.out.println("in2:"+in2);//反序列化的结果
        System.exit(0);

        //以上序列化与反序列化的代码事实上也就是利用了以下2个方法来实现序列化与反序列化实际工作
        //ObjectMapper().writeValueAsString    ObjectMapper().readValue()

    }
}
