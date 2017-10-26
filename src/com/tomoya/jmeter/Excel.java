package com.tomoya.jmeter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 * Jmeter��BeanShell���ô������е���writeExcel��������ÿ������֮����Ҫ������д��Excel
 * 
 * @author ��֪�����ֵĴ���
 * @author tomoya_chen (writeToExcel�޸�Ϊ ����д�뷽�������ʺ�Jmeterʹ��)
 * 
 */
public class Excel {
	private static HSSFWorkbook workbook = null;

	/**
	 * �ж��ļ��Ƿ����.
	 * 
	 * @param fileDir
	 *            �ļ�·��
	 * @return ����ֵ
	 */
	public static boolean fileExist(String fileDir) {
		boolean flag = false;
		File file = new File(fileDir);
		flag = file.exists();
		return flag;
	}

	/**
	 * ������Excel.
	 * 
	 * @param fileDir
	 *            excel��·��
	 * @param sheetName
	 *            Ҫ�����ı������
	 * @param titleRow
	 *            excel�ĵ�һ�м����ͷ
	 * @throws Exception
	 *             �׳��쳣
	 */
	public static void createExcel(String sheetName, String titleRow[], String fileDir) throws Exception {
		
		//�ļ�������ʱ�������ļ���
		File file = new File(fileDir); 
		File fileParent = file.getParentFile(); 
		if(!fileParent.exists()){ 
		 fileParent.mkdirs(); 
		} 
		file.createNewFile();
		
		// ����workbook
		workbook = new HSSFWorkbook();
		// ���Worksheet�������sheetʱ���ɵ�xls�ļ���ʱ�ᱨ��)
		HSSFSheet sheet1 = workbook.createSheet(sheetName);
		// �½��ļ�
		FileOutputStream out = null;
		try {
			// ��ӱ�ͷ
			HSSFRow row = workbook.getSheet(sheetName).createRow(0); // ������һ��
			for (short i = 0; i < titleRow.length; i++) {
				HSSFCell cell = row.createCell(i);
				cell.setCellValue(titleRow[i]);
			}
			out = new FileOutputStream(fileDir);
			workbook.write(out);
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * ɾ���ļ�.
	 * 
	 * @param fileDir
	 *            �ļ�·��
	 * @return ����ֵ
	 */
	public static boolean deleteExcel(String fileDir) {
		boolean flag = false;
		File file = new File(fileDir);
		// �ж�Ŀ¼���ļ��Ƿ����
		if (!file.exists()) { // �����ڷ��� false
			return flag;
		} else {
			// �ж��Ƿ�Ϊ�ļ�
			if (file.isFile()) { // Ϊ�ļ�ʱ����ɾ���ļ�����
				file.delete();
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * �ж��ļ���Sheet�Ƿ����.
	 * 
	 * @param fileDir
	 *            �ļ�·��
	 * @param sheetName
	 *            ���������
	 * @return ����ֵ
	 * 
	 * @throws Exception
	 *             �׳��쳣
	 */
	public static boolean sheetExist(String fileDir, String sheetName) throws Exception {
		boolean flag = false;
		File file = new File(fileDir);
		if (file.exists()) { // �ļ�����
			// ����workbook
			try {
				workbook = new HSSFWorkbook(new FileInputStream(file));
				// ���Worksheet�������sheetʱ���ɵ�xls�ļ���ʱ�ᱨ��)
				HSSFSheet sheet = workbook.getSheet(sheetName);
				if (sheet != null)
					flag = true;
			} catch (Exception e) {
				throw e;
			}

		} else { // �ļ�������
			flag = false;
		}
		return flag;
	}

	/**
	 * ��Excel��д�뵥�����ݣ�������ԭ���ݣ�
	 * @param mapList ���ݼ�
	 * @param fileDir �ļ�·��
	 * @throws Exception �׳��쳣
	 */
	public static void writeExcel2(List<Map> mapList, String fileDir) throws Exception {
		String sheetName = "Sheet1";
		// ����workbook
		File file = new File(fileDir);
		try {
			workbook = new HSSFWorkbook(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// ��
		FileOutputStream out = null;
		HSSFSheet sheet = workbook.getSheet(sheetName);
		// ��ȡ����������
		// int rowCount = sheet.getLastRowNum() + 1; // ��Ҫ��һ
		// ��ȡ��ͷ������
		int columnCount = sheet.getRow(0).getLastCellNum();
		try {
			// ��ñ�ͷ�ж���
			HSSFRow titleRow = sheet.getRow(0);
			if (titleRow != null) {
				for (int rowId = 0; rowId < mapList.size(); rowId++) {
					Map map = mapList.get(rowId);
					HSSFRow newRow = sheet.createRow(rowId + 1);
					for (short columnIndex = 0; columnIndex < columnCount; columnIndex++) { // ������ͷ
						String mapKey = titleRow.getCell(columnIndex).toString().trim().toString().trim();
						HSSFCell cell = newRow.createCell(columnIndex);
						cell.setCellValue(map.get(mapKey) == null ? null : map.get(mapKey).toString());
					}
				}
			}

			out = new FileOutputStream(fileDir);
			workbook.write(out);
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * �Զ������ļ�����ӱ��⣬��Excel��д��ȫ�����ݼ���������ԭ���ݣ�
	 * @param title Excel����
	 * @param mapList ���ݼ�
	 * @param fileDir �ļ�·��
	 * @throws Exception �׳��쳣
	 */
	public static void writeToExcel2(String[] title, List<Map> mapList, String fileDir) throws Exception {
		if (!fileExist(fileDir)) {
			createExcel("Sheet1", title, fileDir);
		}
		writeExcel2(mapList, fileDir);
	}

	/**
	 * ��Excel��д�뵥�����ݣ�׷��д�룩
	 * 
	 * @param rowArray
	 *            ������������
	 * @param filePath
	 *            �ļ�·��
	 * @throws IOException
	 *             �׳��쳣
	 */
	public static void writeExcel(String[] rowArray, String filePath) throws IOException {
		FileInputStream fs = new FileInputStream(filePath); // ��ȡd://test.xls
		POIFSFileSystem ps = new POIFSFileSystem(fs); // ʹ��POI�ṩ�ķ����õ�excel����Ϣ
		HSSFWorkbook wb = new HSSFWorkbook(ps);
		HSSFSheet sheet = wb.getSheetAt(0); // ��ȡ����������Ϊһ��excel�����ж��������
		HSSFRow row = sheet.getRow(0); // ��ȡ��һ�У�excel�е���Ĭ�ϴ�0��ʼ�����������Ϊʲô��һ��excel�������ֶ���ͷ���������ֶ���ͷ�����ڸ�ֵ
		// System.out.println(sheet.getLastRowNum() + " " + row.getLastCellNum()); //
		// �ֱ�õ����һ�е��кţ���һ����¼�����һ����Ԫ��

		FileOutputStream out = new FileOutputStream(filePath); // ��d://test.xls��д����
		row = sheet.createRow((short) (sheet.getLastRowNum() + 1)); // �������кź�׷������
		for (int i = 0; i < rowArray.length; i++) {
			row.createCell(i).setCellValue(rowArray[i]); // ���õ�һ������0��ʼ����Ԫ�������
		}

		out.flush();
		wb.write(out);
		out.close();
		// System.out.println(row.getPhysicalNumberOfCells() + " " +
		// row.getLastCellNum());
	}

	/**
	 * �Զ������ļ�����ӱ��⣬��Excel��д�뵥�����ݣ�׷��д�룩
	 * 
	 * @param title
	 *            ��������
	 * @param rowArray
	 *            ��������
	 * @param filePath
	 *            �ļ�·��
	 * @throws Exception
	 *             �׳��쳣
	 */
	public static void writeToExcel(String[] title, String[] rowArray, String filePath) throws Exception {
		if (!fileExist(filePath)) {
			createExcel("Sheet1", title, filePath);
		}
		writeExcel(rowArray, filePath);

	}

}