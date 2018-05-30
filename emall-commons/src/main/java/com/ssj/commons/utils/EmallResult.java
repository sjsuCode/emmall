package com.ssj.commons.utils;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * 添加商品是否成功的状态类信息
 */
public class EmallResult implements Serializable{

	// 定义jackson对象
	private static final ObjectMapper MAPPER = new ObjectMapper();

	// 响应业务的状态
	private Integer status;

	// 响应信息
	private String msg;

	// 响应中的数据
	private Object data;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public EmallResult(Integer status, String msg, Object data) {
		super();
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	public EmallResult(Object data) {
		this.status = 200;
		this.msg = "OK";
		this.data = data;
	}

	public EmallResult() {
	}

	public static EmallResult build(Integer status, String msg, Object data) {
		return new EmallResult(status, msg, data);
	}

	public static EmallResult build(Integer status, String msg) {
		return new EmallResult(status, msg, null);
	}

	public static EmallResult ok() {
		return new EmallResult(null);
	}

	public static EmallResult ok(Object data) {
		return new EmallResult(data);
	}

	// 将json结果集转换成EmallResult对象
	public static EmallResult formatJsonToEmallResult(String jsonData, Class<?> clazz) {
		try {
			if (clazz == null) {
				return MAPPER.readValue(jsonData, EmallResult.class);
			}
			JsonNode jsonNode = MAPPER.readTree(jsonData);
			JsonNode data = jsonNode.get("data");
			Object obj = null;
			if (clazz != null) {
				if (data.isObject()) {
					obj = MAPPER.readValue(data.traverse(), clazz);
				} else if (data.isTextual()) {
					obj = MAPPER.readValue(data.asText(), clazz);
				}
			}
			return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
		} catch (Exception e) {
			return null;
		}
	}
	
	// 将json结果集转换成EmallResult对象
		public static EmallResult formatJsonToEmallResult(String jsonData) {
			try {
				return MAPPER.readValue(jsonData, EmallResult.class);
			} catch (Exception e) {
				return null;
			}
		}
		
		  public static EmallResult formatToList(String jsonData, Class<?> clazz) {
		        try {
		            JsonNode jsonNode = MAPPER.readTree(jsonData);
		            JsonNode data = jsonNode.get("data");
		            Object obj = null;
		            if (data.isArray() && data.size() > 0) {
		                obj = MAPPER.readValue(data.traverse(),
		                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
		            }
		            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
		        } catch (Exception e) {
		            return null;
		        }
		    }
}
