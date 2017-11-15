package com.tomoya.jmeter;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.*;
import java.text.SimpleDateFormat;

/**
 * ����Jmeter�ṩ�Ĳ��Թ�����
 * 
 * @author tomoya
 *
 */
public class Tool {

	private Tool() {
	};

	/**
	 * ������������
	 * 
	 * @param obj
	 *            ����
	 * @return ��������
	 */
	public static String getType(Object obj) {
		return obj.getClass().getName();
	}

	/**
	 * ����hashCode�ľ���ֵ
	 * 
	 * @param str
	 *            �ַ���
	 * @return hashCode�ľ���ֵ
	 */
	public static int getHashCode(String str) {
		return Math.abs(str.hashCode());
	}

	/**
	 * ����32λСдmd5ֵ
	 * 
	 * @param str
	 *            �ַ���
	 * @return MD5ֵ
	 */
	public static String getMD5(String str) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
			// UTF-8���룬����Ӱ����������MD5
			md5.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte b[] = md5.digest();
		int i;
		StringBuffer buf = new StringBuffer("");
		for (int offset = 0; offset < b.length; offset++) {
			i = b[offset];
			if (i < 0)
				i += 256;
			if (i < 16)
				buf.append("0");
			buf.append(Integer.toHexString(i));
		}

		return buf.toString();
	}

	/**
	 * ����32λ��дmd5ֵ
	 * 
	 * @param str
	 *            �ַ���
	 * @return MD5ֵ
	 */
	public static String getMD5Cap(String str) {
		return getMD5(str).toUpperCase();
	}

	/**
	 * ��ȡ2~4���ֵ��������������ְ棨����Ϊ����ʻ����ڣ�
	 * 
	 * @return ������
	 */
	public static String getChineseName() {
		Random random = new Random(System.currentTimeMillis());

		String[] Surname = { "��", "Ǯ", "��", "��", "��", "��", "֣", "��", "��", "��", "����" };

		int index = random.nextInt(Surname.length);
		String name = Surname[index];

		if (random.nextBoolean()) {
			name = name + getChineseWord() + getChineseWord();
		} else {
			name = name + getChineseWord();
		}

		return name;
	}

	/**
	 * ��ȡ�������ú���
	 * 
	 * @return �������ú���
	 */
	private static String getChineseWord() {
		Random random = new Random();
		String[] firstName = { "һ", "��", "��", "��", "��", "��", "��", "��", "��", "ʮ", "��", "��", "��", "��", "��", "��", "��", "Ԫ",
				"ר", "��", "��", "��", "ľ", "��", "֧", "��", "��", "̫", "Ȯ", "��", "��", "��", "��", "ƥ", "��", "��", "��", "��", "��",
				"��", "��", "��", "ֹ", "��", "��", "��", "��", "��", "��", "ˮ", "��", "��", "ţ", "��", "ë", "��", "��", "��", "��", "ʲ",
				"Ƭ", "��", "��", "��", "��", "��", "��", "��", "צ", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��",
				"��", "Ƿ", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "Ϊ", "��", "��", "��", "��", "��", "��", "��", "��",
				"��", "��", "��", "��", "��", "��", "��", "��", "��", "Ȱ", "˫", "��", "��", "ǧ", "��", "��", "��", "��" };

		int index = random.nextInt(firstName.length);
		return firstName[index];

	}

	/**
	 * ���ַ���д���ļ������ǣ�
	 * 
	 * @param str
	 *            �ַ���
	 * @param filePath
	 *            �ļ�·��
	 * @throws Exception
	 *             �׳��쳣
	 */
	public static void writeFile(String str, String filePath) throws Exception {

		// �ļ�·�����ļ��в�����ʱ�������ļ���
		Tool.createNewFile(filePath);

		// д���ļ�.
		String WRITE_TO_FILE = str;
		String FILE_PATH = filePath;

		try {
			File file = new File(FILE_PATH);
			if (!file.exists()) {
				file.createNewFile();
			}

			boolean isExist = false;

			if (!isExist) {
				FileOutputStream fos = new FileOutputStream(file.getAbsoluteFile(), false);
				fos.write(WRITE_TO_FILE.getBytes());
				fos.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * ���ַ���д���ļ�������д�룩
	 * 
	 * @param str
	 *            �ַ���
	 * @param filePath
	 *            �ļ�·��
	 * @throws Exception
	 *             �׳��쳣
	 */
	public static void writeFile2(String str, String filePath) throws Exception {

		// �ļ�·�����ļ��в�����ʱ�������ļ���
		Tool.createNewFile(filePath);

		// д���ļ�.
		String WRITE_TO_FILE = str;
		String FILE_PATH = filePath;

		try {
			File file = new File(FILE_PATH);
			if (!file.exists()) {
				file.createNewFile();
			}

			boolean isExist = false;

			if (!isExist) {
				FileOutputStream fos = new FileOutputStream(file.getAbsoluteFile(), true);
				fos.write(WRITE_TO_FILE.getBytes());
				fos.write("\r\n".getBytes());
				fos.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * ��Jmeter��JDBC Result��ָ���У�ת���ɵ��ж��е��ַ���
	 * 
	 * @param list
	 *            JDBC���ص�ArrayList��
	 * @param key
	 *            JDBC���ص��ֶ���
	 * @return ���ж��е��ַ���
	 */
	public static String JDBCResultToString(ArrayList list, String key) {

		// �������ݿ��JSON���
		ArrayList DBResultList = list;

		// ƴ���ַ���
		String DBResultToStr = "";

		for (int i = 0; i < DBResultList.size(); i++) {
			if (DBResultList.get(i) instanceof HashMap) {
				DBResultToStr = DBResultToStr + ((HashMap) DBResultList.get(i)).get(key);
			} else {
				DBResultToStr = DBResultToStr + ((String) DBResultList.get(i));
			}

			if (i + 1 < DBResultList.size()) {
				DBResultToStr = DBResultToStr + "\r\n";
			}
		}
		return DBResultToStr;

	}

	/**
	 * ��Jmeter��JDBC Result��ָ���У�д���ļ�
	 * 
	 * @param list
	 *            JDBC���ص�ArrayList��
	 * @param key
	 *            JDBC���ص��ֶ���
	 * @param filePath
	 *            �ļ�·��
	 * @throws Exception
	 *             �׳��쳣
	 */
	public static void JDBCResultWriteFile(ArrayList list, String key, String filePath) throws Exception {

		String str = JDBCResultToString(list, key);
		writeFile(str, filePath);

	}

	/**
	 * ��Jmeter��JDBC Result��ָ�����У�ת���ɶ��ж��е��ַ���
	 * 
	 * @param list
	 *            JDBC���ص�ArrayList��
	 * @param keyArray
	 *            JDBC���ص��ֶ�������
	 * @return ���ж��е��ַ���
	 */
	public static String JDBCResultToString(ArrayList list, String[] keyArray) {

		// �������ݿ��JSON���
		ArrayList DBResultList = list;

		// ƴ���ַ���
		String DBResultToStr = "";

		for (int i = 0; i < DBResultList.size(); i++) {
			String key = "";
			for (int x = 0; x < keyArray.length; x++) {
				if (DBResultList.get(i) instanceof HashMap) {
					key = key + ((HashMap) DBResultList.get(i)).get(keyArray[x]);
				} else {
					key = key + ((String) DBResultList.get(i));
				}
				if (x + 1 < keyArray.length) {
					key = key + ",";
				}

			}
			DBResultToStr = DBResultToStr + key;
			if (i + 1 < DBResultList.size()) {
				DBResultToStr = DBResultToStr + "\r\n";
			}
		}
		return DBResultToStr;

	}

	/**
	 * ��Jmeter��JDBC Result��ָ�����У�д���ļ�
	 * 
	 * @param list
	 *            JDBC���ص�ArrayList��
	 * @param keyArray
	 *            JDBC���ص��ֶ�������
	 * @param filePath
	 *            �ļ�·��
	 * @throws Exception
	 *             �׳��쳣
	 */
	public static void JDBCResultWriteFile(ArrayList list, String[] keyArray, String filePath) throws Exception {

		String str = JDBCResultToString(list, keyArray);
		writeFile(str, filePath);

	}

	/**
	 * 
	 * @param type
	 *            �������� -1�������� 0������� 1��������
	 * @return �������� Ĭ�ϸ�ʽΪyyyy-MM-dd
	 */
	public static String getDate(int type) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, type);
		String date = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		return date;
	}

	/**
	 * 
	 * @param type
	 *            �������� -1�������� 0������� 1��������
	 * @param format
	 *            ���ڸ�ʽ
	 * @return ��������
	 */
	public static String getDate(int type, String format) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, type);
		String date = new SimpleDateFormat(format).format(cal.getTime());
		return date;
	}

	/**
	 * 
	 * @param fileDir
	 *            �ļ�·��
	 * @throws Exception
	 *             �׳��쳣
	 */
	public static void createNewFile(String fileDir) throws Exception {
		// �ļ�·�����ļ��в�����ʱ�������ļ���
		File file = new File(fileDir);
		File fileParent = file.getParentFile();
		if (!fileParent.exists()) {
			fileParent.mkdirs();
		}
		file.createNewFile();
	}

}
