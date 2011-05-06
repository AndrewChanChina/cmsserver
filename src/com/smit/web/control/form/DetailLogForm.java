package com.smit.web.control.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class DetailLogForm extends ActionForm{
	private FormFile upload;

	public FormFile getUpload() {
		return upload;
	}

	public void setUpload(FormFile upload) {
		this.upload = upload;
	}
	
}
