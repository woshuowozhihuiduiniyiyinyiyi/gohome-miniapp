package com.hj.tj.gohome.json;

import java.util.Map;

/**
 * 用于解析客户端请求json转换的类。
 * <p/>
 * <br>
 * =
 */
public class JsonRequest implements Cloneable {
	public static final String key = "jsonRequest";

	// 数据
	Object data;
	// data选择的加密方式,MD5,RSA,AES
	String encrypt = "md5";
	// 如果选择了安全方式是md5则对data数据的md5签名，否则是base64Encode(加密算法(data))后的md5签名
	String sign = "";
	String sid = "";

	private Page page;

	/**
	 * 取得请求参数，以数字返回，即 data 节点下的属性值。
	 * 
	 * @param name
	 *            属性值
	 * @return 如果找不到的话，返回 null
	 */
	public Integer getParamAsInteger(String name) {
		String val = getParamAsString(name);
		if (val == null) {
			return null;
		}
		Integer newVal = null;
		try {
			newVal = Integer.valueOf(val);
		} catch (NumberFormatException e) {
			return null;
		}
		return newVal;
	}

	public Long getParamAsLong(String name) {
		String val = getParamAsString(name);
		if (val == null) {
			return null;
		}
		Long newVal = null;
		try {
			newVal = Long.valueOf(val);
		} catch (NumberFormatException e) {
			return null;
		}
		return newVal;
	}

	public Float getParamAsFloat(String name) {
		String val = getParamAsString(name);
		if (val == null) {
			return null;
		}
		Float newVal = null;
		try {
			newVal = Float.valueOf(val);
		} catch (NumberFormatException e) {
			return null;
		}
		return newVal;
	}

	public Double getParamAsDouble(String name) {
		String val = getParamAsString(name);
		if (val == null) {
			return null;
		}
		Double newVal = null;
		try {
			newVal = Double.valueOf(val);
		} catch (NumberFormatException e) {
			return null;
		}
		return newVal;
	}

	public Integer getParamAsInteger(String name, int defaultValue) {
		Integer v = getParamAsInteger(name);
		if (v != null) {
			return v;
		}
		return defaultValue;
	}

	/**
	 * 取得请求参数，以字符串返回，即 data 节点下的属性值。
	 * 
	 * @param name
	 *            属性值
	 * @return 如果找不到的话，返回 null
	 */
	public String getParamAsString(String name) {
		Object val = getParam(name);
		if (val == null) {
			return null;
		}
		return val.toString();
	}

	/**
	 * 取得请求参数，即 data 节点下的属性值。
	 * 
	 * @param name
	 *            属性值
	 * @return 如果找不到的话，返回 null
	 */
	public Object getParam(String name) {
		if (data == null || name == null) {
			return null;
		}
		if (data instanceof Map) {
			Map params = (Map) data;
			return params.get(name);
		}
		return null;
	}

	/**
	 * 标识请求是否是一个 K1 的请求。
	 */
	private boolean k1;

	public boolean isK1() {
		return k1;
	}

	public void setK1(boolean k1) {
		this.k1 = k1;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getEncrypt() {
		return encrypt;
	}

	public void setEncrypt(String encrypt) {
		this.encrypt = encrypt;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

}
