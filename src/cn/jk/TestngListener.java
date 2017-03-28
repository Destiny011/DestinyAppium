package cn.jk;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;
import org.testng.log4testng.Logger;


public class TestngListener extends TestListenerAdapter {
	private static Logger logger = Logger.getLogger(TestngListener.class);
	public static final String CONFIG = "config.properties";

	@Override
	public void onTestFailure(ITestResult tr) {
		super.onTestFailure(tr);
		logger.info(tr.getName() + " Failure");
		//		takeScreenShot(tr);
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		super.onTestSkipped(tr);
		logger.info(tr.getName() + " Skipped");
		//       takeScreenShot(tr);
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		super.onTestSuccess(tr);
		logger.info(tr.getName() + " Success");
	}

	@Override
	public void onTestStart(ITestResult tr) {
		super.onTestStart(tr);
		logger.info(tr.getName() + " Start");
	}

	@Override
	public void onFinish(ITestContext testContext) {
		super.onFinish(testContext);

	}


	/**
	 * 自动截图，保存图片到本地以及html结果文件中
	 * 
	 * @param tr
	 */
//	private void takeScreenShot(ITestResult tr) {
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
//		String mDateTime = formatter.format(new Date());
//		String fileName = mDateTime + "_" + tr.getName();
//		String filePath = DongDongkeyword.driver.getScreenshotAs(fileName);
//		Reporter.setCurrentTestResult(tr);
//		Reporter.log(filePath);
//               //这里实现把图片链接直接输出到结果文件中，通过邮件发送结果则可以直接显示图片
//		Reporter.log("<img src=\"../" + filePath + "\"/>");
//
//	}
}