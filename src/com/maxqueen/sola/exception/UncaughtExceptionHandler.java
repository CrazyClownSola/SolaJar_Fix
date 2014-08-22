/**
 * <p>Title: UncaughtExceptionHandler.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: www.maxqueen.com</p>
 * @author Sola
 * @date 2013-8-8
 * @version 1.0
 */
package com.maxqueen.sola.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

/**
 * <p>
 * Title: UncaughtExceptionHandler
 * </p>
 * <p>
 * Description:一个工具类，防程序Crash弹框的
 * </p>
 * <p>
 * Company: www.maxqueen.com
 * </p>
 * 
 * @author Sola
 * @date 2013-8-8
 */
public class UncaughtExceptionHandler implements
		java.lang.Thread.UncaughtExceptionHandler {

	private final Context mContext;

	public UncaughtExceptionHandler(Context context) {
		mContext = context;
	}

	/*
	 * <p>Title: uncaughtException</p> <p>Description: </p>
	 */
	@Override
	public void uncaughtException(Thread thread, Throwable exception) {
		StringWriter stackTrace = new StringWriter();
		exception.printStackTrace(new PrintWriter(stackTrace));
		Log.e("Sola", "Un Catch Exception  " + stackTrace);
		Intent intent = new Intent("android.fbreader.action.CRASH",
				new Uri.Builder().scheme(exception.getClass().getSimpleName())
						.build());
		try {
			mContext.startActivity(intent);
		} catch (ActivityNotFoundException e) {
			Log.e("Sola",
					"Activity not Found " + e.getMessage()
							+ e.getLocalizedMessage());
		}

		if (mContext instanceof Activity)
			((Activity) mContext).finish();

		// Process.killProcess(Process.);
		System.exit(10);
	}

}
