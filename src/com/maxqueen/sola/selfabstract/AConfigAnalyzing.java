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
	 *            File文本中读取出来的字节数组
	 * @return 返回不同制式的String字符串
	 */
	protected abstract String changeBufferToString(byte[] buffer);

	/**
	 * {@link InputStream}的输出文档的实现代码
	 * 
	 * @return 返回需要进行解析处理的内容
	 */
	protected abstract InputStream openFile() throws IOException;

	/**
	 * 对解析过后的字符串进行数据处理的函数
	 * 
	 * @param result
	 *            经过解析过后的数据
	 */
	protected abstract void onPostExecute(String result);

	/**
	 * 内部函数发生异常的异常处理函数
	 * 
	 * @param e
	 */
	protected abstract void onExceptionExecute(Exception e);

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
	public <T> T doJsonAnlysis(T result, String request, boolean isTextAvailable)
			throws JsonSyntaxException, IllegalArgumentException {
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
	 * 对于所需解析的字符串进行规格判定,默认是对于Json格式的判定模式
	 * 
	 * @param request
	 * @return
	 */
	protected boolean checkFormat(String request) {
		boolean retBool = true;
		return retBool;
	}

	/**
	 * 用于去除json文档中的前缀多余字符串
	 * 
	 * @param request
	 * @return
	 */
	public String spritResultTitle(String request) {
		return request;
	}

}
