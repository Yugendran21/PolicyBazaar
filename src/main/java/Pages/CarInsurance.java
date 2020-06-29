package Pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CarInsurance {
	public WebDriver driver;  	
	
	public WebDriverWait wait;
	public CarInsurance(WebDriver driver){
		this.driver= driver;
		this.wait  = new WebDriverWait(driver,10);
	}
	
	public void clickOnProceed() {	// clicks on proceed button 	
		WebElement proceed =  driver.findElement(By.xpath("//a[@class='do_not inverse']"));
	    wait.until(ExpectedConditions.elementToBeClickable(proceed));
	    proceed.click();
	}
    
    public void selectRTO(String city, String Rto) {// selects RTO and city from the given options
    	wait.until(ExpectedConditions.titleIs("PolicyBazaar Car Insurance: Insure Your Car Today"));       
    	String cityPath = String.format("//li//span[contains(text(),'%s')]", city);
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(cityPath)));
    	driver.findElement(By.xpath(cityPath)).click(); 
    	
    	String rtoPath = String.format("//li//span[contains(text(),'%s')]", Rto);
    	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(rtoPath)));
    	 driver.findElement(By.xpath(rtoPath)).click();
    }
        
    public void selectCarBrand(String carBrand) {    // selects car brand	
    	String brandPath = String.format("//span[@class='%s']", carBrand);
    	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(brandPath)));
    	 driver.findElement(By.xpath(brandPath)).click();
    }
     
   public void selectCarModel(String model) {//Selects car model 
	 model = model.toUpperCase();  
	 String modelPath= String.format("//b[contains(text(),'%s')]", model);
	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(modelPath)));
	 driver.findElement(By.xpath(modelPath)).click();
   }
    
   public void selectFuelType(String type) {//Selects car fuel type
	   String typePath = String.format("//span[@id='%s']", type);
	   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(typePath)));
	   driver.findElement(By.xpath(typePath)).click();	   
   }
   
   public void selectVariant(String variant) {//Selects car varient
	   String variantPath= String.format("//b[contains(text(),'%s')]", variant);
	   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(variantPath)));
	   driver.findElement(By.xpath(variantPath)).click();	  
   }
   
   public void selectYear(String year) {//Selects year of carf model
	   String yearPath= String.format("//b[contains(text(),'%s')]", year);
	   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(yearPath)));
	   driver.findElement(By.xpath(yearPath)).click();	
   }
   
   public void setName(String name) {//Sets name in the name text box
	   driver.findElement(By.id("name")).sendKeys(name);
   }
   
   public void setEmail(String email) {//Sets email in email text box
	   driver.findElement(By.id("email")).sendKeys(email);
   }
   
   public void setMobileNo(String number) {//Sets mobile number in mobile no. text box
	   driver.findElement(By.id("mobileNo")).sendKeys(number);
   }
  
   public void clickViewPrices() throws InterruptedException {//Clicks on View Price button
	   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'View Prices')]")));
	   driver.findElement(By.xpath("//span[contains(text(),'View Prices')]")).click();
	   Thread.sleep(5000);
   }

   public String getErrorMsg() {//Gets the error message and returns it
	    String Msg ="";
	    List<WebElement> errorMsg = driver.findElements(By.xpath("//div[contains(@class,'msg-error show')]"));      
	    for(WebElement element: errorMsg) {
	      Msg= Msg + element.getText().trim();		  	  
	    }
	    return Msg;
    }    

    public void toHomePage() {// Navigate back to the home page
		this.driver.get("https://www.policybazaar.com/");
		wait.until(ExpectedConditions.titleIs("Insurance - Compare & Buy Insurance Plans – Health, Term, Life, Car"));
	}
}
