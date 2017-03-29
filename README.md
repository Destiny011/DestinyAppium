## Appium iOS 简单分析xml写自动化遍历测试

## 简介

​	基于appium编写的自动化测试工具，通过获取driver.getPageSource()日志内容进行解析出StaticText/button/imge/TextField 进行简单的点击/清空/写入操作，主要是学习遍历程序逻辑思维，都是通过坐标点击事件操作；

## 解决

​	预期是用于版本迭代的时候所有的控件都点击一遍- 看有没有闪退现象 每次点击都会截图且绘制点击的区域，达到最后报错除了日志以外可以看图片点击的操作；

​	

### 跳转的处理

1.每次点击后判断是否离开当前界面(通过xpath和当前的xml判断)

2.如果跳转到保存过的NavigationBar自动点击返回按钮(通过xml路径分析相似度)，返回按钮需要自己录制；

3.点击弹出系统控件时--不作处理自动点击取消，允许/不允许、确定、好等Name，不兼容需要自己录制；

4.保存数据自动返回到上一层自动判断是否在上一层XML上 ;

5.上一界面如果返回到这一层再跳转到下面的界面是相同时点击返回时会设置一个参数来区别；

6.一些异常场景没有做出判断需要屏蔽的需要自己录制；

##### 日志

1、暂时通过文本的形式把日志写入，后续会改成log4j；

2、通过ImageIO的类来做图片绘制控件的点击范围；

### 逻辑图

![模块遍历入口](/Users/zhangzhixiong/项目/DestinyAppium2/imge/模块遍历入口.png)

##### 参数设置

```
/***
 * 启动模块自动化遍历入口
 * @throws Exception
 */
@Test
public void test_01() throws Exception{
    MainSetup.cyrPatn = MainSetup.cyrPatn+"/"+"模块1";
    driver.findElement(By.name("模块1")).click();
    Thread.sleep(2000);
    DistriTest.Traverse(driver);
}
/***
 * 启动模块自动化遍历入口
 * @throws Exception
 */
@Test
public void test_02() throws Exception{
    MainSetup.cyrPatn = MainSetup.cyrPatn+"/"+"模块2";
   driver.findElement(By.name("模块2")).click();
    Thread.sleep(2000);
    DistriTest.Traverse(driver);
}
/***
 * 启动模块自动化遍历入口
 * @throws Exception
 */
@Test
public void test_03() throws Exception{
    MainSetup.cyrPatn = MainSetup.cyrPatn+"/"+"模块3";
    driver.findElement(By.name("模块3")).click();
    Thread.sleep(2000);
    DistriTest.Traverse(driver);
}
/***
 * 启动模块自动化遍历入口
 * @throws Exception
 */
@Test
public void test_04() throws Exception{
    MainSetup.cyrPatn = MainSetup.cyrPatn+"/"+"模块4";
    driver.findElement(By.name("模块4"))   .click();
    Thread.sleep(2000);
    DistriTest.Traverse(driver);
}
```

##### 遍历入口

```
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
```



#### 选择控件入口

```
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
   //根据列表的 TextField 控件做写入事件
   case  "XCUIElementTypeTextField":
      XCUIElemTextField(listerpath.get(i));
      break;
   //根据列表的 TextView 控件做写入事件
   case "XCUIElementTypeTextView":
      XCUIElemTextField(listerpath.get(i));
      break;
}
```



#### 怎么运行程序

1、只要修改被测应用包名称

```
System.out.println("准备开始测试");
DesiredCapabilities capabilities = new DesiredCapabilities();
capabilities.setCapability("deviceName", "iPhone 5s");
capabilities.setCapability("platformVersion", "10.1");
capabilities.setCapability("platformName", "iOS");
capabilities.setCapability("bundleId", "com.app.ng");
capabilities.setCapability("locationServicesAuthorized", true);
capabilities.setCapability("waitForAppScript", "$.delay(5000); $.acceptAlert(); true;");
capabilities.setCapability("app", "*****");
capabilities.setCapability("autoAcceptAlerts", false);
capabilities.setCapability("udid", "b5109a245a39d8eef252ee75887badfe26c4a9f2");
capabilities.setCapability("logLevel", "DEBUG");
capabilities.setCapability("automationName", "XCUITest");  // 新的IOS10
driver=new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
System.out.println("启动知乎app成功");
```

2、修改需要屏蔽的按钮—自己录制

```
//遍历时屏蔽的按钮--黑名单
 public static List<String> listll = Arrays.asList(
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
```

3、修改日志和图片路径

```
public static String cyrPatn = "/Users/xxxx/Documents/AppiumPng";

	   //driveriver 传入appiumauto类
        appiumAuto.driver = driver;
        FileOperation.deleteFile(filetext);
        //获取设备高度和宽度
        appiumAuto.Heiwidth();
        //图片存放处
        MainSetup.cyrPatn = MainSetup.cyrPatn+"/"+ScreenScr.ScreenTime();
        //日志存放处
        filetext = MainSetup.cyrPatn+"/logiOS.txt";
        //创建目录
        FileOperation.createDir(MainSetup.cyrPatn);
```

##### 测试框架

我用的是testng框架  需要登录引导图什么自己建测试类写



#### 缺点

1、点击手速真的很慢...

2、比如A界面跳转到B B跳转C界面 —B自动销毁—然后遍历就没法回去—我直接屏蔽这样的按钮；

3、没有保存错误日志(后续研究)

4、有个别返回按钮没有名称只有路径和坐标—我直接屏蔽点击这个东西



## 后续

1、获取日志：通过终端获取https://testerhome.com/topics/8060

2、修改日志为log4j-优化下代码看重复代码怎么优化



本人代码能力不是很强—有很多重复的代码请大家见谅…希望大神们给我思路让我再进一步；