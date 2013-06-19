package com.lyw.json;

import java.util.LinkedList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.sdicons.json.mapper.JSONMapper;
import com.sdicons.json.mapper.MapperException;
import com.sdicons.json.model.JSONValue;

public class T
{
	public static void main(String[] args)
	{
		T t = new T();
		t.list.add("5");
		t.list.add("2");
		t.list.add("3");
		t.list.add("4");
		t.t1(t.t());
	}

	private void test() throws MapperException
	{
		JSONObject obj = new JSONObject();
//		obj.setData("aaaaaa");

		JSONValue value = JSONMapper.toJSON(obj);
		String jsonStr = value.render(true);
		System.out.println(jsonStr);
	}

	private String t()
	{
		org.json.JSONObject obj = new org.json.JSONObject();
		obj.put("a", "bb");
		obj.put("aa", 1);
		org.json.JSONObject obj1 = new org.json.JSONObject();
		obj1.put("a", "bb");
		obj1.put("a2", obj);

		return obj1.toString();
	}

	private void t1(String str)
	{
		org.json.JSONObject obj = new org.json.JSONObject(str);
		// obj.put("c", 1);

		System.out.println(obj.toString());
		// System.out.println(obj.get("aa"));
	}

	public void jsonArray()
	{
		JSONArray jsonArr = new JSONArray(num);
		
		System.out.println(jsonArr.toString());
	}

	private String[] num = { "1adfadfasdfsafsdfdsfsdfsf", "2", "5", "3", "4" };
	
	private LinkedList<String> list = new LinkedList<String>();
}
