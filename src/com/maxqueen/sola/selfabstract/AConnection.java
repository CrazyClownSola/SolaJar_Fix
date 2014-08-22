/**
 * <p>Title: AConnection.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: www.maxqueen.com</p>
 * @author Sola
 * @date 2013-9-29
 * @version 1.0
 */
package com.maxqueen.sola.selfabstract;

import java.lang.ref.WeakReference;

import com.maxqueen.sola.interfaces.IListener;

/**
 * <p>
 * Title: AConnection
 * </p>
 * <p>
 * Description: ͨѶ�����Ļ���
 * </p>
 * <h2>AConnection's generic types</h2>
 * <p>
 * �������ĸ����͵Ĳ���������������Connect���ӣ��������£�
 * </p>
 * <ol>
 * <li><code>Params</code>,����{@link InnerRunnable#_Param} ,����������������ȥ�����������</li>
 * <li><code>Input</code>�����������õ������͵Ķ���</li>
 * <li><code>Result</code>,����Connect���Ӻ󣬶��ڻ�ȡ������ת���ɵĲ������ͣ����巽��
 * {@link #doAnlyzing(Object)}</li>
 * <li><code>Target</code>,���øú������������,һ����Fragment,Activity��,�Ա���
 * {@link #onPostExecute(Object)} �����к͵����߽���ͨѶ</li>
 * </ol>
 * 
 * <pre>
 * private class MyConnect extends AConnection&lt;Void,String,Void,Activity&lt;
 * </pre>
 * 
 * @author Sola
 * @date 2013-9-29
 */
public abstract class AConnection<Params, Input, Result, Target extends IListener>
		extends InnerRunnable<Params, Target> {

	/**
	 * @param target
	 * @param param
	 */
	public AConnection(WeakReference<Target> target, Params param) {
		super(target, param);
	}

	/**
	 * 
	 * @param e
	 */
	protected abstract void onExceptionExecute(Exception e);

	/**
	 * �������ʵ�Ip��ַ
	 * 
	 * @return
	 */
	protected abstract String buildIp();

	/**
	 * �������ʵ�ַ�Ķ˿�
	 * 
	 * @return
	 */
	protected abstract int buildPort();

	/**
	 * ����URL�ķ����ֶ�
	 * 
	 * @return
	 */
	protected abstract String bulidService();

	/**
	 * ����URL��·���ֶΣ�һ����IIS���Ƶ�·��
	 * 
	 * @return
	 */
	protected abstract String buildPath();

	/**
	 * �������ʵĽӿڷ���
	 * 
	 * @return
	 */
	public abstract String buildMethod();

	/**
	 * �������ȡ����json����xml�ļ����н��� ������ʽ���Լ�����
	 * 
	 * @param request
	 * @param isConnectAvailable
	 * @return
	 */
	protected abstract Result doAnlyzing(Input request,
			boolean isConnectAvailable);

//	/**
//	 * �������ȡ����json����xml�ļ����н��� ������ʽ���Լ�����
//	 * 
//	 * @param request
//	 * @return
//	 */
//	protected abstract Result doAnlyzing(Input request);

	/**
	 * �Խ���������ݽ��д���ĺ���
	 * 
	 * @param result
	 *            ������Ľ��
	 */
	protected abstract void onPostExecute(Result result);

}
