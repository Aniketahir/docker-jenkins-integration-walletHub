package walletHub_assignment2;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class jqueryuiPage {
	WebDriver driver;

	@Test(enabled=true)
	public void menuAndSubMenuSelection_ToolsQA() throws InterruptedException{
		//Adding wait 
		 //driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
		  System.out.println(driver.getTitle());
		 //Instantiate Action Class        
        Actions actions = new Actions(driver);
        WebElement myframe = driver.findElement(By.xpath("//iframe[@class='demo-frame']"));
		driver.switchTo().frame(myframe);
        //Retrieve WebElement 'Music' to perform mouse hover 
     WebElement menuOption = driver.findElement(By.xpath("//div[contains(text(),'Music')]"));
     //Mouse hover menuOption 'Music'
     actions.moveToElement(menuOption).click().perform();
     System.out.println("Done Mouse hover on 'Music' from Menu");
     Thread.sleep(1000);
     
     //Now Select 'Rock' from sub menu which has got displayed on mouse hover of 'Music'
     WebElement subMenuOption = driver.findElement(By.xpath(".//div[contains(text(),'Rock')]")); 
     //Mouse hover menuOption 'Rock'
     actions.moveToElement(subMenuOption).perform();
     System.out.println("Done Mouse hover on 'Rock' from Menu");
     Thread.sleep(1000);
     //Now , finally, it displays the desired menu list from which required option needs to be selected
     //Now Select 'Alternative' from sub menu which has got displayed on mouse hover of 'Rock'
     WebElement selectMenuOption = driver.findElement(By.xpath(".//div[contains(text(),'Alternative')]"));
     actions.moveToElement(selectMenuOption).perform();
     
     driver.switchTo().defaultContent();
     driver.findElement(By.xpath("//li[@class='menu-item current']")).click();
     Thread.sleep(1000);
     System.out.println("Selected 'Alternative' from Menu");
     
	}

	@BeforeMethod
	public void startProcess() {
		System.setProperty("webdriver.chrome.driver", "resources\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://jqueryui.com/menu/");
		driver.manage().window().maximize();
	}

	@AfterMethod
	public void endProcess() {
		// driver.quit();
	}

}
