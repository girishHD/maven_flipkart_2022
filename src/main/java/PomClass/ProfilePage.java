package PomClass;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import UtilityClass.Util1;

public class ProfilePage extends Util1{
	
	WebDriver driver;
	
	@FindBy(xpath = "//div[@class='_1ruvv2']")
	private WebElement fullProfileName;
	
	@FindBy(xpath = "//div[text()='Manage Addresses']")
	private WebElement manageAddressText;
	
	@FindBy(xpath = "//div[@class='_1QhEVk']")
	private WebElement addAddressBtn;
	
	@FindBy(xpath = "//div[@class='_1lRtwc _1Jqgld']/input")
	private List<WebElement> addressDetails;
	
	@FindBy(xpath = "//textarea")
	private WebElement address;
	
	@FindBy(xpath = "//button[text()='Save']")
	private WebElement saveBtn;
	
	@FindBy(xpath = "//div[@class='_1CeZIA']")
	private List<WebElement> savedAddressCount;
	
	public ProfilePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		
		this.driver = driver;
	}
	
	public boolean getFullProfileName() throws InterruptedException
	{
		explicitWait(driver, fullProfileName);
		
		Actions act = new Actions(driver);
		
		act.moveByOffset(400, 0);
		
		Thread.sleep(4000);
		
		act.moveToElement(fullProfileName).click().perform();
		
		if((fullProfileName.getText()).contains("Girish"))
		{
			return true;
		}
		return false;
	}
	
	public void clickOnManageAddressText()
	{
		explicitWait(driver, manageAddressText);
		manageAddressText.click();
	}
	
	public void clickOnAddAddressBtn()
	{
		explicitWait(driver, addAddressBtn);
		addAddressBtn.click();
	}
	
	public void fillAddressDetails()
	{
		String[] k = {"Girish", "8180900965", "441904", "Pande Mahal"};
		
		for(int i=0; i<addressDetails.size(); ++i)
		{
			if(i<4)
			{
				addressDetails.get(i).sendKeys(k[i]);
			}
		}
	}
	
	public void fillMainAddress()
	{
		address.sendKeys("Veer Sawarkar Ward, Bhandara");
	}
	
	public void clickOnSaveBtn()
	{
		saveBtn.click();
	}
	
	public int savedAddressCount()
	{
		return savedAddressCount.size();
	}
}
