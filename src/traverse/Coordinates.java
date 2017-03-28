package traverse;

import static org.junit.Assert.*;

import org.dom4j.io.SAXReader;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;



public class Coordinates {

	public void pul() {
		// TODO Auto-generated method stub
	  	  //
//	   else if(attr.getValue().contains("TypeCell")){
//		    lister.add(attr.getValue());
//			String[] path=attr.getUniquePath().split("/AppiumAUT");
//			String[] list2=path[1].split("/@");
//			listerpath.add(list2[0]);
//			//判断是TypeCell 且不再获取获取以下的控件--直接点击cell
////			cell = 0;
//		   }

		// 创建saxReader对象
		SAXReader reader = new SAXReader();
//		/**
//		 * 获取遍历的页面数据
//		 *
//
//		 * @throws Exception
//		 */
//		public static void TitleStaticText(String text) throws Exception{
//			try{
//				Titlelist.add(text);
//				FileOperation.contentToTxt(DongDongkeyword.filetext, "获取遍历页面："+ text);
//			}catch (Exception e){
//				FileOperation.contentToTxt(DongDongkeyword.filetext, "未检测出可保存的页面");
//			}
//
//		}
		
//	    BufferedImage originalImage =
//	    ImageIO.read(new ByteArrayInputStream(takeScreenshot()));
//	  // 截取webElement所在位置的子图。
//  BufferedImage croppedImage = originalImage.getSubimage(
//	      location.getX(),
//	      location.getY(),
//	      size.getWidth(),
//	      size.getHeight());
//  	File screenShotFile =  ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);  
  	
//	case "XCUIElementTypeCell2":
//			if (Typecellcal<3){ 
//				int op = Typecellcal;
//				FileOperation.contentToTxt(DongDongkeyword.filetext, "Typecellcal :"+ Typecellcal);
//				XCUIElemCell(listerpath.get(i));
//				Typecellcal = op;
//				Typecellcal++;
//			}
//			break;
		
//		
//		/** 
//		 * 判断是xpthCell是否存在 和遍历  
//		 * @param node 
//		 * @throws Exception 
//		 */  
//		public static Boolean isElementxpthCell(String path,String text) throws Exception{
//			Thread.sleep(Waitsleep);  
//			boolean flag = true;
//			  try{
//					  WebElement element=(WebElement) driver.findElementByXPath(path);
//					  String Static = appiumAuto.ElemtCell(path);
//			
//					  if(text.equals(Static))
//					  {	  flag =true;
//					  		String xml = driver.getPageSource();
//					  		String xll =appiumAuto.listdiff(xml);
//					  		System.out.println(xll);
//					  		if(xll.equals(DongDongkeyword.xmlle)){
//					  			FileOperation.contentToTxt(DongDongkeyword.filetext,"上一个界面和现在的界面相同");
//					  			go_back();
//					  			flag = true;}else{
//					  				FileOperation.contentToTxt(DongDongkeyword.filetext,"跳转到1:" + text);
//					  				flag = false;}
//				      } else
//				      {	  FileOperation.contentToTxt(DongDongkeyword.filetext,"跳转到2:" + text);
//				    	  flag = false;}
//					  
//					 
//				  }catch (Exception a){   
//				  			flag = false;
//				  }
//			  return flag;
//		}
		
		
//		/** 
//		*  button click 点击Cell事件操作
//		 * 
//		 * @param node 
//		 * @return 
//		 * @throws Exception 
//		 */  
//		public static void XCUIElemCell(String listerpath) throws Exception{
//			Thread.sleep(Waitsleep);
//			String text = appiumAuto.ElemtCell(listerpath);//获取Cell下第一个StaticText名称
//			if(appiumAuto.isElementBypxth(listerpath)&&ScreenShot(listerpath)){ //判断控件是否存在
//				FileOperation.contentToTxt(DongDongkeyword.filetext, "点击TypeCell名称 ："+text);
//				appiumAuto.ByXPathClick(listerpath); //点击button控件--没有则断言继续执行
//				if(CellClick())
//				{Thread.sleep(Waitsleep);
//				
//				if(!appiumAuto.isElementxpthCell(listerpath,text)&&text!=""){//判断当前控件不在页面内 重新遍历
//					FileOperation.contentToTxt(DongDongkeyword.filetext,"未找到原来的控件/准备遍历元素 : " +text);
//					DongDongkeyword.Dongdongtest002();
//					}
//				}}
//		}	
		
		
//		/** 
//		*  用于判断 点击没反应的界面
//		 * 
//		 * @param node 
//		 * @return 
//		 * @throws Exception 
//		 */  
//
//		public static Boolean CellClick() throws Exception{
//			   Boolean flag = false;
//				String xml = driver.getPageSource();
//				String xll =appiumAuto.listdiff(xml);
//				if(xll.equals(DongDongkeyword.xmlle))
//				{  
//					FileOperation.contentToTxt(DongDongkeyword.filetext, "控件点击没有跳转页面-进行操作下一个");
//					flag =  false;
//					}else{
//						flag = true;}
//			Dimension size = element.getSize();
//			return flag;
//		}
		
		
	////判断是否在屏幕的坐标范围内
		//public static Boolean ScreenShot(String path) throws InterruptedException{
//			Boolean flag = false;
//			  WebElement element = (WebElement) driver.findElementByXPath(path);
//			  Point location =element.getLocation();
//			  FileOperation.contentToTxt(DongDongkeyword.filetext, "按钮坐标 getX: "+location.getX()+" getY: "+location.getY() ,1);
//			  FileOperation.contentToTxt(DongDongkeyword.filetext, path ,1);
//			  if(location.getX()<=width && location.getX()>=0){
//				  if(location.getY() <height-60 && location.getY() >=0){
//					  	flag = true;
//				  	}
//				 } 
//			return flag;
		//}
	}

//	FaceJudge(xml);
//	//首先判断NavigationBar 标题是否存在
//	if(appiumAuto.diffNavigationBar(node))
//	{ 	appiumAuto.go_back();
//		appiumAuto.flag = false;
//	}
//	//在判断Titlelist 列表内是否存在重复的xmll
//	else if(appiumAuto.TitleCycleText(path,appiumAuto.Titlelist)){
//		appiumAuto.go_back();
//	}else{
//		FileOperation.contentToTxt(MainSetup.filetext, "遍历存在且不需要点击返回按钮");
//		xmlle = path;gock = false;
//	}

}
