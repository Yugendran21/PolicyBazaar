
import org.testng.ITestContext ;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import Utils.DriverSetup;
import Utils.Xcell;
import Pages.Homepage;
import Pages.CarInsurance;

@Listeners(Utils.Listener.class)
public class CarTest {
	
	public static WebDriver driver;	
	public Homepage Homepage;
	public CarInsurance CarInsurance;
	public String output;
	
	@Parameters({"node"})
	@BeforeClass
	public void setup(ITestContext context,@Optional("") String node) throws Exception {
		driver = DriverSetup.driverSetup(node);		
		this.Homepage = new Homepage(driver);	
		Homepage.selectFromDropdownMenu("Motor Insurance", "Car Insurance");
		context.setAttribute("WebDriver", driver);	
		
	}

	@Test
	public void invalidDetails(ITestContext context) throws Exception {
		this.CarInsurance = new CarInsurance(driver);
		List<String> data = Xcell.getTestData("invalid");		
		CarInsurance.clickOnProceed();
		CarInsurance.selectRTO(data.get(0), data.get(1));
		CarInsurance.selectCarBrand(data.get(2));
		CarInsurance.selectCarModel(data.get(3));
		CarInsurance.selectFuelType(data.get(4));
		CarInsurance.selectVariant(data.get(5));
		CarInsurance.selectYear(data.get(6));
		CarInsurance.setName(data.get(7));
		CarInsurance.setMobileNo(data.get(8));
		CarInsurance.setEmail(data.get(9));
		CarInsurance.clickViewPrices();		
		output = CarInsurance.getErrorMsg();
		System.out.println(output);
		context.setAttribute("Output", output);		
	}
	
	@AfterClass
	public void quitBrowser() {
		driver.close();
	}


}