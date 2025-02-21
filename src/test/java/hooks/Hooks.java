package hooks;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.After;
import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ConfigReader;
import utilities.Loggerload;


//@Listeners(chainTestListener.class)
public class Hooks {
	// public static WebDriver driver;
	private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	ConfigReader readConfig = new ConfigReader();
	public String CHROME_DRIVER = "webdriver.chrome.driver";
	public String CHROME_DRIVER_LOCATION = readConfig.getChromePath();
	public String FIREFOX_DRIVER_LOCATION = readConfig.getGeckoPath();
	public String EDGE_DRIVER_LOCATION = readConfig.getEdgePath();
	public String IE_DRIVER_LOCATION = readConfig.internetExplorerPath();
	public String APP_URL = readConfig.getApplicationUrl();
	public String USERNAME = readConfig.getUsername();
	public String PASSWORD = readConfig.getPassword();
	public String BROWSER = readConfig.getBrowserType();
	public static Logger logger;

//		System.getProperty("user.dir")= java class only   ==  ./
//		./   java class and properties file 
	@SuppressWarnings("deprecation")
	@Before
	public void setup(Scenario scenario) throws MalformedURLException // so passing that parameter browser as br
	{
		// Initialize the logger
		logger = Logger.getLogger("nopCommerce"); // create object for Logger class
		PropertyConfigurator.configure("src/test/resources/log4j.properties");
		// ChainTest Configuration
		//ChainPluginService.getInstance().addSystemInfo("Build#", "1.0");
		//ChainPluginService.getInstance().addSystemInfo("Owner Name#", "Reka");
		System.out.println("Running scenario: " + scenario.getName());
		if (BROWSER.equalsIgnoreCase("chrome")) {
			ChromeOptions chromeOptions = new ChromeOptions();
			// Check if running inside Docker (or any Linux-based environment)
			if (System.getProperty("os.name").toLowerCase().contains("linux")) {
				System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
				chromeOptions.addArguments("--headless", "--disable-gpu", "--no-sandbox", "--disable-dev-shm-usage",
						"--remote-allow-origins=*");
			} else {
				// Only set WebDriverManager if you want it to automatically manage ChromeDriver
				// WebDriverManager.chromedriver().driverVersion("latest").setup(); // Ensure
				// it's the latest version
				// System.setProperty("webdriver.manager.autodownload", "false");
				// Windows path, use this when running locally in Windows
				WebDriverManager.chromedriver().setup();
				//WebDriverManager.chromedriver().driverVersion("latest").setup();
				System.setProperty("webdriver.chrome.driver",
						"./src/test/resources/ChromeDriver/chromedriver.exe");
				// System.setProperty("chaintest.service.url", "http://localhost:8081");
				// System.setProperty("pdfreport.config.file", "src/test/resources/pdf-report-config.yaml");

				chromeOptions.addArguments("--disable-logging");
				// chromeOptions.addArguments("--remote-debugging-port=9223"); // Use a custom
				// port
				chromeOptions.addArguments("--disable-dev-shm-usage");
				chromeOptions.addArguments("--no-sandbox");
				// chromeOptions.addArguments("--log-level=3");
				chromeOptions.addArguments("--remote-allow-origins=*");
				chromeOptions.addArguments("--disable-extensions");
				chromeOptions.addArguments("--disable-gpu");
				chromeOptions.addArguments("--headless"); // Optionally run in headless mode
				// WebDriverManager.chromedriver().capabilities(chromeOptions).create();
				// To check logs for RemoteWebDriver http://localhost:5555/wd/hub :
				LoggingPreferences logs = new LoggingPreferences();
				logs.enable(LogType.BROWSER, Level.ALL);
				chromeOptions.setCapability(CapabilityType.LOGGING_PREFS, logs);

			}
			
//			driver.set(new ChromeDriver(chromeOptions));
			
/*			
			//To check logs for RemoteWebDriver http://localhost:5555/
	        URL seleniumHubUrl = new URL("http://localhost:5555/wd/hub");
			//WebDriver driver = new RemoteWebDriver(seleniumHubUrl, chromeOptions);
			driver.set(new RemoteWebDriver(seleniumHubUrl,chromeOptions));
*/
/*			
			// Read the selenium.grid.url property from the command line (set by Maven)
	        String gridUrl = System.getProperty("selenium.grid.url", "http://localhost:5555/wd/hub");
	        // Initialize RemoteWebDriver with the grid URL and Chrome options
	        driver.set(new RemoteWebDriver(new URL(gridUrl), chromeOptions));
*/	        
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("browserName", "Chrome");
			capabilities.setCapability("browserVersion", "latest");
			capabilities.setCapability("platformName", "Windows 10");
			capabilities.setCapability("LT:Options", new HashMap<String, Object>() {{
			    put("user", "rekaharisri");
			    put("accessKey", "0UV2Eyfkmupm6epnxh6RK6UDtMOebAibFwtZO1WxuPqeySA0zW");
			    put("build", "Bdd-Framework");
			    put("name", "Login");
			}});
			//WebDriver driver = new RemoteWebDriver(new URL("https://hub.lambdatest.com/wd/hub"), capabilities);
			driver.set(new RemoteWebDriver(new URL("https://hub.lambdatest.com/wd/hub"), capabilities));
			
			getDriver().manage().deleteAllCookies();
			
		} else if (BROWSER.equalsIgnoreCase("firefox")) {
			Loggerload.info("Testing on firefox");
			FirefoxOptions Options = new FirefoxOptions();
			// System.setProperty("webdriver.firefox.driver",
			// "C:\\Users\\nreka\\vscodedevops\\TestNG-Framework\\src\\test\\resources\\FirefoxDriver\\geckodriver.exe");
			FirefoxBinary firefoxBinary = new FirefoxBinary(
					new File("C:\\Program Files\\Mozilla Firefox\\firefox.exe"));
			Options.setBinary(firefoxBinary);
			// Use WebDriverManager to handle the driver setup
			WebDriverManager.firefoxdriver().setup();
			// Options.addArguments("--disable-logging");
			// Options.addArguments("--disable-dev-shm-usage");
			// Options.addArguments("--no-sandbox");
			// Options.addArguments("--disable-extensions");
			// Options.addArguments("--disable-gpu");
			driver.set(new FirefoxDriver(Options));
			getDriver().manage().deleteAllCookies();

		} else if (BROWSER.equalsIgnoreCase("edge")) {
			Loggerload.info("Testing on Edge");
			EdgeOptions Options = new EdgeOptions();
			System.setProperty("webdriver.edge.driver",
					"C:\\Users\\nreka\\vscodedevops\\TestNG-Framework\\src\\test\\resources\\EdgeDriver\\msedgedriver.exe");
			Options.addArguments("--remote-debugging-port=9223");
			Options.addArguments("--disable-logging");
			Options.addArguments("--disable-dev-shm-usage");
			Options.addArguments("--no-sandbox");
			Options.addArguments("--remote-allow-origins=*");
			Options.addArguments("--disable-extensions");
			Options.addArguments("--disable-gpu");
			driver.set(new EdgeDriver(Options));
			getDriver().manage().deleteAllCookies();
		}
		System.out.println("Starting ChromeDriver in Thread: " + Thread.currentThread().getId());
		getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS); // Increase timeout
		getDriver().manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS); // Longer page load timeout
		getDriver().get(APP_URL);
	}

	@AfterStep
	public void attachScreenshot(Scenario scenario) {
		if (scenario.isFailed()) {
			byte[] screenshot = ((TakesScreenshot) Hooks.getDriver()).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", "Failed Step Screenshot");
		}
	}

	public static WebDriver getDriver() {
		return driver.get();
	}

	@After
	public void tearDown() {
		
		if (getDriver() != null) {
			try {
				getDriver().quit(); // Quit the driver
			} catch (Exception e) {
				System.out.println("Error quitting the driver session: " + e.getMessage());
			}
			driver.remove(); // Clean up the thread-local variable
		}
	}

	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
		File destinationFile = new File("Screenshots/Screenshots" + tname + ".png");
		// FileUtils.copyFile(sourceFile3, destinationFile3);
		FileUtils.copyFile(sourceFile, destinationFile);
		System.out.println("Screenshot Taken");
	}

	public String random_String() {
		String generatedString = RandomStringUtils.randomAlphabetic(5); // 5 character string will be generated
		return generatedString;
		// in test case
		// String email=random_String()+"@gmail.com";
		// addCust.custemailid(email);
		// }
	}

	public static String random_Number() {
		String generatedString2 = RandomStringUtils.randomNumeric(4);// 4 digits will be generated
		return generatedString2;
		// in test case
	}

	/*
	 * // to validate if particular page has any message like succcesfully
	 * registered boolean
	 * res=driver.getPageSource().contains("Customer Registered Successfully!!!");
	 * if(res==true) { Assert.assertTrue(true); } else { captureScreen(driver,
	 * "addNewCustomer"); // testcasename Assert.assertTrue(false); }
	 */

}
