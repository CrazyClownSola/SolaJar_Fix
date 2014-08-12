/**
 * <p>Title: MResources.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: www.maxqueen.com</p>
 * @author Sola
 * @date 2013-9-16
 * @version 1.0
 */
package com.maxqueen.sola.tools;

import android.content.Context;

/**
 * <p>
 * Title: MResources
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: www.maxqueen.com
 * </p>
 * 
 * @author Sola
 * @date 2013-9-16
 */
public class MResources {

	public static int getIdByName(final Context context, String className,
			String name) {
		String packageName = context.getPackageName();
		Class<?> _Class = null;
		int id = 0;
		try {
			_Class = Class.forName(packageName + ".R");
			Class<?>[] _Classes = _Class.getClasses();
			Class<?> desireClass = null;
			final int length = _Classes.length;
			for (int i = 0; i < length; i++) {
				if (_Classes[i].getName().split("\\$")[1].equals(className)) {
					desireClass = _Classes[i];
					break;
				}
			}
			// desireClass.
			if (desireClass != null)
				id = desireClass.getField(name).getInt(desireClass);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}

}
