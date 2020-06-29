package Pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResult {
	
	public WebDriver driver;  	
	public WebDriverWait wait;
	public SearchResult(WebDriver driver){
		this.driver= driver;
		this.wait = new WebDriverWait(driver,10);
	}
	
	public void sortLowToHigh() {// clicks on LowToHigh option 		
		driver.findElement(By.xpath("//select[@name='SI']//option[contains(text(),'Low to High')]")).click();
	}	
	
	public String getResults() throws Exception {	// Returns the insurance names 	
		try {			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='okbutton md-button md-default-theme']")));
			driver.findElement(By.xpath("//button[@class='okbutton md-button md-default-theme']")).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@name='SI']//option[contains(text(),'Low to High')]")));
			Thread.sleep(3000);
			driver.findElement(By.xpath("//select[@name='SI']//option[contains(text(),'Low to High')]")).click();
		}
		catch(Exception e){			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@name='SI']//option[contains(text(),'Low to High')]")));
			Thread.sleep(3000);
			driver.findElement(By.xpath("//select[@name='SI']//option[contains(text(),'Low to High')]")).click();
		}	 
		List<WebElement> results = driver.findElements(By.xpath("//div[@class='show_quote-wrapper impotantClass ng-scope']"));
		results= results.subList(0, 3);
		String output="";
		for(WebElement insurance: results) {
				String companyName = insurance.findElement(By.xpath(".//img[contains(@data-ng-src,'supplierLogo')]")).getAttribute("alt");
				String price = insurance.findElement(By.xpath(".//span[@class='ng-binding ng-scope']")).getText();
				output = output + companyName + ":" + price +"\n";					 
		}
		return output;
	}	
	
	public void toHomePage() {//NAvigate back to the Home page
		this.driver.get("https://www.policybazaar.com/");
		wait.until(ExpectedConditions.titleIs("Insurance - Compare & Buy Insurance Plans – Health, Term, Life, Car"));
	}
}
