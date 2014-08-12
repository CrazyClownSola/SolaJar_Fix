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
 * Description: Fragmentϵ���е�Dialog����,Ƕ�׷�����ģʽ�Ĵ����
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
	 * �����߶���,����һ��Fragment����,���ļ������ж��,���¿ɷ�ΪActivity���߳�,������ʹ����������ߵ�ʱ�������ע����� (Ps:
	 * ��ʵ�е���ԥ�Ƿ�Ҫ������������߶���,�����������ڴ���ṹ����,���ǲ������Զ�����չ)
	 */
	private final Publisher _Publisher;

	/**
	 * ���캯��
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

	/******** �������� ********/

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
			 * �����Ƕ�View���в���Id�����ò���������mManage�еĶԲ��ֵ��л�������
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
	 * ���е��ṩ��Fragment��Activityȥ��ȡxml�ļ��е�Dimen��ֵ�ķ���
	 * 
	 * @param _LayoutId
	 *            xml��Id
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
	 * ��Dialog����ǰ��ʹ�ø÷��������ڳ�ʼ��Dialog��һЩ������Ϣ;���÷�
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
