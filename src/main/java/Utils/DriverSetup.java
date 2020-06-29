package Utils;
import java.io.*;
import java.net.URL;
import java.util.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverSetup {
		
	public static WebDriver driver;
	public static String baseUrl,browserName,grid;	
	
	public static void fromProp(){// Configure the property file
		try {
			FileInputStream config = new FileInputStream("config.properties");
			Properties prop = new Properties();
			prop.load(config);			
			config.close();
			baseUrl = prop.getProperty("url");
			browserName = prop.getProperty("browserName");
			grid = prop.getProperty("grid");			
		}catch(Exception e) {
			
		}
	}
	
	public static WebDriver driverSetup(String node) throws Exception{
		                                  // Invoke the browser
		fromProp();	
		if(grid.equals("Off")) {		
		
			if(browserName.equalsIgnoreCase("chrome")) { 
				WebDriverManager.chromedriver().setup();
				WebDriver driver = new ChromeDriver();		
			}
			
			if(browserName.equalsIgnoreCase("firefox")) {				
			 	WebDriverManager.firefoxdriver().setup();
				driver= new FirefoxDriver();
				
			}		
		}else {
			FirefoxOptions options = new FirefoxOptions();			
	        driver = new RemoteWebDriver(new URL(node), options);
		}
		driver.manage().window().maximize();
		driver.get(baseUrl);		
		return driver;
	}	
}
