package traverse;


import io.appium.java_client.ios.IOSDriver;

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
	//
	
	
}
