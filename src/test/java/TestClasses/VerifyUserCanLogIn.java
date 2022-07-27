package TestClasses;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import BaseClass.Base1;
import PomClass.HomePage;
import PomClass.LogInPage;
import UtilityClass.Util1;

public class VerifyUserCanLogIn extends Util1{
	
	static WebDriver driver;
	static LogInPage lp;
	
	ExtentHtmlReporter htmlReporter;
	ExtentReports report;
	ExtentTest test;
	
	@BeforeClass
	public void getDriver() throws IOException
	{
		htmlReporter = Base1.getHtmlReporter();
		report = Base1.getReports();
		test = Base1.getTest("VerifyUserCanLogIn");
		
		driver = Base1.getDriver("chrome");
	}
	
	@BeforeMethod
	public void beforeMethod()
	{
		lp = new LogInPage(driver);
	}
	
	@DataProvider(name="testData")
	public String[][] getData() throws IOException
	{
		String[][] pName = {{Util1.getProperty("userName"), Util1.getProperty("password")}};
		return pName;
	}
	
	@Test(dataProvider="testData")
	public void verifyUserCanLogIn(String id, String pass) throws InterruptedException
	{
		lp.enterEmail(id);
		lp.enterPassword(pass);
		lp.clickLoginBtn();
		
		HomePage hp = new HomePage(driver);
		
		Assert.assertTrue(hp.getProfileName());
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException
	{
		if(result.getStatus() == ITestResult.SUCCESS)
		{
			test.log(Status.PASS, "Test is Passed" + result.getName());
		}
		else
		{
			String path = Util1.getSceenshot(driver, result.getName());
			test.log(Status.FAIL, "Test is Failed" + result.getName(), MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		}
		
	}
	
	@AfterClass
	public void afterClass()
	{
		report.flush();
	}
}
