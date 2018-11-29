package com.oee.pikachu.comm.aop;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Created by Aqua on 2018/11/14.
 */
public class OpenIdRequestWrapper extends HttpServletRequestWrapper {

    private Map<String, Object> params = new HashMap<String, Object>();

    public OpenIdRequestWrapper(HttpServletRequest request) {
        super(request);

        //先通过request原生的方法，遍历获取到的参数
//        Enumeration enu = request.getParameterNames();
//        while (enu.hasMoreElements()) {
//            String paraName = (String)enu.nextElement();
//
//            Map<String, Object> paraObj = JSON.parseObject(paraName);//因为我前台传过来的是json格式的参数
//            Set<Entry<String, Object>> entrySet = paraObj.entrySet();
//            for (Entry<String, Object> entry : entrySet) {
//                String key = entry.getKey();
//                if (key.equalsIgnoreCase(""))
//                Object value = entry.getValue();
//                params.put(key, value);//
//            }
//        }
//        //在这里修改params中的数据，不管是添加、修改
//
//        //将修改好的params重新放入RequestWrapper对象中
//        addParameters(request,params);
    }

//    public void addAllParameters(Map<String , Object>otherParams) {//增加多个参数
//        for(Map.Entry<String , Object>entry : otherParams.entrySet()) {
//            addParameter(entry.getKey() , entry.getValue());
//        }
//    }
}
