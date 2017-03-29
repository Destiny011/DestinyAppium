package MainiOS;/**
 * Created by zhangzhixiong on 17/3/16.
 */

import io.appium.java_client.ios.IOSDriver;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import traverse.FileOperation;
import traverse.appiumAuto;
import traverse.similarityResult;

public class DistriTest {
    public static int Waitsleep = 5000;
    public static String xmll="",xmlle="",xmllle="",path="";
    public static boolean gock = true;


    public static void Traverse(IOSDriver driver) throws Exception{
        Thread.sleep(Waitsleep);
        //清空 StaticText 的记录
        appiumAuto.StaticText.clear();
        String  xml= driver.getPageSource();
        //FileOperation.contentToTxt(MainSetup.filetext,xml,1);
        path =appiumAuto.listdiff(xml);
        Document document = DocumentHelper.parseText(xml);
        //获取根节点元素对象
        Element node = document.getRootElement();
        //首先判断系统控件是否存在(挂起选择拍照/手机相册和图片时自己点击返回按钮)
        if (xml.contains("XCUIElementTypeAlert")||xml.contains("XCUIElementTypeSheet")){
            appiumAuto.SystemButton();}
        //首先判断系统控件是否存在(XCUIElementTypeAlert)
        else if(xml.contains("XCUIElementTypeAlert")){
            appiumAuto.SystemButton2();
        }
        //再次判断xml是否与上次获取的xml相同 且存在一样的标题
        else if(xmllle.equals(path) && appiumAuto.diffNavigationBar(node)){
            FaceJudge(xml);
        }
        //判断path是否存在我们的界面数组里面
        else if(appiumAuto.TitleCycleTextString(path)){
            System.out.println("相似度高点击返回按钮");
            appiumAuto.go_back();
        }//判断标题是否存在列表中
        else if(appiumAuto.diffNavigationBar(node))
        {   System.out.println("标题已存在--返回到上一层界面");
            appiumAuto.go_back();
            appiumAuto.flag = false;
        }
        //执行遍历
        else{
            program(node);
        }
    }



    // 先判断标题是否存在  再判断 xmll与是否存在list里面
    public static void FaceJudge(String xml) throws Exception{
        FileOperation.contentToTxt(MainSetup.filetext, "--xmll判断---");
        //获取整个页面的
        Document document = DocumentHelper.parseText(xml);
        Element node = document.getRootElement();
        appiumAuto.flag = false;
            //在判断Titlelist 列表内是否存在重复的xmll
            if(appiumAuto.TitleCycleText(path,appiumAuto.Titlelist) && appiumAuto.gock ){
                    appiumAuto.go_back();
                    appiumAuto.gock = false;
            }else{
                FileOperation.contentToTxt(MainSetup.filetext, "遍历存在且不需要点击返回按钮");
                appiumAuto.strflag = false;
                xmlle = path;
                gock = false;
            }

    }

    //执行遍历程序
    public static void program(Element node) throws Exception{
        //添加遍历出来的整个列表内容
        appiumAuto.Titlelist.add(xmll);
        FileOperation.contentToTxt(MainSetup.filetext, "遍历所有的元素节点");
        xmllle = xmlle;
        xmlle = path;
        appiumAuto.gock = false;
        appiumAuto.strflag = true;
        appiumAuto.WhiteIcon = true;
        //遍历所有的元素节点
        appiumAuto.listNodes(node);
        appiumAuto.Typecellcal = 1;
        appiumAuto.StaticText.clear();
        if(gock){
            FileOperation.contentToTxt(MainSetup.filetext, "回到上一层遍历");
            appiumAuto.go_back();
        }
        gock = true;
        appiumAuto.strflag = true;
    }
}
