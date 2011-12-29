package com.smit.vo;

public class Menu {

	private int id;
	private String menu_name;
	private int menu_type;
	private String type_name;
	private String href;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMenu_name() {
		return menu_name;
	}
	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}
	
	public int getMenu_type() {
		return menu_type;
	}
	public void setMenu_type(int menu_type) {
		this.menu_type = menu_type;
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	
	
}
