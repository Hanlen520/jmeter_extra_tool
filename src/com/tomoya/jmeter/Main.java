package com.tomoya.jmeter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Main {
	
	public static void main(String[] args){

		System.out.println("getChineseName(): " + Tool.getChineseName());
		System.out.println("getHashCode(): " + Tool.getHashCode("文本"));
		System.out.println("getMD5(): " + Tool.getMD5("文本"));
		System.out.println("getMD5Cap(): " + Tool.getMD5Cap("文本"));
		System.out.println("getType(): " + Tool.getType("文本"));

		
		//JMETER JDBC方法测试
		HashMap HM1 = new HashMap();
		HM1.put("id", 10001);
		HM1.put("name", "chen");
		
		HashMap HM2 = new HashMap();
		HM2.put("id", 10002);
		HM2.put("name", "xu");
		
		ArrayList AL = new ArrayList();
		AL.add(HM1);
		AL.add(HM2);
		
		//Tool.writeFile("文本", "C:\\Users\\FCD\\Desktop\\文本.txt");
		//Tool.JDBCResultWriteFile(AL, "id", "C:\\Users\\FCD\\Desktop\\strID.txt");
		
		//String[] strArray =  {"id", "name"};
		//Tool.JDBCResultWriteFile(AL, strArray, "C:\\Users\\FCD\\Desktop\\strArray.txt");
		

		/*
		String[] rowArray = { "Case 1", "Post id = 1", "200", "响应打算sadsasda" };
		String filePath = "E://test2.xls";
		String[] title = { "用例", "请求", "状态码", "响应" };


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
	        map.put("name", "张三");
	        map.put("password", "111！@#");
	        
	        Map<String,String> map2=new HashMap<String,String>();
	        map2.put("id", "222");
	        map2.put("name", "李四");
	        map2.put("password", "222！@#");
	        list.add(map);
	        list.add(map2);
	        try {
				Excel.writeToExcel2(title, list, "E:/1/1/test2.xls");
			} catch (Exception e) {
				e.printStackTrace();
			}  
			*/
		
		//Tool.writeFile2("大发送撒旦四", "C:\\Users\\FCD\\Desktop\\文本.txt");
		
	    
	      
		System.out.println(Tool.getDate(-1, "yyyy-MM-dd"));
		System.out.println(Tool.getDate(0));
		System.out.println(Tool.getDate(1));

        

	}
}
