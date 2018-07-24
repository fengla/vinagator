package com.navi.util;

import javax.servlet.http.HttpServletResponse;

import lombok.extern.log4j.Log4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 描述：对加了JsonFieldFilter注解的方法进行字段过滤处理
 *
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年10月20日      Simba.Hua         Create
 * ****************************************************************************
 * </pre>
 * @author Simba.Hua
 */
@Log4j
public class JsonReturnHandler implements HandlerMethodReturnValueHandler{

    @Override
    public void handleReturnValue(Object returnObject, MethodParameter paramter,
                                  ModelAndViewContainer container, NativeWebRequest request) throws Exception {
        container.setRequestHandled(true);
        JsonFilterSerializer serializer = new JsonFilterSerializer();
        if(paramter.hasMethodAnnotation(JsonFieldFilter.class)) {//如果有JsonFieldFilter注解，则过滤返回的对象returnObject
            JsonFieldFilter jsonFilter = paramter.getMethodAnnotation(JsonFieldFilter.class);
            serializer.filter(jsonFilter.type() == null ?returnObject.getClass() : jsonFilter.type(), jsonFilter.include(), jsonFilter.exclude());//调用过滤方法
        }
        HttpServletResponse response = request.getNativeResponse(HttpServletResponse.class);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().write(serializer.toJson(returnObject));
    }

    @Override
    public boolean supportsReturnType(MethodParameter methodParameter) {
        return methodParameter.hasMethodAnnotation(JsonFieldFilter.class);
    }

}
