package com.meetu.util;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.meetu.dto.NewsDTO;


/**
 * 描述：
 *
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年10月20日      Simba.Hua         Create
 * ****************************************************************************
 * </pre>
 * @author Simba.Hua
 */
public class JsonFilterSerializer {
    private static final String DYNC_INCLUDE = "DYNC_INCLUDE";//包含的标识
    private static final String DYNC_EXCLUDE = "DYNC_EXCLUDE";//过滤的标识
    private ObjectMapper mapper = new ObjectMapper();
    @JsonFilter(DYNC_EXCLUDE)
    interface DynamicExclude{

    }
    @JsonFilter(DYNC_INCLUDE)
    interface DynamicInclude{

    }
    public void filter(Class<?> clazz , String include , String exclude) {
        if (clazz == null) return;
        if (include != null && include.length() > 0) {//包含的操作
            mapper.setFilterProvider(new SimpleFilterProvider()
                    .addFilter(DYNC_INCLUDE, SimpleBeanPropertyFilter.filterOutAllExcept(include.split(","))));//多个字段用,分割开
            mapper.addMixIn(clazz, DynamicInclude.class);
        } else if (exclude != null && exclude.length() > 0) {
            mapper.setFilterProvider(new SimpleFilterProvider()
                    .addFilter(DYNC_EXCLUDE, SimpleBeanPropertyFilter.serializeAllExcept(exclude.split(","))));
            mapper.addMixIn(clazz, DynamicExclude.class);
        }
    }
    public String toJson(Object object) throws JsonProcessingException{
        return mapper.writeValueAsString(object);
    }

    public static void main(String args[]) throws JsonProcessingException{
        JsonFilterSerializer jsonFilter = new JsonFilterSerializer();
        jsonFilter.filter(NewsDTO.class, null, "ts");//Response中有status、failReason、currentIndex三个属性
        NewsDTO newsDTO = new NewsDTO();
        newsDTO.setDocid("docid123");
        newsDTO.setImg("httP;//www.baidu.com");
        newsDTO.setTs(System.currentTimeMillis()/1000);
        System.out.println(jsonFilter.toJson(newsDTO));

        //过滤测试通过
    }
}
