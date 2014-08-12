/**
 * <p>Title: ReplyDTO.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: www.maxqueen.com</p>
 * @author Sola
 * @date 2013-9-29
 * @version 1.0
 */
package com.maxqueen.sola.parameter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * <p>
 * Title: ReplyDTO
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: www.maxqueen.com
 * </p>
 * 
 * @author Sola
 * @date 2013-9-29
 */
public class ReplyDTO {
	/*
	 * 请求结果码码（0表示返回成功，其它值分别表示不成功的不同含义）
	 * 1-10之间的错误码为系统错误。1表示系统未知错误，2表示WCF的通讯链路Fault,3表示WCF的通讯链路的异常错误
	 */
	@Expose
	@SerializedName("ResultCode")
	private int resultCode;
	/*
	 * 请求发起的时间
	 */
	@Expose
	@SerializedName("RequestTime")
	private String requestTime;
	/*
	 * 请求者的Id
	 */
	@Expose
	@SerializedName("RequesterID")
	private String requesterId;
	/*
	 * 访问授权码
	 */
	@Expose
	@SerializedName("token")
	private String token;
	/*
	 * 返回数据的基本信息
	 */
	@Expose
	@SerializedName("Msg")
	private String msg;

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public String getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}

	public String getRequesterId() {
		return requesterId;
	}

	public void setRequesterId(String requesterId) {
		this.requesterId = requesterId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return this.getMsg() + ":" + getRequestTime() + ":" + getRequesterId()
				+ ":" + getResultCode() + ":" + getToken();
	}
}
