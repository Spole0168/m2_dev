/**
 * 
 */
package com.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shang.base.common.Constant;

/**
 * 
 *
 * @author abudula
 * @date 2017年3月22日
 * @Copyright
 *
 *            <pre>
 * =================Modify Record=================
 * Modifier			date				Content
 * yunbo.wei		2017年3月22日			新增
 *
 * </pre>
 */
public class ExcelsUtils {
	Logger logger = LoggerFactory.getLogger(ExcelsUtils.class);
	public final static String xls = "xls";
	public final static String xlsx = "xlsx";

	/**
	 * excel 文件中要读取单元格的坐标信息
	 * @author Spole
	 *
	 */
	public static class Point{
		/**
		 * excel 文件中要读取单元格的表单的序号 从零开始
		 */
		private int sheetNum;
		/**
		 * excel 文件中 要读取单元格的所在行   从零开始
		 */
		private int x;
		/**
		 * excel 文件中 要读取单元格的所在列   从零开始
		 */
		private int y;
		public int getX() {
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
		
		public int getSheetNum() {
			return sheetNum;
		}
		public void setSheetNum(int sheetNum) {
			this.sheetNum = sheetNum;
		}
		/**
		 * <p> 构造函数
		 * <p> @param sheetNum  excel 文件中要读取单元格的表单的序号 从零开始
		 * <p> @param x  excel 文件中 要读取单元格的所在行   从零开始
		 * <p> @param y  excel 文件中 要读取单元格的所在列   从零开始
		 */
		public Point(int sheetNum, int x, int y) {
			super();
			this.sheetNum = sheetNum;
			this.x = x;
			this.y = y;
		}
	}
	/**
	 * <p> 读取指定的Excel文件内容
	 * @param file
	 *            读取的文件
	 * @return List<List<String>> 返回文件中的内容
	 * @throws IOException
	 */
	public static List<List<String>> readExcel(File file)throws IOException{
		// 获得Workbook工作薄对象
		Workbook workbook = null;
		// 创建返回对象，把每行中的值作为一个数组，所有行作为一个集合返回
		List<List<String>> list = new ArrayList<List<String>>();
		try {
			workbook = getWorkBook(file);
			if (workbook != null) {
				for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
					// 获得当前sheet工作表
					Sheet sheet = workbook.getSheetAt(sheetNum);
					if (sheet == null) {
						continue;
					}
					// 获得当前sheet的结束行
					int lastRowNum = sheet.getLastRowNum();
					for (int rowNum = 0; rowNum <= lastRowNum; rowNum++) {
						// 获得当前行
						Row row = sheet.getRow(rowNum);
						if (row == null) {
							continue;
						}
						// 获得当前行的列数row
						int lastCellNum = row.getLastCellNum();
						ArrayList<String> rowList = new ArrayList<String>();
						// 循环当前列
						for (int cellNum = 0; cellNum < lastCellNum; cellNum++) {
							Cell cell = row.getCell(cellNum);
							String cellData = getCell2StringValue(cell);
							rowList.add(cellData);
						}
						list.add(rowList);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(null!=workbook){
				workbook.close();
				workbook = null;
			}
		}
		return list;
	}

	/**
	 * <p> 把指定的list写入指定的要创建的文件中
	 * <p>@param list 要写入Excel文件的list数组
	 * <p> @param resultFileName 生成文件的名称 默认生成2010版本
	 * @throws IOException
	 */
	public static String createExcel(List<List<String>> list, String filePath,int totalLine)throws IOException{
		if (null != list && list.size() > 0) {
			FileOutputStream fos = null;
			try {
				int rowNum = totalLine==0?2:totalLine;
				fos = new FileOutputStream(filePath);
				XSSFWorkbook newxssfWorkbook = new XSSFWorkbook();
				XSSFSheet newsheet = newxssfWorkbook.createSheet("data");
				for (int i = 0; i < rowNum; i++) {
					List<String> cell = list.get(i);// 可能是对象
					if (null == cell || cell.size() == 0) {
						continue;
					}
					XSSFRow newrow = newsheet.createRow(i);
					int cellLength = cell.size();
					for (int j = 0; j < cellLength; j++) {
						String cellData = null;
						try {
							cellData = cell.get(j);
						} catch (Exception e) {
							cellData = null;
						}
						if (null != cellData && cellData.length() > 0) {
							newrow.createCell(j).setCellValue(cellData);
						}
					}

				}
				newxssfWorkbook.write(fos);
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(null!=fos){
					fos.close();
					fos = null;
				}
			}
		} else {
			System.out.println("参数有误");
		}
		return filePath;
	}

	/**
	 * <p>在已经存在的excel 中写入数据
	 * @param list
	 * @param resultFileName
	 * @param writeStartLine
	 * @return
	 * @throws IOException
	 */
	public static String writeExcel(List<List<String>> list,
			String resultFileName) throws IOException {
		if (null != list && list.size() > 0) {
			Workbook workBook = getWorkBook(new File(resultFileName));
			Sheet sheet = workBook.getSheet("data");
			int lastRowNo = sheet.getLastRowNum();
			for (int i = 2; i < list.size(); i++) {
				List<String> arr = list.get(i);
				if (null == arr || arr.size() == 0) {
					continue;
				}
				Row createRow = sheet.createRow(lastRowNo - 1 + i);
				for (int j = 0; j < arr.size(); j++) {
					createRow.createCell(j).setCellValue(arr.get(j));
				}
			}
			FileOutputStream fileOut = null;
			try {
				fileOut = new FileOutputStream(resultFileName);
				workBook.write(fileOut);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				if(null!=fileOut){
					fileOut.close();
					fileOut = null;
				}
			}
		}
		return null;
	}

	/**
	 * 
	 * @param file
	 *            Excel文件内容java
	 * @return Workbook
	 * @throws IOException
	 */
	private static Workbook getWorkBook(File file) throws IOException {
		// 判断文件是否存在
		if (null == file) {
			throw new FileNotFoundException("文件不存在！");
		}
		// 获得文件名
		String fileName = file.getName();
		// 判断文件是否是excel文件
		if (!fileName.endsWith(xls) && !fileName.endsWith(xlsx)) {
			throw new IOException(fileName + "不是excel文件");
		}
		// 创建Workbook工作薄对象，表示整个excel
		Workbook workbook = null;
		try {
			// 获取excel文件的io流
			InputStream is = new FileInputStream(file);
			// 根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
			if (fileName.endsWith(xls)) {
				// 2003
				workbook = new HSSFWorkbook(is);
			} else if (fileName.endsWith(xlsx)) {
				// 2007
				workbook = new XSSFWorkbook(is);
			}
		} catch (IOException e) {
		}finally{
//			if(null!=workbook){
//				workbook.close();
//				workbook = null;
//			}
		}
		return workbook;
	}

	/**
	 * <p>
	 * 获取单元格数据内容为字符串类型的数据
	 * 
	 * @param cell
	 * @return
	 */
	private static String getCell2StringValue(Cell cell) {
		String strCell = Constant.EXCEL_CELL_BLANK;
		if (cell == null) {
			return strCell;
		}
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_NUMERIC:
			//科学计数的数字问题  //日期问题
			String  dataFormat = ","+cell.getCellStyle().getDataFormat()+",";
			String  dataFormatString = cell.getCellStyle().getDataFormatString();
			if (HSSFDateUtil.isCellDateFormatted(cell)||null==dataFormatString||
					(Constant.EXCEL_CELL_DATAFORMAT_VALUE.contains(dataFormat)&&dataFormatString.matches(Constant.EXCEL_CELL_DATAFORMATSTRING_REG))
					) {
            	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
            	strCell = simpleDateFormat.format(cell.getDateCellValue());  
                break;  
            }else {  
            	Object value = cell.getNumericCellValue();  
                //这里处理 数字的科学计算法  
            	strCell  = value.toString();
                if(strCell.contains("E")){  
                    DecimalFormat bdf=new DecimalFormat("#");  
                    strCell = bdf.format(value);  
                }
                break;  
            } 
		case Cell.CELL_TYPE_STRING:
			strCell = "".equals(cell.getStringCellValue().trim()) ? Constant.EXCEL_CELL_BLANK
					: cell.getStringCellValue();
			break;
		case Cell.CELL_TYPE_BLANK:
			strCell = Constant.EXCEL_CELL_BLANK;
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			strCell = String.valueOf(cell.getBooleanCellValue());
			break;
		default:
			strCell = Constant.EXCEL_CELL_BLANK;
			break;
		}
		return strCell;
	}

	
	
	/**
 * 
 * <p> description : 根据指定的配置信息读取excel种内容写入指定的对象中
 * <p> author   : Spole
 * <p> date 	: 2017年6月15日
 * <p> methodName ：readExcelSpecifyCells
 * <p> paramters  : @param file
 * <p> paramters  : @param config
 * <p> paramters  : @param target
 * <p> paramters  : @throws Exception
 * <p> return	: void
 */
	public static void readExcelSpecifyCells(File file,
			Map<String, Point> config, Object target) throws Exception {
		if(null==target||null==config||config.size()==0){
			throw new Exception("参错误--配置信息或者写入对象不可为空");
		}
		Class targetClass = target.getClass();// 得到对象的Class
		Field[] targetFields = targetClass.getDeclaredFields();// 得到Class对象的所有属性
		// 获得Workbook工作薄对象
		Workbook workbook = null;
		try {
			workbook = getWorkBook(file);
		} catch (IOException e1) {
			throw new Exception("参错误--文件不存在");
		}
		if (workbook != null) {
			// 获得第一个页签内容
			int sheetTotalNum = workbook.getNumberOfSheets();
			Sheet[] sheetArr = new Sheet[sheetTotalNum];
			for (int sheetNum = 0; sheetNum < sheetTotalNum; sheetNum++) {
				// 获得指定excel中所有的表单sheet
				sheetArr[sheetNum] = workbook.getSheetAt(sheetNum);
			}
			for (String key : config.keySet()) {
				// ""name key
				Point point = config.get(key);
				String val = getStringValue(sheetArr[point.getSheetNum()],
						point.getX(), point.getY());
				String methodName = key.substring(0, 1).toUpperCase()
						+ key.substring(1);
				for (Field targetField : targetFields) {
					Class targetType = targetField.getType();// 目标属性类型
					String targetName = targetField.getName();// 目标对象的属性名
					if (targetName.equals(key)) {
						Object value = trans2Type(val, targetType);
						try {
							Method setMethod = targetClass.getMethod("set"
									+ methodName, targetType);// 属性对应的set方法
							setMethod.invoke(target, value);// 执行目标对象的set方法
							break;// 为了提高效率。
						} catch (Exception e) {
							break;// 为了提高效率。
						}
	
					}
				}
			}
	
		}
	}
	/**
	 * 导出list<T>到excel中
	 * @param list
	 * @param filePath
	 * @param totalLine
	 * @return
	 * @throws IOException
	 */
	public static XSSFWorkbook exportList2Excel(String[] titles, JSONArray jsonArr){
			try {
				//第一步，创建一个workbook，对应一个Excel文件
				XSSFWorkbook newxssfWorkbook = new XSSFWorkbook();
				//第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
				XSSFSheet newsheet = newxssfWorkbook.createSheet("data");
				//第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
				XSSFRow newrow = newsheet.createRow(0);
				for (int i = 0; i < titles.length; i++) {
					newrow.createCell(i).setCellValue(titles[i]);
				}
				//增加数据
				for(int i=0;i<jsonArr.size();i++){
					//T
					newrow  = newsheet.createRow(i+1);
					Object obj = jsonArr.get(i);
					JSONObject jsonObj = JSONObject.parseObject(JSON.toJSONString(obj));
					for (int j = 0; j < titles.length; j++) {
						newrow.createCell(j).setCellValue(jsonObj.getString(titles[j]));
					}
				}
				return newxssfWorkbook;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
	}

	
	/**
	 * 
	 * <p> description : 获得指定单元格的内容  不存在或者为空，返回空
	 * <p> author   : Spole
	 * <p> date 	: 2017年6月2日
	 * <p> methodName ：getStringValue
	 * <p> paramters  : @param sheet
	 * <p> paramters  : @param rowNum
	 * <p> paramters  : @param cellNum
	 * <p> paramters  : @return
	 * <p> return	: String
	 * <p>
	 * <p>
	 */
	private static String getStringValue(Sheet sheet,int rowNum,int cellNum) {
		int lastRowNum = sheet.getLastRowNum();
		if(rowNum > lastRowNum){
			return " ";
		}
		Row row = sheet.getRow(rowNum);
		int lastCellNum;
		try {
			lastCellNum = row.getLastCellNum();
		} catch (Exception e1) {
			return " ";
		}
		if(cellNum > lastCellNum){
			return " ";
		}
		Cell cell;
		try {
			cell = row.getCell(cellNum);
		} catch (Exception e) {
			return " ";
		}
		return getCell2StringValue(cell);
		
	}

	
	public static Object trans2Type(String val, Class targetType) {
		Object obj = null;
		if(targetType.equals(Integer.class)){
			try {
				obj = Integer.valueOf(val);
			} catch (Exception e) {
				obj = 0;
			}
		}
		if(targetType.equals(Short.class)){
			try {
				obj = Short.valueOf(val);
			} catch (Exception e1) {
				obj = 0;
			}
		}
		if(targetType.equals(Long.class)){
			try {
				obj = Long.valueOf(val);
			} catch (Exception e1) {
				obj = 0L;
			}
		}
		if(targetType.equals(Double.class)){
			try {
				obj = Double.valueOf(val);
			} catch (Exception e1) {
				obj = 0.0;
			}
		}
		if(targetType.equals(Float.class)){
			try {
				obj = Float.valueOf(val);
			} catch (Exception e1) {
				obj = 0f;
			}
		}
		if(targetType.equals(Boolean.class)){
			try {
				obj = Boolean.valueOf(val);
			} catch (Exception e1) {
				obj = Boolean.FALSE;
			}
		}
		if(targetType.equals(Byte.class)){
			try {
				obj = Byte.valueOf(val);
			} catch (Exception e1) {
				obj = 0;
			}
		}
		if(targetType.toString().contains("Date")){
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");  
			try {
				obj = simpleDateFormat.parseObject(val);
			} catch (Exception e1) {
				obj = new Date();
			}
		}
		if(targetType.equals(String.class)){
			obj = val;
		}
		return obj;
	}
	
}
