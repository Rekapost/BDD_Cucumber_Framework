package utilities;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentTestReport {
	public WebDriver driver;
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;
	
	@BeforeTest
	public void setExtent() {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());  // Time Stamp
		String repName="Test-Report-" + timeStamp+".html";
		htmlReporter= new ExtentHtmlReporter(System.getProperty("user.dir")+ "/test-output/"+ repName); //specify location
		try {
			//htmlReporter.loadXMLConfig(System.getProperty("user.dir")+"/src/test/resources/extent-config.xml");
		
		      htmlReporter.loadXMLConfig(System.getProperty("user.dir")+"/src/test/resources/extent_config.xml");

		} catch (IOException e) {
			
			e.printStackTrace();
		}
		htmlReporter.config().setDocumentTitle("nopcommerce test project"); // title of report
		htmlReporter.config().setReportName(" Functional test report");  // name of report
		//htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP); // location of chart
		htmlReporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host name",  "Localhost");
		extent.setSystemInfo("Environment","QA");
		extent.setSystemInfo("OS","Windows10");
		extent.setSystemInfo("Tester Name","reka");
		extent.setSystemInfo("Browser","chrome");
	 }
	
	@AfterTest
	 public void endReport() {
		extent.flush();
	}
}
