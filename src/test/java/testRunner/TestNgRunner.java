package testRunner;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import utilities.Reporting;


@CucumberOptions(
		features = "src/test/resources/features/",
		tags = "@login or @multiplelogin or @sanity or @smoke or @regression",
		glue = {"stepDefinitions","hooks"}, 
		dryRun = false, 
		monochrome = true,
		plugin = { "pretty",			
		   "html:target/cucumber-reports.html", 
		   "json:target/cucumber-reports.json",
		   "junit:target/cucumber-reports.xml",
		   "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",	
		   "com.aventstack.chaintest.plugins.ChainTestCucumberListener:",
		   "timeline:test-output-thread/"
           }
		)

public class TestNgRunner extends AbstractTestNGCucumberTests {
// parallel execution
//@DataProvider(parallel = true) enables parallel execution of scenarios in Cucumber with TestNG.
		@Override
	    @DataProvider(parallel = true)
	    public Object[][] scenarios() {
	        return super.scenarios();
    }
}

//mvn clean test
//you can specify the thread count from the command line
//mvn clean test -DthreadCount=4
//This will pick up the maven-surefire-plugin configuration from your pom.xml and execute your Cucumber scenarios in parallel.


