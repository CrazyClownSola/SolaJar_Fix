/**
 * <p>Title: AListFragment.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: www.maxqueen.com</p>
 * @author Sola
 * @date 2013-11-21
 * @version 1.0
 */
package com.maxqueen.sola.selfabstract;

import java.lang.ref.WeakReference;

import com.maxqueen.sola.interfaces.IF_Constants;
import com.maxqueen.sola.interfaces.IListener;
import com.maxqueen.sola.tools.FG_T_Manage;
import com.maxqueen.sola.tools.Publisher;

import android.app.Activity;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * <p>
 * Title: AListFragment
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: www.maxqueen.com
 * </p>
 * 
 * @author Sola
 * @date 2013-11-21
 */
public abstract class AListFragment extends ListFragment implements
		IF_Constants, IListener {

	protected final String mName;
	protected final FG_T_Manage _TManage;
	protected final WeakReference<AListFragment> mContext;

	protected boolean isSystemOk = false;
	protected int FG_Id;

	/**
	 * 发布者对象,对于一个Fragment而言,他的监听者有两个,一个是Activity,一个是View,所以在使用这个发布者的时候请务必注意这点
	 * (Ps: 其实有点犹豫是否要开放这个发布者对象,封闭这个有利于代码结构完整,但是不利于自定义拓展)
	 */
	private final Publisher _Publisher;

	/**
	 * 构造函数
	 */
	public AListFragment() {
		super();
		mContext = new WeakReference<AListFragment>(this);
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
		try {
			_Publisher.RegistePosCallBack((IListener) activity);
		} catch (ClassCastException exp) {
			Log.e(Error, "IListener can not confirm " + exp.getMessage());
		} catch (NullPointerException exp) {
			Log.e(Error, "PositivePublisher not Create " + exp.getMessage());
		}
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

	public final boolean IsSystemOk() {
		boolean retBool = false;
		if (isSystemOk)
			retBool = true;
		return retBool;
	}

	@Override
	public boolean isCallBackAvailable() {
		return isResumed();
	}

	@Override
	public abstract void onListItemClick(ListView l, View v, int position,
			long id);

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

	/*********************** 子类Fragment调用的对于Publisher的使用的方法集合 **************************/

	public final void submitDataInteraction(int binaryValue, Object obj) {
		try {
			_Publisher.execute(binaryValue, obj);
		} catch (NullPointerException exp) {
			Log.e(Error, "PositivePublisher not Create " + exp.getMessage());
		}
	}

}
