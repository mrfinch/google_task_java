package com.google.api.services.samples.tasks.cmdline;

public class mycontainer{
	private String m_title;
	private String m_id;
	
	public mycontainer(String s,String i){
		m_title = s;
		m_id = i;
	}
	
	public String getMName(){
		return m_title;
	}
	
	public String getMId(){
		return m_id;
	}
}
