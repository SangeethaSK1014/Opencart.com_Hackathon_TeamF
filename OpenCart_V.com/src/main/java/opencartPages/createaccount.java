package opencartPages;


import org.openqa.selenium.WebDriver;

import opencartDataProvider.OpencartConfigReader;

import java.util.Properties;

import org.openqa.selenium.By;
   
public class createaccount {
	
	  WebDriver driver;
	  Properties prop;
	  OpencartConfigReader configfile;
	 
     
     public createaccount(WebDriver driver) {
         this.driver = driver;
     }
     
     public void fillCreateaccountform(String Firstname, String Lastname, String Email, String Telephone, String Password, String PasswordConfirm)
     {
    	 
    	 configfile =new OpencartConfigReader();
    	 prop = configfile.init_prop();
    	 
    	 driver.findElement(By.xpath(prop.getProperty("Firstname_xpath"))).sendKeys(Firstname);
    	 driver.findElement(By.xpath(prop.getProperty("Lastname_xpath"))).sendKeys(Lastname);
    	 driver.findElement(By.xpath(prop.getProperty("Email_xpath"))).sendKeys(Email);
    	 driver.findElement(By.xpath(prop.getProperty("Telephonenumber_xpath"))).sendKeys(Telephone);
    	 driver.findElement(By.xpath(prop.getProperty("Createpassword_xpath"))).sendKeys(Password);
    	 driver.findElement(By.xpath(prop.getProperty("Confirmpassword_xpath"))).sendKeys(PasswordConfirm);
     }
     
     public void ClickSubmit() {
    	 configfile =new OpencartConfigReader();
    	 prop = configfile.init_prop();
    	 driver.findElement(By.xpath(prop.getProperty("Checkbox_xpath"))).click();
    	 driver.findElement(By.xpath(prop.getProperty("Continue_xpath"))).click();
     }
}
