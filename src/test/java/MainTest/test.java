package MainTest;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.baseclass.BaseClass;

public class test extends BaseClass{
	@Test
	public void attachScreenShotTest() throws IOException {
		test = extent.createTest("first Test");
				
		 driver =new ChromeDriver();
		//test.pass("Browser Opened");
		driver.get("https://google.com");
		driver.findElement(By.name("qs")).sendKeys("Automation",Keys.ENTER);
//		  Thread.sleep(2000);
//		MediaEntityBuilder
//		test.pass("Value entered," , MediaEntityBuilder.createScreenCaptureFromPath(getScreenshotpath()).build());
//		test.pass("Value entered," , MediaEntityBuilder.createScreenCaptureFromBase64String(getScreenShortAsBase64()).build());
//		test.pass("test finished");
			
	}

}
