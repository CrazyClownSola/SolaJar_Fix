/**
 * <p>Title: AService.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: www.maxqueen.com</p>
 * @author Sola
 * @date 2014-8-5
 * @version 1.0
 */
package com.maxqueen.sola.selfabstract;

import java.lang.ref.WeakReference;

import com.maxqueen.sola.interfaces.IListener;
import com.maxqueen.sola.interfaces.IPublisher;
import com.maxqueen.sola.tools.FG_T_Manage;
import com.maxqueen.sola.tools.Publisher;

import android.app.Service;
import android.util.Log;

/**
 * <p>
 * Title: AService
 * </p>
 * <p>
 * Description: Service的抽象类,主要是将观察者的架构模式套入到这个Service的模组里面,来适应整套库的通讯体系
 * </p>
 * <p>
 * Company: www.maxqueen.com
 * </p>
 * 
 * @author Sola
 * @date 2014-8-5
 */
public abstract class AService extends Service implements IListener, IPublisher {

	/**
	 * 类名,通常用于写日志
	 */
	protected final String mName;
	/**
	 * 线程管理类,内部维护了一个线程的集合,方便进行管理
	 */
	protected final FG_T_Manage _TManage;
	/**
	 * 实例对象的弱引用类
	 */
	protected final WeakReference<AService> mContext;
	/**
	 * 发布者对象 (Ps: 该对象的私有化一直是我在纠结的一个问题)
	 */
	private final Publisher _Publisher;

	public AService() {
		super();
		mContext = new WeakReference<AService>(this);
		mName = this.getClass().getSimpleName();
		_TManage = new FG_T_Manage();
		_Publisher = new Publisher();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
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
}
