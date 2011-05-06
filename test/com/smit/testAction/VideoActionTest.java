package com.smit.testAction;

import java.io.File;

import servletunit.struts.MockStrutsTestCase;

public class VideoActionTest extends MockStrutsTestCase {
	
	public void setUp() throws Exception {
        super.setUp();
        setInitParameter("validating","false");
        setContextDirectory(new File("WebRoot"));
        //this.setConfigFile("/WEB-INF/struts-config.xml");
        SpringWebContextHelper.setWebApplicationContext(context); 
        
    }
	
	 public void testSuccessfulLogin() {
	        //addRequestParameter("username","deryl");
	        //addRequestParameter("password","radar");
	        setRequestPathInfo("/video");
	        actionPerform();
	        verifyForward("success");
	        //verifyForwardPath("/main/success.jsp");
	        //assertEquals("deryl",getSession().getAttribute("authentication"));
	        verifyNoActionErrors();
	 }
	 
	 public static void main(String[] args) {
	        junit.textui.TestRunner.run(VideoActionTest.class);
	 }

}
