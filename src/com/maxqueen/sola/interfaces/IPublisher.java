/**
 * <p>Title: IPublisher.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: www.maxqueen.com</p>
 * @author Sola
 * @date 2014-1-23
 * @version 1.0
 */
package com.maxqueen.sola.interfaces;

import android.os.Handler;

/**
 * <p>
 * Title: IPublisher
 * </p>
 * <p>
 * Description:发布者接口
 * </p>
 * <p>
 * Company: www.maxqueen.com
 * </p>
 * 
 * @author Sola
 * @date 2014-1-23
 */
public interface IPublisher {

	/**
	 * 内部处理过程通过{@link Handler}队列 检索{@link Publisher#_IList}中所有对象,并且主观调用
	 * {@link IListener#responseInteractiveData(int, Object)}方法,此方法类似广播
	 * 
	 * @param binaryValue
	 *            序列值
	 * @param obj
	 *            参数
	 */
	void submitDataInteraction(int binaryValue, Object obj);

	/**
	 * 内部处理过程通过{@link Handler}队列 检索{@link Publisher#_IList}中是否拥有key为{@value
	 * listenerId}的对象 如果有,会主观调用
	 * {@link IListener#responseInteractiveData(int, Object)}方法
	 * 
	 * @param binaryValue
	 *            序列值
	 * @param listenerId
	 *            监听者Id
	 * @param obj
	 *            参数
	 */
	void submitDataInteraction(int binaryValue, int listenerId, Object obj);

	/**
	 * 继承该接口的类必须并调用该方法,才能触发循环流,向发布者移除自身监听
	 * 
	 * @param _Listener
	 */
	public void RemovePosCallBack(IListener _Listener);

	/**
	 * 继承该接口的类必须并调用该方法,才能触发循环流,用于向发布者注册自己
	 * 
	 * @param _Listener
	 */
	public void RegistePosCallBack(IListener _Listener);
}
