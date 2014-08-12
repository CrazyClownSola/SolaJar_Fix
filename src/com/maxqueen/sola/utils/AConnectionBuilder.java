/**
 * <p>Title: AConnectionBuilder.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: www.maxqueen.com</p>
 * @author Sola
 * @date 2013-8-24
 * @version 1.0
 */
package com.maxqueen.sola.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.util.Log;

/**
 * <p>
 * Title: AConnectionBuilder
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: www.maxqueen.com
 * </p>
 * 
 * @author Sola
 * @date 2013-8-24
 */
public abstract class AConnectionBuilder<Result> extends AURLBuilder<Result> {

	protected HttpURLConnection mUrlConnection = null;
	protected InputStream mInputStream = null;

	public AConnectionBuilder(String... args) {
		super(args);
	}

	public AConnectionBuilder(String method, String... args) {
		super(method, args);
	}

	@Override
	public Result getRequest() {
		return mResult;
	}

	public void connect() throws MalformedURLException, IOException {
		mInputStream = getInputStream();
		mResult = readBuffer(mInputStream);
		if (mInputStream != null)
			mInputStream.close();
	}

	public void disConnect() {
		if (mUrlConnection != null)
			mUrlConnection.disconnect();
	}

	private URL getURL() throws MalformedURLException {
		// if (DEBUG)
		Log.d("URL", getUrlNet());
		return new URL(getUrlNet());
	}

	private HttpURLConnection getUrlConnection() throws MalformedURLException,
			IOException {
		mUrlConnection = (HttpURLConnection) getURL().openConnection();
		mUrlConnection.setConnectTimeout(TIME_OUT);
		mUrlConnection.setReadTimeout(TIME_OUT);
		mUrlConnection.connect();
		return mUrlConnection;
	}

	private InputStream getInputStream() throws MalformedURLException,
			IOException {
		return getUrlConnection().getInputStream();
	}

	/*
	 * <p>Title: getProtocol</p> <p>Description: </p>
	 */
	@Override
	public String buildProtocol() {
		return "http";
	}

	protected abstract Result readBuffer(InputStream is) throws IOException;
}
