package TestClasses;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import BaseClass.Base1;
import PomClass.HomePage;
import PomClass.ProfilePage;
import UtilityClass.Util1;

public class verifyUserCanAddNewAddress {
	
	static WebDriver driver;
	HomePage hp;
	ProfilePage pp;
	
	ExtentHtmlReporter htmlReporter;
	ExtentReports report;
	ExtentTest test;
	
	@BeforeClass
	public void getDriver() throws IOException
	{
		htmlReporter = Base1.getHtmlReporter();
		report = Base1.getReports();
		test = Base1.getTest("verifyUserCanAddNewAddress");
		
		driver = Base1.getDriver("chrome");
	}

	@BeforeMethod
	public void beforeMethod()
	{
		hp = new HomePage(driver);
		pp = new ProfilePage(driver);
	}
	
	@Test
	public void verifyUserCanGoToProfilePage() throws InterruptedException
	{
		hp.moveToProfileName();
		
		hp.clickOnMyProfileText();
		
		Assert.assertTrue(pp.getFullProfileName());
	}
	
	@Test(priority=1)
	public void verifyUserCanAddAddress() throws InterruptedException
	{
		pp.clickOnManageAddressText();
		
		Thread.sleep(5000);
		
		int previousAddressCount = pp.savedAddressCount();
		
		System.out.println(previousAddressCount);
		
		pp.clickOnAddAddressBtn();
		
		pp.fillAddressDetails();
		
		pp.fillMainAddress();
		
		Thread.sleep(2000);
		
		pp.clickOnSaveBtn();
		
		Thread.sleep(10000);
		
		int currentAddressCount = pp.savedAddressCount();
		
		Assert.assertEquals(currentAddressCount, (previousAddressCount+1));
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
