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
 * Description: 通讯函数的基类
 * </p>
 * <h2>AConnection's generic types</h2>
 * <p>
 * 这里有四个类型的参数，来用作构建Connect链接，介绍如下：
 * </p>
 * <ol>
 * <li><code>Params</code>,存入{@link InnerRunnable#_Param} ,子类根据自身的需求去调用这个属性</li>
 * <li><code>Input</code>对于连接所得到的类型的定义</li>
 * <li><code>Result</code>,用于Connect连接后，对于获取的内容转换成的参数类型，具体方法
 * {@link #doAnlyzing(Object)}</li>
 * <li><code>Target</code>,调用该函数的类的类型,一般有Fragment,Activity等,以便在
 * {@link #onPostExecute(Object)} 方法中和调用者进行通讯</li>
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
	 * 构建访问的Ip地址
	 * 
	 * @return
	 */
	protected abstract String buildIp();

	/**
	 * 构建访问地址的端口
	 * 
	 * @return
	 */
	protected abstract int buildPort();

	/**
	 * 构建URL的服务字段
	 * 
	 * @return
	 */
	protected abstract String bulidService();

	/**
	 * 构建URL的路径字段，一般由IIS控制的路径
	 * 
	 * @return
	 */
	protected abstract String buildPath();

	/**
	 * 构建访问的接口方法
	 * 
	 * @return
	 */
	public abstract String buildMethod();

	/**
	 * 对网络获取到的json或者xml文件进行解析 解析方式由自己定义
	 * 
	 * @param request
	 * @param isConnectAvailable
	 * @return
	 */
	protected abstract Result doAnlyzing(Input request,
			boolean isConnectAvailable);

//	/**
//	 * 对网络获取到的json或者xml文件进行解析 解析方式由自己定义
//	 * 
//	 * @param request
//	 * @return
//	 */
//	protected abstract Result doAnlyzing(Input request);

	/**
	 * 对解析后的数据进行处理的函数
	 * 
	 * @param result
	 *            解析后的结果
	 */
	protected abstract void onPostExecute(Result result);

}
