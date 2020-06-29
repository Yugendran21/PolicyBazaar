package Utils;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class ScreenShot {
	
	 public static String getScreenShot(String fileName, WebDriver driver) throws Exception {
		 	Thread.sleep(2000);
			TakesScreenshot screen =((TakesScreenshot)driver);
			File screenShot = screen.getScreenshotAs(OutputType.FILE);
			String filePath = String.format("screenShots/%s.png", fileName);			
			File screenShotFolder =new File(filePath);
			if(screenShotFolder.exists()) {
			    Listener.count++;			    
				filePath = String.format("screenShots/%s_%s.png", fileName,Listener.count);				
				screenShotFolder =new File(filePath);
			}
	        FileUtils.copyFile(screenShot, screenShotFolder);
	    return filePath;
		}

}
