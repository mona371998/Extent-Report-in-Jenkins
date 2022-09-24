package com.baseclass;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class BaseClass {
	public ExtentReports extent;
	public static WebDriver driver;
	public ExtentTest test;

@BeforeSuite//First step
public void setup()throws IOException{
	extent = new ExtentReports();
	ExtentSparkReporter spark = new ExtentSparkReporter("QATestReport.html");
	spark.loadXMLConfig("extentconfig.xml");
	//spark.loadXMLConfig(new File("extentconfig.xml"));
	extent.attachReporter(spark);
	
}
@AfterSuite//Last step
public void tearDown() throws IOException {
	extent.flush();
	Desktop.getDesktop().browse(new File("QAreport.html").toURI());
}
@AfterMethod
public void tearDown(ITestResult result) throws IOException{
	
	if(result.getStatus()==ITestResult.FAILURE){
		test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+"- Test Case Fail", ExtentColor.RED));
		test.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable()+"- Test Case Fail", ExtentColor.RED));	
		test.pass("Value FailScreenShot," , MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64()).build());
		test.pass("test finished");
	}
	else if(result.getStatus()==ITestResult.SKIP){
		test.log(Status.SKIP, "Test Case SKIPPED IS " + result.getName());
		System.out.println("helo1o");	
	}
	else if(result.getStatus()==ITestResult.SUCCESS){
	test.log(Status.PASS, "Test Case PASSED IS " + result.getName());
			}
	driver.close();
}	


//Take a screenshot as base64 and attach to report
public String getBase64() {
	return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
}
}