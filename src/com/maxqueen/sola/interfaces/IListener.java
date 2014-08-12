/**
 * <p>Title: IListener.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: www.maxqueen.com</p>
 * @author Sola
 * @date 2013-10-11
 * @version 1.0
 */
package com.maxqueen.sola.interfaces;

/**
 * <p>
 * Title: IListener
 * </p>
 * <p>
 * Description:监听者接口,建议由监听方去继承
 * </p>
 * <p>
 * Company: www.maxqueen.com
 * </p>
 * 
 * @author Sola
 * @date 2013-10-11
 */
public interface IListener extends IRunning {

	/**
	 * 用户可根据自身需求自定义实现,建议根据监听者所继承的接口去对应处理函数,一般不推荐主观调用该方法
	 * 注意:若主观调用该方法的时候,请务必注意线程安全问题
	 * 
	 * @param binaryValue
	 *            序列值
	 * @param obj
	 *            参数
	 */
	void responseInteractiveData(int binaryValue, Object obj);

	/**
	 * 返回调用者序列值,可以根据具体情况设计
	 * 
	 * @return
	 */
	int getListenerId();
}
