package traverse;

import java.io.*;
import java.util.*;

import MainiOS.MainSetup;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.openqa.selenium.*;

import MainiOS.DistriTest;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.NoSuchElementException;

public class appiumAuto {
	 public static IOSDriver driver;
	 //延迟  时间
	 public static int Waitsleep = 2000;
	 //获取xml路径内容 添加遍历的界面里
	 public static ArrayList<String> Titlelist = new ArrayList<>();
	 //获取NavigationBar
	 public static ArrayList<String> Titlevalue = new ArrayList<>();
	 //用于判断是否存在NavigationBar
	 public static ArrayList<String> Tit = new ArrayList<>();
	 //判断是否再继续遍历 再同一个节点的数据
	 public static boolean strflag = true;
	//遍历cell控件只遍历用的变量
	 public static int Typecellcal = 1;
	 //遍历NavigationBar元素释是否为true
	 public static boolean flag = false;
	 //用于保存当前遍历的数据
	 public static ArrayList<String> StaticText = new ArrayList<>();
	 //屏幕的高度和宽度
	 static int width = 0;
	 static int height = 0;
	 //是否需要遍历标题
	 public static boolean WhiteIcon = true;
	 //用于退出tabflag循环
	 public static boolean Tabflag = false;
	 //切换Tab时  再次获取一样的控件不计入错误坐标总数
	 public static boolean Taberror = true;
		//切换Tab时  再次获取一样的控件不计入错误坐标总数
		public static boolean gock = false;
	 //获取标题
	 public static String TitlE = "";


	 //失败的坐标
	 public static ArrayList<String> Coorlist = new ArrayList<>();
	 //遍历时屏蔽的按钮--黑名单
	 public static List<String> listll = Arrays.asList(
			 //搜索按钮
			 "Dynamic Message SearchBar Icon","TopLine SearchBar White Icon","Message SearchIcon", "Message SearchBar Icon",
			 "DDCircle SearchIcon White",
			 //发现群
			 "DongCircleSearch addButton",
			 //屏蔽图片下载
			 "MW Download Icon",
			 //系统的图片/拍摄
			 "Chat ToolBar Add","相册",
			 //屏蔽 个人中心 相同功能的按钮  会点击两次的页面
             "退出登录","手机号","昵称","获取验证码","密码","语音验证","简介：","修改",
			 //屏蔽网页
			 "工具中心","帮助中心及意见反馈",
			 //点击跳转到其他页面的按钮 不需要----
             "我要贷款","给我评分","清除文本"

			 //测试屏蔽模块

	 );

	//返回按钮统一坐标屏蔽
	public static int getX = 16;
	public static int getY = 32;
	//遍历返回按钮
	public static List<String> listback = Arrays.asList(

			"Nav Back White","Nav Back Red","Nav More","Nav Back","Nav Close","返回","取消",
			"Topic NavBack WhiteIcon","Topic NavBack WhiteShadowIcon","DDSigninCloseBtn"

    );



/**
 * 每个列表的返回按钮
 *

 * @throws Exception
 */
public static void go_back() throws Exception {
	Thread.sleep(Waitsleep);
	String xml = driver.getPageSource();
    int i = 0;
	while (i<listback.size()) {
          String ele = listback.get(i);
          if(xml.contains(ele))
          {	  FileOperation.contentToTxt(MainSetup.filetext, ele ,1);
              driver.findElement(By.name(ele)).click();
              FileOperation.contentToTxt(MainSetup.filetext, "点击返回按钮:"+ele);
			  gock = true;
              Thread.sleep(Waitsleep);
              return;
		  }
		i++;
	}
	}

/**
 * 根据路径点击事件
 *

 * @throws Exception
 */
public static void ByXPathClick(String path) throws Exception {
	  	  	if( path !=null){
	  	  		try{
					Point location = driver.findElement(By.xpath(path)).getLocation();
					Dimension size = driver.findElement(By.xpath(path)).getSize();
					//点击之前先截图 画出要点击区域
					iOSTratool.ScreenScr.getScreen(driver,location.getX(),location.getY(),size.getWidth(),size.getHeight());
	  	  			driver.findElement(By.xpath(path)).click();
	  	  			}catch (NoSuchElementException e) {

	  	  				FileOperation.contentToTxt(MainSetup.filetext, "未找到点击控件："+path);
	  	  			}
			}
	  	  	Thread.sleep(Waitsleep);
	  }


	/**
	 * 输入框 随机输入
	 *

	 * @throws Exception
	 */
	public static void ByXPathText(String path) throws Exception {
		if( path !=null){
			try{
				Point location = driver.findElement(By.xpath(path)).getLocation();
				Dimension size = driver.findElement(By.xpath(path)).getSize();
				//点击之前先截图 画出要点击区域
				iOSTratool.ScreenScr.getScreen(driver,location.getX(),location.getY(),size.getWidth(),size.getHeight());
				WebElement element = driver.findElement(By.xpath(path));
				FileOperation.contentToTxt(MainSetup.filetext, "--输入随机数--");
				element.sendKeys(ement.getRandomCharAndNumr(30));
			}catch (NoSuchElementException e) {

				FileOperation.contentToTxt(MainSetup.filetext, "未找到点击控件："+path);
			}
		}
		Thread.sleep(Waitsleep);
	}


/**
 * 根据路径点击事件
 *
 * @throws Exception
 */
public static void ByClick(String path) throws Exception {
		if( path !=null){
			try{
				driver.findElement(By.xpath(path)).click();
			}catch (NoSuchElementException e) {
				FileOperation.contentToTxt(MainSetup.filetext, "未找到点击控件："+path);
				PrintWriter p = new PrintWriter(new FileOutputStream(MainSetup.filetext+"/error.txt"));
				e.printStackTrace(p);
			}}
		Thread.sleep(Waitsleep);
	}
/**
 * 判断控件是否存在
 *

 * @throws Exception
 */
public static Boolean isElementBypxth(String path) throws Exception{

		  boolean flagisElement = true;
		  //获取已点击过的path绝对路径  查看当前是否有点击过的 点击过的不再点击
		  if(!StaticText.isEmpty())
		  {
			  //获取遍历过的控件
			  for(String value : StaticText){
				  if(path.equals(value)){
					  return false;
				  }
			  }
		}


		 //判断当前的控件是否存在  不存在则无效点击事件
	      Coorlist.addAll(listll);
	      Coorlist.addAll(listback);
		  try{
		      String text = driver.findElement(By.xpath(path)).getAttribute("label");
		      if(text !=null)
			  {
                  for (Object aListll : Coorlist) {
                      try {
                          if (text.equals(aListll)) {
                              flagisElement = false;
                          }
                      } catch (Exception ignored) {

                      }
			  }Coorlist.clear();
			  //根据右上方返回路径按钮点击无效  个人左上方返回按钮 没有名字通过路径防止点击事件
			  String lipath = "/XCUIElementTypeApplication/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeNavigationBar/XCUIElementTypeButton";

			   if(path.equals(lipath))
			   	{flagisElement=false;}}
		      }catch (Exception e) {
			  e.printStackTrace();
		    	 FileOperation.contentToTxt(MainSetup.filetext, "未找到该元素控件"+path);
		    	 flagisElement = false;
			}

	    return flagisElement;
	  }



/**
 * 判断是button是否存在 和遍历

 * @throws Exception
 */
public static Boolean isElementText(String path,String text,String xll) throws Exception{
		  boolean flag = true;
		  if(ement.issElement(path,driver))
		  {
			  if(ement.issElement(path, text,driver)&&text!=null){
				  FileOperation.contentToTxt(MainSetup.filetext, "控件存在 页面未跳转成功1");
			  }else{
				  String txt = driver.findElement(By.xpath(path)).getAttribute("label");
				  double result=similarityResult.SimilarDegree(ement.cnToUnicode(text),ement.cnToUnicode(txt));
				  String xml = driver.getPageSource();
				  if(result >= 0.8){
					  FileOperation.contentToTxt(MainSetup.filetext, "控件存在--页面未跳转成功2");
				  }
				  else if(listdiff(xll).equals(listdiff(xml))){
					  FileOperation.contentToTxt(MainSetup.filetext, "控件存在 页面未跳转成功3");
				  }
				  else{
					  FileOperation.contentToTxt(MainSetup.filetext, "控件名称有误 发出遍历请求");
				  flag = false;}}
		  }else{
			  //控件不存在 判断是否在原来的界面 判断标题
			  if(GetTitle()){
				  FileOperation.contentToTxt(MainSetup.filetext, "标题存在表示点击的控件有数据被隐藏");
				  flag = true;
			  }else{
			  FileOperation.contentToTxt(MainSetup.filetext, "控件不存在 页面跳转成功");
				  flag = false;
			  }

		  }
	return flag;
}

	/**
	 * 判断点击控件后是否是否在原来的列表

	 * @throws Exception
	 */
	public static Boolean GetTitle() throws Exception{
		String xml = driver.getPageSource();
		System.out.println("type=\"XCUIElementTypeNavigationBar\" name=\""+TitlE+"\"");
		return xml.contains(TitlE) && xml.contains("type=\"XCUIElementTypeNavigationBar\" name=\""+TitlE+"\"") ;

	}



/**
 * 判断是xpthText是否存在 和遍历

 * @throws Exception
 */
public static Boolean isElementxpthText(String path,String text,String xll) throws Exception{
	boolean flag = true;
	  try{
		  if(text!=null ){
			  if(HasDigit.getNumbers(text))
			  {
				  driver.findElementByXPath(path);
				  String xml = driver.getPageSource();
				  if(listdiff(xll).equals(listdiff(xml)))
				  {
					  FileOperation.contentToTxt(MainSetup.filetext, "Text存在 页面未跳转成功--1");
				  }else {
					  FileOperation.contentToTxt(MainSetup.filetext, "Text不存在 页面跳转成功--3");
				  	flag = false;
				  }
			  }
			  else{
			  	driver.findElementByXPath(path+"[@label='"+text+"']");
				  FileOperation.contentToTxt(MainSetup.filetext, "Text存在 页面未跳转成功--2");
			  }
		  }
		  else{
			 	driver.findElementByXPath(path);
			  	FileOperation.contentToTxt(MainSetup.filetext, "Text存在 页面未跳转成功--4");}
		  }catch (Exception e){

		  		System.out.println("判断是xpthText 找不到了...");
		  		flag = false;
		  }
	  return flag;
}


	/**
	 * 判断是 isElementImage 是否存在 和遍历

	 * @throws Exception
	 */
	public static Boolean isElementImage(String path,int X,int Y) throws Exception{
		try{
			Point loca = driver.findElement(By.xpath(path)).getLocation();
			if(loca.getX()==X && loca.getY() ==Y){
				FileOperation.contentToTxt(MainSetup.filetext, "Image 存在-未跳转成功--1");
				ByClick(path);
				return true;
			}else{
				FileOperation.contentToTxt(MainSetup.filetext, "Image 跳转成功--1");return false;
			}
		}catch (Exception e){
			FileOperation.contentToTxt(MainSetup.filetext, "Image 跳转成功--2");
			return false;
		}
	}




/**
* 判断是button是否需要跳转
 *

 * @return  返回 文本内容
 * @throws Exception
 */
public static String ElemtText(String path) throws Exception{
		  String flag = "";
		  try{
			  flag =driver.findElement(By.xpath(path)).getAttribute("label");
		  }catch (Exception e)
		  {
			  FileOperation.contentToTxt(MainSetup.filetext, "未找到元素控件："+path);}
		  return flag;
	  }



/**
* 判断是Elemt
 *

 * @return  返回 文本内容
 * @throws Exception
 */
public static String ElemtCell(String path) throws Exception{
		  String flag = "";
		  try{
			  flag =driver.findElementByXPath(path+"/XCUIElementTypeStaticText[1]").getText();
		  }catch (Exception a)
		  {
			  try{
				  flag =driver.findElementByXPath(path+"/XCUIElementTypeButton[1]").getText();
			  }catch (Exception aa){

				  FileOperation.contentToTxt(MainSetup.filetext, "未找到元素控件："+path);
			  }
			  }
		  return flag;
 }



/**
* 判断是否为系统控件--系统控件直接点击取消按钮
 *

 * @throws Exception
 */
public static void SystemButton() throws Exception{
	try{
		driver.findElementByName("取消").click();
		Thread.sleep(Waitsleep);
		}catch (Exception e){
			e.printStackTrace();
			FileOperation.contentToTxt(MainSetup.filetext, "未检测出系统控件");
		}
}

	/**
	 * 判断是否为系统控件--系统控件直接点击好按钮
	 *

	 * @throws Exception
	 */
	public static void SystemButton2() throws Exception{
		List<String> liSt1 = Arrays.asList(
				"好","确定","取消","允许","不允许"
		);
		try{
			for(String value : liSt1)
			{
				driver.findElementByName(value).click();
				Thread.sleep(Waitsleep);
			}
		}catch (Exception e){
			e.printStackTrace();
			FileOperation.contentToTxt(MainSetup.filetext, "未检测出系统控件");
		}

	}


/**
* 判断是否已经获取过的页面
 *
 * @throws Exception
 */
public static boolean TitleCycleText(String path,ArrayList<String> Tvalue) throws Exception{
	Thread.sleep(Waitsleep);
	if(!Tvalue.isEmpty()){
		for(String value : Tvalue){
				if(path.equals(value)){
					FileOperation.contentToTxt(MainSetup.filetext, "存在遍历 :"+value,1);
				return true;
			}
		}
	}
	return false;
}




    /**
* 遍历相似度界面--相似度百分之80以上
 *
 * @throws Exception
 */
public static boolean TitleCycleTextString(String path) throws Exception{
	if(!Titlelist.isEmpty()){
		for(String value : Titlelist){
			if(value.length()>20)
			{
			double result=similarityResult.SimilarDegree(path, value);
				if(path.equals(value)){
					FileOperation.contentToTxt(MainSetup.filetext, "存在遍历"+value,1);
					return true;
					}
				if(result>=0.9){
					FileOperation.contentToTxt(MainSetup.filetext, "相似度很高！百分之:" +similarityResult.similarityResult(result)+"  "+result);
					return true;
				}else{
					FileOperation.contentToTxt(MainSetup.filetext, "相似度不高！百分之:" +similarityResult.similarityResult(result)+"  "+result,1);

			}
		}
		}
	}

	return false;
}





/**
 * 遍历当前节点元素下面的所有(元素的)子节点
 *

* @throws Exception
 */
public static void listNodes(Element node) throws Exception {
	//直接退出控件点击循环
	if(!strflag)return;
	Tit.clear();
    // 获取当前节点的所有属性节点
    List<Attribute> list = node.attributes();
    //创建一个可点击控件名称列表
    ArrayList<String> lister = new ArrayList<>();
    ArrayList<String> listerpath = new ArrayList<>();
    // 遍历属性节点
    for (Attribute attr : list) {
    	 if(!Tit.isEmpty()){
			 FileOperation.contentToTxt(MainSetup.filetext, "Tit不为空："+ attr.getValue()+WhiteIcon);
    			if(Tit.get(0).contains("NavigationBar") && WhiteIcon){
    			 FileOperation.contentToTxt(MainSetup.filetext, "获取标题内容1："+ attr.getValue());
					TitlE = attr.getValue();
    			 if(TitleCycleText(attr.getValue(),Titlevalue)){
    				FileOperation.contentToTxt(MainSetup.filetext,"该页面已遍历过，不再遍历该数据: ");
    			    strflag = false;
    			 	Tit.clear();
    			    return;
    			 }else{
    				 FileOperation.contentToTxt(MainSetup.filetext,"添加标题："+ attr.getValue());
    				 Titlelist.add(TitlE);
					 Titlevalue.add(TitlE);
    	  			}
    			}
			 	Tit.clear();
     		}
  	  //获取能点击的控件比如 Button / StaticText
  	  if(attr.getValue().contains("Button") || attr.getValue().contains("StaticText") ||
			  attr.getValue().contains("TypeImage")||attr.getValue().contains("TypeText")){
  		lister.add(attr.getValue());
  		String[] path=attr.getUniquePath().split("/AppiumAUT");
  		String[] list2=path[1].split("/@");
  		//判断是否在屏幕的坐标内 在屏幕的坐标内按钮添加到列表
		if(ScreenShot(list2[0])){
  			listerpath.add(list2[0]);
  		}
  		//判断这些控件的直接退出循环 不再往下查找数据 下面的数据都是系统控件 不用再去匹配
  	  }
	  else if (attr.getValue().contains("TypeTabBar")||attr.getValue().contains("TypeKeyboard")){
		  strflag = false;
  		  return;
	  }
  	  //获取界面名称
  	   else if(attr.getValue().contains("NavigationBar")){
		  	FileOperation.contentToTxt(MainSetup.filetext, "获取NavigationBar："+ attr.getValue());
  	  		Tit.add(attr.getValue());
	  }
  	 }
    for (int i =0; i<listerpath.size();i++) {
		switch (lister.get(i)) {
			//根据列表的 button 控件做点击事件
			case "XCUIElementTypeButton":
				XCUIElemButton(listerpath.get(i));
				break;
			//根据列表的 StaticText 做点击事件
			case "XCUIElementTypeStaticText":
				XCUIElemStaticText(listerpath.get(i));
				if (Tabflag) {
					System.out.println("退出上一个页面的循环");
					Tabflag = false;
					strflag = false;
					return;
				}
                break;
			//根据列表的 TypeImage 控件做点击事件
			case "XCUIElementTypeImage":
				XCUIElemTypeImage(listerpath.get(i));
				break;
			//根据列表的 TextField 控件做点击事件
			case  "XCUIElementTypeTextField":
				XCUIElemTextField(listerpath.get(i));
				break;
			//根据列表的 TextView 控件做点击事件
			case "XCUIElementTypeTextView":
				XCUIElemTextField(listerpath.get(i));
				break;
		}
	}

    // 当前节点下面子节点迭代器
    Iterator<Element> it = node.elementIterator();
    // 遍历
    while (it.hasNext()) {
        // 获取某个子节点对象
        Element e = it.next();
        // 对子节点进行遍历
        listNodes(e);
    }

}
/**
 *  Taberror 进行判断
 *  @throws Exception
 */
private static Boolean XCUIElemTaB()throws Exception{
	if(!Taberror){
		Taberror = true;
	}
	else if(Typecellcal<=20){
		Typecellcal += 1;
		return true;
	}
	else{
		strflag = false;
		Typecellcal = 1;
		return true;
	}
	return false;
}



/**
 *  button click 点击Butto事件操作
 *
 * @throws Exception
 */
public static void XCUIElemButton(String listerpath) throws Exception{

	if(isElementBypxth(listerpath)){ //判断控件是否存在
		String text = appiumAuto.ElemtText(listerpath);//获取控件name
		FileOperation.contentToTxt(MainSetup.filetext, "点击Button名称 ："+text);
		String xll = driver.getPageSource();
		ByXPathClick(listerpath); //点击button控件--没有则断言继续执行
			StaticText.add(listerpath);
		if(!isElementText(listerpath,text,xll)&& !Objects.equals(text, "")){//判断当前控件不在页面内 重新遍历
			FileOperation.contentToTxt(MainSetup.filetext,"未找到原来的控件/准备遍历元素 : " +text);
			ListSaved();
		}
		else {
			ByClick(listerpath);
			diffStat();
		}}
}




/**
 *  StaticText click 点击StaticText事件操作
 * @throws Exception
 */
public static void XCUIElemStaticText(String listerpath) throws Exception{
	if(isElementBypxth(listerpath)){ //判断控件是否存在
		String text = appiumAuto.ElemtText(listerpath);//获取控件name
		FileOperation.contentToTxt(MainSetup.filetext, "点击StaticText名称 ："+text);
		String xll = driver.getPageSource();
		ByXPathClick(listerpath); //点击StaticText控件--没有则断言继续执行;
			StaticText.add(listerpath);
			//判断当前控件不在页面内 重新遍历
			if(!isElementxpthText(listerpath,text,xll)&& ! Objects.equals(text, "")){
                FileOperation.contentToTxt(MainSetup.filetext,"未找到原来的控件/准备遍历元素 : " +text);
                ListSaved();
			}
			else if(!diffStaticText(xll))
			{diffStat();}
			else
			{Tabflag = false;}
	}
}



	/**
	 *  StaticText click 点击 Image 事件操作
	 * @throws Exception
	 */

public static void XCUIElemTypeImage(String listerpath) throws Exception{
	if(isElementBypxth(listerpath)){ //判断控件是否存在
		FileOperation.contentToTxt(MainSetup.filetext, "点击Image名称");
		Point location = driver.findElement(By.xpath(listerpath)).getLocation();
		ByXPathClick(listerpath); //点击Image控件--没有则断言继续执行;
		StaticText.add(listerpath);
		//判断当前控件不在页面内 重新遍历
		if(!isElementImage(listerpath,location.getX(),location.getY())){
			FileOperation.contentToTxt(MainSetup.filetext,"未找到原来的控件/准备遍历元素 : ");
			ListSaved();
		}
		else{Tabflag = false;}
	}

}



	/**
	 *  StaticText click 点击 TextField 事件操作
	 * @throws Exception
	 */

	public static void XCUIElemTextField(String listerpath) throws Exception{
		//判断控件是否存在
		if(isElementBypxth(listerpath)){
			FileOperation.contentToTxt(MainSetup.filetext, "选择 TextField 名称");
			FileOperation.contentToTxt(MainSetup.filetext, "清空原来的内容");
			driver.findElement(By.xpath(listerpath)).clear();
			//判断存在输入30个随机字符串
			ByXPathText(listerpath);
			}
	}


	/**
	 *  StaticText click 判断xll是否一致  一致且不进入遍历 反之进入遍历
	 *
	 * @throws Exception
	 */
	public static void diffStat() throws Exception{
				//发现数据不一致  重新获取遍历节点
				FileOperation.contentToTxt(MainSetup.filetext, "----重新获取元素----");
				String xml = driver.getPageSource();
				Document document = DocumentHelper.parseText(xml);
				//获取根节点元素对象
				Element node = document.getRootElement();
				WhiteIcon = false;
				strflag=true;
				listNodes(node);
				Tabflag = true;
				WhiteIcon = true;

	}

/**
 *  StaticText click 进入重新遍历  记录当前的列表 返回时列表给回上一层
 *
 * @throws Exception
 */
public static void ListSaved() throws Exception{
    //记录上一个页面的list内容
    ArrayList<String> SingList = new ArrayList<>();
	String tel = TitlE;
	int Typecal  = Typecellcal;
    SingList.addAll(StaticText);
	DistriTest.Traverse(driver);
	strflag=true;
	Tabflag = false;
    //返回上一层的list内容
	Typecellcal  = Typecal;
	TitlE = tel;
    StaticText.addAll(SingList);
	SingList.clear();
}

//StaticText页面对比是否切换TAB
public static boolean diffStaticText(String xll) throws Exception{
	Boolean flag = false;
	Thread.sleep(Waitsleep);
	String xxll = driver.getPageSource();
		if(listdiff(xll).equals(listdiff(xxll)))
		{flag = true;}
	return flag;
}



//遍历 xml 数据 做diff
public static String listdiff(String xml) throws Exception{
	    //获取根节点元素对象
        Document document = DocumentHelper.parseText(xml);
	    //获取根节点元素对象
        Element node = document.getRootElement();
		DistriTest.xmll="";
	    //遍历
	    listNodesdiff(node);
	    return DistriTest.xmll;
}


//遍历当前节点下的所有节点
public static void listNodesdiff(Element node){
	Tit.clear();
		//首先获取当前节点的所有属性节点
		List<Attribute> list = node.attributes();
		//遍历属性节点
		for(Attribute attr : list){
			//获取标题的名称  判断是否在同一个界面上
			if(!Tit.isEmpty()){
    			if(Tit.get(0).contains("NavigationBar")){
					DistriTest.xmll = DistriTest.xmll + attr.getValue();}
    		Tit.clear();
    	  }
			//判断这些控件的直接退出循环 不再往下查找数据 下面的数据都是系统控件 不用再去匹配
			if(attr.getValue().contains("TypeTabBar")||attr.getValue().contains("StatusBar")){
		  		  return;}
			 //获取能点击的控件或者可写入的text
			else if(attr.getValue().contains("Button")||attr.getValue().contains("TextField")
					||attr.getValue().contains("TypeCell")||attr.getValue().contains("TypeImage")||attr.getValue().contains("ScrollView")){
				DistriTest.xmll = DistriTest.xmll + attr.getValue();
			 }
			else if(attr.getValue().contains("NavigationBar")){
  	  		Tit.add(attr.getValue());
  	  		}
		}
		//如果当前节点内容不为空，则输出
		if(!(node.getTextTrim().equals(""))){
			 System.out.println( node.getName() + "：" + node.getText());}
		//同时迭代当前节点下面的所有子节点
		Iterator<Element> iterator = node.elementIterator();
		while(iterator.hasNext()){
			Element e = iterator.next();
			listNodesdiff(e);
		}
}

//NavigationBar 判断 标题是否存在
public static Boolean diffNavigationBar(Element node) throws Exception{
		if(flag)
		{return flag;}
				Tit.clear();
				//首先获取当前节点的所有属性节点
				List<Attribute> list = node.attributes();
				//遍历属性节点
				for(Attribute attr : list){
					if(!Tit.isEmpty()){
			    		if(Tit.get(0).contains("NavigationBar")){
			    		 	FileOperation.contentToTxt(MainSetup.filetext, "获取标题内容2："+ attr.getValue());
			    		 if(TitleCycleText(attr.getValue(),Titlevalue)){
							FileOperation.contentToTxt(MainSetup.filetext,"已遍历该页面: "+attr.getValue());
			    		    Tit.clear();
			    		    //遍历存在 点击返回按钮
			    		    return flag = true;
						 }
			    		 else{
							 FileOperation.contentToTxt(MainSetup.filetext, "未找到该遍历标题");Tit.clear();}
			    		}
			    	}
					if(attr.getValue().contains("NavigationBar")){
						FileOperation.contentToTxt(MainSetup.filetext, "添加 NavigationBar");
                        Tit.add(attr.getValue());
			  	  	}
				}
				//同时迭代当前节点下面的所有子节点ƒ
				Iterator<Element> iterator = node.elementIterator();
				while(iterator.hasNext()){
					Element e = iterator.next();
					diffNavigationBar(e);
                }return flag;
	}


//获取当前手机的屏幕
public static void Heiwidth(){
	 width = driver.manage().window().getSize().width;
	 height = driver.manage().window().getSize().height;
		System.out.println("width:"+width+" height:"+height);
	 }


//判断是否在屏幕的坐标范围内
public static Boolean ScreenShot(String path) throws InterruptedException, FileNotFoundException {
	Boolean flag2 = false;
	if (!WhiteIcon) {
		//获取已点击过的path绝对路径  查看当前是否有点击过的 点击过的不再点击
		if(!StaticText.isEmpty())
		{
			//获取遍历过的控件
			for(String value : StaticText){
				if(path.equals(value)){
					Taberror = false;
					return false;
				}
			}
		}
	}
	FileOperation.contentToTxt(MainSetup.filetext,path,1);
	try{
		Point location = driver.findElement(By.xpath(path)).getLocation();
		FileOperation.contentToTxt(MainSetup.filetext, "按钮坐标 getX: "+location.getX()+" getY: "+location.getY(),1);
			if(location.getX()<=width && location.getY() < height && location.getX()>=0 && location.getY() >= 0)
			{
				if (location.getX() != 0 && location.getY() != 0) {
					//屏蔽返回按钮的坐标
					if(location.getX()==getX && location.getY()== getY){
						FileOperation.contentToTxt(MainSetup.filetext, "坐标为返回按钮 不点击该事件");
					}else {
						flag2 = true;
					}
				}
				else {
				FileOperation.contentToTxt(MainSetup.filetext, "---0 0坐标不需要点击事件---");
					//判断出现过多坐标不在屏幕内的自动退出当前页面
					if(Typecellcal>10) {strflag = false; return false;}
					Typecellcal++;
				}
			}else{
				//判断出现过多坐标不在屏幕内的自动退出当前页面
				if(Typecellcal>10) {strflag = false; return false;}
				Typecellcal++;
			}
		}
	catch (Exception e) {
		FileOperation.contentToTxt(MainSetup.filetext, "xpath出现问题,请查看对应的路径是否存在");
		PrintWriter p = new PrintWriter(new FileOutputStream(MainSetup.cyrPatn+"/error2.txt"));
		e.printStackTrace(p);
		}
	return flag2;
	}



}
