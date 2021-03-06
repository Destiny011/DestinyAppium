package MainiOS;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import traverse.FileOperation;
import traverse.appiumAuto;
import iOSTratool.ScreenScr;
import java.net.URL;

/**
 * Created by zhangzhixiong on 17/3/16.
 **/
@Listeners({cn.jk.AssertionListener.class})
public class MainSetup {
    public static IOSDriver driver;
    public static String filetext = "";
    public static  int appclose = 0;
    public static String logpat = "";


    public static String cyrPatn2 = "/Users/zhangzhixiong/Documents/AppiumPng";
    public static String cyrPatn = "",cyrPatn3 = "";
    @BeforeMethod
    public void beforeMethod() throws Exception {
        if (appclose > 0)
        {
            Thread.sleep(1000);
            driver.closeApp();
            appiumAuto.Titlelist.clear();
            Thread.sleep(5000);
            driver.launchApp();

        } appclose++;
        Thread.sleep(9000);
    }

    @AfterMethod
    public void afterMethod() throws Exception {
        System.out.println("当前模块遍历结束");
    }
    /***
     * 启动app程序
     * @throws Exception
     */
    @BeforeClass
    public static void setup() throws Exception{

        System.out.println("准备开始测试");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "iPhone 5s");
        capabilities.setCapability("platformVersion", "10.1");
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("bundleId", "com.szhome.SZHomeForum");
        capabilities.setCapability("locationServicesAuthorized", true);
        capabilities.setCapability("waitForAppScript", "$.delay(5000); $.acceptAlert(); true;");
        capabilities.setCapability("app", "/Users/zhangzhixiong/项目/家在深圳/Build/Products/Debug-iphoneos/SZHomeForum.app");
        capabilities.setCapability("autoAcceptAlerts", false);
        capabilities.setCapability("udid", "b5109a245a39d8eef252ee75887badfe26c4a9f2");
        //b5109a245a39d8eef252ee75887badfe26c4a9f2
        //33370874330955d31bfd2fdf3077e51b80d16401
        capabilities.setCapability("logLevel", "DEBUG");
        capabilities.setCapability("automationName", "XCUITest");  // 新的IOS10
        driver=new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        System.out.println("启动知乎app成功");

        //driveriver 传入appiumauto类
        appiumAuto.driver = driver;
        FileOperation.deleteFile(filetext);
        //获取设备高度和宽度
        appiumAuto.Heiwidth();
        //图片存放处
        cyrPatn3 = cyrPatn2+"/"+ScreenScr.ScreenTime();
        //日志存放处
        filetext = cyrPatn3+"/logiOS.txt";
        logpat = cyrPatn3+"/logpath";
        //创建目录
        FileOperation.createDir(cyrPatn3);
        //创建日志文件目录
        FileOperation.createDir(logpat);
    }

    @AfterClass
    public void tearDown() throws Exception {
        System.out.println("----用例测试完毕-APP退出----");
        Thread.sleep(2000);
    }
    /***
     * 启动头条模块自动化遍历入口
     * @throws Exception
     */
    @Test
    public void test_01() throws Exception{
        cyrPatn = cyrPatn3+"/"+"首页";
        driver.findElement(By.name("首页")).click();
        Thread.sleep(2000);
        DistriTest.Traverse(driver);
    }
    /***
     * 启动消息模块自动化遍历入口
     * @throws Exception
     */
    @Test
    public void test_02() throws Exception{
        cyrPatn = cyrPatn3+"/"+"社区";
       driver.findElement(By.name("社区")).click();
        Thread.sleep(2000);
        DistriTest.Traverse(driver);
    }
    /***
     * 启动找房模块自动化遍历入口
     * @throws Exception
     */
    @Test
    public void test_03() throws Exception{
        cyrPatn = cyrPatn3+"/"+"消息";
        driver.findElement(By.name("消息")).click();
        Thread.sleep(2000);
        DistriTest.Traverse(driver);
    }
    /***
     * 启动咚圈模块自动化遍历入口
     * @throws Exception
     */
    @Test
    public void test_04() throws Exception{
        cyrPatn = cyrPatn3+"/"+"我家";
        driver.findElement(By.name("我家"))   .click();
        Thread.sleep(2000);
        DistriTest.Traverse(driver);
    }
}
