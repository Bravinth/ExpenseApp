package com.pkb.expense.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Utils {

	public static String getDisplayDate(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
		return  sdf.format(date);
		
	}

	public static Map<String, String> getUsersShareMap(String allUsersShare) {
		
		Map<String, String> userShareMap = new HashMap<String, String>();
		String[] allUsersShareArray;
		String[] userAndShareArray;	
		
		if(!allUsersShare.isEmpty()){
			allUsersShareArray = allUsersShare.split(",");
			for(String userShare : allUsersShareArray){
				userAndShareArray = userShare.split("-");
				userShareMap.put(userAndShareArray[0], userAndShareArray[1]);
			}
		}
		
		return userShareMap;
	}
}
