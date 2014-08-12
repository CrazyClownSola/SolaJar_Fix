/**
 * <p>Title: IPublisher.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: www.maxqueen.com</p>
 * @author Sola
 * @date 2014-1-23
 * @version 1.0
 */
package com.maxqueen.sola.interfaces;

import android.os.Handler;

/**
 * <p>
 * Title: IPublisher
 * </p>
 * <p>
 * Description:�����߽ӿ�
 * </p>
 * <p>
 * Company: www.maxqueen.com
 * </p>
 * 
 * @author Sola
 * @date 2014-1-23
 */
public interface IPublisher {

	/**
	 * �ڲ��������ͨ��{@link Handler}���� ����{@link Publisher#_IList}�����ж���,�������۵���
	 * {@link IListener#responseInteractiveData(int, Object)}����,�˷������ƹ㲥
	 * 
	 * @param binaryValue
	 *            ����ֵ
	 * @param obj
	 *            ����
	 */
	void submitDataInteraction(int binaryValue, Object obj);

	/**
	 * �ڲ��������ͨ��{@link Handler}���� ����{@link Publisher#_IList}���Ƿ�ӵ��keyΪ{@value
	 * listenerId}�Ķ��� �����,�����۵���
	 * {@link IListener#responseInteractiveData(int, Object)}����
	 * 
	 * @param binaryValue
	 *            ����ֵ
	 * @param listenerId
	 *            ������Id
	 * @param obj
	 *            ����
	 */
	void submitDataInteraction(int binaryValue, int listenerId, Object obj);

	/**
	 * �̳иýӿڵ�����벢���ø÷���,���ܴ���ѭ����,�򷢲����Ƴ��������
	 * 
	 * @param _Listener
	 */
	public void RemovePosCallBack(IListener _Listener);

	/**
	 * �̳иýӿڵ�����벢���ø÷���,���ܴ���ѭ����,�����򷢲���ע���Լ�
	 * 
	 * @param _Listener
	 */
	public void RegistePosCallBack(IListener _Listener);
}
