package opencartRunner;

import java.io.File;
import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.testng.annotations.BeforeTest;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import opencartDataFactory.OpencartDataFactory;
import opencartDataProvider.OpencartConfigReader;
import opencartPages.createaccount;
import opencartPages.editaccount;
import opencartUtils.OpencartExcelReader;

public class OpencartStepsTest {
	
WebDriver driver;
    
    OpencartConfigReader configfile;
    Properties prop;
    OpencartDataFactory DriverFactory;
    editaccount account;
    
    @Before(order=0)
    public void getProperty() {
    	
    	//Call driver from OpencartDatafactory
        configfile = new OpencartConfigReader();
        prop = configfile.init_prop();
    }

    @Before(order=1)
    public void launchBrowser() {
    	
    	//Launch driver from OpencartDatafactory
        String browserName = prop.getProperty("browser");
        DriverFactory = new OpencartDataFactory();
        driver = DriverFactory.init_driver(browserName);
    	
        
    }

    @Given("^User should open link$")
    public void user_should_open_link() throws Throwable {
    	
    	
    	//Open link from excelsheet through  OpencartExcelReader
    	File src=new File("C:\\Users\\admin\\Desktop\\Testdata.xlsx");
    	FileInputStream input =new FileInputStream(src);
    	XSSFWorkbook wb=new XSSFWorkbook(input);
    	XSSFSheet sheet=wb.getSheet("Data");
        String urllink=sheet.getRow(1).getCell(0).getStringCellValue();
        driver.get(urllink);   
    }

    
    @Then("^User Register account should fail \"([^\"]*)\" and \"([^\"]*)\"$")
    public void user_Register_account_should_fail_and(String SheetName, int RowNumber) throws Throwable {
	    driver.findElement(By.xpath(prop.getProperty("Myaccount_xpath"))).click();
	    driver.findElement(By.xpath(prop.getProperty("Register_xpath"))).click();
	    OpencartExcelReader reader=new OpencartExcelReader();
	    createaccount create=new createaccount(driver);
        List<Map<String,String>> testData =reader.getData("C:\\Users\\admin\\Desktop\\Testdata.xlsx", SheetName);
        String firstname =testData.get(RowNumber).get("Firstname");
        String lastname =testData.get(RowNumber).get("Lastname");
        String emailaddress =testData.get(RowNumber).get("Email");
        String telephonenumber =testData.get(RowNumber).get("Telephone");
        String createpassword =testData.get(RowNumber).get("Password");
        String confirmpassword =testData.get(RowNumber).get("PasswordConfirm");
       
       create.fillCreateaccountform(firstname, lastname, emailaddress, telephonenumber, createpassword, confirmpassword);
	    
       
	    
	}
    
    @And("^Click on submit$")
    public void click_on_submit() throws Throwable {
    	createaccount click=new createaccount(driver);
    	click.ClickSubmit();
    	
        Thread.sleep(2000);
    	String error = driver.findElement(By.className("text-danger")).getText();
        System.out.println(error);
    }
    
    @Given("^User should login to application$")
    public void user_should_login_to_application() throws Throwable {
    	
    	File src=new File("C:\\Users\\admin\\Desktop\\Testdata.xlsx");
    	FileInputStream input =new FileInputStream(src);
    	XSSFWorkbook wb=new XSSFWorkbook(input);
    	XSSFSheet sheet=wb.getSheet("Login");
        String urllink=sheet.getRow(1).getCell(0).getStringCellValue();
        driver.get(urllink);
        
    }

    @Then("^User enter credentials \"([^\"]*)\" and \"([^\"]*)\"$")
    public void user_enter_credentials_and(String SheetName, int RowNumber) throws Throwable {
		driver.findElement(By.xpath(prop.getProperty("MyAccount_xpath"))).click();
		driver.findElement(By.xpath(prop.getProperty("Login_xpath"))).click();
		OpencartExcelReader reader=new OpencartExcelReader();
	    account = new editaccount(driver);
        List<Map<String,String>> test =reader.getData("C:\\Users\\admin\\Desktop\\Testdata.xlsx", SheetName);
        String Emailaddress =test.get(RowNumber).get("email");
        String Createpassword =test.get(RowNumber).get("password");
        
        account.Enterlogin(Emailaddress, Createpassword);
    }

    @Then("^Click on login$")
    public void click_on_login() throws Throwable {
    	editaccount login=new editaccount(driver);
    	login.Clicklogin();
        	 
    }


	@Then("^User able edit the Existing login account$")
	public void user_able_edit_the_Existing_login_account() throws Throwable {
		WebElement ele=  driver.findElement(By.xpath(prop.getProperty("Edit_xpath")));
	    Actions act =new Actions( driver);
	    act.moveToElement(ele).perform();
	    driver.findElement(By.xpath(prop.getProperty("Edit_xpath"))).click();
	    WebElement Quantity = driver.findElement(By.xpath(prop.getProperty("lastName_xpath")));
        Quantity.clear();
        driver.findElement(By.xpath(prop.getProperty("lastName_xpath"))).sendKeys("A");
	    driver.findElement(By.xpath(prop.getProperty("continue_xpath"))).click();
	    driver.findElement(By.xpath(prop.getProperty("myaccount_xpath"))).click();
	    driver.findElement(By.xpath(prop.getProperty("Logout_xpath"))).click();
	    driver.findElement(By.xpath(prop.getProperty("conTinue_xpath"))).click();
	
	}

	@Then("^User apply empty coupon code$")
	public void user_apply_empty_coupon_code() throws Throwable {
		driver.findElement(By.xpath(prop.getProperty("Desktop_xpath"))).click();
		driver.findElement(By.xpath(prop.getProperty("Mac_xpath"))).click();
		driver.findElement(By.xpath(prop.getProperty("Addtocart_xpath"))).click();
		WebElement ele=  driver.findElement(By.xpath(prop.getProperty("Shoppingcart_xpath")));
	    Actions act =new Actions( driver);
	    act.moveToElement(ele).perform();
	    driver.findElement(By.xpath(prop.getProperty("Shoppingcart_xpath"))).click();
		driver.findElement(By.xpath(prop.getProperty("Couponcode_xpath"))).click();
		driver.findElement(By.xpath(prop.getProperty("Apply_xpath"))).click();
		
		Thread.sleep(2000);
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE); 
	    FileUtils.copyFile(screenshot, new File(".//screnshot/screen.png"));
	}

}
