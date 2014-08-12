package com.maxqueen.sola.tools;

import java.math.BigDecimal;

import android.app.Activity;
import android.graphics.Rect;

/**
 * <p>
 * Title: RoundTool
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: www.maxqueen.com
 * </p>
 * 
 * @author Sola
 * @date 2013-9-24
 */
public final class RoundTool {

	/**
	 * ��double���ݽ���ȡ����.
	 * <p>
	 * For example: <br>
	 * double value = 100.345678; <br>
	 * double ret = round(value,4,BigDecimal.ROUND_HALF_UP); <br>
	 * retΪ100.3457 <br>
	 * 
	 * @param value
	 *            double����.
	 * @param scale
	 *            ����λ��(������С��λ��).
	 * @param roundingMode
	 *            ����ȡֵ��ʽ.
	 * @return ���ȼ���������.
	 */
	public static double round(double value, int scale, int roundingMode) {
		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(scale, roundingMode);
		double b = bd.doubleValue();
		bd = null;
		return b;
	}

	public static float round(double value, int scale) {
		return (float) round(value, scale, BigDecimal.ROUND_UP);
	}

	public static float round(float value, int scale) {
		return round(value, scale, BigDecimal.ROUND_UP);
	}

	public static float round(float value, int scale, int roundingMode) {
		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(scale, roundingMode);
		float f = bd.floatValue();
		bd = null;
		return f;
	}

	/**
	 * ������״̬���߶�
	 * 
	 * @param activity
	 * @return > 0 success; <= 0 fail
	 */
	public static int getStatusHeight(Activity activity) {
		int statusHeight = 0;
		Rect localRect = new Rect();
		activity.getWindow().getDecorView()
				.getWindowVisibleDisplayFrame(localRect);
		statusHeight = localRect.top;
		if (0 == statusHeight) {
			Class<?> localClass;
			try {
				localClass = Class.forName("com.android.internal.R$dimen");
				Object localObject = localClass.newInstance();
				int i5 = Integer.parseInt(localClass
						.getField("status_bar_height").get(localObject)
						.toString());
				statusHeight = activity.getResources()
						.getDimensionPixelSize(i5);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			}
		}
		return statusHeight;
	}
	// public static int ratioCompute(float cod){
	//
	// return 0;
	// }
	// private static int

}
