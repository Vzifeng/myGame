package com.example.game.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 处理数据工具类
 * @author fengs 
 *
 */
public class DataUtil {

	/**
	 * 限制double后的小数位数
	 * @param num double数字
	 * @param accuracy 精度
	 * @return
	 */
	public static Double getDoubleVelue(Double num,int accuracy) {
		if (num==null || Double.isNaN(num)) {
			return 0.00D;
		}
		BigDecimal b = new BigDecimal(num);
		return b.setScale(accuracy, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	/**
	 * 计算两个整数之间的百分比
	 * @param num1 除数
	 * @param num2 被除数
	 * @param accuracy 精度
	 * @return 返回百分比（乘以100后的结果）
	 */
	public static Double getPercent(int num1, int num2, int accuracy) {
		if(num2 == 0){
			return (double) 0;
		}
		Double num = ((double)num1/num2)*100;
		return getDoubleVelue(num, accuracy);
	}

	
	/**
	 * 计算两个整数之间的百分比
	 * @param num1 除数
	 * @param num2 被除数
	 * @param accuracy 精度
	 * @return 返回百分比（乘以100后的结果）
	 */
	public static Double getPercent(Long num1, Long num2, int accuracy) {
		if(num2 == 0){
			return (double) 0;
		}
		Double num = ((double)num1/num2)*100;
		return getDoubleVelue(num, accuracy);
	}
	
	/**
	 * 计算环比
	 * @param num1 本月
	 * @param num2 同期
	 * @param accuracy 精度
	 * @return 返回百分比（乘以100后的结果）
	 */
	public static Double getUpPercent(int num1, int num2, int accuracy) {
		if(num2 == 0){
			return (double) 0;
		}
		Double num = ((double)(num1-num2)/num2)*100;
		return getDoubleVelue(num, accuracy);
	}
	
	/**
	 * 得到字符串中，{}符号包含的参数列表
	 * @param url ep:/etc/aaa/{p1}{p2}
	 * @return
	 */
	public static List<String> getParamList(String url) {
		List<String> list = new ArrayList<String>();
		String temp = url+"";//重新创建一个临时字符串
		String param = null;
		while (temp.indexOf("{")!=-1 && temp.indexOf("}")!=-1) {
			param = temp.substring(temp.indexOf("{")+1, temp.indexOf("}"));
			list.add(param);
			temp = temp.substring(temp.indexOf("}")+1, temp.length());
		}
		return list;
	}
}
