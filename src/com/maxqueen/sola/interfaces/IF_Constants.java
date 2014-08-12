/**
 * <p>Title: IF_Constants.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: www.maxqueen.com</p>
 * @author Sola
 * @date 2013-8-7
 * @version 1.0
 */
package com.maxqueen.sola.interfaces;

import android.os.Environment;

/**
 * <p>
 * Title: IF_Constants
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: www.maxqueen.com
 * </p>
 * 
 * @author Sola
 * @date 2013-8-7
 */
public interface IF_Constants {

	int SPACE_TIME = 0 * 1000;
	int POLLING_TIME = 30 * 1000;
	int TIME_TIME = 60 * 1000;
	int AUTO_TIME = 20 * 1000;

//	int MaxCount = 20;

	String sdPath = Environment.getExternalStorageDirectory().getPath();
}
