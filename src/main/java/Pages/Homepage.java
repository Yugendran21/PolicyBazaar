package Pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Homepage {
	
	public  WebDriver driver;     
	public Actions action;
	public WebDriverWait wait;
	
	public Homepage(WebDriver driver){
		this.driver= driver;
		this.action = new Actions(driver);
		this.wait = new WebDriverWait(driver,10);
	}
	
	public void selectFromDropdownMenu(String menuName, String insuranceName) throws Exception {//Selects option from drop down menu
		String menuPath = String.format("//a[contains(text(),'%s')]", menuName);
		String insurancePath = String.format("//span[contains(text(),'%s')]", insuranceName);
		WebElement menu= driver.findElement(By.xpath(menuPath));
		WebElement insurance= driver.findElement(By.xpath(insurancePath));	
			
		while(!insurance.isDisplayed()) {
			wait.until(ExpectedConditions.visibilityOf(menu));		
			action.moveToElement(menu).perform();
			Thread.sleep(2000);
		}
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(insurancePath)));
		action.moveToElement(insurance).click().perform();
		insurance.click();				
	}
	
	public String getInsuranceNames(String menuName) {// Returns the option name from the menu
		String menuPath = String.format("//a[contains(text(),'%s')]", menuName);
		WebElement menu=driver.findElement(By.xpath(menuPath));		
		action.moveToElement(menu).perform();
		String listInsurance = String.format("//a[contains(text(),'%s')]/following::ul[@class='dropdown-menu']//child::li", menuName);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(listInsurance)));
		List<WebElement> names = driver.findElements(By.xpath(listInsurance));// Stores the webelement in list
		String Names="";
		for(WebElement element : names) {
			String name = element.getText();
			Names = Names + name + "\n";			
		}
		return Names;
	}	



}