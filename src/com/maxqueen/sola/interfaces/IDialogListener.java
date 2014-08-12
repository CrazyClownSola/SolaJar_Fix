/**
 * <p>Title: IDialogEventListener.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: www.maxqueen.com</p>
 * @author Sola
 * @date 2013-8-15
 * @version 1.0
 */
package com.maxqueen.sola.interfaces;

/**
 * <p>
 * Title: IDialogEventListener
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: www.maxqueen.com
 * </p>
 * 
 * @author Sola
 * @date 2013-8-15
 */
public interface IDialogListener extends IListener {
	/**
	 * 消极事件 一般由取消按钮引发的事件
	 * 
	 * @param FG_Id
	 *            触发Dialog显示的Fragment的Id号，一般用在和调用者的交互时
	 * @param obj
	 *            交互所带的参数数据
	 */
	public void onNegative(int FG_Id, Object obj);

	/**
	 * 积极事件 一般由确认按钮引发的事件
	 * 
	 * @param FG_Id
	 *            触发Dialog显示的Fragment的Id号，一般用在和调用者的交互时
	 * @param obj
	 *            交互所带的参数数据
	 */
	public void onPositive(int FG_Id, Object obj);

	/**
	 * 自定义事件 一般由特殊自定义按钮引发的事件
	 * 
	 * @param FG_Id
	 *            触发Dialog显示的Fragment的Id号，一般用在和调用者的交互时
	 * @param _BtnStr
	 *            按钮的String 索引值
	 */
	public void onSpecial(int FG_Id, int _BtnStr);
}
