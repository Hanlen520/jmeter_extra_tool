package com.tomoya.jmeter;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {
	
	public static void main(String[] args){

		System.out.println("getChineseName(): " + Tool.getChineseName());
		System.out.println("getHashCode(): " + Tool.getHashCode("�ı�"));
		System.out.println("getMD5(): " + Tool.getMD5("�ı�"));
		System.out.println("getMD5Cap(): " + Tool.getMD5Cap("�ı�"));
		System.out.println("getType(): " + Tool.getType("�ı�"));

		//JMETER JDBC��������
		HashMap HM1 = new HashMap();
		HM1.put("id", 10001);
		HM1.put("name", "chen");
		
		HashMap HM2 = new HashMap();
		HM2.put("id", 10002);
		HM2.put("name", "xu");
		
		ArrayList AL = new ArrayList();
		AL.add(HM1);
		AL.add(HM2);
		
		Tool.writeFile("�ı�", "C:\\Users\\FCD\\Desktop\\�ı�.txt");
		Tool.JDBCResultWriteFile(AL, "id", "C:\\Users\\FCD\\Desktop\\strID.txt");
		
		String[] strArray =  {"id", "name"};
		Tool.JDBCResultWriteFile(AL, strArray, "C:\\Users\\FCD\\Desktop\\strArray.txt");

	}
}
