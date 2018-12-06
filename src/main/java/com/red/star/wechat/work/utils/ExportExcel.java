package com.red.star.wechat.work.utils;


import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class ExportExcel {
    private static final Logger logger = LoggerFactory.getLogger(ExportExcel.class);
    /**
     * CSV文件列分隔符
     */
    private static final String CSV_COLUMN_SEPARATOR = ",";
    /**
     * CSV文件列分隔符
     */
    private static final String CSV_RN = "\r\n";

    /**
     * @param dataList 集合数据
     * @param colNames 表头部数据
     * @param mapKey   查找的对应数据
     */
    public static boolean doExport(List<Map<String, Object>> dataList, String colNames, String mapKey, OutputStream os) {
        try {
            StringBuffer buf = new StringBuffer();
            String[] colNamesArr = null;
            String[] mapKeyArr = null;
            colNamesArr = colNames.split(",");
            mapKeyArr = mapKey.split(",");
            // 完成数据csv文件的封装
            // 输出列头
            for (int i = 0; i < colNamesArr.length; i++) {
                buf.append(colNamesArr[i]).append(CSV_COLUMN_SEPARATOR);
            }
            buf.append(CSV_RN);
            if (null != dataList) { // 输出数据
                for (int i = 0; i < dataList.size(); i++) {
                    for (int j = 0; j < mapKeyArr.length; j++) {
                        buf.append(dataList.get(i).get(mapKeyArr[j])).append(CSV_COLUMN_SEPARATOR);
                    }
                    buf.append(CSV_RN);
                }
            }
            // 写出响应
            os.write(buf.toString().getBytes("GBK"));
            os.flush();
            return true;
        } catch (Exception e) {
            logger.error("doExport错误...", e);
        }
        return false;
    }

    /**
     * @throws UnsupportedEncodingException setHeader
     */
    public static void responseSetProperties(String fileName, HttpServletResponse response) throws UnsupportedEncodingException {
        // 设置文件后缀
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String fn = fileName + sdf.format(new Date()).toString() + ".csv";
        // 读取字符编码
        String utf = "UTF-8";
        // 设置响应
        response.setContentType("application/ms-txt.numberformat:@");
        response.setCharacterEncoding(utf);
        response.setHeader("Pragma", "public");
        response.setHeader("Cache-Control", "max-age=30");
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fn, utf));
    }
    //模板下载

    public static void exportExcel(HttpServletRequest request, HttpServletResponse response, HttpSession session, String[] tableHeader, String fileName) {
        OutputStream ouputStream = null;
        try {
            // 创建新的Excel 工作簿
            HSSFWorkbook workbook = new HSSFWorkbook();
            // 新建工作表，其语句为：
            HSSFSheet sheet = workbook.createSheet("sheet1");
              /* sheet.setColumnWidth(0, 8000);
               sheet.setColumnWidth(1, 5000);
               sheet.setColumnWidth(2, 5000);
               sheet.setColumnWidth(3, 5000);
               sheet.setColumnWidth(4, 5000);
               sheet.setColumnWidth(5, 5000);*/


            // 声明"Sheet1"工作表的第一行表头信息


            // 创建第一行
            HSSFRow firstRow = sheet.createRow((short) 0);
            // 创建第一行里的格子
            for (int i = 1; i <= tableHeader.length; i++) {
                // 创建第i个格子
                sheet.setColumnWidth(i, 5000);
                HSSFCell cell = firstRow.createCell((short) (i - 1));
                if (cell.getCellType() != 1) {
                    cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                }


                //新增的四句话，设置CELL格式为文本格式
                HSSFCellStyle cellStyle2 = workbook.createCellStyle();
                HSSFDataFormat format = workbook.createDataFormat();
                cellStyle2.setDataFormat(format.getFormat("@"));
                cell.setCellStyle(cellStyle2);
                // 定义单元格为字符串类型
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(tableHeader[i - 1]);
            }

            for (int i = 1; i <= tableHeader.length; i++) {
                if (i <= tableHeader.length) {
                    HSSFCell cell3 = firstRow.createCell((short) (i - 1));
                    HSSFCellStyle style = workbook.createCellStyle();
                    style.setFillForegroundColor(HSSFColor.RED.index);
                    style.setFillPattern(CellStyle.SOLID_FOREGROUND);
                    cell3.setCellStyle(style);
                    cell3.setCellValue(tableHeader[i - 1]);
                }
            }

            response.setContentType("application/vnd.ms-excel");
            // 解决中文乱码
            response.setHeader("Content-Disposition", "attachment;filename="
                    + new String(fileName.getBytes("gbk"), "iso-8859-1"));
            ouputStream = response.getOutputStream();
            workbook.write(ouputStream);
            ouputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 操作结束，关闭文件
            IOUtils.closeQuietly(ouputStream);
        }

    }

}
