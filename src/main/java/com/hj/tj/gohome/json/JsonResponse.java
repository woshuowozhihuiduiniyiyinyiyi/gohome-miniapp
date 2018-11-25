package com.hj.tj.gohome.json;

import com.hj.tj.gohome.enums.ErrorMsgEnum;

/**
 * 服务器端响应json转换类。
 * <p/>
 */
public class JsonResponse implements java.io.Serializable, Cloneable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Integer SUCCESS_CODE = 2000;
	private static String SUCCESS_MSG = "success";	
	
	private static Integer ERROR_CODE = 5000;
	private static String ERROR_MSG = "内部异常";

	private static Integer DEVICE_BLOCK = 5001;

	private static Integer REG_LIMIT = 9000;

	private static Integer INVITE_CODE_ERROR = 5101;

	private static Integer SID_EXPIRED_ERROR = 5102;

	public static final String key = "jsonResponse";
	//客户端请求id
	long id = 0;
	//服务器响应状态
	State state;
	//数据
	Object data;


	/**
	 * 分页信息。
	 */
	Pagination page;
	
	Long time;

	public JsonResponse() {
		super();
		state = new State();
		data = null;
	}

	public static class State
	{
		private Integer code ;
		private String msg;

		public static State newOk() {
			State state = new State();
			state.setCode(SUCCESS_CODE);
			state.setMsg(SUCCESS_MSG);
			return state;
		}

		public static State newError() {
			State state = new State();
			state.setCode(ERROR_CODE);
			state.setMsg(ERROR_MSG);
			return state;
		}

		public static State DeviceBlock(String msg){
			State state = new State();
			state.setCode(DEVICE_BLOCK);
			state.setMsg(msg);
			return state;
		}

		public static State regLimit(String msg){
			State state = new State();
			state.setCode(REG_LIMIT);
			state.setMsg(msg);
			return state;
		}

		public static State inviteCodeError(String msg){
			State state = new State();
			state.setCode(INVITE_CODE_ERROR);
			state.setMsg(msg);
			return state;
		}

		public static State sidExpiredError(String msg){
			State state = new State();
			state.setCode(SID_EXPIRED_ERROR);
			state.setMsg(msg);
			return state;
		}

		public Integer getCode() {
			return code;
		}

		public void setCode(Integer code) {
			this.code = code;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

		
	}
	
	/**
	 * 实例一个成功的响应，状态码为：2000000（具体以字典为准），状态描述使用字典中的定义。
	 *
	 * @return 响应实例
	 */
	public static JsonResponse newOk() {
		JsonResponse response = new JsonResponse();
		response.setState(State.newOk());
		return response;
	}

	/**
	 * 实例一个包括数据的成功响应，状态码为：2000000（具体以字典为准），状态描述使用字典中的定义。
	 *
	 * @param data 数据
	 * @return 响应实例
	 */
	public static JsonResponse newOk(Object data) {
		JsonResponse response = new JsonResponse();
		response.setData(data);
		response.setState(State.newOk());
		return response;
	}


	/**
	 * 实例化一个自定义状态信息的响应，状态描述使用字典中的定义。
	 *
	 * @param data      数据
	 * @param state 自定义的状态信息
	 * @return 响应实例
	 */
	public static JsonResponse newInstance(State state, Object data) {
		JsonResponse response = new JsonResponse();
		response.setData(data);
		response.setState(state);
		return response;
	}


	/**
	 * 使用自定义的状态信息，实例化一个错误的响应，响应信息对应字典中的信息。
	 *
	 * @return 响应实例
	 */
	public static JsonResponse newError() {
		JsonResponse response = new JsonResponse();
		response.setState(State.newError());
		return response;
	}
	
	public static JsonResponse newError(Integer code, String msg) {
		JsonResponse response = new JsonResponse();
		State state = new State();
		state.setCode(code);
		state.setMsg(msg);
		response.setState(state);
		return response;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Pagination createPage() {
		page = new Pagination();
		return page;
	}

	public Pagination getPage() {
		return page;
	}

	public void setPage(Pagination page) {
		this.page = page;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public static JsonResponse newError(String msg) {
		JsonResponse response = new JsonResponse();
		State state = new State();
		state.setCode(ERROR_CODE);
		state.setMsg(msg);
		response.setState(state);
		return response;
	}

	public static JsonResponse newOk(Pagination page, Object date) {
		JsonResponse response = new JsonResponse();
		response.setState(State.newOk());
		response.setPage(page);
		response.setData(date);
		return response;
	}

	public static JsonResponse deviceBlock(String msg){
		JsonResponse response = new JsonResponse();
		response.setState(State.DeviceBlock(msg));
		return response;
	}

	public static JsonResponse regLimit(String msg){
		JsonResponse response = new JsonResponse();
		response.setState(State.regLimit(msg));
		return response;
	}
	
	public static JsonResponse newError(ErrorMsgEnum errorMsgEnum) {
		JsonResponse response = new JsonResponse();
		State state = new State();
		state.setCode(errorMsgEnum.getCode());
		state.setMsg(errorMsgEnum.getUserMsg());
		response.setState(state);
		return response;
	}

	public static JsonResponse InviteCodeError(String msg){
		JsonResponse response = new JsonResponse();
		response.setState(State.inviteCodeError(msg));
		return response;
	}

	public static JsonResponse sidExpiredError(String msg){
		JsonResponse response = new JsonResponse();
		response.setState(State.sidExpiredError(msg));
		return response;
	}
	
}
