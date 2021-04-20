package opencartPages;



import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import opencartDataProvider.OpencartConfigReader;



public class editaccount {
	
	 WebDriver driver;
     Properties prop;
	 OpencartConfigReader configfile;
	
    
    public editaccount(WebDriver driver) {
        this.driver = driver;
    }
    
    public void Enterlogin(String Email, String Password) throws InterruptedException {
        
    	configfile = new OpencartConfigReader();
        prop = configfile.init_prop();
        
        driver.findElement(By.xpath(prop.getProperty("E-mailAddress_xpath"))).sendKeys(Email);
        driver.findElement(By.xpath(prop.getProperty("Password_xpath"))).sendKeys(Password);
       	
    }
    
    public void Clicklogin() {
    	
    	configfile = new OpencartConfigReader();
        prop = configfile.init_prop();
        driver.findElement(By.xpath(prop.getProperty("loginButton_xpath"))).click();
    	
    }

}

