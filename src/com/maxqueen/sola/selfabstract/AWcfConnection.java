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
 * Description: ������wcfͨѶ�Ļ��ࡣ
 * </p>
 * <h2>AWcfConnection's generic types</h2>
 * <p>
 * �������������͵Ĳ���������������Http���ӣ��������£�
 * </p>
 * <ol>
 * <li><code>Params</code>,����{@link InnerRunnable#_Param} ,����������������ȥ�����������</li>
 * <li><code>Result</code>,����Connect���Ӻ󣬶��ڻ�ȡ������ת���ɵĲ������ͣ����巽��
 * {@link #doAnlyzing(String)}</li>
 * <li><code>Target</code>,���øú������������,һ����Fragment,Activity��,�Ա���
 * {@link #onPostExecute(Object)} �����к͵����߽���ͨѶ</li>
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
	 *            ���ú����������
	 * @param args
	 *            url���ʷ���������Ҫ����Ĳ���(�˹���Ĭ���ǲ���"/+����"����ʽ����)
	 */
	protected AWcfConnection(WeakReference<Target> target, String... args) {
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
	protected AWcfConnection(WeakReference<Target> target, Params param,
			String... args) {
		this(target, null, param, args);
	}

	/**
	 * @param target
	 *            ���ú����������
	 * @param method
	 *            URL�еķ�������{@link #AWcfConnectionBuilder}�Ĺ��췽��
	 * @param args
	 *            url���ʷ���������Ҫ����Ĳ���(�˷����Ǹ��ݴ����method������ͬ�Ĳ�������)
	 */
	protected AWcfConnection(WeakReference<Target> target, String method,
			String... args) {
		this(target, method, null, args);
	}

	/**
	 * @param target
	 *            ���ú����������
	 * @param method
	 *            URL�еķ�������{@link #AWcfConnectionBuilder}�Ĺ��췽��
	 * @param param
	 *            �������Ĵ������{@link Params}
	 * @param args
	 *            url���ʷ���������Ҫ����Ĳ���
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
	 * ���е� ��json��ʽ�����ݽ��н����ĺ������ڲ�ʵ����ͨ��{@link Gson}����ʵ��
	 * �����ø÷�����ʹ�ø÷�����ʱ����ע�⣬����Ķ��������е����Ա����json�ļ��еĲ���������ͬ
	 * 
	 * @param result
	 *            �͵�����ʱ�趨��Results ��ͬ�Ĳ���,���ø÷���ʱ����ö����ʵ��
	 * @param request
	 *            �������ӵõ���json��ʽ�Ĳ���
	 * @param isTextAvailable
	 *            �������json�Ƿ���Ҫ�������⴦��,true Ĭ���ж����������ʵ��
	 *            {@link #spritResultTitle(String)}���ع��÷���,false ֱ�ӽ�������Ĳ���
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
	 * ���е� ��json��ʽ�����ݽ��н����ĺ������ڲ�ʵ����ͨ��{@link Gson}����ʵ��
	 * �����ø÷�����ʹ�ø÷�����ʱ����ע�⣬����Ķ��������е����Ա����json�ļ��еĲ���������ͬ
	 * 
	 * @param resultType
	 *            ���ز����Ĳ�������
	 * @param request
	 *            �������ӵõ���json��ʽ�Ĳ���
	 * @param isTextAvailable
	 *            �������json�Ƿ���Ҫ�������⴦��,true Ĭ���ж����������ʵ��
	 *            {@link #spritResultTitle(String)}���ع��÷���,false ֱ�ӽ�������Ĳ���
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
	 * ����δʵ�� ����û���ҵ����ʵķ���Json��ʽ��������
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
	 * ����ȥ��json�ĵ��е�ǰ׺�����ַ���
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
