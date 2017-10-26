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
 * Jmeter中BeanShell后置处理器中调用writeExcel，可以在每个请求之后将想要的数据写入Excel
 * 
 * @author 不知道名字的大神
 * @author tomoya_chen (writeToExcel修改为 单行写入方法，更适合Jmeter使用)
 * 
 */
public class Excel {
	private static HSSFWorkbook workbook = null;

	/**
	 * 判断文件是否存在.
	 * 
	 * @param fileDir
	 *            文件路径
	 * @return 布尔值
	 */
	public static boolean fileExist(String fileDir) {
		boolean flag = false;
		File file = new File(fileDir);
		flag = file.exists();
		return flag;
	}

	/**
	 * 创建新Excel.
	 * 
	 * @param fileDir
	 *            excel的路径
	 * @param sheetName
	 *            要创建的表格索引
	 * @param titleRow
	 *            excel的第一行即表格头
	 * @throws Exception
	 *             抛出异常
	 */
	public static void createExcel(String sheetName, String titleRow[], String fileDir) throws Exception {
		
		//文件不存在时，创建文件夹
		File file = new File(fileDir); 
		File fileParent = file.getParentFile(); 
		if(!fileParent.exists()){ 
		 fileParent.mkdirs(); 
		} 
		file.createNewFile();
		
		// 创建workbook
		workbook = new HSSFWorkbook();
		// 添加Worksheet（不添加sheet时生成的xls文件打开时会报错)
		HSSFSheet sheet1 = workbook.createSheet(sheetName);
		// 新建文件
		FileOutputStream out = null;
		try {
			// 添加表头
			HSSFRow row = workbook.getSheet(sheetName).createRow(0); // 创建第一行
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
	 * 删除文件.
	 * 
	 * @param fileDir
	 *            文件路径
	 * @return 布尔值
	 */
	public static boolean deleteExcel(String fileDir) {
		boolean flag = false;
		File file = new File(fileDir);
		// 判断目录或文件是否存在
		if (!file.exists()) { // 不存在返回 false
			return flag;
		} else {
			// 判断是否为文件
			if (file.isFile()) { // 为文件时调用删除文件方法
				file.delete();
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * 判断文件的Sheet是否存在.
	 * 
	 * @param fileDir
	 *            文件路径
	 * @param sheetName
	 *            表格索引名
	 * @return 布尔值
	 * 
	 * @throws Exception
	 *             抛出异常
	 */
	public static boolean sheetExist(String fileDir, String sheetName) throws Exception {
		boolean flag = false;
		File file = new File(fileDir);
		if (file.exists()) { // 文件存在
			// 创建workbook
			try {
				workbook = new HSSFWorkbook(new FileInputStream(file));
				// 添加Worksheet（不添加sheet时生成的xls文件打开时会报错)
				HSSFSheet sheet = workbook.getSheet(sheetName);
				if (sheet != null)
					flag = true;
			} catch (Exception e) {
				throw e;
			}

		} else { // 文件不存在
			flag = false;
		}
		return flag;
	}

	/**
	 * 往Excel中写入单行数据（不保留原内容）
	 * @param mapList 数据集
	 * @param fileDir 文件路径
	 * @throws Exception 抛出异常
	 */
	public static void writeExcel2(List<Map> mapList, String fileDir) throws Exception {
		String sheetName = "Sheet1";
		// 创建workbook
		File file = new File(fileDir);
		try {
			workbook = new HSSFWorkbook(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 流
		FileOutputStream out = null;
		HSSFSheet sheet = workbook.getSheet(sheetName);
		// 获取表格的总行数
		// int rowCount = sheet.getLastRowNum() + 1; // 需要加一
		// 获取表头的列数
		int columnCount = sheet.getRow(0).getLastCellNum();
		try {
			// 获得表头行对象
			HSSFRow titleRow = sheet.getRow(0);
			if (titleRow != null) {
				for (int rowId = 0; rowId < mapList.size(); rowId++) {
					Map map = mapList.get(rowId);
					HSSFRow newRow = sheet.createRow(rowId + 1);
					for (short columnIndex = 0; columnIndex < columnCount; columnIndex++) { // 遍历表头
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
	 * 自动创建文件，添加标题，往Excel中写入全部数据集（不保留原内容）
	 * @param title Excel标题
	 * @param mapList 数据集
	 * @param fileDir 文件路径
	 * @throws Exception 抛出异常
	 */
	public static void writeToExcel2(String[] title, List<Map> mapList, String fileDir) throws Exception {
		if (!fileExist(fileDir)) {
			createExcel("Sheet1", title, fileDir);
		}
		writeExcel2(mapList, fileDir);
	}

	/**
	 * 往Excel中写入单行数据（追加写入）
	 * 
	 * @param rowArray
	 *            单行数据数组
	 * @param filePath
	 *            文件路径
	 * @throws IOException
	 *             抛出异常
	 */
	public static void writeExcel(String[] rowArray, String filePath) throws IOException {
		FileInputStream fs = new FileInputStream(filePath); // 获取d://test.xls
		POIFSFileSystem ps = new POIFSFileSystem(fs); // 使用POI提供的方法得到excel的信息
		HSSFWorkbook wb = new HSSFWorkbook(ps);
		HSSFSheet sheet = wb.getSheetAt(0); // 获取到工作表，因为一个excel可能有多个工作表
		HSSFRow row = sheet.getRow(0); // 获取第一行（excel中的行默认从0开始，所以这就是为什么，一个excel必须有字段列头），即，字段列头，便于赋值
		// System.out.println(sheet.getLastRowNum() + " " + row.getLastCellNum()); //
		// 分别得到最后一行的行号，和一条记录的最后一个单元格

		FileOutputStream out = new FileOutputStream(filePath); // 向d://test.xls中写数据
		row = sheet.createRow((short) (sheet.getLastRowNum() + 1)); // 在现有行号后追加数据
		for (int i = 0; i < rowArray.length; i++) {
			row.createCell(i).setCellValue(rowArray[i]); // 设置第一个（从0开始）单元格的数据
		}

		out.flush();
		wb.write(out);
		out.close();
		// System.out.println(row.getPhysicalNumberOfCells() + " " +
		// row.getLastCellNum());
	}

	/**
	 * 自动创建文件，添加标题，往Excel中写入单行数据（追加写入）
	 * 
	 * @param title
	 *            标题数组
	 * @param rowArray
	 *            单行数组
	 * @param filePath
	 *            文件路径
	 * @throws Exception
	 *             抛出异常
	 */
	public static void writeToExcel(String[] title, String[] rowArray, String filePath) throws Exception {
		if (!fileExist(filePath)) {
			createExcel("Sheet1", title, filePath);
		}
		writeExcel(rowArray, filePath);

	}

}