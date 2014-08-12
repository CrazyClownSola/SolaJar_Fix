/**
 * <p>Title: AHttpConnection.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: www.maxqueen.com</p>
 * @author Sola
 * @date 2013-8-24
 * @version 1.0
 */
package com.maxqueen.sola.selfabstract;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;

import com.maxqueen.sola.interfaces.IListener;
import com.maxqueen.sola.utils.AConnectionBuilder;

/**
 * <p>
 * Title: AHttpConnection
 * </p>
 * <p>
 * Description: ����Http���������ӵĻ��ࡣ
 * </p>
 * <h2>AHttpConnection's generic types</h2>
 * <p>
 * �������������͵Ĳ���������������Http���ӣ��������£�
 * </p>
 * <ol>
 * <li><code>Params</code>,����{@link InnerRunnable#_Param} ,����������������ȥ�����������</li>
 * <li><code>Result</code>,����Connect���Ӻ󣬶��ڻ�ȡ������ת���ɵĲ������ͣ����巽��
 * {@link #dealData(InputStream)},���ڲ�����{@link HttpConnect}ʹ����ͬ����</li>
 * <li><code>Target</code>,���øú������������,һ����Fragment,Activity��,�Ա���
 * {@link #dealResult(Object)}�����к͵����߽���ͨѶ</li>
 * </ol>
 * 
 * <pre>
 * private class MyConnect extends AHttpConnection&lt;Void,Void,Activity&lt;
 * </pre>
 * 
 * 
 * @author Sola
 * @date 2013-8-24
 */
public abstract class AHttpConnection<Params, Result, Target extends IListener>
		extends AConnection<Params, InputStream, Result, Target> {

	protected final HttpConnect mConnect;

	/**
	 * @param target
	 *            ���ú����������
	 * @param args
	 *            url���ʷ���������Ҫ����Ĳ���(�˹���Ĭ���ǲ���"/+����"����ʽ����)
	 */
	public AHttpConnection(WeakReference<Target> target, String... args) {
		this(target, null, null, args);
	}

	/**
	 * @param target
	 *            ���ú����������
	 * @param param
	 *            �������Ĵ������{@link Params}
	 * @param args
	 *            url���ʷ���������Ҫ����Ĳ���(�˹���Ĭ���ǲ���"/+����"����ʽ����)
	 */
	public AHttpConnection(WeakReference<Target> target, Params param,
			String... args) {
		this(target, null, param, args);
	}

	/**
	 * @param target
	 *            ���ú����������
	 * @param method
	 *            URL�еķ�������{@link #AConnectionBuilder}�Ĺ��췽��
	 * @param args
	 *            url���ʷ���������Ҫ����Ĳ���
	 */
	public AHttpConnection(WeakReference<Target> target, String method,
			String... args) {
		this(target, method, null, args);
	}

	/**
	 * @param target
	 *            ���ú����������
	 * @param method
	 *            URL�еķ�������{@link #AConnectionBuilder}�Ĺ��췽��
	 * @param param
	 *            �������Ĵ������{@link Params}
	 * @param args
	 *            url���ʷ���������Ҫ����Ĳ���
	 */
	public AHttpConnection(WeakReference<Target> target, String method,
			Params param, String... args) {
		super(target, param);
		mConnect = new HttpConnect(method, args);
	}

	/*
	 * <p>Title: run</p> <p>Description: </p>
	 */
	@Override
	public void run() {
		try {
			mConnect.connect();
		} catch (MalformedURLException e) {
			onExceptionExecute(e);
		} catch (IOException e) {
			onExceptionExecute(e);
		} finally {
			mConnect.disConnect();
		}
		try {
			Thread.sleep(0 * 1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		try {
			onPostExecute(mConnect.getRequest());
		} catch (Exception e) {
			onExceptionExecute(e);
		}
	}

	/**
	 * 
	 * <p>Title: HttpConnect</p>
	 * <p>Description: </p>
	 * <p>Company: www.maxqueen.com</p>
	 * @author Sola
	 * @date 2014-8-5
	 */
	class HttpConnect extends AConnectionBuilder<Result> {

		public HttpConnect(String... params) {
			super(params);
		}

		public HttpConnect(String method, String... params) {
			super(method, params);
		}

		@Override
		protected Result readBuffer(InputStream is) throws IOException {
			return doAnlyzing(is);
		}

		@Override
		public int buildBasicPort() {
			return buildPort();
		}

		@Override
		public String buildBasicService() {
			return bulidService();
		}

		@Override
		public String buildBasicIp() {
			return buildIp();
		}

		@Override
		public String buildBasicPath() {
			return buildPath();
		}

		@Override
		public String buildBasicMethod() {
			return null;
		}
	}
}
