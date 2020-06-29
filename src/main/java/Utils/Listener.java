package Utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listener extends TestListenerAdapter
{
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;
	public static int count =0;	  
	
		
	public void onStart(ITestContext testContext)
	{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
		String repName="Test-Report-"+timeStamp+".html";
		
		htmlReporter=new ExtentHtmlReporter(repName);
		htmlReporter.loadXMLConfig(System.getProperty("user.dir")+ "\\extent-config.xml");
		
		extent=new ExtentReports();
		
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host name","localhost");
		extent.setSystemInfo("Environemnt","QA");
		extent.setSystemInfo("user","Maddy");
		
		htmlReporter.config().setDocumentTitle("PolicyBazaar Test Project"); // Tile of report
		htmlReporter.config().setReportName("Functional Test Automation Report"); // name of the report
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP); //location of the chart
		htmlReporter.config().setTheme(Theme.DARK);
	}
	
	
	public void onTestSuccess(ITestResult result) {  
		
		
		logger=extent.createTest(result.getName()); // create new entry in the report
		logger.log(Status.PASS,MarkupHelper.createLabel(result.getName(),ExtentColor.GREEN)); 
		logger.log(Status.PASS,"Testcase passed" );
		
				
		ITestContext context = result.getTestContext();
		String folderName = result.getInstanceName();	
		String testName = result.getName();
		String filePath = folderName+ "/" + testName + "/" + testName + "_Passed";			
		WebDriver driver = (WebDriver) context.getAttribute("WebDriver");		
		try {
			  String screenshotPath =ScreenShot.getScreenShot(filePath, driver);
			logger.log(Status.PASS, "Snapshot below: " + logger.addScreenCaptureFromPath(screenshotPath));
		} catch (Exception e) {			
			e.printStackTrace();		
		}
		
		String output = (String) context.getAttribute("Output");	
		try {
			Xcell.writeToExcell(folderName, testName, output,"Passed");
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}  
	  
	public void onTestFailure(ITestResult result) {  			

		logger=extent.createTest(result.getName()); // create new entry in the report
		logger.log(Status.FAIL,MarkupHelper.createLabel(result.getName(),ExtentColor.RED));
		logger.log(Status.FAIL, result.getThrowable());
		
		ITestContext context = result.getTestContext();		
		String folderName = result.getInstanceName();		
		String filePath = folderName+ "/" + result.getName()+ "/"+ result.getName() + "_Failed";
		WebDriver driver = (WebDriver) context.getAttribute("WebDriver");
		try {
			String screenshotPath =ScreenShot.getScreenShot(filePath, driver);
			logger.log(Status.FAIL, "Actual result " + logger.addScreenCaptureFromPath( screenshotPath));
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}  
	public void onTestSkipped(ITestResult tr)
	{
		logger=extent.createTest(tr.getName()); // create new entry in the report
		logger.log(Status.SKIP,MarkupHelper.createLabel(tr.getName(),ExtentColor.ORANGE));
	}
	
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
	}

}
