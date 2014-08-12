/**
 * <p>Title: ABroadCastReceiver.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: www.maxqueen.com</p>
 * @author Sola
 * @date 2014-7-1
 * @version 1.0
 */
package com.maxqueen.sola.selfabstract;

import java.lang.ref.WeakReference;

import com.maxqueen.sola.interfaces.IListener;
import com.maxqueen.sola.interfaces.IPublisher;
import com.maxqueen.sola.interfaces.IS_DEBUG;
import com.maxqueen.sola.tools.Publisher;

import android.content.BroadcastReceiver;
import android.util.Log;

/**
 * <p>
 * Title: ABroadCastReceiver
 * </p>
 * <p>
 * Description: 广播的通用类,同样套用库类的基本通讯法则
 * </p>
 * <p>
 * Company: www.maxqueen.com
 * </p>
 * 
 * @author Sola
 * @date 2014-7-1
 */
public abstract class ABroadCastReceiver<Target extends IListener> extends
		BroadcastReceiver implements IPublisher, IS_DEBUG {

	/**
	 * 持有的发布者对象
	 */
	private final Publisher _Publisher;
	/**
	 * 调用者的弱引用对象
	 */
	protected final WeakReference<Target> _Target;

	public ABroadCastReceiver(WeakReference<Target> target) {
		_Target = target;
		_Publisher = new Publisher();
		if (target != null)
			_Publisher.RegistePosCallBack((IListener) target.get());
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
