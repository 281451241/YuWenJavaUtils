package com.lyw.io.socket;

import java.io.Serializable;

public class User implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	
	public void setId(int i) {
		id = i;
	}
	
	public void setName(String str)
	{
		name = str;
	}
	
	public String getName() {
		return name;
	}
}