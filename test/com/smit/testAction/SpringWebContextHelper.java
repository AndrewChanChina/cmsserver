package com.smit.testAction;

import java.io.File;
import java.io.FilenameFilter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

public class SpringWebContextHelper {
	private static XmlWebApplicationContext wac = null;

	public static void setWebApplicationContext(ServletContext context) {

		if (context
				.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE) != null) {
			return;
		}
		if (wac == null) {
			wac = new XmlWebApplicationContext();
			wac.setServletContext(context);
			URL classUrl = SpringWebContextHelper.class.getResource("");
			String path = classUrl.getPath();
			try {
				path = URLDecoder.decode(path, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			path = path.substring(0, path.indexOf("WEB-INF")) + "WEB-INF/";
			path = "/E:/Myeclipse/SMITService/CMSServer/src/";
			File configPath = new File(path);
			String[] applicationContexts = configPath
					.list(new FilenameFilter() {
						public boolean accept(File dir, String name) {
							if (name.toLowerCase().startsWith(
									"applicationcontext")) {
								return true;
							}
							return false;
						}
					});
			for (int i = 0; i < applicationContexts.length; i++) {
				applicationContexts[i] = path 
						+ applicationContexts[i];
			}
			wac.setConfigLocations(applicationContexts);
			wac.refresh();
		}

		context.setAttribute(
				WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE,
				wac);
	}

}
