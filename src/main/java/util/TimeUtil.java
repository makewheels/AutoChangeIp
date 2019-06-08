package util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 * 
 * @author Administrator
 *
 */
public class TimeUtil {

	/**
	 * 当前日期
	 * 
	 * @return
	 */
	public static String getDate() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return simpleDateFormat.format(new Date());
	}

	/**
	 * 当前时间
	 * 
	 * @return
	 */
	public static String getTime() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simpleDateFormat.format(new Date());
	}

	/**
	 * 程序结束，输出耗时
	 * 
	 * @param startTimeMillis
	 *            开始时间戳
	 */
	public static void printCostTime(long startTimeMillis) {
		long cost = System.currentTimeMillis() - startTimeMillis;
		System.out.println("done cost: " + cost);
	}
}
