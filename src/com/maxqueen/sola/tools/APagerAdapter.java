/**
 * <p>Title: APagerAdapter.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: www.maxqueen.com</p>
 * @author Sola
 * @date 2014-6-11
 * @version 1.0
 */
package com.maxqueen.sola.tools;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;

/**
 * <p>
 * Title: APagerAdapter
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: www.maxqueen.com
 * </p>
 * 
 * @author Sola
 * @date 2014-6-11
 */
public abstract class APagerAdapter extends FragmentPagerAdapter {

	protected Fragment[] mFragments;
	private FragmentManager mManager;

	/**
	 * @param fm
	 */
	public APagerAdapter(FragmentManager fm) {
		super(fm);
		mManager = fm;
	}

	/**
	 * 初始化配置器中的{@link #mFragments}
	 * 
	 * @param list
	 *            {@link ViewPager}中所要显示的{@link Fragment}的集合
	 */
	protected void setFragmentList(List<Fragment> list) {
		removeAllCacheFragments();
		if (list != null) {
			int listSize = list.size();
			if (listSize == 0)
				return;
			else {
				mFragments = new Fragment[listSize];
				System.arraycopy(list.toArray(), 0, mFragments, 0, listSize);
			}
		}
	}

	/**
	 * 注销原有缓存{@link #mFragments}中的Fragment数据,主要是为了解决替换Fragment之后ViewPager无响应的问题
	 */
	protected void removeAllCacheFragments() {
		if (this.mFragments != null) {
			FragmentTransaction ft = mManager.beginTransaction();
			for (Fragment f : this.mFragments) {
				ft.remove(f);
			}
			ft.commitAllowingStateLoss();
			ft = null;
		}
	}

	@Override
	public int getItemPosition(Object object) {
		return POSITION_NONE;
	}
}
