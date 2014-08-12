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
 * Description:�Ǽ�ʽ������,�����ڼ̳й�ϵ
 * </p>
 * <p>
 * Company: www.maxqueen.com
 * </p>
 * 
 * @author Sola
 * @date 2014-2-18
 */
public class RegSingleton {
	/** �����е����ʽ������ **/
	private static HashMap<String, Object> m_Instances = new HashMap<String, Object>();

	static {
		RegSingleton c = new RegSingleton();
		m_Instances.put(c.getClass().getName(), c);
	}

	protected RegSingleton() {
	}

	/**
	 * ���ػ�������� һ�㲻�Ƽ�ʹ�ø÷���
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
	 * ʹ�ø÷�����ȡʵ��ʱ��ע��,��һ�ε��ø÷���ʱ���ص�ʵ�����ɷ���{@link Class}
	 * ���ɵ�ʵ��,��ʱ��ȷ��������Ĺ��췽�����л�,������׳��쳣 {@link IllegalAccessException}
	 * 
	 * @param name
	 * @return {@code HashMap<String, Object>}��KeyֵΪ @param name��ʵ��
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
