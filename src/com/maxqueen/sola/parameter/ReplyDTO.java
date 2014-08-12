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
	 * ���������루0��ʾ���سɹ�������ֵ�ֱ��ʾ���ɹ��Ĳ�ͬ���壩
	 * 1-10֮��Ĵ�����Ϊϵͳ����1��ʾϵͳδ֪����2��ʾWCF��ͨѶ��·Fault,3��ʾWCF��ͨѶ��·���쳣����
	 */
	@Expose
	@SerializedName("ResultCode")
	private int resultCode;
	/*
	 * �������ʱ��
	 */
	@Expose
	@SerializedName("RequestTime")
	private String requestTime;
	/*
	 * �����ߵ�Id
	 */
	@Expose
	@SerializedName("RequesterID")
	private String requesterId;
	/*
	 * ������Ȩ��
	 */
	@Expose
	@SerializedName("token")
	private String token;
	/*
	 * �������ݵĻ�����Ϣ
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
