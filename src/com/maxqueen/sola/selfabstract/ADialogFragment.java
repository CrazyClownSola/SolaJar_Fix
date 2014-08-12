/**
 * <p>Title: ADialogFragment.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: www.maxqueen.com</p>
 * @author Sola
 * @date 2014-2-13
 * @version 1.0
 */
package com.maxqueen.sola.selfabstract;

import java.lang.ref.WeakReference;

import com.maxqueen.sola.interfaces.IListener;
import com.maxqueen.sola.interfaces.IPublisher;
import com.maxqueen.sola.tools.FG_T_Manage;
import com.maxqueen.sola.tools.Publisher;
import com.maxqueen.sola.tools.SelfManage_FG;

import android.app.Activity;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * <p>
 * Title: ADialogFragment
 * </p>
 * <p>
 * Description: Fragment系列中的Dialog部分,嵌套发布者模式的代码块
 * </p>
 * <p>
 * Company: www.maxqueen.com
 * </p>
 * 
 * @author Sola
 * @date 2014-2-13
 */
public abstract class ADialogFragment extends DialogFragment implements
		IListener, IPublisher {

	protected final String mName;
	protected final FG_T_Manage _TManage;
	protected final WeakReference<ADialogFragment> mContext;
	
	/**
	 * 发布者对象,对于一个Fragment而言,他的监听者有多个,大致可分为Activity和线程,所以在使用这个发布者的时候请务必注意这点 (Ps:
	 * 其实有点犹豫是否要开放这个发布者对象,封闭这个有利于代码结构完整,但是不利于自定义拓展)
	 */
	private final Publisher _Publisher;

	/**
	 * 构造函数
	 */
	public ADialogFragment() {
		super();
		mContext = new WeakReference<ADialogFragment>(this);
		mName = this.getClass().getSimpleName();
		// F_Handler = new AHandler(mContext);
		_TManage = new FG_T_Manage();
		_Publisher = new Publisher();
		if (S_DEBUG)
			Log.v(Pro, "Structure " + mName);
	}

	/******** 生命周期 ********/

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if (S_DEBUG)
			Log.v(Pro, "On Attach " + mName);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (S_DEBUG)
			Log.v(Pro, "On Create " + mName);
	}

	@Override
	public void onDestroy() {
		if (_TManage != null)
			_TManage.destoryTheWorld();
		if (_Publisher != null) {
			_Publisher.cleanInstance();
		}
		if (S_DEBUG)
			Log.v(Pro, "On Destroy " + mName);
		super.onDestroy();
	}

	@Override
	public boolean isCallBackAvailable() {
		return isResumed();
	}

	protected final View initView(LayoutInflater inflater, int layoutId) {
		View mView = null;
		if (layoutId != 0) {
			try {
				mView = inflater.inflate(layoutId, null);
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
	 * 共有的提供给Fragment和Activity去获取xml文件中的Dimen数值的方法
	 * 
	 * @param _LayoutId
	 *            xml的Id
	 * @return
	 */
	public float getDimension(int _LayoutId) {
		Resources r = null;
		float retF = 0f;
		try {
			r = getResources();
			if (r != null)
				retF = r.getDimension(_LayoutId);
		} catch (NotFoundException e) {
			Log.e(Error, e.getMessage());
		}
		return retF;
	}

	/**
	 * 在Dialog调用前会使用该方法，用于初始化Dialog的一些界面信息;调用方
	 * {@link SelfManage_FG#dialogShow(ADialogFragment, Object)}
	 * 
	 * @param initialData
	 */
	public abstract void initaialOperation(Object initialData);

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
