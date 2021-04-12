package mx.tec.lab;

import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.fail;

import java.util.concurrent.TimeUnit;

//import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestHtml2ApplicationTests {

	private static WebDriver driver;
	
	@BeforeEach
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "D:\\Trabajos\\6to Semestre\\Software Quality and Testing\\Lab 9\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@AfterEach
	public void tearDown() {
		driver.quit();
	}
	
	@Test
	public void givenAClient_whenEnteringAutomationPractice_thenPageTitleIsCorrect() throws Exception {
		// When
		driver.get("http://automationpractice.com/index.php");
		String title = driver.getTitle();
		
		// Then
		assertEquals("My Store", title);
	}
	
	@Test
	public void givenAClient_whenEnteringLoginCredentials_thenAccountPageIsDisplayed() throws Exception {
		// When
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		WebElement emailField = driver.findElement(By.id("email"));
		emailField.sendKeys("a01568181@itesm.mx");
		WebElement passwordField = driver.findElement(By.id("passwd"));
		passwordField.sendKeys("12345");
		WebElement submitButton = driver.findElement(By.id("SubmitLogin"));
		submitButton.click();
		String title = driver.getTitle();
		
		// Then
		assertEquals("My account - My Store", title);
	}
	
	
	// Step 9
	@Test
	public void givenAClient_whenEnteringWrongLoginCredentials_thenAuthenticationPageIsDisplayed() 
		throws Exception {
			// When
			driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
			WebElement emailField = driver.findElement(By.id("email"));
			emailField.sendKeys("a01568181@itesm.mx");
			WebElement passwordField = driver.findElement(By.id("passwd"));
			passwordField.sendKeys("likjkabdf");
			WebElement submitButton = driver.findElement(By.id("SubmitLogin"));
			submitButton.click();
			String title = driver.getTitle();
				
			// Then
			assertEquals("Login - My Store", title);
	}
	
	@Test
	public void givenAClient_whenEnteringWrongLoginCredentials_thenErrorMessageIsDisplayed() 
		throws Exception {
			// When
			driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
			WebElement emailField = driver.findElement(By.id("email"));
			emailField.sendKeys("a01568181@itesm.mx");
			WebElement passwordField = driver.findElement(By.id("passwd"));
			passwordField.sendKeys("likjkabdf");
			WebElement submitButton = driver.findElement(By.id("SubmitLogin"));
			submitButton.click();
			WebElement errorMessage = driver.findElement(By.xpath("//div[@class='alert alert-danger']/ol/li"));
						
			// Then
			assertEquals("Authentication failed.", errorMessage.getText());
	}
	
	@Test
	public void givenAClient_whenSearchingNotExistingProduct_thenNoResultsDisplayed() 
		throws Exception {
			// When
			driver.get("http://automationpractice.com/index.php");
			WebElement searchField = driver.findElement(By.id("search_query_top"));
			searchField.sendKeys("chivigon");
			WebElement searchButton = driver.findElement(By.xpath("//button[@class='btn btn-default button-search']"));
			searchButton.click();
			WebElement warningMessage = driver.findElement(By.xpath("//p[@class='alert alert-warning']"));
			
			// Then
			assertEquals("No results were found for your search \"chivigon\"", warningMessage.getText());
	}
	
	@Test
	public void givenAClient_whenSearchingEmptyString_thenPleaseEnterDisplayed() 
		throws Exception {
			// When
			driver.get("http://automationpractice.com/index.php");
			WebElement searchButton = driver.findElement(By.xpath("//button[@class='btn btn-default button-search']"));
			searchButton.click();
			WebElement warningMessage = driver.findElement(By.xpath("//p[@class='alert alert-warning']"));
			
			// Then
			assertEquals("Please enter a search keyword", warningMessage.getText());
	}
	
	@Test
	public void givenAClient_whenSigningOut_thenAuthenticationPageIsDisplayed() 
		throws Exception {
			// When
			driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
			WebElement emailField = driver.findElement(By.id("email"));
			emailField.sendKeys("a01568181@itesm.mx");
			WebElement passwordField = driver.findElement(By.id("passwd"));
			passwordField.sendKeys("12345");
			WebElement submitButton = driver.findElement(By.id("SubmitLogin"));
			submitButton.click();
			
			WebElement logoutButton = driver.findElement(By.xpath("//a[@class='logout']"));
			logoutButton.click();
			
			String title = driver.getTitle();
				
			// Then
			assertEquals("Login - My Store", title);
	}

}
