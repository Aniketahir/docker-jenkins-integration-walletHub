import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WalletHubMainPage {
	WebDriver driver;
	// Login button locator
	@FindBy(xpath = "//span[@class='brgm-button brgm-signup']")
	private WebElement login;
	@FindBy(xpath = "//input[@name='em']")
	private WebElement userName;
	@FindBy(xpath = "//input[@name='pw']")
	private WebElement passWord;
	@FindBy(xpath = "//button[@class='btn blue center reg-tabs-bt touch-element-cl']")
	private WebElement loginButton;
	@FindBy(xpath = "//div[@class='review-action ng-enter-element']//*[5]")
	private WebElement stars;
	@FindBy(xpath = "//*[@id='reviews-section']/modal-dialog/div/div/write-review/div/ng-dropdown/div/span")
	private WebElement dropdown;
	@FindBy(xpath = "//*[@id='reviews-section']/modal-dialog/div/div/write-review/div/ng-dropdown/div/ul/li")
	List<WebElement> dropdownlist;
	@FindBy(xpath = "//textarea[@class='textarea wrev-user-input validate']")
	private WebElement commentBox;
	@FindBy(xpath = "//div[@class='sbn-action semi-bold-fontbtn fixed-w-c tall']")
	private WebElement submit;

	@Test
	public void reviewPage() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Launch the application
		driver.get("https://wallethub.com/profile/test-insurance-company-13732055i");

		// Click on Login buton to go to login page
		login.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		userName.sendKeys("aniket.ahir@tarabasoft.com");
		passWord.sendKeys("Aniket@123");
		loginButton.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String Paragraph = "This is testing review comment using automated script"
				+ "created using selenium in java for given task which will give a good result for sure,"
				+ " writing demo review for wallethub insurance domain Thanks for such a good task.";
		
		// Find element by link text and store in variable "Element"
		WebElement Element = driver.findElement(By.xpath("//span[@class='rsba-h3-no-mob'][1]"));
		// This will scroll the page till the element is found
		js.executeScript("arguments[0].scrollIntoView();", Element);

		// finding the location path for the review stars
		stars.click();
		// find the dropdow and click on dropdown
		dropdown.click();
		
		// selecting the required option from given list of dropdown options.
		for (WebElement options : dropdownlist) {
			String Optiontext = options.getText();
			String expectedOption = "Health";
			boolean ans = Optiontext.contains(expectedOption);
			if (ans == true) {
				options.click();
				break;
			}
		}

		// Fill in the review comment section
		commentBox.sendKeys(Paragraph);
		// Click on submit button
		submit.click();
	}

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", "resources\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		PageFactory.initElements(driver, this);
	}

	@AfterMethod
	public void afterMethod() {
		driver.close();
	}

}
