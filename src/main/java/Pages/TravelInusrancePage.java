package Pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TravelInusrancePage {
	
	public WebDriver driver;  	
	public WebDriverWait wait;
	public Actions action;
	public JavascriptExecutor jse;
	public TravelInusrancePage(WebDriver driver){
		this.driver= driver;
		this.wait =  new WebDriverWait(driver,10);
		this.action = new Actions(driver);
		this.jse  = (JavascriptExecutor)driver;
	}

	
	public void selectStudentTab() { // selects the student tab
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[2]/section[1]/div[2]/section[1]/div[2]/article[1]/ul[1]/li[3]/a[1]")));
		driver.findElement(By.xpath("//ul[@class='travelFormTab']//a[contains(text(),'Student')]")).click();	
	}
	
	public void setDestination(String destination) {//sets destination in destination text box	
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("proceedStepOne")));
		 WebElement countryBox=driver.findElement(By.xpath("//input[@id='destination-autocomplete']"));
		  wait.until(ExpectedConditions.elementToBeClickable(countryBox));	    
		  countryBox.sendKeys(destination);	  
		  countryBox.sendKeys(Keys.RETURN);
		  try {
			  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='autocomplete wn-insurance-quote-editor left']")));
			  countryBox.sendKeys(Keys.RETURN);	  
		  }catch(Exception e) {
			  
		  }
	  	
	}
	
	public void setNumberOfStudents(int noOfStudents) {// clicks on '+' icon if number of students is greater than four
		if(noOfStudents>4) {
			int toAdd = noOfStudents-4;
			for(int i=0;i<toAdd;i++) {
				driver.findElement(By.xpath("//span[@class='addBtn']"));
			}			
		}	
	}
	
	public void setAgeOfTravellers(String age) { //sets age of travellers in age text box
		String Age[] = age.split(",");
		this.setNumberOfStudents(Age.length);
		for(int i=1;i<=Age.length;i++) {
			String id = String.format("memage%s", i);			
			driver.findElement(By.id(id)).sendKeys(Age[i-1]);
		}		
	}
	
	public void setTripStartDate(String date) {//sets trip start date from the calendar
		String script = String.format("arguments[0].value='%s';" , date);	
		WebElement Date =  driver.findElement(By.xpath("//input[@id='startdate']"));			
		jse.executeScript(script, Date);		
	}
	
	public void setTripEndDate(String date) {	// sets trip end date from the calendar	
		String script = String.format("arguments[0].value='%s';" , date);		
		WebElement Date =  driver.findElement(By.xpath("//input[@id='enddate']"));
		jse.executeScript(script, Date);
	}
	
	public void setMedicalCondition(String option) {//clicks on medical condition radio button 
		option = String.format("//div[@id='isPED']//label[@class='container'][contains(text(),'%s')]", option);		
		driver.findElement(By.xpath(option)).click();		
	}
	
	public void clickProceed() {//clicks on proceed button
		WebElement proceed = driver.findElement(By.id("proceedStepOne"));
		wait.until(ExpectedConditions.elementToBeClickable(proceed));		
		proceed.click();
	}
	
	public void setPrefix(String Prefix) {// sets the prefix in full name text box
		Prefix= Prefix+".";
		String genderPath = String.format("//select[@id='travelgender']//option[contains(text(),'%s')]", Prefix);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(genderPath))));
	    driver.findElement(By.xpath(genderPath)).click();
	}
	
	public void setName(String Name) {// sets the name in full name text box
		String script = String.format("arguments[0].value='%s';", Name);		
		WebElement travelName = driver.findElement(By.id("travelname"));
		jse.executeScript(script,travelName);
	}

	public void setCountryCode(String countryCode) {// sets the country code in country code text box
		countryCode = "+"+countryCode;
		String codePath =String.format("//select[@id='travelCountry']//option[contains(text(),'%s')]", countryCode );
	    wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(codePath))));
		driver.findElement(By.xpath(codePath)).click();
	}
	
	public void setMobileNumber(String mobileNumber) {//sets the mobile number in mobile number text box
		String script = String.format("arguments[0].value='%s';", mobileNumber);
	    WebElement  travelMobile = driver.findElement(By.id("travelmobile"));
	    jse.executeScript(script, travelMobile );		
	}

	public void setEmail(String email) {// sets the email in email text box
		String script = String.format("arguments[0].value= '%s' ;", email);
		WebElement travelEmail = driver.findElement(By.id("travelemail"));
		jse.executeScript(script,travelEmail);
	}
	
	public void clickGetFreeQuotes() {// clicks on Get Free Quotes button
		driver.findElement(By.xpath("//a[@class='proceedButton travelproceed']")).click();
	}
	
	public String getErrorMsg() {//Returns the error message 
		 String Msg ="";
		 List<WebElement> errorMsg = driver.findElements(By.xpath("//div[starts-with(@class,'err')]"));      
		 for(WebElement element: errorMsg) {
		    Msg= Msg + element.getText().trim();		  	  
		 }		 
		 return Msg;
	}
	
	public void toHomePage(WebDriver driver) {// Navigate back to the home page
		driver.get("https://www.policybazaar.com/");		
	}
	
	public String getDestination() {
		return driver.findElement(By.id("selected-destinations")).getText();
	}
	
	public String getAge() {
		return driver.findElement(By.id("memage1")).getAttribute("value");
	}
	
	public String getStartDate() {
		return driver.findElement(By.xpath("//input[@id='startdate']")).getAttribute("value");
	}
	
	public String getEndDate() {
		return driver.findElement(By.xpath("//input[@id='enddate']")).getAttribute("value");
	}
	
	public boolean getMedicalCondition(String option) {		
		option = String.format("//div[@id='isPED']//label[@class='container'][contains(text(),'%s')]", option);			
		return driver.findElement(By.xpath(option)).isEnabled();
	}
	
	public boolean getPrefix(String Prefix) {
		Prefix= Prefix+".";
		String genderPath = String.format("//select[@id='travelgender']//option[contains(text(),'%s')]", Prefix);		
	    return driver.findElement(By.xpath(genderPath)).isSelected();
	}
	
	public String getName() {
		return driver.findElement(By.id("travelname")).getAttribute("value");
	}
	
	public String getCountryCode() {
		Select drop = new Select(driver.findElement(By.id("travelCountry")));			    
		return drop.getFirstSelectedOption().getText();
	}
	
	public String getMobileNumber() {
		return driver.findElement(By.id("travelmobile")).getAttribute("value");
	}
	
	public String getEmail() {
		return driver.findElement(By.id("travelemail")).getAttribute("value");
	}
}
