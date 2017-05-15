package com.jesse.util;

import java.util.Date;
import java.util.Random;

public class RandomNumberUtil {
	
    /**
     * 生成随机数字和字母
     * @param length 位数长度
     * @return 随机数
     */
    public static String getStringRandom(int length) {
         
        String val = "";
        Random random = new Random();
         
        //参数length，表示生成几位随机数
        for(int i = 0; i < length; i++) {
             
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if( "char".equalsIgnoreCase(charOrNum) ) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char)(random.nextInt(26) + temp);
            } else if( "num".equalsIgnoreCase(charOrNum) ) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }
    
	/**
	 * 产生四位随机数(数字)
	 * @return
	 */
	public static String getFourNumber() {
		String str  = (Math.random()*9000+1000) + "";
		str= str.substring(0, 4);
		return str;
	}
	
	/**
	 * 
	 * @Title: getSixNumber   
	 * @Description: 产生六位随机数(数字)
	 * @author: lizhie  
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public static String getSixNumber() {
		String str  = (Math.random()*10000000) + "";
		str= str.substring(0, 6);
		return str;
	}
	
	/**
	 * 产生19位随机数(数字)
	 * @return
	 */
	public static String get19Number() {
		String str  = (Math.random()*9000+1000) + "";
		str= str.substring(0, 4);
		return str+DateUtils.formatNoLineDateTime(new Date());
	}
}
