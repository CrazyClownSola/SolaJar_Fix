/**
 * <p>Title: AWcfConnection.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: www.maxqueen.com</p>
 * @author Sola
 * @date 2013-8-24
 * @version 1.0
 */
package com.maxqueen.sola.selfabstract;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Type;

import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.maxqueen.sola.interfaces.IListener;
import com.maxqueen.sola.utils.AWcfConnectionBuilder;

/**
 * <p>
 * Title: AHttpConnection
 * </p>
 * <p>
 * Description: 用于与wcf通讯的基类。
 * </p>
 * <h2>AWcfConnection's generic types</h2>
 * <p>
 * 这里有三个类型的参数，来用作构建Http链接，介绍如下：
 * </p>
 * <ol>
 * <li><code>Params</code>,存入{@link InnerRunnable#_Param} ,子类根据自身的需求去调用这个属性</li>
 * <li><code>Result</code>,用于Connect连接后，对于获取的内容转换成的参数类型，具体方法
 * {@link #doAnlyzing(String)}</li>
 * <li><code>Target</code>,调用该函数的类的类型,一般有Fragment,Activity等,以便在
 * {@link #onPostExecute(Object)} 方法中和调用者进行通讯</li>
 * </ol>
 * 
 * <pre>
 * private class MyConnect extends AHttpConnection&lt;Void,Void,Activity&lt;
 * </pre>
 * 
 * @author Sola
 * @date 2013-9-5
 */
public abstract class AWcfConnection<Params, Results, Target extends IListener>
		extends AConnection<Params, String, Results, Target> {

	protected WcfConnect mConnect;

	/**
	 * @param target
	 *            调用函数类的类型
	 * @param args
	 *            url访问方法中所需要传入的参数(此构造默认是采用"/+参数"的形式构建)
	 */
	protected AWcfConnection(WeakReference<Target> target, String... args) {
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
	protected AWcfConnection(WeakReference<Target> target, Params param,
			String... args) {
		this(target, null, param, args);
	}

	/**
	 * @param target
	 *            调用函数类的类型
	 * @param method
	 *            URL中的方法名称{@link #AWcfConnectionBuilder}的构造方法
	 * @param args
	 *            url访问方法中所需要传入的参数(此方法是根据传入的method来将不同的参数填入)
	 */
	protected AWcfConnection(WeakReference<Target> target, String method,
			String... args) {
		this(target, method, null, args);
	}

	/**
	 * @param target
	 *            调用函数类的类型
	 * @param method
	 *            URL中的方法名称{@link #AWcfConnectionBuilder}的构造方法
	 * @param param
	 *            这个子类的传入参数{@link Params}
	 * @param args
	 *            url访问方法中所需要传入的参数
	 */
	protected AWcfConnection(WeakReference<Target> target, String method,
			Params param, String... args) {
		super(target, param);
		mConnect = new WcfConnect(method, args);
	}

	@Override
	public void run() {
		boolean isConnect = false;
		try {
			mConnect.connect();
			isConnect = true;
		} catch (ParseException e) {
			onExceptionExecute(e);
			// ExpectionManage.invokeException(e, this);
		} catch (ClientProtocolException e) {
			onExceptionExecute(e);
			// ExpectionManage.invokeException(e, this);
		} catch (IOException e) {
			onExceptionExecute(e);
			// ExpectionManage.invokeException(e, this);
		}
		onPostExecute(doAnlyzing(mConnect.getRequest(), isConnect));
	}

	@Override
	protected Results doAnlyzing(String request) {
		return null;
	}

	protected void ResetConnect(String method, String... args) {
		mConnect = new WcfConnect(method, args);
	}

	/**
	 * 公有的 对json格式的数据进行解析的函数，内部实现是通过{@link Gson}类来实现
	 * 请慎用该方法，使用该方法的时候请注意，传入的对象类型中的属性必须和json文件中的参数名称相同
	 * 
	 * @param result
	 *            和调用类时设定的Results 相同的参数,调用该方法时传入该对象的实例
	 * @param request
	 *            网络连接得到的json格式的参数
	 * @param isTextAvailable
	 *            所传入的json是否需要进行特殊处理,true 默认有对特殊操作的实现
	 *            {@link #spritResultTitle(String)}可重构该方法,false 直接解析传入的参数
	 *            request
	 * @return
	 * @throws JsonSyntaxException
	 * @throws IllegalArgumentException
	 */
	public Results doJsonAnlysis(Results result, String request,
			boolean isTextAvailable) throws JsonSyntaxException,
			IllegalArgumentException {
		return doJsonAnlysis(result.getClass(), request, isTextAvailable);
	}

	/**
	 * 公有的 对json格式的数据进行解析的函数，内部实现是通过{@link Gson}类来实现
	 * 请慎用该方法，使用该方法的时候请注意，传入的对象类型中的属性必须和json文件中的参数名称相同
	 * 
	 * @param resultType
	 *            返回参数的参数类型
	 * @param request
	 *            网络连接得到的json格式的参数
	 * @param isTextAvailable
	 *            所传入的json是否需要进行特殊处理,true 默认有对特殊操作的实现
	 *            {@link #spritResultTitle(String)}可重构该方法,false 直接解析传入的参数
	 *            request
	 * @return
	 * @throws JsonSyntaxException
	 * @throws IllegalArgumentException
	 */
	public Results doJsonAnlysis(Type resultType, String request,
			boolean isTextAvailable) throws JsonSyntaxException,
			IllegalArgumentException {
		if (!checkFormat(request))
			throw new IllegalArgumentException(
					"The members does not conform the rule of json " + request);
		Gson gson = new Gson();
		return gson.fromJson(isTextAvailable ? spritResultTitle(request)
				: request, resultType);
	}

	/**
	 * 方法未实现 暂且没有找到合适的符合Json格式的正则法则
	 * 
	 * @param request
	 * @return
	 */
	protected boolean checkFormat(String request) {
		int htmlIndex = request == null || request.isEmpty() ? 0 : request
				.indexOf("</html>") == -1 ? request.indexOf("</Fault>")
				: request.indexOf("</html>");
		return htmlIndex == -1;
	}

	/**
	 * 用于去除json文档中的前缀多余字符串
	 * 
	 * @param request
	 * @return
	 */
	public String spritResultTitle(String request) {
		String retStr = null;
		String title = null;
		String[] strs = null;
		StringBuffer rule = null;
		if (mConnect != null) {
			title = mConnect.getMethodTitle();
			if (title != null && !title.isEmpty())
				rule = new StringBuffer(title).append("Result").append("\":");
			// request.split(regularExpression)
			if (request != null && request.length() > 2) {
				if (rule != null)
					strs = request.split(rule.toString());
				if (strs != null && strs.length == 2) {
					String detail = strs[1];
					if (detail != null && detail.length() > 0)
						retStr = detail.substring(0, detail.length() - 1);
				}
			}
		}
		return retStr == null ? request : retStr;
	}

	class WcfConnect extends AWcfConnectionBuilder {

		/**
		 * @param params
		 */
		public WcfConnect(String method, String... params) {
			super(method, params);
		}

		/*
		 * <p>Title: getBasicPort</p> <p>Description: </p>
		 */
		@Override
		public int buildBasicPort() {
			return buildPort();
		}

		/*
		 * <p>Title: getBasicService</p> <p>Description: </p>
		 */
		@Override
		public String buildBasicService() {
			return bulidService();
		}

		/*
		 * <p>Title: buildBasicIp</p> <p>Description: </p>
		 */
		@Override
		public String buildBasicIp() {
			return buildIp();
		}

		@Override
		public String buildBasicPath() {
			return buildPath();
		}

		/*
		 * <p>Title: buildBasicMethod</p> <p>Description: </p>
		 */
		@Override
		public String buildBasicMethod() {
			return buildMethod();
		}

	}

}
