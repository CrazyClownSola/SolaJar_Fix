/**
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: www.maxqueen.com</p>
 * @author Sola
 * @date 2014-08-22
 * @version 1.0
 */
package com.maxqueen.sola.selfabstract;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;

import com.maxqueen.sola.interfaces.IListener;
import com.maxqueen.sola.interfaces.IPublisher;
import com.maxqueen.sola.tools.FG_T_Manage;
import com.maxqueen.sola.tools.Publisher;
import com.maxqueen.sola.tools.FragmentManagerTool;

import android.content.res.Resources.NotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Activity的组合类,内部包含
 * <ul>
 * <li>
 * <p>
 * {@link Publisher}的实例,用于数据的传输流
 * </p>
 * <p>
 * {@link FragmentManagerTool}的实例,对于{@link FragmentManager}的封装类,提供关于控制
 * {@link Fragment}的一系列功能
 * </p>
 * <p>
 * {@link FG_T_Manage}的实例,线程的控制类,负责线程的总管理
 * </p>
 * </ul>
 * 
 * @author Sola
 * @date 2014-1-24
 */
public abstract class ABinaryFragmentActivity extends FragmentActivity
		implements IListener, IPublisher {

	protected FragmentManagerTool mManage = null;
	protected FG_T_Manage _TManage = null;

	private final Publisher _Publisher;

	protected WeakReference<ABinaryFragmentActivity> mContext = new WeakReference<ABinaryFragmentActivity>(
			this);

	public ABinaryFragmentActivity() {
		_Publisher = new Publisher();
	}

	/************** 生命周期 ***************/
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		onCreateInit();
		if (S_DEBUG)
			Log.v(Pro, "On Create " + this.getClass().getSimpleName());
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (mManage != null) {
			mManage.cleanInstance();
			mManage = null;
		}
		if (S_DEBUG)
			Log.v(Pro, "On Pause " + this.getClass().getSimpleName());
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		if (mManage == null)
			mManage = new FragmentManagerTool(getSupportFragmentManager());
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (mManage == null)
			mManage = new FragmentManagerTool(getSupportFragmentManager());
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (S_DEBUG)
			Log.v(Pro, "On Destory " + this.getClass().getSimpleName());
		if (_Publisher != null)
			_Publisher.cleanInstance();
		if (_TManage != null)
			_TManage.destoryTheWorld();
	}

	@Override
	public final void submitDataInteraction(int binaryValue, Object obj) {
		try {
			_Publisher.execute(binaryValue, obj);
		} catch (NullPointerException exp) {
			Log.e(Error, "PositivePublisher not Create " + exp.getMessage());
		}
	}

	@Override
	public final void submitDataInteraction(int binaryValue, int listenerId,
			Object obj) {
		try {
			_Publisher.execute(binaryValue, listenerId, obj);
		} catch (NullPointerException exp) {
			Log.e(Error, "PositivePublisher not Create " + exp.getMessage());
		}
	}

	@Override
	public void RemovePosCallBack(IListener _Listener) {
		try {
			_Publisher.RemovePosCallBack(_Listener);
		} catch (Exception e) {
			Log.e("Error", e.getMessage());
		}
	}

	@Override
	public void RegistePosCallBack(IListener _Listener) {
		try {
			_Publisher.RegistePosCallBack(_Listener);
		} catch (NullPointerException exp) {
			Log.e(Error, "Publisher not Create " + exp.getMessage());
		}
	}

	private void onCreateInit() {
		if (_TManage == null)
			_TManage = new FG_T_Manage();
		if (mManage == null)
			mManage = new FragmentManagerTool(getSupportFragmentManager());
	}

	protected final View initView(int layoutId) {
		View mView = null;
		if (layoutId != 0) {
			try {
				mView = View.inflate(this, layoutId, null);
			} catch (NotFoundException e) {
				Log.e(Error, "" + e.getMessage());
			}
			/*
			 * 这里是对View进行布局Id的设置操作，用于mManage中的对布局的切换的问题
			 */
			if (mView != null) {
				initView((ViewGroup) mView);
			}
		}
		return mView;
	}

	private void initView(final ViewGroup mView) {
		for (int i = 0, len = mView.getChildCount(); i < len; i++)
			dealView(mView.getChildAt(i));
	}

	protected void dealView(final View viewChild) {
		if (viewChild instanceof ViewGroup)
			initView((ViewGroup) viewChild);
	}

	/**
	 * 从Asserts目录下打开图片文件
	 * 
	 * @param pathName
	 * @return
	 */
	public Bitmap readBitMap(String pathName) {
		InputStream is = null;
		try {
			is = getAssets().open(pathName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return readBitMap(is);
	}

	/**
	 * 从传入的图片输出流转换成Bitmap实例
	 * 
	 * @param is
	 * @return
	 */
	public Bitmap readBitMap(InputStream is) {
		if (is == null)
			return null;
		BitmapFactory.Options opt = new BitmapFactory.Options();
		opt.inPreferredConfig = Bitmap.Config.RGB_565;
		opt.inPurgeable = true;
		opt.inInputShareable = true;
		return BitmapFactory.decodeStream(is, null, opt);
	}

}
