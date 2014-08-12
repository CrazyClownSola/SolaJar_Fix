/**
 * <p>Title: AConfigAnalyzing.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: www.maxqueen.com</p>
 * @author Sola
 * @date 2014-2-18
 * @version 1.0
 */
package com.maxqueen.sola.selfabstract;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.maxqueen.sola.interfaces.IListener;

/**
 * <p>
 * Title: AConfigAnalyzing
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: www.maxqueen.com
 * </p>
 * 
 * @author Sola
 * @date 2014-2-18
 */
public abstract class AConfigAnalyzing<Params, Target extends IListener>
		extends InnerRunnable<Params, Target> {

	/**
	 * @param target
	 * @param param
	 */
	protected AConfigAnalyzing(WeakReference<Target> target, Params param) {
		super(target, param);
	}

	@Override
	public void run() {
		try {
			InputStream is = openFile();
			int size = is.available();
			byte[] buffer = new byte[size];
			is.read(buffer);
			is.close();
			// String text = new String(buffer, "GBK");
			onPostExecute(changeBufferToString(buffer));
		} catch (IOException e) {
			onExceptionExecute(e);
		} catch (JsonSyntaxException e) {
			onExceptionExecute(e);
		}
	}

	/**
	 * 
	 * @param buffer
	 *            File�ı��ж�ȡ�������ֽ�����
	 * @return ���ز�ͬ��ʽ��String�ַ���
	 */
	protected abstract String changeBufferToString(byte[] buffer);

	/**
	 * {@link InputStream}������ĵ���ʵ�ִ���
	 * 
	 * @return ������Ҫ���н������������
	 */
	protected abstract InputStream openFile() throws IOException;

	/**
	 * �Խ���������ַ����������ݴ���ĺ���
	 * 
	 * @param result
	 *            �����������������
	 */
	protected abstract void onPostExecute(String result);

	/**
	 * �ڲ����������쳣���쳣������
	 * 
	 * @param e
	 */
	protected abstract void onExceptionExecute(Exception e);

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
	public <T> T doJsonAnlysis(T result, String request, boolean isTextAvailable)
			throws JsonSyntaxException, IllegalArgumentException {
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
	 * 
	 * @see #checkFormat(String)
	 */
	public <T> T doJsonAnlysis(Type resultType, String request,
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
	 * ��������������ַ������й���ж�,Ĭ���Ƕ���Json��ʽ���ж�ģʽ
	 * 
	 * @param request
	 * @return
	 */
	protected boolean checkFormat(String request) {
		boolean retBool = true;
		return retBool;
	}

	/**
	 * ����ȥ��json�ĵ��е�ǰ׺�����ַ���
	 * 
	 * @param request
	 * @return
	 */
	public String spritResultTitle(String request) {
		return request;
	}

}
