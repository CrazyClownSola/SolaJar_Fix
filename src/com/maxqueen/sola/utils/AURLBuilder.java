package com.maxqueen.sola.utils;

import java.io.File;

import com.maxqueen.sola.interfaces.IService;

public abstract class AURLBuilder<Result> implements IService<Result> {

	protected Result mResult = null;
	private StringBuffer _NetUrl = null;
	private boolean hasParams = false;
	private String _MethodTitle = null;

	public AURLBuilder(String... args) {
		this(null, args);
	}

	public AURLBuilder(String _Method, String... args) {
		if (_NetUrl == null)
			_NetUrl = buildUrl();
		int index = -1;
		if (_Method != null) {
			index = _Method.indexOf("?");
			hasParams = _Method != null && index != -1;
		} else {
			String meString = buildBasicMethod();
			if (meString != null) {
				_NetUrl.append(File.separatorChar).append(meString);
				index = meString.indexOf("?");
			}
			hasParams = meString != null && index != -1;
		}
		if (hasParams) {
			_MethodTitle = _Method.substring(0, index);
			buildParams(_Method, args);
		} else {
			if (_Method != null)
				_NetUrl.append(File.separatorChar).append(_Method);
			if (args != null && args.length != 0) {
				for (String string : args) {
					_NetUrl.append(File.separatorChar + string);
				}
			}
		}

	}

	public final String getMethodTitle() {
		return _MethodTitle;
	}

	public final String getUrlNet() {
		return _NetUrl == null || _NetUrl.length() == 0 ? "" : _NetUrl
				.toString().replaceAll(" ", "&nbsp");
	}

	/**
	 * @param string
	 */
	private void buildParams(String url, String[] params) {
		String[] _List = url.split("\\{[A-Za-z0-9_]{0,}\\}");
		if (_List == null || _List.length == 0) {
			throw new NullPointerException(
					"The Method String contains ? ,but nothing after ");
		} else if (params == null || params.length == 0) {
			throw new NullPointerException("The params in constructor is Null");
		} else {
			if (_List.length == params.length) {
				_NetUrl.append(File.separatorChar);
				for (int i = 0, len = _List.length; i < len; i++) {
					if (params[i] == null || params[i].isEmpty())
						_NetUrl.append(_List[i]);
					else
						_NetUrl.append(_List[i]).append(params[i]);
				}
			} else {
				throw new IllegalArgumentException(
						"the size of method's params is not equal to constructor's params");
			}
		}
		// if(_NetUrl != null && _NetUrl.length() != 0 && _NetUrl.indexOf(" "))
		// _NetUrl.r
	}

//	public final void ReplaceParams(String key, String value) {
//		String url = _NetUrl.toString();
//		String[] _List = url.split(key + "=");
//	}

	/**
	 * @return
	 */
	private StringBuffer buildUrl() {
		StringBuffer _Buffer = new StringBuffer();
		// _Buffer = new
		String _Http = buildProtocol();
		if (_Http == null || _Http.isEmpty())
			throw new NullPointerException("Protocol is NULL");
		String _Ip = buildBasicIp();
		if (_Ip == null || _Ip.isEmpty())
			throw new NullPointerException("Ip is NULL");
		// else if (!_Ip
		// .matches("[0-9]{1,3}\\.[0-9]{3}\\.[0-9]{1,3}\\.[0-9]{1,3}"))
		// throw new IllegalArgumentException(_Ip + " conform rule error");
		int _Port = buildBasicPort();
		String _Service = buildBasicService();
		_Buffer.append(_Http)
				.append("://")
				.append(_Ip)
				.append(_Port == 0 ? "" : ":" + _Port)
				.append(_Service == null || _Service.isEmpty() ? "" : "/"
						+ _Service);
		String _Path = buildBasicPath();
		if (_Path != null && !_Path.isEmpty())
			_Buffer.append(File.separatorChar).append(_Path);
		return _Buffer;
	}

}
