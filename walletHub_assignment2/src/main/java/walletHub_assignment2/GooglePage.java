package walletHub_assignment2;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GooglePage {
	WebDriver driver;
	WebDriverWait wait;

	// click any icon on google app icon(drive, gmail, map, play)
	@Test(enabled = false)
	// clicking on the google App icon and opening the gmail through it
	public void openGmail() throws Exception {
		// driver.get("https://www.google.com/");
		Thread.sleep(3000);
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@aria-label='Google
		// apps']")));
		WebElement googleAppIcon = driver.findElement(By.xpath("//a[@class='gb_D gb_vc']"));
		googleAppIcon.click();

		WebElement myFrame = driver.findElement(By.xpath("//iframe[@role='presentation']"));
		driver.switchTo().frame(myFrame);
		List<WebElement> listIcons = driver.findElements(By.tagName("li"));
		// we are adding the try catch blockk to avoid stale element exception
		try {
			for (WebElement iconList : listIcons) {
				System.out.println(iconList);
				List<WebElement> spanName = iconList.findElements(By.tagName("span"));
				String myValue = null;
				for (WebElement mySpan : spanName)
					// here we used "innerHTML" to get the enclosed text/tag
					// content (<a>Aniket</a>, aniket is enclosed text)
					myValue = mySpan.getAttribute("innerHTML");
				if (myValue.equals("Gmail")) {
					System.out.println("My Span name is " + myValue);
					iconList.click();
					System.out.println("This is my clicking li " + iconList);
				}
			}
		} catch (Exception e) {
			System.err.println("Stale element exception to be handled");
			e.printStackTrace();
		}
	}

	// we will open the Gmail in new tab, gmail icon on google page
	@Test(enabled = false)
	public void openGmailInNewTab() throws Exception {
		// driver.get("Http://www.google.com");
		// we have declared the url page in before method as common
		WebElement gmailPage = driver.findElement(By.xpath("//div[1][@class='gb_h gb_i']/a[1]"));
		// gmailPage.click();
		// you can simply use the code below to open a new tab in the same
		// browser:
		String openInNewTab = Keys.chord(Keys.CONTROL, Keys.RETURN);
		gmailPage.sendKeys(openInNewTab);
		// Then when you open a new tab, you have to switch to it to be able to
		// work with the newly opened tab:
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		// driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		Thread.sleep(4000);
		driver.close();
	}

	// For finding broken links
	@Test(enabled = true)
	public void brokenLInks() {
		List<WebElement> links = driver.findElements(By.tagName("a"));
		for (int i = 0; i <= links.size(); i++) {
			// driver.findElement(By.tagName("href"));
			WebElement element = links.get(i);
			//System.out.println(element);
			// By using "href" attribute, we could get the url of the requried
			// link
			String url = element.getAttribute("href");
			// calling verifyLink() method here. Passing the parameter as url
			// which we collected in the above link
			// See the detailed functionality of the verifyLink(url) method
			// below
			verifyLink(url);

		}

	}

	private void verifyLink(String urlLink) {
		//Sometimes we may face exception "java.net.MalformedURLException". Keep the code in try catch block to continue the broken link analysis
        try {
 //Use URL Class - Create object of the URL Class and pass the urlLink as parameter 
 URL link = new URL(urlLink);
 // Create a connection using URL object (i.e., link)
 HttpURLConnection httpConn =(HttpURLConnection)link.openConnection();
 //Set the timeout for 2 seconds
 httpConn.setConnectTimeout(2000);
 //connect using connect method
 httpConn.connect();
 //use getResponseCode() to get the response code. 
 if(httpConn.getResponseCode()== 200) { 
 System.out.println(urlLink+" - "+httpConn.getResponseMessage());
 }
 if(httpConn.getResponseCode()== 404) {
 System.out.println(urlLink+" - "+httpConn.getResponseMessage());
 }
 else{
	 System.out.println("Response code is unknown");
 }
        }
        //getResponseCode method returns = IOException - if an error occurred connecting to the server. 
        catch (Exception e) {
        System.out.println("unknown error in catch");
        	e.printStackTrace();
        }
           
	}

	@BeforeMethod
	public void initialProcess() {
		System.setProperty("webdriver.chrome.driver", "resources\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("Http://www.google.com");
		WebDriverWait wait = new WebDriverWait(driver, 10);
	}

	@AfterMethod
	public void lastProcess() {
		// driver.quit();
	}

}
