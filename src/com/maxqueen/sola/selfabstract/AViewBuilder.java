/**
 * <p>Title: ADialogFactory.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: www.maxqueen.com</p>
 * @author Sola
 * @date 2013-10-10
 * @version 1.0
 */
package com.maxqueen.sola.selfabstract;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.maxqueen.sola.interfaces.IViewListener;
import com.maxqueen.sola.interfaces.IS_DEBUG;
import com.maxqueen.sola.tools.Publisher;

/**
 * <p>
 * Title: AViewBuilder
 * </p>
 * <p>
 * Description:�����ѱ�����
 * </p>
 * <p>
 * Company: www.maxqueen.com
 * </p>
 * 
 * @author Sola
 * @date 2013-10-10
 */
public abstract class AViewBuilder implements IViewListener, OnClickListener,
		IS_DEBUG {

	protected View mView = null;

	protected Publisher _Publisher = null;
	protected int _FG_Id = 0;

//	public AViewBuilder(View view, int callerId, IControlListener listener,
//			Object param) {
//		mView = initView((android.view.ViewGroup) view);
//		buildView(param);
//		_FG_Id = callerId;
//		_Publisher.RegistePosCallBack(listener);
//	}

	@Override
	public void onViewDestory(int sequenceId) {

	}

	/**
	 * View��֪ͨFragment��ʱ���õ��ķ����ӿ�
	 * 
	 * @param obj
	 */
	public void raiseViewEvent(Object obj) {

	}

	public View getView() {
		return mView;
	}

	private View initView(final ViewGroup mView) {
		for (int i = 0, len = mView.getChildCount(); i < len; i++)
			dealView(mView.getChildAt(i));
		// mView = _SetViewLayout(mView);
		return mView;
	}

	protected LayoutParams _SetViewLayout(View mView2) {
		return _GetLayout(mView2, LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT, -1, false, 0, 0, 0, 0, 0);
	}

	private void dealView(final View viewChild) {
		if (viewChild instanceof TextView)
			TextView((TextView) viewChild);
		else if (viewChild instanceof ProgressBar)
			ProgressBar((ProgressBar) viewChild);
		else if (viewChild instanceof ImageView)
			ImageView((ImageView) viewChild);
		else if (viewChild instanceof SurfaceView)
			SurfaceView((SurfaceView) viewChild);
		else if (viewChild instanceof ViewGroup)
			ViewGroup((ViewGroup) viewChild);
		else
			return;
	}

	/************ View ��ֱ������ private���� ������д *************/
	private final void TextView(final TextView viewChild) {
		if (viewChild instanceof Button)
			dealButton((Button) viewChild);
		else if (viewChild instanceof EditText)
			dealEdit((EditText) viewChild);
		else
			dealText(viewChild);
	}

	private final void ViewGroup(final ViewGroup viewChild) {
		if (viewChild instanceof AdapterView<?>) {
			if (viewChild instanceof Spinner)
				dealSpinner((Spinner) viewChild);
			if (viewChild instanceof GridView)
				dealGrid((GridView) viewChild);
			if (viewChild instanceof ListView)
				dealList((ListView) viewChild);
		} else if (viewChild instanceof LinearLayout) {
			if (viewChild instanceof NumberPicker)
				dealPicker((NumberPicker) viewChild);
			else {
				dealLinearLayout((LinearLayout) viewChild);
				initView(viewChild);
			}
		} else if (viewChild instanceof FrameLayout) {
			initView(viewChild);
		} else if (viewChild instanceof ViewPager) {
			dealPager((ViewPager) viewChild);
		} else
			initView(viewChild);
	}

	private void SurfaceView(final SurfaceView viewChild) {
		dealSurfaceView(viewChild);
	}

	private void ImageView(final ImageView viewChild) {
		if (viewChild instanceof ImageButton)
			dealImageBtn((ImageButton) viewChild);
		else
			dealImageText(viewChild);
	}

	private void ProgressBar(final ProgressBar viewChild) {
		dealProgress(viewChild);
	}

	/********* Fragment �ṩ���ⲿ���� *********/
	/**
	 * ����View�ڸ��಼���е�λ��
	 * 
	 * @param _Width
	 *            ���
	 * @param _Height
	 *            �߶�
	 * @param _Gravity
	 *            ���ַ�ʽ(������LinearLayout)
	 * @param _AlignParent
	 *            �Ƿ�͸������(������RelativeLayout)
	 * @param _Weight
	 *            ռλ��Ȩ��
	 * @param _Left
	 *            �ĸ�Margin�йص�����
	 * @param _Right
	 * @param _Bottom
	 * @param _Top
	 */
	public final LayoutParams _GetLayout(View mView, int _Width, int _Height,
			int _Gravity, boolean _AlignParent, float _Weight, int _Left,
			int _Right, int _Bottom, int _Top) {
		ViewGroup.LayoutParams _Layout = null;
		try {
			if (mView == null)
				throw new NullPointerException(
						"You want to set layout before View create");
			_Layout = mView.getLayoutParams();
			if (_Layout == null)
				_Layout = new LayoutParams(_Width, _Height);
			else {
				if (_Layout instanceof android.support.v4.view.ViewPager.LayoutParams) {
					ViewPager.LayoutParams _PLayout = new ViewPager.LayoutParams();
					_PLayout.isDecor = false;
					_PLayout.width = _Width;
					_PLayout.height = _Height;
					_PLayout.gravity = _Gravity;
					_Layout = _PLayout;
				} else if (_Layout instanceof android.widget.FrameLayout.LayoutParams) {
					FrameLayout.LayoutParams _FLayout = new android.widget.FrameLayout.LayoutParams(
							_Width, _Height, _Gravity);
					_FLayout.setMargins(_Left, _Top, _Right, _Bottom);
					_Layout = _FLayout;
				} else if (_Layout instanceof android.widget.RelativeLayout.LayoutParams) {
					RelativeLayout.LayoutParams _RLayout = new android.widget.RelativeLayout.LayoutParams(
							_Width, _Height);
					_RLayout.alignWithParent = _AlignParent;
					_RLayout.setMargins(_Left, _Top, _Right, _Bottom);
					_Layout = _RLayout;
				} else if (_Layout instanceof android.widget.LinearLayout.LayoutParams) {
					LinearLayout.LayoutParams _LLayout = new android.widget.LinearLayout.LayoutParams(
							_Width, _Height);
					if (_Weight != -1)
						_LLayout.weight = _Weight;
					_LLayout.setMargins(_Left, _Top, _Right, _Bottom);
					_Layout = _LLayout;
				} else
					_Layout = new LayoutParams(_Width, _Height);
			}

		} catch (ClassCastException e) {
			if (S_DEBUG)
				Log.e("Sola", "View Set LayoutParams Error " + e.getMessage());
			_Layout = new LayoutParams(_Width, _Height);
		}
		return _Layout;
	}

	public final LayoutParams _GetF_Layout(View mView, int _Width, int _Height,
			int _Gravity, int _Left, int _Right, int _Bottom, int _Top) {
		return _GetLayout(mView, _Width, _Height, _Gravity, false, -1, _Left,
				_Right, _Bottom, _Top);
	}

	public final LayoutParams _GetF_Layout(View mView, int _Width, int _Height,
			int _Gravity) {
		return _GetF_Layout(mView, _Width, _Height, _Gravity, 0, 0, 0, 0);
	}

	public final LayoutParams _GetR_Layout(View mView, int _Width, int _Height,
			boolean _AlignParent, int _Left, int _Right, int _Bottom, int _Top) {
		return _GetLayout(mView, _Width, _Height, -1, _AlignParent, -1, _Left,
				_Right, _Bottom, _Top);
	}

	public final LayoutParams _GetL_Layout(View mView, int _Width, int _Height,
			float _Weight, int _Left, int _Right, int _Bottom, int _Top) {
		return _GetLayout(mView, _Width, _Height, -1, false, _Weight, _Left,
				_Right, _Bottom, _Top);
	}

	/************ View �ļ������ protect���� һ���õ����ֿؼ�����д���ֿؼ��ķ������� *************/
	protected void dealSpinner(final Spinner viewChild) {
	}

	protected void dealEdit(final EditText viewChild) {
	}

	protected void dealText(final TextView viewChild) {
	}

	protected void dealButton(final Button viewChild) {
		if (viewChild instanceof CheckBox)
			dealCheckBox((CheckBox) viewChild);
		else
			viewChild.setOnClickListener(this);
	}

	protected void dealCheckBox(final CheckBox viewChild) {
	}

	protected void dealImageBtn(final ImageButton viewChild) {
		viewChild.setOnClickListener(this);
	}

	protected void dealLinearLayout(final LinearLayout viewChild) {
	}

	protected void dealImageText(final ImageView viewChild) {
	}

	protected void dealSurfaceView(final android.view.SurfaceView viewChild) {
	}

	protected void dealProgress(final android.widget.ProgressBar viewChild) {
	}

	protected void dealGrid(final GridView viewChild) {
	}

	protected void dealList(final ListView viewChild) {
	}

	protected void dealPicker(final NumberPicker viewChild) {
	}

	protected void dealFrameLayout(final FrameLayout viewChild) {
	}

	protected void dealPager(final ViewPager viewChild) {
	}

}
