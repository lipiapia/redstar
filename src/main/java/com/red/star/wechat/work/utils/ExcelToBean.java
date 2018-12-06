package com.red.star.wechat.work.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

public class ExcelToBean {


    /**
     * 对外提供读取excel 的方法
     */
    public static List<Map<String, Object>> parseExcel(String fileName, InputStream filePath, String column) throws Exception {
        //String fileName = file.getName();
        String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName.substring(fileName.lastIndexOf(".") + 1);
        if ("xls".equals(extension)) {
            return read2003Excel(filePath, column);
        } else if ("xlsx".equals(extension)) {
            return read2007Excel(filePath, column);
        } else {
            throw new IOException("不支持的文件类型");
        }
    }

    /**
     * 将workbook中的值放入List<Map<String,Object>>结构中
     */
    public static List<Map<String, Object>> read2003Excel(InputStream filePath, String column) throws Exception {
        //Workbook workbook = convertExcel(filePath);
        HSSFWorkbook workbook = new HSSFWorkbook(filePath);
       /* try {
            workbook = new XSSFWorkbook(filePath);
        } catch (Exception ex) {
            workbook = new HSSFWorkbook(filePath);
        }*/
        List<Map<String, Object>> result = new LinkedList<Map<String, Object>>();
        int excleRowLength = workbook.getSheetAt(0).getRow(0).getPhysicalNumberOfCells();
        String[] columnName = column.split(",");
        for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {    //sheet
            HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(sheetIndex);
            for (int rowIndex = 0; rowIndex < sheet.getPhysicalNumberOfRows(); rowIndex++) {   //row
                HSSFRow row = sheet.getRow(rowIndex);
                Map<String, Object> map = new LinkedHashMap<String, Object>();
                for (int cellIndex = 0; cellIndex < columnName.length; cellIndex++) { //cell
                    HSSFCell cell = row.getCell(cellIndex);
                    if (cellIndex == 0) {
                        map.put(columnName[cellIndex].trim(), getCellValue(cell));
                    } else {
                        if (columnName[cellIndex] != null && columnName[cellIndex].trim().length() > 0) { //该列值在对应的java对象中有值
                            //取出当前cell的值和对应Javabean类的属性放入到map中
                            map.put(columnName[cellIndex].trim(), getCellValue(cell).trim());
                        }
                    }

                }
                result.add(map);
            }
        }
        System.out.println(JSONObject.toJSONString("list<>=" + result));
        return result;
    }

    /**
     * 解析2007
     *
     * @param
     * @return
     * @throws IOException
     */
    private static List<Map<String, Object>> read2007Excel(InputStream filePath, String column) throws Exception {

        XSSFWorkbook workbook = new XSSFWorkbook(filePath);//整个excel文件
        List<Map<String, Object>> result = new LinkedList<Map<String, Object>>();
        int excleRowLength = workbook.getSheetAt(0).getRow(0).getPhysicalNumberOfCells();
        String[] columnName = column.split(",");
        // 读取第一章表格内容
        //excel文件中的一个sheet文件
        XSSFSheet sheet = workbook.getSheetAt(0);
        for (int rowIndex = 0; rowIndex < sheet.getPhysicalNumberOfRows(); rowIndex++) {   //row
            XSSFRow row = sheet.getRow(rowIndex);
            Map<String, Object> map = new LinkedHashMap<String, Object>();
            for (int cellIndex = 0; cellIndex < columnName.length; cellIndex++) { //cell
                XSSFCell cell = row.getCell(cellIndex);
                if (cellIndex == 0) {
                    map.put(columnName[cellIndex].trim(), getCellValue(cell));
                } else {
                    if (columnName[cellIndex] != null && columnName[cellIndex].trim().length() > 0) { //该列值在对应的java对象中有值
                        //取出当前cell的值和对应Javabean类的属性放入到map中
                        map.put(columnName[cellIndex].trim(), getCellValue(cell).trim());
                    }
                }

            }
            result.add(map);
        }
        System.out.println(JSONObject.toJSONString("list<>=" + result));
        return result;
    }

    /**
     * 静态方法  解决创建Workbook 创建产生的问题
     *
     * @param inp
     * @return
     * @throws IOException
     */
    private static Workbook convertExcel(InputStream inp) throws IOException, InvalidFormatException {
        if (!inp.markSupported()) {
            inp = new PushbackInputStream(inp, 8);
        }
        if (POIFSFileSystem.hasPOIFSHeader(inp)) {
            return new HSSFWorkbook(inp);
        }
        if (POIXMLDocument.hasOOXMLHeader(inp)) {
            return new XSSFWorkbook(OPCPackage.open(inp));
        }
        throw new IllegalArgumentException("你的excel版本目前poi解析不了");
    }


    /**
     * 利用反射将    List<Map<String,Object>>结构 生成相应的List<T>数据
     */
    public static <T> List<T> toObjectList(List<Map<String, Object>> list, Class<T> clazz) throws Exception {
        List<T> returnList = new LinkedList<T>();
        for (int i = 0; i < list.size(); i++) {
            Set<Map.Entry<String, Object>> set = list.get(i).entrySet();
            Iterator<Entry<String, Object>> it = set.iterator();
            T obj = clazz.newInstance();
            Method[] methods = clazz.getDeclaredMethods();
            while (it.hasNext()) {      //生成一个obj
                Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
                for (Method m : methods) {
                    if (m.getName().startsWith("set")) {      //为obj赋值
                        String methodName = entry.getKey().toString();
                        StringBuffer sb = new StringBuffer(methodName);
                        sb.replace(0, 1, (methodName.charAt(0) + "").toUpperCase());
                        methodName = "set" + sb.toString();
                        if (methodName.equals(m.getName())) {
                            m.invoke(obj, entry.getValue());
                            break;
                        }
                    }
                }
            }
            returnList.add(obj);
        }
        System.out.println("size=" + returnList.size());
        return returnList;
    }


    /**
     * 获取当前单元格内容
     */
    private static String getCellValue(Cell cell) {
        String value = "";
        if (cell != null) {
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:
                    if (HSSFDateUtil.isCellDateFormatted(cell)) { //日期类型
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
                        value = sdf.format(date);
                    } else {
                        DecimalFormat df = new DecimalFormat("0");
                        value = df.format(cell.getNumericCellValue());
                    }
                    break;
                case Cell.CELL_TYPE_STRING:
                    value = cell.getStringCellValue();
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    Boolean data = cell.getBooleanCellValue();
                    value = data.toString();
                    break;
                case Cell.CELL_TYPE_ERROR:
                    System.out.println("单元格内容出现错误");
                    break;
                case Cell.CELL_TYPE_FORMULA:
                    value = String.valueOf(cell.getNumericCellValue());
                    if (value.equals("NaN")) {// 如果获取的数据值非法,就将其装换为对应的字符串
                        value = cell.getStringCellValue().toString();
                    }
                    break;
                case Cell.CELL_TYPE_BLANK:
                    System.out.println("单元格内容 为空值 ");
                    break;
                default:
                    value = cell.getStringCellValue().toString();
                    break;
            }
        }
        return value;
    }

    public static void main(String args[]) throws Exception {
        String filePath = "D:\\客户信息导入模板.xls";
        String column = "name,mobile,intentMall,intentBrand,communicateStatus,remark";


    }

    /**
     * 获取第一行表头数据
     */
    public static List<String> getHead(InputStream filePath, String column) throws Exception {
        List list = new ArrayList();
        HSSFWorkbook workbook = new HSSFWorkbook(filePath);
        List<Map<String, Object>> result = new LinkedList<Map<String, Object>>();
        int excleRowLength = workbook.getSheetAt(0).getRow(0).getPhysicalNumberOfCells();
        String[] columnName = column.split(",");
        for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {    //sheet
            HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(sheetIndex);
            HSSFRow row = sheet.getRow(0);
            Map<String, Object> map = new HashMap<String, Object>();
            for (int cellIndex = 0; cellIndex < row.getPhysicalNumberOfCells(); cellIndex++) { //cell
                HSSFCell cell = row.getCell(cellIndex);
                if (columnName[cellIndex] != null && columnName[cellIndex].trim().length() > 0) { //该列值在对应的java对象中有值
                    //取出当前cell的值和对应Javabean类的属性放入到map中
                    // map.put(columnName[cellIndex].trim(), getCellValue(cell));
                    list.add(getCellValue(cell));
                }
            }


        }
        System.out.println(JSONObject.toJSONString("list<>=" + result));
        return list;
    }


}
