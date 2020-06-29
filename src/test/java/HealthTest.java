import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;
import Pages.Homepage;
import Utils.DriverSetup;

@Listeners(Utils.Listener.class)
public class HealthTest {
	
	public static WebDriver driver;	
	public Homepage Homepage;	
	public String output;
	
	@Parameters({"node"})
	@BeforeClass
	public void setup(ITestContext context, @Optional("") String node) throws Exception {
		driver = DriverSetup.driverSetup(node);		
		this.Homepage = new Homepage(driver);
		context.setAttribute("WebDriver", driver);
	}
	
	@Test
	public void getNameList(ITestContext context) {
		output = Homepage.getInsuranceNames("Health Insurance");
		context.setAttribute("Output", output);
		System.out.println(output);
		Assert.assertFalse(output.isEmpty());		
	}
	
	@AfterClass
	public void quitBrowser() {
		driver.close();		
	}
	
}