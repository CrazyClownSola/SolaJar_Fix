/**
 * <p>Title: InnerRunnable.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: www.maxqueen.com</p>
 * @author Sola
 * @date 2013-8-24
 * @version 1.0
 */
package com.maxqueen.sola.selfabstract;

import java.lang.ref.WeakReference;

import android.util.Log;

import com.maxqueen.sola.interfaces.IF_Constants;
import com.maxqueen.sola.interfaces.IListener;
import com.maxqueen.sola.interfaces.IPublisher;
import com.maxqueen.sola.interfaces.IS_DEBUG;
import com.maxqueen.sola.tools.Publisher;

/**
 * <p>
 * Title: InnerRunnable
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
public abstract class InnerRunnable<Params, Target extends IListener>
		implements Runnable, IF_Constants, IPublisher, IS_DEBUG {

	protected Params _Param;
	private final Publisher _Publisher;

	protected final WeakReference<Target> _Target;


	protected InnerRunnable(WeakReference<Target> target, Params param) {
		_Param = param;
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
