package com.smit.service.push;

import java.util.List;

import org.jivesoftware.smack.XMPPConnection;

/**
 * this service provider all push function. 
 * 用来登录服务器用的
 * @since 2011年5月12日15:25:01
 * @author chenyzpower@gmail.com
 *
 */
public interface IPushDataService {

	public boolean login(String host,String user,String password);
	public boolean logout();
	public boolean isConnected();
	public boolean sendPushDataFromDevToAll(
			String pushServiceName,
			boolean bDelay,
			String collapseKey,
			String title,
			String ticker,
			String uri,
			String message);
	public boolean sendPushDataFromDev(
			String pushServiceName,
			List<String> pushIdList,
			boolean bDelay,
			String collapseKey,
			String title,
			String ticker,
			String uri,
			String message);
	public boolean sendPushDataFromUser(
			List<String> userList,
			boolean bDelay,
			String collapseKey,
			String title,
			String ticker,
			String uri,
			String message,
			boolean bNotify);
	public XMPPConnection getConnection();
	public boolean sendQueryResourceId(String userName);
	public boolean sendPushServiceInf(String serviceName,String account);
}
