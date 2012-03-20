<?xml version="1.0" encoding="UTF-8"?>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<ehotel>
  <status data="success"/>
  <hotelLogo data="http://192.168.0.195:8080/pushcmsserver/media/logo.png"/>
  <welcomeMusic data="http://192.168.0.195:8080/pushcmsserver/media/dream.mp3"/>
  <birthdayMusic data="http://192.168.0.195:8080/pushcmsserver/media/poweron.wav"/>
</ehotel>

