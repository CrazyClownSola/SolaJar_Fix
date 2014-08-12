/**
 * <p>Title: StatisticsReplyDTO.java</p>
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
 * Title: StatisticsReplyDTO
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
public class StatisticsReplyDTO<Target> extends StatisticsReplyNoTimeDTO<Target> {
	/*
	 * ������б�ʽ���ݵ����ݲ�ѯ��ʼʱ��
	 */
	@Expose
	@SerializedName("TimeFrom")
	private String timeFrom;
	/*
	 * ������б�ʽ���ݵ������ѯ����ʱ��
	 */
	@Expose
	@SerializedName("TimeTo")
	private String timeTo;

	public String getTimeFrom() {
		return timeFrom;
	}

	public void setTimeFrom(String timeFrom) {
		this.timeFrom = timeFrom;
	}

	public String getTimeTo() {
		return timeTo;
	}

	public void setTimeTo(String timeTo) {
		this.timeTo = timeTo;
	}

	@Override
	public String toString() {
		return this.timeFrom + ":" + this.timeTo + ":" + this.getData();
	}

}
