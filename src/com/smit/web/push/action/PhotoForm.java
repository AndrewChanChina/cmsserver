package com.smit.web.push.action;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class PhotoForm extends ActionForm{

	private FormFile file;

	public FormFile getFile() {
		return file;
	}

	public void setFile(FormFile file) {
		this.file = file;
	}
	
}
