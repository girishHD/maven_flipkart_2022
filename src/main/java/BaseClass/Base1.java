package BaseClass;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import UtilityClass.Util1;

public class Base1 {
	
	static WebDriver driver = null;
	
	
	static ExtentHtmlReporter htmlReporter = null;
	
	static ExtentReports report = null;
	
	static ExtentTest test = null;
	
	public static WebDriver getDriver(String browser) throws IOException
	{
		if(driver==null)
		{
			if(browser.equals("chrome"))
			{
				System.setProperty("webdriver.chrome.driver", "src//main//resources//Browser//chromedriver.exe");
				
				driver = new ChromeDriver();
			}
			
			driver.get(Util1.getProperty("url"));
			
			driver.manage().window().maximize();
			
			return driver;
		}
		else
		{
			return driver;
		}			
	}
	
	public static ExtentHtmlReporter getHtmlReporter()
	{
		if(htmlReporter == null)
		{
			htmlReporter = new ExtentHtmlReporter("ExtentReporter.html");
		}
			return htmlReporter;
		
	}
	
	public static ExtentReports getReports()
	{
		if(report == null)
		{
			report = new ExtentReports();
			report.attachReporter(htmlReporter);
		}
			return report;
	}
	
	public static ExtentTest getTest(String testName)
	{
		test = report.createTest(testName);
		return test;
	}
}
