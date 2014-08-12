/**
 * <p>Title: IDEBUG.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: www.maxqueen.com</p>
 * @author Sola
 * @date 2013-8-14
 * @version 1.0
 */
package com.maxqueen.sola.interfaces;

/**
 * <p>
 * Title: IS_DEBUG
 * </p>
 * <p>
 * Description: 用作Debug调试信息的接口,本库类中基本都继承于此
 * </p>
 * <p>
 * Company: www.maxqueen.com
 * </p>
 * 
 * @author Sola
 * @date 2013-8-14
 */
public interface IS_DEBUG {

	/**
	 * 用于DeBug调试的
	 * 
	 * @hide
	 */
	boolean S_DEBUG = true;

	String Error = "Error";
	String Tag = "Sola";
	String Pro = "Progress";
	String Lis = "Listener";
}
