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
 * Description: 用于Http的网络连接的基类。
 * </p>
 * <h2>AHttpConnection's generic types</h2>
 * <p>
 * 这里有三个类型的参数，来用作构建Http链接，介绍如下：
 * </p>
 * <ol>
 * <li><code>Params</code>,存入{@link InnerRunnable#_Param} ,子类根据自身的需求去调用这个属性</li>
 * <li><code>Result</code>,用于Connect连接后，对于获取的内容转换成的参数类型，具体方法
 * {@link #dealData(InputStream)},和内部函数{@link HttpConnect}使用相同参数</li>
 * <li><code>Target</code>,调用该函数的类的类型,一般有Fragment,Activity等,以便在
 * {@link #dealResult(Object)}方法中和调用者进行通讯</li>
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
	 *            调用函数类的类型
	 * @param args
	 *            url访问方法中所需要传入的参数(此构造默认是采用"/+参数"的形式构建)
	 */
	public AHttpConnection(WeakReference<Target> target, String... args) {
		this(target, null, null, args);
	}

	/**
	 * @param target
	 *            调用函数类的类型
	 * @param param
	 *            这个子类的传入参数{@link Params}
	 * @param args
	 *            url访问方法中所需要传入的参数(此构造默认是采用"/+参数"的形式构建)
	 */
	public AHttpConnection(WeakReference<Target> target, Params param,
			String... args) {
		this(target, null, param, args);
	}

	/**
	 * @param target
	 *            调用函数类的类型
	 * @param method
	 *            URL中的方法名称{@link #AConnectionBuilder}的构造方法
	 * @param args
	 *            url访问方法中所需要传入的参数
	 */
	public AHttpConnection(WeakReference<Target> target, String method,
			String... args) {
		this(target, method, null, args);
	}

	/**
	 * @param target
	 *            调用函数类的类型
	 * @param method
	 *            URL中的方法名称{@link #AConnectionBuilder}的构造方法
	 * @param param
	 *            这个子类的传入参数{@link Params}
	 * @param args
	 *            url访问方法中所需要传入的参数
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
