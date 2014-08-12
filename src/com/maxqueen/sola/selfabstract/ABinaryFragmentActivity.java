/**
 * <p>Title: ABinaryFragmentActivity.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: www.maxqueen.com</p>
 * @author Sola
 * @date 2014-1-24
 * @version 1.0
 */
package com.maxqueen.sola.selfabstract;

import java.lang.ref.WeakReference;

import com.maxqueen.sola.interfaces.IListener;
import com.maxqueen.sola.interfaces.IPublisher;
import com.maxqueen.sola.tools.FG_T_Manage;
import com.maxqueen.sola.tools.Publisher;
import com.maxqueen.sola.tools.SelfManage_FG;

import android.app.ProgressDialog;
import android.content.res.Resources.NotFoundException;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * <p>
 * Title: ABinaryFragmentActivity
 * </p>
 * <p>
 * Description: 
 * </p>
 * <p>
 * Company: www.maxqueen.com
 * </p>
 * 
 * @author Sola
 * @date 2014-1-24
 */
public abstract class ABinaryFragmentActivity extends FragmentActivity
		implements IListener, IPublisher {

	/**
	 * 
	 */
	protected SelfManage_FG mManage = null;
	protected FG_T_Manage _TManage = null;
	protected ProgressDialog mWaitingDialog = null;

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
		// super.setContentView(initView(initLayout()));
		// super.setContentView(R.layout.null_relative_layout);
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
			mManage = new SelfManage_FG(getSupportFragmentManager());
	}

	@Override
	protected void onResume() {
		super.onResume();

		if (mManage == null)
			mManage = new SelfManage_FG(getSupportFragmentManager());
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (S_DEBUG)
			Log.v(Pro, "On Destory " + this.getClass().getSimpleName());
		if (mWaitingDialog != null)
			mWaitingDialog = null;
		if (_Publisher != null)
			_Publisher.cleanInstance();
		if (_TManage != null)
			_TManage.destoryTheWorld();
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

	protected abstract int initLayout();

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			if (mManage.getCount() != 0)
				mManage.popBackStack();
			else
				this.finish();
			break;
		case KeyEvent.KEYCODE_MENU:
			return false;
		case KeyEvent.ACTION_DOWN:
			return false;
		default:
			break;
		}
		return super.onKeyDown(keyCode, event);
	}

	/**
	 * onCreate()方法中初始化参数
	 */
	private void onCreateInit() {
		if (mWaitingDialog == null)
			mWaitingDialog = new ProgressDialog(this);
		if (_TManage == null)
			_TManage = new FG_T_Manage();
		if (mManage == null)
			mManage = new SelfManage_FG(getSupportFragmentManager());
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

}
