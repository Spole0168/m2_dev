package com.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.dom4j.Attribute;
import org.dom4j.Element;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class HelpUtils {
	
	protected static Logger logger = Logger.getLogger(HelpUtils.class);
	/**
	 * http 调用服务端 json
	 * @param url
	 * @param jsonParam
	 * @return
	 * @throws Exception
	 */
	public static String getCreditService(String url, JSONObject jsonParam)
			throws  Exception{
		logger.info("getCreditService(String url, JSONObject jsonParam)params ="+url+"\t"+jsonParam);
		if (null == url || url.length() == 0 || null == jsonParam
				|| jsonParam.isEmpty()) {
			logger.error("getCreditService :参数有误");
		}
		HttpPost httpPost = new HttpPost(url);
		CloseableHttpClient client = HttpClients.createDefault();
		String respContent = null;
		logger.info("getCreditService(String url, JSONObject jsonParam)params ="+url+"\t"+jsonParam);
		// json方式 设置参数
		StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");// 解决中文乱码问题
		entity.setContentEncoding("UTF-8");
		entity.setContentType("application/json");
		httpPost.setEntity(entity);
		// 设置请求和传输超时时间\t
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(30 * 1000).setConnectTimeout(30 * 1000).build();
		httpPost.setConfig(requestConfig);

		try {
			HttpResponse resp = client.execute(httpPost);
			if (resp.getStatusLine().getStatusCode() == 200) {
				HttpEntity he = resp.getEntity();
				respContent = EntityUtils.toString(he, "UTF-8");
			}
		} catch (ClientProtocolException e) {
			logger.error("getCreditService :ClientProtocolException"+e);
			e.printStackTrace();
		} catch (ParseException e) {
			logger.error("getCreditService :ParseException "+e);
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("getCreditService :IOException "+e);
			e.printStackTrace();
		}finally{
			if(null!=client ){
				client.close();
				client = null;
			}
		}
		logger.info("getCreditService(String url, JSONObject jsonParam) result ="+respContent);
		return respContent;
	}
	
	/**
	 * 读取指定文件内容
	 * @param file
	 * @return
	 */
	public static String readFileByLines(File file) {
		String res = "";
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
            	res = res + tempString;
            }
            reader.close();
        } catch (IOException e) {
        	return res;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return res;
    }
	
	/**
     * xml转化json
     * @param element
     * @param json
     */
    public static void dom4jXml2Json(Element element,JSONObject json){
    	//如果是属性
    	for(Object o:element.attributes()){
    		Attribute attr=(Attribute)o;
    		if(!StringUtils.isEmpty(attr.getValue())){
    			json.put("@"+attr.getName(), attr.getValue());
    		}
    	}
    	List<Element> chdEl=element.elements();
    	if(chdEl.isEmpty()&&!StringUtils.isEmpty(element.getText())){//如果没有子元素,只有一个值
    		json.put(element.getName(), element.getText());
    	}
    	
    	for(Element e:chdEl){//有子元素
    		if(!e.elements().isEmpty()){//子元素也有子元素
    			JSONObject chdjson=new JSONObject();
    			dom4jXml2Json(e,chdjson);
    			Object o=json.get(e.getName());
    			if(o!=null){
    				JSONArray jsona=null;
    				if(o instanceof JSONObject){//如果此元素已存在,则转为jsonArray
    					JSONObject jsono=(JSONObject)o;
    					json.remove(e.getName());
    					jsona=new JSONArray();
    					jsona.add(jsono);
    					jsona.add(chdjson);
    				}
    				if(o instanceof JSONArray){
    					jsona=(JSONArray)o;
    					jsona.add(chdjson);
    				}
    				json.put(e.getName(), jsona);
    			}else{
    				if(!chdjson.isEmpty()){
    					json.put(e.getName(), chdjson);
    				}
    			}
    			
    			
    		}else{//子元素没有子元素
    			for(Object o:element.attributes()){
    				Attribute attr=(Attribute)o;
    				if(!StringUtils.isEmpty(attr.getValue())){
    					json.put("@"+attr.getName(), attr.getValue());
    				}
    			}
    			if(!e.getText().isEmpty()){
    				json.put(e.getName(), e.getText());
    			}
    		}
    	}
    }
	
    /**
     * 复制源对象和目标对象的属性值 作用： 进行更新操作时，只需要从库里load出来后，
     * 用copyObjectTypeProperties把修改后的的数据赋值到load出来的对象即可。
     *  赋值时要求：
     *   1.名称相同的属性
     * 	 2.类型相同的属性 
     *   3.源对象属性值不可为null或者空 的属性 可参考
     * 方法：saveOrUpdateInfo
     */
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static void copyObjectTypeProperties(Object target, Object source) {

        Class sourceClass = source.getClass();// 得到对象的Class
        Class targetClass = target.getClass();// 得到对象的Class

        Field[] sourceFields = sourceClass.getDeclaredFields();// 得到Class对象的所有属性
        Field[] targetFields = targetClass.getDeclaredFields();// 得到Class对象的所有属性

        for (Field sourceField : sourceFields) {
            String name = sourceField.getName();// 属性名
            Class sourceType = sourceField.getType();// 属性类型

            String methodName = name.substring(0, 1).toUpperCase() + name.substring(1);

            Object value = null;
            try {
                Method getMethod = sourceClass.getMethod("get" + methodName);// 得到属性对应get方法
                value = getMethod.invoke(source);// 执行源对象的get方法得到属性值
            } catch (Exception e) {
                continue;
            }
            if (null != value && !"".equals(value)) {
                for (Field targetField : targetFields) {
                    Class targetType = targetField.getType();// 目标属性类型
                    String targetName = targetField.getName();// 目标对象的属性名
                    if (targetName.equals(name) && sourceType.equals(targetType)) {
                        try {
                            Method setMethod = targetClass.getMethod("set" + methodName, targetType);// 属性对应的set方法
                            setMethod.invoke(target, value);// 执行目标对象的set方法
                            break;// 为了提高效率。
                        } catch (Exception e) {
                            continue;
                        }

                    }
                }
            }
        }
    }
    
    /**
     * 把map中的对象复制到JavaBean 中
     * 1.名称一致
     * 2.
     * @param target
     * @param source
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static void copyMap2Bean(Map<String,Object> map , Object target) {
    	
    	Class targetClass = target.getClass();// 得到对象的Class
    	
    	Field[] targetFields = targetClass.getDeclaredFields();// 得到Class对象的所有属性
    	for (Map.Entry<String,Object> entry : map.entrySet()) {
    		
    		String name = entry.getKey();// 属性名
    		Object value = entry.getValue();
			for (Field targetField : targetFields) {
				Class targetType = targetField.getType();// 目标属性类型
				String targetName = targetField.getName();// 目标对象的属性名
				if (targetName.equals(name)) {
					try {
						String methodName = name.substring(0, 1).toUpperCase() + name.substring(1);
						Method setMethod = targetClass.getMethod("set" + methodName, targetType);// 属性对应的set方法
						setMethod.invoke(target, value);// 执行目标对象的set方法
						break;// 为了提高效率。
					} catch (Exception e) {
						continue;
					}
					
				}
			}
    	}
    }
    /**
     * 
     * @param map
     * @param target
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static void copyJson2Bean(JSONObject json , Object target) {
    	
    	Class targetClass = target.getClass();// 得到对象的Class
    	
    	Field[] targetFields = targetClass.getDeclaredFields();// 得到Class对象的所有属性
    	for (Map.Entry<String,Object> entry : json.entrySet()) {
    		
    		String name = entry.getKey();// 属性名
    		Object value = entry.getValue();
    		for (Field targetField : targetFields) {
    			Class targetType = targetField.getType();// 目标属性类型
    			String targetName = targetField.getName();// 目标对象的属性名
    			if (targetName.equals(name)) {
    				try {
    					String methodName = name.substring(0, 1).toUpperCase() + name.substring(1);
    					Method setMethod = targetClass.getMethod("set" + methodName, targetType);// 属性对应的set方法
    					setMethod.invoke(target, value);// 执行目标对象的set方法
    					break;// 为了提高效率。
    				} catch (Exception e) {
    					continue;
    				}
    				
    			}
    		}
    	}
    }
    
    /**
     * 把标准JavaBean 的对象放入map中
     * @param source
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static Map<String,Object> copyBeans2Map(Object source) {
    	Map<String,Object> resMap = new HashMap<String,Object>();
        Class sourceClass = source.getClass();// 得到对象的Class
        Field[] sourceFields = sourceClass.getDeclaredFields();// 得到Class对象的所有属性
        for (Field sourceField : sourceFields) {
            String name = sourceField.getName();// 属性名
            String methodName = name.substring(0, 1).toUpperCase() + name.substring(1);
            Object value = null;
            try {
                Method getMethod = sourceClass.getMethod("get" + methodName);// 得到属性对应get方法
                value = getMethod.invoke(source);// 执行源对象的get方法得到属性值
                resMap.put(name, value);
            } catch (Exception e) {
                continue;
            }
        }
        return resMap;
    }
    /**
     * 
     * @param request
     * @return
     */
    public static Map copyRequst2Map(HttpServletRequest request){
    	Map resMap = new HashMap<>();
        Enumeration<String> names = request.getParameterNames();  
        while(names.hasMoreElements()){  
        	String key = names.nextElement();
        	resMap.put(key, request.getParameter(key));  
        }  
    	return resMap;
    }
    
    
}
