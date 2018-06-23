package com.hey.util;

import java.util.UUID;

/**
 * 产生UUID
 * @author lym
 *
 */
public class UUIDUtil {

    public static String uuid(){
		String  uuid = UUID.randomUUID().toString();
		String uu = uuid.substring(0,8)+uuid.substring(9,13)+uuid.substring(14,18)+uuid.substring(19,23)+uuid.substring(24);
		return uu;
	}

	//获取随机订单号
	public static String getTimeStamp() {
		return String.valueOf(System.currentTimeMillis() / 1000);
	}
	
}