package com.smit.util;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;


/**
 * ���ɱ�����
 * @author ligm
 * @date 2011-4-1
 * ʹ��˵����д��hibernateӳ�����ã��л���myeclipse database��ͼ�������Ӽ���
 */
public class DBExport {
	public static void main(String[] args){
		Configuration cfg = new Configuration().configure();
		SchemaExport export = new SchemaExport(cfg);
		export.create(true, true);
	}

}
