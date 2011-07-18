package com.smit.web.pubsubhubbub;

public class HubConstraint {

	public static final String HUB_MODE_PARAM = "hub.mode";
	public static final String HUB_URL_PARAM = "hub.url";
	public static final String HUB_CALLBACK_PARAM = "hub.callback";
	public static final String HUB_TOPIC_PARAM = "hub.topic";
	public static final String HUB_CHALLENGE_PARAM = "hub.challenge";
	public static final String HUB_VERIFY = "hub.verify";//value:sync,async
	//可选项
	public static final String HUB_SECRET = "hub.secret";
	public static final String HUB_LEASE_SECONDS = "hub.lease_seconds";
	public static final String HUB_VERIFY_TOKEN = "hub.verify_token";
}
