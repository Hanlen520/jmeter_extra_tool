package com.tomoya.jmeter;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {
	
	public static void main(String[] args){
		System.out.println(Tool.getHashCode("aaaa"));
		System.out.println(Tool.getType("aaaa"));
		System.out.println(Tool.getMD5("à»à»à»"));
		System.out.println(Tool.getChineseName());
		
		/*
		String aaaaa = RSA.priKeyEncrypt("aaaaa", "C:\\Users\\FCD\\Desktop\\key");
		System.out.println(aaaaa);
		System.out.println(RSA.pubKeyDecrypt(aaaaa, "C:\\Users\\FCD\\Desktop\\key"));
		
		
		String bbbbb = RSA.pubKeyEncrypt("bbbbb", "C:\\Users\\FCD\\Desktop\\key");
		System.out.println(bbbbb);
		System.out.println(RSA.priKeyDecrypt(bbbbb, "C:\\Users\\FCD\\Desktop\\key"));
		*/


		HashMap bbb = new HashMap();

		bbb.put("id", 111);
		bbb.put("name", "chen");
		
		HashMap ccc = new HashMap();
		ccc.put("id", 222);
		ccc.put("name", "xu");
		
		ArrayList aaa = new ArrayList();
		aaa.add(bbb);
		aaa.add(ccc);
		System.out.println(aaa);
		System.out.println(bbb);
		System.out.println(ccc);
		Tool.JDBCResultWriteFile(aaa, "id", "C:\\Users\\chen\\Desktop\\debug1.txt");
		String[] array =  {"id", "name"};
		String sad = Tool.JDBCResultToString(aaa, array);
		System.out.println(sad);
		Tool.JDBCResultWriteFile(aaa, array, "C:\\Users\\chen\\Desktop\\debug2.txt");
		System.out.println("Git Done");

	}
}
