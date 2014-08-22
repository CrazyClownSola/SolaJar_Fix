/**
 * <p>Title: AttributesParam.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: www.maxqueen.com</p>
 * @author Sola
 * @date 2014-8-22
 * @version 1.0
 */
package com.maxqueen.sola.parameter;

/**
 * <p>
 * Title: AttributesParam
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: www.maxqueen.com
 * </p>
 * 
 * @author Sola
 * @date 2014-8-22
 */
public class AttributesParam<T> {

	public AttributesParam(int key, T value) {
		_Key = key;
		_Value = value;
	}

	public int _Key;
	public T _Value;
}
