package com.tomoya.jmeter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
		
		//Tool.writeFile("�ı�", "C:\\Users\\FCD\\Desktop\\�ı�.txt");
		//Tool.JDBCResultWriteFile(AL, "id", "C:\\Users\\FCD\\Desktop\\strID.txt");
		
		//String[] strArray =  {"id", "name"};
		//Tool.JDBCResultWriteFile(AL, strArray, "C:\\Users\\FCD\\Desktop\\strArray.txt");
		

		/*
		String[] rowArray = { "Case 1", "Post id = 1", "200", "��Ӧ����sadsasda" };
		String filePath = "E://test2.xls";
		String[] title = { "����", "����", "״̬��", "��Ӧ" };


		try {
			Excel.writeToExcel(title, rowArray, filePath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
			/*
	        System.out.println(Excel.fileExist("E:/1/1/test2.xls"));   
	        String[] title = {"id","name","password"};  
	        List<Map> list=new ArrayList<Map>();
	        Map<String,String> map=new HashMap<String,String>();
	        map.put("id", "111");
	        map.put("name", "����");
	        map.put("password", "111��@#");
	        
	        Map<String,String> map2=new HashMap<String,String>();
	        map2.put("id", "222");
	        map2.put("name", "����");
	        map2.put("password", "222��@#");
	        list.add(map);
	        list.add(map2);
	        try {
				Excel.writeToExcel2(title, list, "E:/1/1/test2.xls");
			} catch (Exception e) {
				e.printStackTrace();
			}  
			*/
		
		//Tool.writeFile2("����������", "C:\\Users\\FCD\\Desktop\\�ı�.txt");
		
	    
	      
		System.out.println(Tool.getDate(-1, "yyyy-MM-dd"));
		System.out.println(Tool.getDate(0));
		System.out.println(Tool.getDate(1));

        

	}
}
