package pageObjects;
import java.time.Duration;
import hooks.Hooks;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	
	 WebDriver driver;	
	//WaitHelper waithelper;
	 
	//using constructor to initialze webelements in pagefactory	
		public LoginPage(WebDriver rdriver)
		{   
			this.driver = rdriver;
			PageFactory.initElements(driver, this);
			//waithelper=new WaitHelper(rdriver);
		}

	
//  What is CacheLookup in Selenium?
//	@CacheLookup, as the name suggests helps us control when to cache a WebElement and when not to. 
//	This annotation, when applied over a WebElement, instructs Selenium to keep a cache of the WebElement 
//	instead of searching for the WebElement every time from the WebPage. This helps us save a lot of time.
	
	//@FindBy(how=How.ID,using="Email")
	@FindBy(how=How.XPATH,using="//input[@id='input-email']")
	@CacheLookup
	WebElement username;
	
	//@FindBy(how=How.ID,using="Password")
	@FindBy(how=How.XPATH,using="//input[@id='input-password']")
	@CacheLookup
	WebElement password;
	
	//@FindBy(how=How.XPATH,using="//button[normalize-space()='Log in']")
	//@FindBy(how=How.XPATH,using="//button[@type='submit']")
	@FindBy(how=How.XPATH,using="//input[@value='Login']")
	@CacheLookup   
	WebElement loginButton;
	
// if u r collecting multiple row elements from a table //tbody/tr
// List<WebElement> tableRows;
// if u r collecting multiple column elements from a table //tbody/tr/td
// List<WebElement> tableColumns;
	
	public void username(String uname)
	{
		username.clear();
		//waithelper.WaitForElement(username,30);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-email")));
		emailField.sendKeys(uname);
	}
	
	public void password(String pwd)
		{
		//waithelper.WaitForElement(PASSWORD,30);
		password.clear();
		password.sendKeys(pwd);
		}
	
	public void login()
		{
	//waithelper.WaitForElement(PASSWORD,30);
		loginButton.click();		
	  }
	
	public void loginDDT(String userName, String passWord) throws InterruptedException {
			
		username.clear();
		username.sendKeys(userName);
		password.clear();
		password.sendKeys(passWord);
		loginButton.click();
	}
}
