package com.smit.service.push;

import java.util.List;

import org.jivesoftware.smack.XMPPConnection;

public interface IPubSubService {

	 public void setConnection(XMPPConnection con);
	 public List<String> getTopics();
	 public boolean createTopic(String topicName);
	 public boolean deleteTopic(String topic);
	 public void sendTopic(String topic,String message);
}
