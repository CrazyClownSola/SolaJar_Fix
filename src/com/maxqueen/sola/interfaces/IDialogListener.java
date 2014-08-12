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
	 * �����¼� һ����ȡ����ť�������¼�
	 * 
	 * @param FG_Id
	 *            ����Dialog��ʾ��Fragment��Id�ţ�һ�����ں͵����ߵĽ���ʱ
	 * @param obj
	 *            ���������Ĳ�������
	 */
	public void onNegative(int FG_Id, Object obj);

	/**
	 * �����¼� һ����ȷ�ϰ�ť�������¼�
	 * 
	 * @param FG_Id
	 *            ����Dialog��ʾ��Fragment��Id�ţ�һ�����ں͵����ߵĽ���ʱ
	 * @param obj
	 *            ���������Ĳ�������
	 */
	public void onPositive(int FG_Id, Object obj);

	/**
	 * �Զ����¼� һ���������Զ��尴ť�������¼�
	 * 
	 * @param FG_Id
	 *            ����Dialog��ʾ��Fragment��Id�ţ�һ�����ں͵����ߵĽ���ʱ
	 * @param _BtnStr
	 *            ��ť��String ����ֵ
	 */
	public void onSpecial(int FG_Id, int _BtnStr);
}
