package traverse;

import static org.junit.Assert.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import org.junit.Test;

public class HasDigit {

	public static final String REGEX = "\r\n";//分割规则
    public static final String REGEX2 = "";//控制布局
    
	// 判断一个字符串是否都为数字  
	public boolean isDigit1(String strNum) {  
	    return strNum.matches("[0-9]{1,}");  
	}  
	  
	// 判断一个字符串是否都为数字  
	public boolean isDigit(String strNum) {  
	    Pattern pattern = Pattern.compile("[0-9]{1,}");  
	    Matcher matcher = pattern.matcher((CharSequence) strNum);  
	    return matcher.matches();  
	}

	//截取数字  
	public static boolean getNumbers(String content) {  
	    Pattern pattern = Pattern.compile("\\d+");  
	    Matcher matcher = pattern.matcher(content);  
	    while (matcher.find()) {  
	       return true;  
	    }  
	    return false;  
	}  
	  
	// 截取非数字  
	public String splitNotNumber(String content) {  
	    Pattern pattern = Pattern.compile("\\D+");  
	    Matcher matcher = pattern.matcher(content);  
	    while (matcher.find()) {  
	        return matcher.group(0);  
	    }  
	    return "";  
	}
	  
	// 判断一个字符串是否含有数字
	public static boolean HasDigit(String content) {
	    boolean flag = false;
	    Pattern p = Pattern.compile(".*\\d+.*");
	    System.out.println(content);
	    Matcher m = p.matcher(content);
	    if (m.matches()) {
	        flag = true;
	    }
	    return flag;
	}
	
	


}
