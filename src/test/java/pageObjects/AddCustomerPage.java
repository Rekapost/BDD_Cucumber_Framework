package pageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddCustomerPage {

	WebDriver ldriver;
	public AddCustomerPage(WebDriver rdriver) {
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	
	@FindBy (xpath="//a[@href='#']//p[contains(text(),'Customers')]") WebElement lnkcustomers_main_xpath;
	@FindBy (xpath="//a[@href='/Admin/Customer/List']//p[contains(text(),'Customers')]") WebElement lnkcustomers_menuitem_xpath;
	@FindBy (xpath="//a[normalize-space()='Add new']") WebElement btnaddnew_customer_xpath;
	@FindBy (xpath="//input[@id='Email']") WebElement txtcustomer_email_xpath;
	@FindBy (xpath="//input[@id='Password']") WebElement txtcustomer_password_xpath;
	@FindBy (xpath="//input[@id='FirstName']") WebElement txtcustomer_firstname_xpath;
	@FindBy (xpath="//input[@id='LastName']") WebElement txtcustomer_lastname_xpath;
	@FindBy (xpath="//input[@id='Gender_Male']") WebElement rdmalecustomer_gender_xpath;
	@FindBy (xpath="//input[@id='Gender_Female']") WebElement rdfemalecustomer_gender_xpath;
	@FindBy (xpath="//input[@id='DateOfBirth']") WebElement txtcustomer_dateofbirth_xpath;
	@FindBy (xpath="//input[@id='Company']") WebElement txtcustomer_companyname;
	@FindBy (xpath="//input[@id='IsTaxExempt']") WebElement btncustomer_taxexmpt_xpath;
	@FindBy (xpath="//div[@class='k-widget k-multiselect k-multiselect-clearable k-state-hover']//input[@role='listbox']") WebElement btncustomer_newsletter_xpath;
	
	@FindBy (xpath="//*[@id='customer-info']/div[2]/div[10]/div[2]/div/div[1]/div/div") WebElement txtcustomer_role_xpath;
	@FindBy (xpath="//span[normalize-space()='Administrators']") WebElement lstitemcustroleradmin_xpath;
	@FindBy (xpath="//span[normalize-space()='Guests']") WebElement lstitemcustroleguest_xpath;
	@FindBy (xpath="//span[normalize-space()='Registered']") WebElement lstitemcustroleregi_xpath;
	@FindBy (xpath="//span[normalize-space()='Vendors']") WebElement lstitemcustrolevend_xpath;
	
	@FindBy (xpath="//select[@id='VendorId']") WebElement drpcustomer_managerofvendor_xpath;
	@FindBy (xpath="//input[@id='Active']") WebElement btncustomer_active_xpath;
	@FindBy (xpath="//textarea[@id='AdminComment']") WebElement txtcustomer_admincontent_xpath;
	@FindBy (xpath="//button[@name='save']") WebElement btncustomer_save_xpath;


//		 for every element write action method
		public void  clickoncustomermenu() {
			        lnkcustomers_main_xpath.click();
		}
		public void clickcustomeritem() {
			       lnkcustomers_menuitem_xpath.click();
		}
		public void clickaddnew() {
			     btnaddnew_customer_xpath.click();
		}
		public void setemail(String email) {   // we will get email from actual test case
		       txtcustomer_email_xpath.sendKeys(email);
		}
		public void setpassword(String pwd) {
		       txtcustomer_password_xpath.sendKeys(pwd);
		}
		public void setfirstname(String fname) {
		        txtcustomer_firstname_xpath.sendKeys(fname);
		}
		public void setlastname(String lname) {
		       txtcustomer_lastname_xpath.sendKeys(lname);
		}
		public void setGender(String gender) {
		        if ("Male".equals(gender))
		        {
		           rdmalecustomer_gender_xpath.click();
		        }
		        else if ("Female".equals(gender))
		        {
		            rdfemalecustomer_gender_xpath.click();
		        } 
			}
		public void setdateofbirth(String dob) {
		        txtcustomer_dateofbirth_xpath.sendKeys(dob);
		}
		public void setcompanyname(String cmpname) {
		        txtcustomer_companyname.sendKeys(cmpname);
		}
		
//		 #  def taxexmpt(self):
//		 #      self.driver.find_element(By.XPATH,self.btncustomer_taxexmpt_xpath).click()
//		 #  def newsletter(self):
//		 #      self.driver.find_element(By.XPATH,self.btncustomer_newsletter_xpath).click()
		 public void setcustomerroles(String role) 
		 {
		 //      setcustomerroles.click();
		       
			        if ("Registered".equals(role)) {
			           lstitemcustroleregi_xpath.click();
			        }
		           else if ("Administrators".equals(role)) {
		            lstitemcustroleradmin_xpath.click();
		           }
		           else if( "Guests".equals(role))
		           {// here user can be registered user (has account) or guest(no account), only one
		            //time.sleep(3)  // already registered will be there , if selecting guest ,
		           // u have to remove registered by clicking again on registered
		            lstitemcustroleregi_xpath.click();
		            // then click guests and guests will be saved in the variable listitem
		            lstitemcustroleguest_xpath.click();
		 			}
		          else if ("Registered".equals(role)) {
		            lstitemcustroleregi_xpath.click();
		          }
		          else if ("Vendors".equals(role))
		          {
		            lstitemcustrolevend_xpath.click();
		       
		          }
		          else{  // if nothing is selected , by default guests is selected
		           lstitemcustroleguest_xpath.click();
		          
		        //time.sleep(3)
		        // select one of the item from textbox
		        // self.listitem.click() is not working so execute script method
//		           JavascriptExecutor jse= new (JavascriptExecutor)driver ;				};
//		           jse.executeScript("arguments[0].click();",listitem); // 2 argumenets java script
		          }
			        }
		          
//		        public void setmanagerofvendor(String value)
//		        {
//		        	drp=Select(drpcustomer_managerofvendor_xpath));
//		        	drp.select_by_visible_text(value)
//		        }
		        
		        public void admincontent(String content) 
		        {
		        	txtcustomer_admincontent_xpath.sendKeys(content);
		        }
		        
		        public void clickonsave() {
		        	btncustomer_save_xpath.click();
		        }
}
