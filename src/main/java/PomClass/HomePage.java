package PomClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import UtilityClass.Util1;

public class HomePage extends Util1{

	WebDriver driver;
	
	@FindBy(xpath = "//div[text()='Girish']")
	private WebElement profileName;
	
	@FindBy(xpath = "//div[text()='My Profile']")
	private WebElement myProfileText;
	
	public HomePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
	
	public boolean getProfileName()
	{
		explicitWait(driver, profileName);
		
		if((profileName.getText()).equals("Girish"))
		{
			return true;
		}
		
		return false;
		
		
	}
	
	public void moveToProfileName()
	{
		Actions act = new Actions(driver);
		
		act.moveToElement(profileName).clickAndHold().perform();;
	}	
	
	public void clickOnMyProfileText()
	{
		explicitWait(driver, myProfileText);
		
		myProfileText.click();
	}
}
