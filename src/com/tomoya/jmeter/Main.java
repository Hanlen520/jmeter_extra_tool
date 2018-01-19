package com.tomoya.jmeter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	public static void main(String[] args) {
		String  aaaaa  = "aa";
		System.out.println("a的变量地址: " + aaaaa.hashCode());
		/*
		 * System.out.println("getChineseName(): " + Tool.getChineseName());
		 * System.out.println("getHashCode(): " + Tool.getHashCode("文本"));
		 * System.out.println("getMD5(): " + Tool.getMD5("文本"));
		 * System.out.println("getMD5Cap(): " + Tool.getMD5Cap("文本"));
		 * System.out.println("getType(): " + Tool.getType("文本"));
		 * 
		 * // JMETER JDBC方法测试 HashMap HM1 = new HashMap(); HM1.put("id", 10001);
		 * HM1.put("name", "chen");
		 * 
		 * HashMap HM2 = new HashMap(); HM2.put("id", 10002); HM2.put("name", "xu");
		 * 
		 * ArrayList AL = new ArrayList(); AL.add(HM1); AL.add(HM2);
		 * 
		 * System.out.println(Tool.JDBCResultToSql(AL, "id"));
		 */
		// Tool.writeFile("文本", "C:\\Users\\FCD\\Desktop\\文本.txt");
		// Tool.JDBCResultWriteFile(AL, "id", "C:\\Users\\FCD\\Desktop\\strID.txt");

		// String[] strArray = {"id", "name"};
		// Tool.JDBCResultWriteFile(AL, strArray,
		// "C:\\Users\\FCD\\Desktop\\strArray.txt");

		/*
		 * String[] rowArray = { "Case 1", "Post id = 1", "200", "响应打算sadsasda" };
		 * String filePath = "E://test2.xls"; String[] title = { "用例", "请求", "状态码", "响应"
		 * };
		 * 
		 * 
		 * try { Excel.writeToExcel(title, rowArray, filePath); } catch (Exception e) {
		 * // TODO Auto-generated catch block e.printStackTrace(); }
		 */
		/*
		 * System.out.println(Excel.fileExist("E:/1/1/test2.xls")); String[] title =
		 * {"id","name","password"}; List<Map> list=new ArrayList<Map>();
		 * Map<String,String> map=new HashMap<String,String>(); map.put("id", "111");
		 * map.put("name", "张三"); map.put("password", "111！@#");
		 * 
		 * Map<String,String> map2=new HashMap<String,String>(); map2.put("id", "222");
		 * map2.put("name", "李四"); map2.put("password", "222！@#"); list.add(map);
		 * list.add(map2); try { Excel.writeToExcel2(title, list, "E:/1/1/test2.xls"); }
		 * catch (Exception e) { e.printStackTrace(); }
		 */

		// Tool.writeFile2("大发送撒旦四", "C:\\Users\\FCD\\Desktop\\文本.txt");
		/*
		 * System.out.println(Tool.getDate(-1, "yyyy-MM-dd"));
		 * System.out.println(Tool.getDate(0)); System.out.println(Tool.getDate(1));
		 * 
		 * System.out.println(Tool.getMobile()); System.out.println(Tool.getEmail(5,
		 * 10));
		 * 
		 * IdCard g = new IdCard(); for (int i = 0; i < 3; i++) {
		 * System.out.println(g.getIdCard()); }
		 * 
		 * System.out.println(IdCard.getIdCard());
		 * System.out.println(IdCard.getIdCard("上海市"));
		 * 
		 * //////////////////
		 * 
		 * String source =
		 * "m_threads\">${__P(willStartThreads,50)}</stringProp>${__P(wasdi1teads,)}123213${__P(123123213,)}";
		 * String regex = "\\$\\{__P\\((.*?),(.*?)\\)\\}";\
		 * System.out.println(Tool.getRegexResult(regex, source, 1)); ///////
		 * 
		 * System.out.println(Tool.isSpecialChar("!@"));
		 */
		//System.out.println(Tool.getRegexResult("SmsCode\":\"(.+?)\",", "SmsCode\":\"895848\","));
		
		int money = 500000000;
		int ren = 1;
		int[] play = qiangRedBag(money, ren);
		System.out.println("红包金额为" + (double) (money / 100) + "元, " + "总共" + ren + "个人抢! ");
		for (int i = 0; i < play.length; i++) {
			System.out.println("第" + (i + 1) + "个人领取的红包是: " + (double) play[i] / 100 + "元");
		}
		
		try {
			Tool.writeFile("A", "C:\\Users\\FCD\\Desktop\\companyId.txt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		Redis re = new Redis();
//		re.HOST = "192.168.1.105";
//		System.out.println(re.HOST);
		
		

		
		//生成数据
		long mobileIndex = 13800140001L;
		int mobileLoop = 0;
		String mobile = "";
		for (int i = 0; i < mobileLoop; i++) {
			mobile += (mobileIndex + i);
			if (i < mobileLoop - 1) {
				mobile += "\r\n";
			}
		
		}
		
		System.out.println(mobile);
		try {
			Tool.writeFile(mobile, "C:\\Users\\FCD\\Desktop\\mobile4.txt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



//		//坐标系
//		Redis reZBX = new Redis();
//		System.out.println(reZBX.getRedisZBX(1,"18651191111"));
//		
//		//其他
//		Redis re1 = new Redis("192.168.1.105", 6379, "qk365.com");
//		System.out.println(re1.getRedis("n:VerifyCode,c:VerifyCode:1:18651191111", "SmsCode\":\"(.+?)\","));
//		
//		//其他2
//		Redis re2 = new Redis("192.168.1.117", 6379);
//		System.out.println(re2.getRedis("merchant:00101"));

		
//		//获取验证码
//		
//		long mobileIndex2 = 13800130001L;
//		int mobileLoop2 = 5000;
//		String mobile2 = "";
//		Redis re = new Redis();
//		re.setConfig("192.168.1.135", 6379, "qk365.com");
//		for (int i = 0; i < mobileLoop2; i++) {
//			mobile2 = (mobileIndex2 + i) + "," + re.getRedis(2,mobileIndex2 + i + "");
//			if (i < mobileLoop2 - 1) {
//				mobile2 += "\r\n";
//			}
//		
//		}
//		System.out.println(mobile2);
//
//		try {
//			Tool.writeFile(mobile2, "C:\\Users\\FCD\\Desktop\\mobilesms.txt");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		System.out.println(Tool.ifInclude("dsadabcsadsa","abc"));

	}
	
	public static int[] qiangRedBag(int money, int renShu) {
		int[] moneyPerRen = new int[renShu];
		int moneyTmp = money;
		for (int i = 0; i < renShu - 1; i++) {
			int tmp = moneyTmp / (renShu - i);
			moneyPerRen[i] = (int) (Math.random() * tmp) + 1;
			moneyTmp = moneyTmp - moneyPerRen[i];
		}
		moneyPerRen[renShu - 1] = moneyTmp;

		return moneyPerRen;

	}
	
	
	;
}
