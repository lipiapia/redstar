package com.red.star.wechat.work.utils;

import com.github.pagehelper.Page;
import com.red.star.wechat.work.entity.TableContainer;

import java.util.List;

/**
 * @Author: Yenan
 * @Description:
 * @Date: Created in 11:53 2018/2/5
 */
public class CommonUtil {


    public static TableContainer backTableContainer(List<?> list, Page<?> page) {
        TableContainer tableContainer = new TableContainer();
        tableContainer.setAaData(list);
        tableContainer.setiTotalDisplayRecords(page.getTotal());
        tableContainer.setiTotalRecords(page.getTotal());
        return tableContainer;
    }

    public static TableContainer backTableContainer(List<?> list, Long count) {
        TableContainer tableContainer = new TableContainer();
        tableContainer.setAaData(list);
        tableContainer.setiTotalDisplayRecords(count);
        tableContainer.setiTotalRecords(count);
        return tableContainer;
    }

}
