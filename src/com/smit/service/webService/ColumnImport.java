package com.smit.service.webService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.smit.service.ColumnService;
import com.smit.vo.Part;

public class ColumnImport {
	
	private String name;
	private Integer partId;
	private Integer rootId;
	private List<String> firstName = new ArrayList<String>();
	private List<String> firstPartId = new ArrayList<String>();
	private List<String> secondName = new ArrayList<String>();
	private List<String> secondPartId = new ArrayList<String>();
	private ColumnService columnService;
	


	public void setTopColumn(String name,Integer partId,ColumnService columnService){
		this.name = name;
		this.partId = partId;
		this.columnService = columnService;
	}
	
	public void addFirstColumn(String name, String partId){
		firstName.add(name);
		firstPartId.add(partId);
	}
	
	public void addSecondColumn(String name, String partId){
		secondName.add(name);
		secondPartId.add(partId);
	}
	
	
	public void importColumn() throws Exception{
		
		Part part;		
		part = columnService.queryRootColumn();
		
		Part partTop = new Part();
		partTop.setFather_id(part.getId());
		partTop.setPartId(partId);
		partTop.setTypename(name);
		columnService.savePart(partTop);
		
		int i = 0;
		String partId;
		for(String name : firstName){
			partId = firstPartId.get(i);
			i++;
			Part p = new Part();
			p.setTypename(name);
			p.setPartId(Integer.valueOf(partId));
			p.setFather_id(partTop.getId());
			p.setTopid(partTop.getId());
			columnService.savePart(p);
		}
	}
	
	

}
