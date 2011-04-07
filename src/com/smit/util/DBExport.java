package com.smit.util;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;


/**
 * 生成表工具类
 * @author ligm
 * @date 2011-4-1
 * 使用说明，写好hibernate映射配置，切换到myeclipse database视图，打开连接即可
 */
public class DBExport {
	public static void main(String[] args){
		Configuration cfg = new Configuration().configure();
		SchemaExport export = new SchemaExport(cfg);
		export.create(true, true);
	}

}
