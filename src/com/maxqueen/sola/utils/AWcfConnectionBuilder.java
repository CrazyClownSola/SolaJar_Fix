/**
 * <p>Title: AWcfConnectionBuilder.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: www.maxqueen.com</p>
 * @author Sola
 * @date 2013-8-24
 * @version 1.0
 */
package com.maxqueen.sola.utils;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

import android.util.Log;

import com.maxqueen.sola.tools.HttpManager;

/**
 * <p>
 * Title: AWcfConnectionBuilder
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: www.maxqueen.com
 * </p>
 * 
 * @hide
 * @author Sola
 * @date 2013-8-24
 */
public abstract class AWcfConnectionBuilder extends AURLBuilder<String> {

	// private HttpGet httpRequest = null;

	// private static final String USER_AGENT = "Mozilla/4.5";s

	public AWcfConnectionBuilder(String... args) {
		super(args);
	}

	public AWcfConnectionBuilder(String method, String... args) {
		super(method, args);
	}

	public void connect() throws ParseException, ClientProtocolException,
			IOException {
		mResult = dealResult(getHttpResponse());
	}

	private HttpResponse getHttpResponse() throws ClientProtocolException,
			IOException {
		return HttpManager.execute(getHttpRequest());
	}

	protected HttpGet getHttpRequest() {
		Log.d("URL", getUrlNet());
		return new HttpGet(getUrlNet());
	}

	private String dealResult(HttpResponse request) throws ParseException,
			ClientProtocolException, IOException {
		try {
			return EntityUtils.toString(getEntity(request));
		} catch (NullPointerException e) {
			return null;
		}
	}

	private HttpEntity getEntity(HttpResponse request)
			throws ClientProtocolException, IOException {

		int statusCode = -1;
		// Log.v("Sola", "Get Entity "+ mHttpResponse);
		statusCode = request.getStatusLine().getStatusCode();
		if (S_DEBUG)
			Log.e("Sola", "Http Connect Code : " + statusCode);
		return request.getEntity();
	}

	/*
	 * <p>Title: getRequest</p> <p>Description: </p>
	 */
	@Override
	public String getRequest() {
		return mResult;
	}

	/*
	 * <p>Title: buildProtocol</p> <p>Description: </p>
	 */
	@Override
	public String buildProtocol() {
		return "http";
	}

}
