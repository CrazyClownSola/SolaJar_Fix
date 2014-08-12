/**
 * <p>Title: RegSingleton.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: www.maxqueen.com</p>
 * @author Sola
 * @date 2014-2-18
 * @version 1.0
 */
package com.maxqueen.sola.tools;

import java.util.HashMap;

/**
 * <p>
 * Title: RegSingleton
 * </p>
 * <p>
 * Description:登记式单例类,可用于继承关系
 * </p>
 * <p>
 * Company: www.maxqueen.com
 * </p>
 * 
 * @author Sola
 * @date 2014-2-18
 */
public class RegSingleton {
	/** 这里有点饿汉式的做法 **/
	private static HashMap<String, Object> m_Instances = new HashMap<String, Object>();

	static {
		RegSingleton c = new RegSingleton();
		m_Instances.put(c.getClass().getName(), c);
	}

	protected RegSingleton() {
	}

	/**
	 * 返回基类的事例 一般不推荐使用该方法
	 * 
	 * @return
	 */
	protected static RegSingleton getInstance() {
		return getInstance(null);
	}

	protected static void replaceInstance(String name, RegSingleton instance) {
		if (name == null || name.isEmpty())
			throw new NullPointerException();
		else
			try {
				m_Instances.put(name, instance == null ? Class.forName(name)
						.newInstance() : instance);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

	}

	public static boolean containsKey(String name) {
		return m_Instances.containsKey(name);
	}

	/**
	 * 使用该方法获取实例时需注意,第一次调用该方法时返回的实例是由反射{@link Class}
	 * 生成的实例,此时需确保该子类的构造方法共有化,否则会抛出异常 {@link IllegalAccessException}
	 * 
	 * @param name
	 * @return {@code HashMap<String, Object>}中Key值为 @param name的实例
	 */
	public static RegSingleton getInstance(String name) {
		return getInstance(name, null);
	}

	public static RegSingleton getInstance(String name, RegSingleton instance) {
		if (name == null || name.isEmpty())
			name = RegSingleton.class.getName();
		if (m_Instances.get(name) == null) {
			try {
				m_Instances.put(name, instance == null ? Class.forName(name)
						.newInstance() : instance);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return m_Instances.get(name) == null ? null
				: (RegSingleton) m_Instances.get(name);
	}

}
