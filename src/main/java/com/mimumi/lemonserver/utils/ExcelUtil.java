package com.mimumi.lemonserver.utils;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

/**
 * excel文件导入工具类
 */
public class ExcelUtil {

    public static String EXCEL_2007_ContentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    public static String EXCEL_2003_ContentType = "application/vnd.ms-excel";

    /**
     * excel文件导入
     * @param file
     * @param ignoreRows 需要忽略掉的行数
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static String[][] importFile(MultipartFile file, int ignoreRows) throws FileNotFoundException, IOException {
        List<String[]> result = new ArrayList<String[]>();
        String contentType = file.getContentType();	 //内容类型
        InputStream stream = file.getInputStream();  //输入流
        Workbook wb=null;
        //根据inputStream建立一个Excel对象
        if (EXCEL_2003_ContentType.equals(contentType)){
            wb =(Workbook) new HSSFWorkbook(stream);	//2003
        }else if(EXCEL_2007_ContentType.equals(contentType)) {
            wb =(Workbook) new XSSFWorkbook(stream);	//2007
        }
        int rowSize = 0;
        Cell cell = null;
        for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
            Sheet st = wb.getSheetAt(sheetIndex);
            for (int rowIndex = ignoreRows; rowIndex <= st.getLastRowNum(); rowIndex++) {
                Row row = st.getRow(rowIndex);
                if (null == row) {
                    continue;
                }
                int tempRowSize = row.getLastCellNum() + 1;
                if (tempRowSize > rowSize) {
                    rowSize = tempRowSize;
                }
                String[] values = new String[rowSize];
                Arrays.fill(values, "");
                boolean hasValue = false;
                for (short columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++) {
                    String value = "";
                    cell = row.getCell(columnIndex);
                    if (null != cell) {
                        switch (cell.getCellType()) {
                            case STRING:
                                value = cell.getStringCellValue();
                                break;
                            case NUMERIC:
                                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                    Date date = cell.getDateCellValue();
                                    if (null != date) {
                                        value = new SimpleDateFormat("yyyy-MM-dd").format(date);
                                    } else {
                                        value = "";
                                    }
                                } else {
                                    DecimalFormat df = new DecimalFormat("0.#######");
                                    //value = df.format(cell.getNumericCellValue());
                                    value = String.valueOf(df.format(cell.getNumericCellValue()));
                                }
                                break;
                            case FORMULA:
                                // 公式生成的数据
                                if (!"".equals(cell.getStringCellValue())) {
                                    value = cell.getStringCellValue();
                                } else {
                                    value = cell.getNumericCellValue() + "";
                                }
                                break;
                            case BLANK:
                                break;
                            case ERROR:
                                value = "";
                                break;
                            case BOOLEAN:
                                value = (cell.getBooleanCellValue() == true ? "1" : "0");
                                break;
                            default:
                                value = "";
                        }
                    }
                    if (columnIndex == 0 && value.trim().equals("")) {
                        break;
                    }
                    values[columnIndex] = rightTrim(value);
                    hasValue = true;
                }
                if (hasValue) {
                    result.add(values);
                }
            }
        }
        stream.close();
        String[][] returnArray = new String[result.size()][rowSize];
        for (int i = 0; i < returnArray.length; i++) {
            returnArray[i] = (String[]) result.get(i);
        }
        return returnArray;
    }

    /**
     * excel文件导入
     * @param file
     * @param ignoreRows 需要忽略掉的行数
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static String[][] importFile(MultipartFile file,int sheetIndex, int ignoreRows,Integer colCnt) throws FileNotFoundException, IOException {
        List<String[]> result = new ArrayList<String[]>();
        String contentType = file.getContentType();	 //内容类型
        InputStream stream = file.getInputStream();  //输入流
        Workbook wb=null;
        //根据inputStream建立一个Excel对象
        if (EXCEL_2003_ContentType.equals(contentType)){
            wb =(Workbook) new HSSFWorkbook(stream);	//2003
        }else if(EXCEL_2007_ContentType.equals(contentType)) {
            wb =(Workbook) new XSSFWorkbook(stream);	//2007
        }
        int rowSize = 0;
        Cell cell = null;

        Sheet st = wb.getSheetAt(sheetIndex);
        for (int rowIndex = ignoreRows; rowIndex <= st.getLastRowNum(); rowIndex++) {
            Row row = st.getRow(rowIndex);
            if (null == row) {
                continue;
            }
    //			int tempRowSize = row.getLastCellNum() + 1;
            int tempRowSize = row.getPhysicalNumberOfCells();

            if(colCnt==null){
                if (tempRowSize > rowSize) {
                    rowSize = tempRowSize;
                }
                colCnt=rowSize;
            }
            String[] values = new String[colCnt];
            Arrays.fill(values, "");
            boolean hasValue = false;
            for (short columnIndex = 0; columnIndex < colCnt; columnIndex++) {
                String value = "";
                cell = row.getCell(columnIndex);
                if (null != cell) {
                    switch (cell.getCellType()) {
                        case STRING:
                            value = cell.getStringCellValue();
                            break;
                        case NUMERIC:
                            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                Date date = cell.getDateCellValue();
                                if (null != date) {
                                    value = new SimpleDateFormat("yyyy-MM-dd").format(date);
                                } else {
                                    value = "";
                                }
                            } else {
                                DecimalFormat df = new DecimalFormat("0.#######");
                                //value = df.format(cell.getNumericCellValue());
                                value = String.valueOf(df.format(cell.getNumericCellValue()));
                            }
                            break;
                        case FORMULA:
                            // 公式生成的数据
                            if (!"".equals(cell.getStringCellValue())) {
                                value = cell.getStringCellValue();
                            } else {
                                value = cell.getNumericCellValue() + "";
                            }
                            break;
                        case BLANK:
                            break;
                        case ERROR:
                            value = "";
                            break;
                        case BOOLEAN:
                            value = (cell.getBooleanCellValue() == true ? "1" : "0");
                            break;
                        default:
                            value = "";
                    }
                }
                if (columnIndex == 0 && value.trim().equals("")) {
                    break;
                }
                values[columnIndex] = rightTrim(value);
                hasValue = true;
            }
            if (hasValue) {
                result.add(values);
            }
        }

        stream.close();
        String[][] returnArray = new String[result.size()][colCnt];
        for (int i = 0; i < returnArray.length; i++) {
            returnArray[i] = (String[]) result.get(i);
        }
        return returnArray;
    }

    /**
     * 去除字符串右侧空格(直接使用trim()将不能支持中间存在空格的单元格)
     * @param str
     * @return
     */
    public static String rightTrim(String str) {
        if (str == null) {
            return "";
        }
        int length = str.length();
        for (int i = length - 1; i >= 0; i--) {
            if (0x20 != str.charAt(i)) {
                break;
            }
            length--;
        }
        return str.substring(0, length);
    }


}
