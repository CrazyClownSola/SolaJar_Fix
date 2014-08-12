/**
 * <p>Title: StatisticsReplyNoTimeDTO.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: www.maxqueen.com</p>
 * @author Sola
 * @date 2014-2-13
 * @version 1.0
 */
package com.maxqueen.sola.parameter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * <p>
 * Title: StatisticsReplyNoTimeDTO
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: www.maxqueen.com
 * </p>
 * 
 * @author Sola
 * @date 2014-2-13
 */
public class StatisticsReplyNoTimeDTO<Target> extends ReplyDTO {

	/*
	 * 统计数据的数据内容
	 */
	@Expose
	@SerializedName("Data")
	protected Target data;

	public Target getData() {
		return data;
	}

	public void setData(Target data) {
		this.data = data;
	}

}
