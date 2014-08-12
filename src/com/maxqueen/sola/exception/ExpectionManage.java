/**
 * <p>Title: ExpectionManage.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: www.maxqueen.com</p>
 * @author Sola
 * @date 2014-2-21
 * @version 1.0
 */
package com.maxqueen.sola.exception;

import com.maxqueen.sola.interfaces.IHandler;
import com.maxqueen.sola.interfaces.IPublisher;
import com.maxqueen.sola.interfaces.IS_DEBUG;

import android.util.Log;

/**
 * <p>
 * Title: ExpectionManage
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: www.maxqueen.com
 * </p>
 * 
 * @author Sola
 * @date 2014-2-21
 */
public class ExpectionManage implements IS_DEBUG {

	public ExpectionManage() {

	}

	public static void invokeException(Exception exp, IPublisher target) {
		if (exp != null) {
			Log.e(Error, "Something Error " + exp.toString());
			target.submitDataInteraction(IHandler.Something_Crash, exp);
			exp.printStackTrace();
		} else {
			target.submitDataInteraction(IHandler.Something_Crash, null);
			throw new NullPointerException(
					"What do you want to do? input nothing into Exception invoke method");
		}
	}
}
