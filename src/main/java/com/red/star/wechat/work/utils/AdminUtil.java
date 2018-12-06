package com.red.star.wechat.work.utils;

import com.red.star.wechat.data.utils.CheckUtil;
import com.red.star.wechat.work.constant.AdminSessionHolder;
import com.red.star.wechat.work.constant.Constant;
import com.red.star.wechat.work.entity.Admin;

public class AdminUtil {

	/**
	 * 判断管理员类型
	 * @return admin_group:管理员,admin_mall:商场,admin_other:其他
	 */
	public static String checkAdminType() {
		Admin admin = AdminSessionHolder.get();
		if(CheckUtil.isEmpty(admin.getMallCode()) && Constant.REDSTART_MANAG.equals(admin.getEnumValue())) {
			return "admin_group";
		}else if(Constant.MALL_CODE.equals(admin.getEnumValue()) && !CheckUtil.isEmpty(admin.getMallCode())) {
			return "admin_mall";
		}else {
			return "admin_other";
		}
	}
}
