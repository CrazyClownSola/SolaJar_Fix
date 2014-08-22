/**
 * <p>Title: Publisher.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: www.maxqueen.com</p>
 * @author Sola
 * @date 2013-10-11
 * @version 1.0
 */
package com.maxqueen.sola.tools;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import com.maxqueen.sola.interfaces.IListener;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * <p>
 * Title: Publisher
 * </p>
 * <p>
 * Description: ��������,���жԹ۲��ߵĵ��ö�ͨ����������,���Ǿ������Ϊ�ɹ۲�������ʵ��,ͬʱӵ�и�ʵ���Ķ������Ծ��̳�IHandler�ӿ�
 * </p>
 * <p>
 * Company: www.maxqueen.com
 * </p>
 * 
 * @author Sola
 * @date 2013-10-11
 */
public class Publisher {

	/**
	 * ����������ά���Ľӿڼ���
	 */
	public List<IListener> _IList = null;

	/**
	 * �ڲ�����Ϣ����
	 */
	private final P_Handler mHandler;

	/**
	 * ���췽��
	 */
	public Publisher() {
		mHandler = new P_Handler(new WeakReference<Publisher>(this));
		if (_IList == null)
			_IList = new ArrayList<IListener>();
	}

	/**
	 * Fragment��ActivityͨѶ��ʱ���õ��Ľӿڷ�����
	 * 
	 * @param _Listener
	 */
	public void RegistePosCallBack(IListener _Listener) {
		if (_IList == null)
			throw new NullPointerException(this.getClass().getSimpleName()
					+ " IListener List is NULL ");
		else
			_IList.add(_Listener);
	}

	/**
	 * �ڽӿڶ�����ע����Ƴ�����
	 * 
	 * @param _Listener
	 */
	public void RemovePosCallBack(IListener _Listener) {
		if (_IList == null)
			throw new NullPointerException(this.getClass().getSimpleName()
					+ " IListener is NULL ");
		else if (_IList.size() != 0) {
			int index = _IList.indexOf(_Listener);
			if (index != -1)
				_IList.remove(index);
		}
	}
	
	public void cleanInstance() {
		if (_IList != null && _IList.size() != 0) {
			_IList.removeAll(_IList);
			_IList = null;
		}
	}

	private final void dealMessage(Message msg) {
		if (_IList == null)
			throw new NullPointerException(this.getClass().getSimpleName()
					+ " IListener is NULL ");
		else if (_IList.size() != 0) {
			if (msg.arg1 == 0) {
				for (IListener _Listener : _IList)
					if (_Listener.isCallBackAvailable())
						_Listener.responseInteractiveData(msg.what, msg.obj);
			} else {
				for (IListener _Listener : _IList)
					if (_Listener.getListenerId() == msg.arg1
							&& _Listener.isCallBackAvailable())
						_Listener.responseInteractiveData(msg.what, msg.obj);
			}
		}
	}

	public void execute(int binaryValue, int listenerId, Object obj) {
		if (mHandler == null)
			throw new NullPointerException();
		else
			Message.obtain(mHandler, binaryValue, listenerId, 0, obj)
					.sendToTarget();
	}

	public void execute(int binaryValue, Object obj) {
		execute(binaryValue, 0, obj);
	}

	static class P_Handler extends Handler {
		private Publisher _Publisher;

		public P_Handler(WeakReference<Publisher> mTarget) {
			super();
			_Publisher = mTarget.get();
		}
		
		@Override
		public void handleMessage(Message msg) {
			try {
				_Publisher.dealMessage(msg);
			} catch (NullPointerException exp) {
				exp.printStackTrace();
				Log.e("Error", "Event Handler occured " + exp.getMessage()
						+ exp.getLocalizedMessage());
			} catch (Exception exp) {
				exp.printStackTrace();
				Log.e("Error", "Event Handler occured " + exp.getMessage()
						+ exp.getLocalizedMessage());
			}
		}
	}

}
