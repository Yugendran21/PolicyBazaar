
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Pages.Homepage;
import Pages.SearchResult;
import Pages.TravelInusrancePage;
import Utils.DriverSetup;
import Utils.Xcell;

@Listeners(Utils.Listener.class)
public class TravelTest {
	
	public static WebDriver driver;	
	public Homepage Homepage;
	public TravelInusrancePage TravelInusrancePage;
	public SearchResult SearchResult;	
	public String output;
	
	
	@DataProvider(name="data")
	public Object[][] dataProviderlast(ITestNGMethod testContext) throws Exception{
		Object[][] data = null;		
		if(testContext.getMethodName().equals("testValidDetails")) data = Xcell.getTestData2d("validForm");	
		if(testContext.getMethodName().equals("invalidForm1")) data = Xcell.getTestData2d("invalidForm1");			
		if(testContext.getMethodName().equals("invalidForm2")) data = Xcell.getTestData2d("invalidForm2");	
		if(testContext.getMethodName().equals("nullForm1")) data = Xcell.getTestData2d("nullForm1");	
		if(testContext.getMethodName().equals("nullForm2")) data = Xcell.getTestData2d("nullForm2");
		if(testContext.getMethodName().equals("fieldValidationForm1")) data = Xcell.getTestData2d("fieldValidationForm1");
		if(testContext.getMethodName().equals("fieldValdiationForm2")) data = Xcell.getTestData2d("fieldValdiationForm2");
		return data;		
	}
	@Parameters({"node"})
	@BeforeClass
	public void setup(ITestContext context,@Optional("") String node) throws Exception {
		driver = DriverSetup.driverSetup(node);		
		this.Homepage = new Homepage(driver);	
		this.TravelInusrancePage = new TravelInusrancePage(driver);	
		context.setAttribute("WebDriver", driver);			
	}
	@Test(priority=0)
	public void urlCheck(){
		String expectedTitle ="Insurance - Compare & Buy Insurance Plans – Health, Term, Life, Car";
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
	}
	
	@Test(priority = 1)
	public void naviagteToTravelInsurance() throws Exception {
		Homepage.selectFromDropdownMenu("Other Insurance", "Travel Insurance");
		String expectedTitle ="Travel Insurance: Compare & Buy Travel Insurance Policy Online";
		Thread.sleep(5000);
		String actualTitle =driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
	}
	
	@Test(priority = 2, dataProvider="data")
	public void testValidDetails(ITestContext context ,String country, String age, String startDate, String endDate, String option,
			String Prefix, String Name, String countryCode, String mobileNumber, String email ) throws Exception {
		driver.manage().deleteAllCookies();
		TravelInusrancePage.toHomePage(driver);
		Homepage.selectFromDropdownMenu("Other Insurance", "Travel Insurance");			
		TravelInusrancePage.selectStudentTab();
		TravelInusrancePage.setDestination(country);
		TravelInusrancePage.setAgeOfTravellers(age);
		TravelInusrancePage.setTripStartDate(startDate);
		TravelInusrancePage.setTripEndDate(endDate);
		TravelInusrancePage.setMedicalCondition(option);
		TravelInusrancePage.clickProceed();	
		TravelInusrancePage.setPrefix(Prefix);
		TravelInusrancePage.setName(Name);
		TravelInusrancePage.setCountryCode(countryCode);
		TravelInusrancePage.setMobileNumber(mobileNumber);
		TravelInusrancePage.setEmail(email);	
		TravelInusrancePage.clickGetFreeQuotes();
		this.SearchResult = new SearchResult(driver);
		output = SearchResult.getResults();	
		System.out.println(output);
		context.setAttribute("Output", output);	
		Assert.assertFalse(output.isEmpty());
	}
	
	@Test(priority = 3, dataProvider="data")
	public void invalidForm1(ITestContext context,String country, String age, String startDate, String endDate, String option) throws Exception  {
		driver.manage().deleteAllCookies();
		TravelInusrancePage.toHomePage(driver);
		Homepage.selectFromDropdownMenu("Other Insurance", "Travel Insurance");		
		TravelInusrancePage.selectStudentTab();
		TravelInusrancePage.setDestination(country);
		TravelInusrancePage.setAgeOfTravellers(age);
		TravelInusrancePage.setTripStartDate(startDate);
		TravelInusrancePage.setTripEndDate(endDate);
		TravelInusrancePage.setMedicalCondition(option);
		TravelInusrancePage.clickProceed();
		output = TravelInusrancePage.getErrorMsg();
		System.out.println(output);
		context.setAttribute("Output", output);	
		Thread.sleep(1000);
		WebElement proceed = driver.findElement(By.id("proceedStepOne"));
		Assert.assertTrue(proceed.isDisplayed());		
	}	
	
	@Test(priority = 4, dataProvider="data")
	public void invalidForm2(ITestContext context, String country, String age, String startDate, String endDate, String option,
			String Prefix, String Name, String countryCode, String mobileNumber, String email) throws Exception  {
		
		driver.manage().deleteAllCookies();
		TravelInusrancePage.toHomePage(driver);
		Homepage.selectFromDropdownMenu("Other Insurance", "Travel Insurance");		
		TravelInusrancePage.selectStudentTab();
		TravelInusrancePage.setDestination(country);
		TravelInusrancePage.setAgeOfTravellers(age);
		TravelInusrancePage.setTripStartDate(startDate);
		TravelInusrancePage.setTripEndDate(endDate);
		TravelInusrancePage.setMedicalCondition(option);
		TravelInusrancePage.clickProceed();
		TravelInusrancePage.setPrefix(Prefix);
		TravelInusrancePage.setName(Name);
		TravelInusrancePage.setCountryCode(countryCode);
		TravelInusrancePage.setMobileNumber(mobileNumber);
		TravelInusrancePage.setEmail(email);
		TravelInusrancePage.clickGetFreeQuotes();
		output = TravelInusrancePage.getErrorMsg();
		System.out.println(output);
		context.setAttribute("Output", output);	
		Thread.sleep(5000);
		String expected = "Travel Insurance: Compare & Buy Travel Insurance Policy Online";
		String actual = driver.getTitle();
		Assert.assertEquals(actual, expected);
	}	
	
	@Test(priority = 5, dataProvider="data")
	public void nullForm1(ITestContext context, String country, String age, String startDate, String endDate, String option) throws Exception  {
		driver.manage().deleteAllCookies();
		TravelInusrancePage.toHomePage(driver);
		Homepage.selectFromDropdownMenu("Other Insurance", "Travel Insurance");	
		TravelInusrancePage.selectStudentTab();
		if(country!="")TravelInusrancePage.setDestination(country);
		if(age!="")TravelInusrancePage.setAgeOfTravellers(age);
		if(startDate!="")TravelInusrancePage.setTripStartDate(startDate);
		if(endDate!="")TravelInusrancePage.setTripEndDate(endDate);
		TravelInusrancePage.setMedicalCondition(option);
		TravelInusrancePage.clickProceed();
		output = TravelInusrancePage.getErrorMsg();
		System.out.println(output);
		context.setAttribute("Output", output);
		Thread.sleep(1000);
		WebElement proceed = driver.findElement(By.xpath("//a[@class='proceedButton travelproceed']"));		
		Assert.assertTrue(proceed.isEnabled());
		
	}	
	
	@Test(priority = 6, dataProvider="data")
	public void nullForm2(ITestContext context, String country, String age, String startDate, String endDate, String option,
			String Prefix, String Name, String countryCode, String mobileNumber, String email) throws Exception  {
		driver.manage().deleteAllCookies();
		TravelInusrancePage.toHomePage(driver);
		Homepage.selectFromDropdownMenu("Other Insurance", "Travel Insurance");		
		TravelInusrancePage.selectStudentTab();
		TravelInusrancePage.setDestination(country);
		TravelInusrancePage.setAgeOfTravellers(age);
		TravelInusrancePage.setTripStartDate(startDate);
		TravelInusrancePage.setTripEndDate(endDate);
		TravelInusrancePage.setMedicalCondition(option);
		TravelInusrancePage.clickProceed();
		if(Prefix!="")TravelInusrancePage.setPrefix(Prefix);
		if(Name!="")TravelInusrancePage.setName(Name);
		if(countryCode!="")TravelInusrancePage.setCountryCode(countryCode);
		if(mobileNumber!="")TravelInusrancePage.setMobileNumber(mobileNumber);
		if(email!="")TravelInusrancePage.setEmail(email);
		TravelInusrancePage.clickGetFreeQuotes();
		output = TravelInusrancePage.getErrorMsg();
		System.out.println(output);
		context.setAttribute("Output", output);	
		Thread.sleep(5000);
		String expected = "Travel Insurance: Compare & Buy Travel Insurance Policy Online";
		String actual = driver.getTitle();
		Assert.assertEquals(actual, expected);
	}	
	
	@Test(priority = 7, dataProvider="data")
	public void fieldValidationForm1(ITestContext context,String country, String age, String startDate, String endDate, String option) throws Exception  {
		driver.manage().deleteAllCookies();
		TravelInusrancePage.toHomePage(driver);
		Homepage.selectFromDropdownMenu("Other Insurance", "Travel Insurance");		
		TravelInusrancePage.selectStudentTab();
		TravelInusrancePage.setDestination(country);
		TravelInusrancePage.setAgeOfTravellers(age);
		TravelInusrancePage.setTripStartDate(startDate);
		TravelInusrancePage.setTripEndDate(endDate);
		TravelInusrancePage.setMedicalCondition(option);	
		Assert.assertEquals(TravelInusrancePage.getAge(), age);			
		Assert.assertTrue(TravelInusrancePage.getDestination().contains(country));
		Assert.assertEquals(TravelInusrancePage.getStartDate(), startDate);
		Assert.assertEquals(TravelInusrancePage.getEndDate(), endDate);		
		Assert.assertTrue(TravelInusrancePage.getMedicalCondition(option));
	}	
	
	@Test(priority = 8, dataProvider="data")
	public void fieldValdiationForm2(ITestContext context, String country, String age, String startDate, String endDate, String option,
			String Prefix, String Name, String countryCode, String mobileNumber, String email) throws Exception  {		
		driver.manage().deleteAllCookies();
		TravelInusrancePage.toHomePage(driver);
		Homepage.selectFromDropdownMenu("Other Insurance", "Travel Insurance");		
		TravelInusrancePage.selectStudentTab();
		TravelInusrancePage.setDestination(country);
		TravelInusrancePage.setAgeOfTravellers(age);
		TravelInusrancePage.setTripStartDate(startDate);
		TravelInusrancePage.setTripEndDate(endDate);
		TravelInusrancePage.setMedicalCondition(option);		
		TravelInusrancePage.clickProceed();
		TravelInusrancePage.setPrefix(Prefix);
		TravelInusrancePage.setName(Name);
		TravelInusrancePage.setCountryCode(countryCode);
		TravelInusrancePage.setMobileNumber(mobileNumber);
		TravelInusrancePage.setEmail(email);
		Assert.assertTrue(TravelInusrancePage.getPrefix(Prefix));
		Assert.assertEquals(TravelInusrancePage.getName(), Name);
		Assert.assertEquals(TravelInusrancePage.getCountryCode(), "+"+countryCode);
		Assert.assertEquals(TravelInusrancePage.getMobileNumber(), mobileNumber);
		Assert.assertEquals(TravelInusrancePage.getEmail(),email);
	}	
	
	@AfterClass
	public void quitBrowser() {
		driver.close();
	}



}
