/**
 * <p>Title: IListener.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: www.maxqueen.com</p>
 * @author Sola
 * @date 2013-10-11
 * @version 1.0
 */
package com.maxqueen.sola.interfaces;

/**
 * <p>
 * Title: IListener
 * </p>
 * <p>
 * Description:�����߽ӿ�,�����ɼ�����ȥ�̳�
 * </p>
 * <p>
 * Company: www.maxqueen.com
 * </p>
 * 
 * @author Sola
 * @date 2013-10-11
 */
public interface IListener extends IRunning {

	/**
	 * �û��ɸ������������Զ���ʵ��,������ݼ��������̳еĽӿ�ȥ��Ӧ������,һ�㲻�Ƽ����۵��ø÷���
	 * ע��:�����۵��ø÷�����ʱ��,�����ע���̰߳�ȫ����
	 * 
	 * @param binaryValue
	 *            ����ֵ
	 * @param obj
	 *            ����
	 */
	void responseInteractiveData(int binaryValue, Object obj);

	/**
	 * ���ص���������ֵ,���Ը��ݾ���������
	 * 
	 * @return
	 */
	int getListenerId();
}
