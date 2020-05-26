package walletHub_assignment2;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

//Go to rahulShetty page and from the webtable select and print the row with price =25
public class RahulShetty {
	WebDriver driver;
	JavascriptExecutor js = (JavascriptExecutor) driver;

	@Test
	public void MycoursePage() throws NullPointerException, InterruptedException {
		String price = "25";
		//Collect the rows tr from the table
		List<WebElement> tableRow = driver.findElements(By.xpath("//table[@id='product']/tbody/tr"));
		//iterate list of rows to get td from each row
		for (WebElement rowData : tableRow) {
			List<WebElement> cell = rowData.findElements(By.tagName("td"));
			
			//now from list of td iterate and get text of each cell
			for (WebElement singleCell : cell) {
				String singleCellText = singleCell.getAttribute("innerHTML").toString();
				
		// now check the td value and if td = 25 then print the tr for that td
				if (singleCellText.equals(price)) {
					//after comparing again call each td in particular tr which contain 25
					List<WebElement> cellInRow = rowData.findElements(By.tagName("td"));
					for (WebElement SingleRow : cellInRow) {
						System.out.print(SingleRow.getAttribute("innerHTML") + "  ");
					}
					System.out.println();
					
					//Select Parent Window from iframe window
					driver.switchTo().defaultContent();
				}
			}
		}
	}

	@BeforeMethod
	public void initialisationOfBrowser() {
		System.setProperty("webdriver.chrome.driver", "resources\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.rahulshettyacademy.com/AutomationPractice/");

	}

	@AfterMethod
	public void tearDown() {
		 driver.quit();
	}
}
