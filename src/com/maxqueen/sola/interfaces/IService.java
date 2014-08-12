package com.maxqueen.sola.interfaces;

public interface IService<Result> extends IS_DEBUG {

	int TIME_OUT = 20 * 1000;

	/**
	 * ���ӵķ��غ���
	 * 
	 * @return
	 */
	public Result getRequest();

	/**
	 * �������紫��Э��Title
	 * 
	 * @return
	 */
	public String buildProtocol();

	/**
	 * ���÷��������Ip��ַ ����192.168.1.1����
	 * 
	 * @return
	 */
	public String buildBasicIp();

	/**
	 * �������紫���Ip��ַ�˿�
	 * 
	 * @return
	 */
	public int buildBasicPort();

	/**
	 * ������·�����Service
	 * 
	 * @return
	 */
	public String buildBasicService();

	/**
	 * �����������·��
	 * 
	 * @return
	 */
	public String buildBasicPath();

	/**
	 * ����������ʵĽӿڷ���
	 * 
	 * @return
	 */
	public String buildBasicMethod();

}
