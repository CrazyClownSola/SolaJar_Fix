package com.maxqueen.sola.interfaces;

public interface IService<Result> extends IS_DEBUG {

	int TIME_OUT = 20 * 1000;

	/**
	 * 连接的返回函数
	 * 
	 * @return
	 */
	public Result getRequest();

	/**
	 * 设置网络传出协议Title
	 * 
	 * @return
	 */
	public String buildProtocol();

	/**
	 * 设置访问网络的Ip地址 类似192.168.1.1这种
	 * 
	 * @return
	 */
	public String buildBasicIp();

	/**
	 * 设置网络传输的Ip地址端口
	 * 
	 * @return
	 */
	public int buildBasicPort();

	/**
	 * 设置网路传输的Service
	 * 
	 * @return
	 */
	public String buildBasicService();

	/**
	 * 设置网络访问路径
	 * 
	 * @return
	 */
	public String buildBasicPath();

	/**
	 * 设置网络访问的接口方法
	 * 
	 * @return
	 */
	public String buildBasicMethod();

}
