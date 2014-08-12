/**
 * <p>Title: ListReplyNoTimeDTO.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: www.maxqueen.com</p>
 * @author Sola
 * @date 2014-2-13
 * @version 1.0
 */
package com.maxqueen.sola.parameter;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * <p>
 * Title: ListReplyNoTimeDTO
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
public class ListReplyNoTimeDTO<Target> extends ReplyDTO {

	/*
	 * �б�ʽ��������
	 */
	@Expose
	@SerializedName("DataList")
	protected List<Target> dataList;


	/**
	 * �ж�{@link #dataList}�Ƿ�Ϊ��
	 * 
	 * @return true ListΪ��,false ��Ϊ��
	 */
	public boolean checkList() {
		return dataList == null || dataList.size() == 0;
	}
	public List<Target> getDataList() {
		return dataList;
	}

	public void setDataList(List<Target> dataList) {
		this.dataList = dataList;
	}
}
