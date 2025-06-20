package AmazonTest;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.idealized.Javascript;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Automation {

	public static void main(String[] args) throws InterruptedException, TimeoutException {
		// chrome driver path
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\gowth\\Downloads\\chromedriver-win64 (1)\\chromedriver-win64\\chromedriver.exe");
	    WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
		
		// amazon homepage
        driver.get("https://www.amazon.in/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("a-button-text")));
		continueButton.click();
		System.out.println("✅ Clicked on 'Continue Shopping' button.");
        System.out.println("Page title is: " +driver.getTitle());
		
		/*WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement signInButton = wait1.until(ExpectedConditions.elementToBeClickable(By.id("nav-link-accountList")));
		signInButton.click();
		
		System.out.println("🧭 Current URL: " + driver.getCurrentUrl());
		System.out.println("🧭 Current Page Title: " + driver.getTitle());


		WebElement emailField = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("ap_email_login")));
		emailField.sendKeys("enter email");
		driver.findElement(By.id("continue")).click();

		WebElement passwordField = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("ap_password")));
		passwordField.sendKeys("enter password");
		driver.findElement(By.id("signInSubmit")).click();*/

        // search for product
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("laptop"); //search for product
		driver.findElement(By.id("nav-search-submit-button")).click();
		
		// Scroll down let products load
		JavascriptExecutor js = (JavascriptExecutor) driver;
		for (int i = 0; i < 5; i++) {
		    js.executeScript("window.scrollBy(0, 1000)");
		    Thread.sleep(2000); // wait for products to load
		}

		// click on product
		WebDriverWait wait11 = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement product = wait11.until(ExpectedConditions
		    .visibilityOfElementLocated(By.xpath("//span[contains(text(), 'Dell Inspiron ')]")));

		// Scroll and click
		js.executeScript("arguments[0].scrollIntoView(true);", product);
		js.executeScript("arguments[0].click();", product);
		
		
		// Wait until element is present in the DOM
		WebDriverWait wait111 = new WebDriverWait(driver, Duration.ofSeconds(15));
		WebElement addToCart = wait111.until(ExpectedConditions.presenceOfElementLocated(By.id("add-to-cart-button")));

		// Scroll into view
		JavascriptExecutor j = (JavascriptExecutor) driver;
		j.executeScript("arguments[0].scrollIntoView(true);", addToCart);
		Thread.sleep(1000); // Small pause to ensure scroll is complete

		// Click using JavaScript
		j.executeScript("arguments[0].click();", addToCart);
        System.out.println("✅ Product successfully added to cart.");
		
		driver.findElement(By.id("nav-cart")).click();
		driver.findElement(By.id("nav-cart-count-container")).click();
		System.out.println("✅ Navigated to the Cart page.");
		String pageTitle = driver.getTitle();
		System.out.println("🧭 Page Title after clicking cart: " + pageTitle);
		
		try {
            WebElement price = driver.findElement(By.xpath("//*[contains(@class,'a-price-whole')]"));
            System.out.println("💰 Product Price: ₹" + price.getText());
        } catch (Exception e) {
            System.out.println("⚠️ Could not fetch product price");
        }

        
        System.out.println("🚫 Skipping 'Add to Cart' — user not signed in.");
        driver.quit();
        System.out.println("✅ Done!");
		
	



		
	}
}
