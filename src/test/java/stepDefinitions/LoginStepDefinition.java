package stepDefinitions;
import java.io.IOException;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.LoginPage;
import utilities.ConfigReader;
import utilities.Loggerload;

public class LoginStepDefinition {
	//private final String browser = "chrome";
	WebDriver driver;
	static Logger testCaseLogger = Logger.getLogger(LoginStepDefinition.class);
	LoginPage lp;
	ConfigReader readConfig = new ConfigReader();
	public String APP_URL = readConfig.getApplicationUrl();
    public String USERNAME = readConfig.getUsername();
    public String PASSWORD = readConfig.getPassword();
    
    public LoginStepDefinition() {
    	this.driver = Hooks.getDriver();
    	lp = new LoginPage(driver);
    }
    
	@Given("I am in login page")
	public void i_am_in_login_page() {
		System.out.println("In Login Page");
	} 
	
	@Given("Open {string}")
	public void open(String string) {
		System.out.println("Open nop commerce site");
	    System.out.println("Open site: " + string);
	    driver.get(APP_URL);
		String testName = this.getClass().getSimpleName() + "_" + "loginTestCase";
		System.out.println(testName);
		driver.manage().window().maximize();
	}

	@Given("Enter {string} and {string}")
	public void enter_and(String username, String password) {
		System.out.println("Enter credentials:");	
		lp.username(USERNAME);
		testCaseLogger.info("entering username:");     //log4j  base class
		lp.password(PASSWORD);
		testCaseLogger.info("entering password:");     //log4j2   logeerload class		
	}

	@When("Click login")
	public void click_login() {
		lp.login();
	}

	@When("User is logged in to the website")
	public void user_is_logged_in_to_the_website() {
		System.out.println("opening dashboard page:");
		Loggerload.info("loging out of nop commerce ");  // log4j2   logeerload class
		testCaseLogger.info("loging out of nop commerce");     //log4j  base class
		System.out.println("loging out of nop commerce:");
	}

	@Then("I validate the credentails")
	public void i_validate_the_credentails() throws IOException {
		if (driver.getTitle().equals("My Account") ){
			Assert.assertTrue(true);
			System.out.println(" successful login");
			//Assert.assertTrue(driver.getTitle().equals("My Account"), "Failed to login, page title does not match!");
			utilities.chainTestListener.log("successful login");
		  }
		else 
		{
			//BaseClassOriginal.captureScreen(driver,"loginTestCase");
			Assert.assertTrue(false);
		}
	}
}
