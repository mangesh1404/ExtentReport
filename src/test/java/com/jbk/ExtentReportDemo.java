package com.jbk;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReportDemo {

	ExtentReports extent;
	ExtentTest logger;
	WebDriver driver;
	
	@BeforeTest
	public void setup() {
		extent = new ExtentReports(System.getProperty("user.dir") +"/test-output/ExtentReport.html");
		System.setProperty("webdriver.chrome.driver","C://chromedriver.exe");
	//	System.setProperty("webdriver.gecko.driver","C://geckodriver.exe");
		//driver= new FirefoxDriver();
		driver = new ChromeDriver();
	    driver.get("file:///D:/Java%20study/Selenium%20Software/Offline%20Website/index.html");
	}
	@Test
	public void getTitle_Fail()throws Exception {
		logger= extent.startTest("getTitle_Fail");
		String title=driver.getTitle();
		SoftAssert sa= new SoftAssert();
		sa.assertEquals(title, "no Title");
		if(!title.equals("no Title")) {
		logger.log(LogStatus.FAIL, "title is not matching");
		String screenshotPath = TakeScreenShotEx.getScreenhot(driver, "getTitle_Fail");
		logger.log(LogStatus.FAIL, logger.addScreenCapture(screenshotPath));
		}
	}
	@AfterTest
	public void tearDown() {
		extent.endTest(logger);
		extent.flush();
		extent.close();
		driver.close();
	}
}
