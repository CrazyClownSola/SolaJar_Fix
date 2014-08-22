package com.maxqueen.sola.tools;

import com.maxqueen.sola.interfaces.IS_DEBUG;
import com.maxqueen.sola.selfabstract.ADialogFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class FragmentManagerTool implements IS_DEBUG {

	private FragmentManager mManager = null;
	private FragmentManagerTool instance = null;

	public FragmentManagerTool(FragmentManager fragmentManager) {
		mManager = fragmentManager;
	}

	public FragmentManager getFragmentManager() {
		return mManager;
	}

	public Fragment findById(int FG_Id) {
		if (mManager == null)
			return null;
		else
			return mManager.findFragmentById(FG_Id);
	}

	public void cleanInstance() {
		if (instance != null) {
			mManager = null;
			instance = null;
		}
	}

	public int getCount() {
		return mManager == null ? 0 : mManager.getBackStackEntryCount();
	}

	public void popBackStack() {
		mManager.popBackStack();
	}

	/**
	 * 
	 * @param newFrag
	 * @param isDestory
	 *            <code>false</code> 自定义返回键操作
	 *            {@link FragmentManager#popBackStack()},会依次唤醒在堆栈内,标志位为false的
	 *            {@link Fragment}; 当popBackStack()调用的是自身的时候,进入Fragment的自行销毁流程
	 *            <code>true</code> 自定义返回键操作
	 *            {@link FragmentManager#popBackStack()}
	 *            ,不会对该标志位为true的任何Fragment产生影响,不会调用该Fragment的生命周期,想要调用该声明周期的销毁流程
	 *            ,请参见方法{@link FragmentTransaction#remove(Fragment)}
	 * 
	 * @param resourceId
	 *            所要替换的Fragment的Id
	 */
	public void replaceFragment(Fragment newFrag, boolean isDestory,
			int resourceId) {
		if (mManager != null) {
			// mManager.
			FragmentTransaction transaction = mManager.beginTransaction();
			transaction.replace(resourceId, newFrag);
			if (!isDestory)
				/****
				 * 在后退栈中保存被替换的Fragment的状态 添加这句话后， 用户按返回键会将前面的所有动作反向执行一次（事务回溯）
				 ***/
				transaction.addToBackStack(null);
			transaction.commitAllowingStateLoss();
		}
	}

	/**
	 * 单向性质的添加{@link Fragment}到 @param resourceId中去,只是添加
	 * 
	 * @param newFrag
	 * @param isDestory
	 * @param resourceId
	 */
	public void addFragment(Fragment newFrag, boolean isDestory, int resourceId) {
		if (mManager != null) {
			// mManager.
			FragmentTransaction transaction = mManager.beginTransaction();
			transaction.add(resourceId, newFrag, null);
			if (!isDestory)
				/****
				 * 在后退栈中保存被替换的Fragment的状态 添加这句话后， 用户按返回键会将前面的所有动作反向执行一次（事务回溯）
				 ***/
				transaction.addToBackStack(null);
			transaction.commitAllowingStateLoss();
		}
	}

	public void replaceAndRemoveFragment(Fragment newFrag, boolean isDestory,
			int resourceId) {
		if (mManager != null) {
			FragmentTransaction transaction = mManager.beginTransaction();
			if (!checkFragment(resourceId))
				mManager.popBackStack(resourceId,
						FragmentManager.POP_BACK_STACK_INCLUSIVE);
			transaction.replace(resourceId, newFrag);
			if (!isDestory)
				/****
				 * 在后退栈中保存被替换的Fragment的状态 添加这句话后， 用户按返回键会将前面的所有动作反向执行一次（事务回溯）
				 ***/
				transaction.addToBackStack(null);
			transaction.commitAllowingStateLoss();
			// mManager.p();
		}
	}

	public void dialogShow(ADialogFragment dialog, Object initialData) {
		Fragment frag = mManager.findFragmentByTag("dialog");
		if (frag == null && dialog != null) {
			dialog.initaialOperation(initialData);
			dialog.show(mManager, "dialog");
		} else if (dialog == null)
			throw new NullPointerException("Dialog do not instance");
	}

	public void dialogDismiss(int _Id) {
		Fragment frag = mManager.findFragmentByTag("dialog");
		if (frag != null && frag instanceof ADialogFragment) {
			ADialogFragment _Dialog = (ADialogFragment) frag;
			if (_Dialog.getListenerId() == _Id || _Id == 0)
				_Dialog.dismiss();
		}
	}

	public boolean checkFragment(int resourceId) {
		Fragment frag = mManager.findFragmentById(resourceId);
		return frag == null;
	}

	public void removeFragment(int id) {
		if (mManager != null) {
			Fragment frag = mManager.findFragmentById(id);
			if (frag != null) {
				FragmentTransaction transaction = mManager.beginTransaction();
				transaction.remove(frag);
				transaction.commitAllowingStateLoss();
			}
		}
	}

	public Fragment findFragmentByLayoutId(int _Layout) {
		Fragment _Frag = null;
		if (mManager != null) {
			_Frag = mManager.findFragmentById(_Layout);
		}
		return _Frag;
	}

}
