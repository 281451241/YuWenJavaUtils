package com.util;

public class GetString
{
	private String[] symbolList = {
			"，", "。", "？", "！", "、", 
			"：", "；", "“”", "……", ".", 
			"'", "@", "_", "/", "-", 
			
			"()", "《》", "【】", "[]", "{}", 
			"‘’", "－", "\\", "*", "#", 
			"&", "￥", "$", "~", "^", 
			
			":-)", ";-)", ":-(", ":-D", ":-p", 
			"-.-||", ":-x", ":-O", ":-*", "Orz", 
			"T_T", "$_$", "→_→", "?_?", ">_<", 
			
			"@_@", ">3<", "-︵-", "︶︿︶", "^◎-", 
			".", "www.", "wap.", ".com", ".cn", 
			"http://", ".com.cn", "bbs.", ".net", "blog.", 
			
			"＋", "%", "＝", "＜", "＞", 
			"|", "℃", "°", "`", "·", 
			"№", "‰", "®", "©", "の"
			};
	private void f() {
		int perPageSymCoutn = symbolList.length / 5;
		for(int i=0; i<perPageSymCoutn; i++)
		{
			p(i);
			System.out.println();
		}
	}
	
	private void p(int idx) {
		for(int i=0; i<5; i++)
		{
			if(i == 0)
				System.out.print("\"" + symbolList[idx + i*15] + "\"");
			else
				System.out.print(", \"" + symbolList[idx + i*15] + "\"");
		}
	}
	public static void main(String[] args)
	{
		GetString gs = new GetString();
		gs.f();
	}
}
