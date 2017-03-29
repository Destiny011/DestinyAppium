package traverse;


import io.appium.java_client.ios.IOSDriver;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ement {
	//判断路径是否正确
	public static boolean issElement(String path,IOSDriver driver) {
		try{
			driver.findElementByXPath(path);
		}catch (Exception e){
			return false;
		} 
		return true;
	}
	//判断路径+名称是否正确
	public static boolean issElement(String path,String text,IOSDriver driver) {
		try{
			 driver.findElementByXPath(path+"[@label='"+text+"']");
		}catch (Exception e){
			return false;
		} 
		return true;
	}
	
	//unicode编码中文的转换
	public static String unicodeToCn(String unicode) {
	    /** 以 \ u 分割，因为java注释也能识别unicode，因此中间加了一个空格*/
	    String[] strs = unicode.split("\\\\u");
	    String returnStr = "";
	    // 由于unicode字符串以 \ u 开头，因此分割出的第一个字符是""。
	    for (int i = 1; i < strs.length; i++) {
	      returnStr += (char) Integer.valueOf(strs[i], 16).intValue();
	    }
	    return returnStr;
	}
	 //中文到unicode编码的转换
	public static String cnToUnicode(String cn) {
		if(cn!=null)
		{ char[] chars = cn.toCharArray();
	    String returnStr = "";
	    for (int i = 0; i < chars.length; i++) {
	      returnStr += "\\u" + Integer.toString(chars[i], 16);
	    	}
	    return returnStr;
	    	}else{
	    	return "null";
	    }
	}



	//英文和数字随机数 10个字节
	/**
	 * 获取随机字母数字组合
	 *
	 * @param length
	 *            字符串长度
	 * @return
	 */
	public static String getRandomCharAndNumr(Integer length) {
		String str = "";
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			boolean b = random.nextBoolean();
			if (b) { // 字符串
				// int choice = random.nextBoolean() ? 65 : 97; 取得65大写字母还是97小写字母
				str += (char) (65 + random.nextInt(26));// 取得大写字母
			} else { // 数字
				str += String.valueOf(random.nextInt(10));
			}
		}
		return str;
	}

	/**
	 * 验证随机字母数字组合是否纯数字与纯字母
	 *
	 * @param str
	 * @return true 是 ， false 否
	 */
	public static boolean isRandomUsable(String str) {
		// String regExp =
		// "^[A-Za-z]+(([0-9]+[A-Za-z0-9]+)|([A-Za-z0-9]+[0-9]+))|[0-9]+(([A-Za-z]+[A-Za-z0-9]+)|([A-Za-z0-9]+[A-Za-z]+))$";
		String regExp = "^([0-9]+)|([A-Za-z]+)$";
		Pattern pat = Pattern.compile(regExp);
		Matcher mat = pat.matcher(str);
		return mat.matches();
	}
}
