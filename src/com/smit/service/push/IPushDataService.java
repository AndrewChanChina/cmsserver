package com.smit.service.push;

import java.util.List;

import org.jivesoftware.smack.XMPPConnection;

/**
 * this service provider all push function. 
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
}
