package com.smit.util;

public class Constants {

	// storage data in application context with those keys
	public final static String PUSH_DEV_CON = "push_dev_con"; 	// communication with server
	
	// data storage in session with this key
	public final static String LOGIN_SUC = "loginSuc";
	// status
	public final static String FAIL = "fail";
	public final static String SUCCESS = "success";
	
	public final static String CURUSERNAME = "current_user_name"; 	// String
	public final static String SERVER_NAME = "server_name"; 	// String
	public final static String CUR_USER_ID = "current_user_id";		// Integer
	public final static String IS_DEVELOPER	= "is_developer";		// Boolean
	public final static String CUR_GROUPNAME = "current_group_name";
	//  管理员的level是1；用户的level是2，开发者的level是3
	public final static String LEVEL = "level";
	public final static Integer LEVEL_ADMIN = 1;
	public final static Integer LEVEL_USER = 2;
	public final static Integer LEVEL_DEVELOPER = 3;
	
	
	public final static String OPERATOR = "opt";

	// for push service
	public final static String PUSH_GROUPNAME = "user";
	

	//public final static String PUSH_HOST = "192.168.0.198";//"192.168.0.181";

	public final static String PUSH_HOST = "localhost";//"192.168.0.181";

	public final static String PUSH_CONNECTION	= "pushConnection";
	public final static String PUSH_SERVERNAME = "server";
	public final static String PUSH_SERVERPASSWORD = "123456";
	
	// push service type
	public final static String PUSH_TYPE_MESSAGE = "message";
	public final static String PUSH_TYPE_NOTIFICATION = "nofity";
	public final static String PUSH_TYPE_ALERT = "alert";
	
	public final static String PUSH_CLOCK_KEY = "T3aXoTF0oz8nIbqCBdEq34a00O67raaa";
	
	public final static String RINGS_PATH_TEMP = "upload/temp";
	public final static String RINGS_PATH = "upload/rings";
	public final static String APK_PATH = "upload/apk";
	
	
}
