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
	 *            <code>false</code> �Զ��巵�ؼ�����
	 *            {@link FragmentManager#popBackStack()},�����λ����ڶ�ջ��,��־λΪfalse��
	 *            {@link Fragment}; ��popBackStack()���õ��������ʱ��,����Fragment��������������
	 *            <code>true</code> �Զ��巵�ؼ�����
	 *            {@link FragmentManager#popBackStack()}
	 *            ,����Ըñ�־λΪtrue���κ�Fragment����Ӱ��,������ø�Fragment����������,��Ҫ���ø��������ڵ���������
	 *            ,��μ�����{@link FragmentTransaction#remove(Fragment)}
	 * 
	 * @param resourceId
	 *            ��Ҫ�滻��Fragment��Id
	 */
	public void replaceFragment(Fragment newFrag, boolean isDestory,
			int resourceId) {
		if (mManager != null) {
			// mManager.
			FragmentTransaction transaction = mManager.beginTransaction();
			transaction.replace(resourceId, newFrag);
			if (!isDestory)
				/****
				 * �ں���ջ�б��汻�滻��Fragment��״̬ �����仰�� �û������ؼ��Ὣǰ������ж�������ִ��һ�Σ�������ݣ�
				 ***/
				transaction.addToBackStack(null);
			transaction.commitAllowingStateLoss();
		}
	}

	/**
	 * �������ʵ����{@link Fragment}�� @param resourceId��ȥ,ֻ�����
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
				 * �ں���ջ�б��汻�滻��Fragment��״̬ �����仰�� �û������ؼ��Ὣǰ������ж�������ִ��һ�Σ�������ݣ�
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
				 * �ں���ջ�б��汻�滻��Fragment��״̬ �����仰�� �û������ؼ��Ὣǰ������ж�������ִ��һ�Σ�������ݣ�
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
