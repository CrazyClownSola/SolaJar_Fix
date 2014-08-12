/**
 * <p>Title: IRunning.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: www.maxqueen.com</p>
 * @author Sola
 * @date 2013-8-6
 * @version 1.0
 */
package com.maxqueen.sola.interfaces;

/**
 * <p>
 * Title: IRunning
 * </p>
 * <p>
 * Description: 判断自身是否活着的接口方法
 * </p>
 * <p>
 * Company: www.maxqueen.com
 * </p>
 * 
 * @author Sola
 * @date 2013-8-6
 */
public interface IRunning extends IS_DEBUG {

	/**
	 * 检测该接口的实现是否活着 这个方法是否有存在的价值还需要再考虑考虑
	 * 
	 * @return 根据不同的实例进行不同的返回
	 */
	public boolean isCallBackAvailable();
}
