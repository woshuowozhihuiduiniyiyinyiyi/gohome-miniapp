package com.hj.tj.gohome.json;

import java.io.Serializable;

/**
 * 封装了单一请求和组合请求共有的属性.
 * <p/>
 */
public class ApiRequest extends JsonRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	// 请求id
    public long id;

    // 客户端公共参数
    public Client client;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
