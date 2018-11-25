package com.hj.tj.gohome.json;

import com.hj.tj.gohome.utils.StringUtil;

import java.io.Serializable;


/**
 * 封装了请求中的分页信息.
 * <p/>
 */
public class Page implements Serializable {
	private static final long serialVersionUID = 4333019690903946141L;
	/**
	 * 当前页。
	 */
	private int page = 1;

	/**
	 * 每页显示记录数。
	 */
	private int size = 10;

	/**
	 * 排序列标识。
	 */
	private String order;

	public Page() {

	}

	public Page(String page, String size) {
		if (StringUtil.isNotBlank(page)) {
			this.page = new Integer(page);
		}
		if (StringUtil.isNotBlank(size)) {
			this.size = new Integer(size);
		}
	}

	public Page(int page, int size) {
		this.page = page;
		this.size = size;
	}
	
	@Override
	public String toString() {
		return "Page{" + "page=" + page + ", size=" + size + ", order='" + order + '\'' + '}';
	}
	
	public String getOrderByIdDesc() {
		return " id desc limit " + getOffset() + "," + getSize();
	}
	
	public String getOrderById(String fieldName) {
		return " id "+fieldName+" limit " + getOffset() + "," + getSize();
	}
	
	public String getOrderByFieldName(String fieldName) {
		return " "+fieldName+" limit " + getOffset() + "," + getSize();
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getOffset() {
		return (page - 1) * size;
	}
}
