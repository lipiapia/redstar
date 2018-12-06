package com.red.star.wechat.work.entity;

import lombok.Data;

import java.util.List;


/**
 * @Author: Yenan
 * @Description:
 * @Date: Created in 10:11 2018/2/6
 */
@Data
public class TableContainer {

    //来自客户端 sEcho 的没有变化的复制品
    private int sEcho;
    //iTotalRecords	实际的行数
    private long iTotalRecords;
    //iTotalDisplayRecords 	过滤之后，实际的行数。
    private long iTotalDisplayRecords;
    //可选，以逗号分隔的列名
    private String sColumns;
    //数组的数组，表格中的实际数据。

    private List aaData;

    public int getsEcho() {
        return sEcho;
    }

    public void setsEcho(int sEcho) {
        this.sEcho = sEcho;
    }

    public long getiTotalRecords() {
        return iTotalRecords;
    }

    public void setiTotalRecords(long iTotalRecords) {
        this.iTotalRecords = iTotalRecords;
    }

    public long getiTotalDisplayRecords() {
        return iTotalDisplayRecords;
    }

    public void setiTotalDisplayRecords(long iTotalDisplayRecords) {
        this.iTotalDisplayRecords = iTotalDisplayRecords;
    }

    public String getsColumns() {
        return sColumns;
    }

    public void setsColumns(String sColumns) {
        this.sColumns = sColumns;
    }
}
