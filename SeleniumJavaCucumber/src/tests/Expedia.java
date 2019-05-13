package tests;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Expedia {
	WebDriver driver;
	String browserType = "chrome";
	String city = "New York, NY";
	String checkin = "11/01/2019";
	String checkout = "11/08/2019";
	String numOfChild = "2";
	String starRating = "3 star";
	String searchResult = "3";
	
	@Test
	public void hotelReservation() {
		// 1. Search
		driver.findElement(By.id("tab-hotel-tab-hp")).click();
		driver.findElement(By.cssSelector("#gcw-hotel-form-hp-hotel label[for=hotel-destination-hp-hotel] input")).clear();
		driver.findElement(By.cssSelector("#gcw-hotel-form-hp-hotel label[for=hotel-destination-hp-hotel] input")).sendKeys(city);
		driver.findElement(By.id("hotel-checkin-hp-hotel")).sendKeys(checkin);
		driver.findElement(By.id("hotel-checkout-hp-hotel")).sendKeys(checkout);
		driver.findElement(By.cssSelector("button[data-year='2019'][data-month='10'][data-day='9']")).click();
		driver.findElement(By.id("traveler-selector-hp-hotel")).click();
		driver.findElements(By.cssSelector("#traveler-selector-hp-hotel .children-wrapper button")).get(1).click();
		new Select(driver.findElement(By.cssSelector("#traveler-selector-hp-hotel select[data-gcw-storeable-name='gcw-child-age-1-1']"))).selectByValue(numOfChild);
		driver.findElement(By.cssSelector("#gcw-hotel-form-hp-hotel button[type='submit']")).click();
		
		String cityName = driver.findElement(By.cssSelector("button[data-stid=hotels-destination-dialog-trigger]")).getText();
		// print city name
		System.out.println("CITY: " + cityName);
		
		
		// 2. Modify the search result page, give criteria
//		driver.findElement(By.cssSelector("input[type=checkbox][aria-label='" + starRating + "']")).click();

		
		// 3. Analyze the result and make our selection
		driver.findElement(By.xpath("//section[@class='results']//li[@tabindex='-1'][" + searchResult + "]//a")).click();

		// Switch the window to the popUp
		ArrayList<String> windows = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(windows.get(1));
		
		// Print hotel name and star rating
		String hotelName = driver.findElement(By.cssSelector("section[class='uitk-cell uitk-card-text-segment all-x-padding-three uitk-card-separator-bottom'] h1")).getText();
		String hotelRating = driver.findElement(By.cssSelector("span[class='reviews-summary__rating-value']")).getText();
		System.out.println("Hotel : " + hotelName + " Rating : " + hotelRating);
		
//		driver.switchTo().window(windows.get(0));
//		driver.findElement(By.xpath("//section[@class='results']//li[@tabindex='-1'][1]//a")).click();
		
		
		// 4. Book reservation
		driver.findElements(By.cssSelector("button[data-stid=submit-hotel-reserve]")).get(0).click();
		
		// print total price
		String price = driver.findElement(By.cssSelector("span[data-price-update='total']")).getText();
		System.out.println("Pirce: " + price);
		
		// 5. Fill out contact / billing
		
		// 6. Get confirmation
		String pageTitle = driver.getTitle();
		Assert.assertTrue(pageTitle.contains("Payment"));
	}
	
	@BeforeMethod
	public void setUp() {
		driver = utilities.DiverFactory.open(browserType);
		driver.manage().window().maximize();
		driver.get("https://www.expedia.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}
	
	@AfterMethod
	public void tearDown() {
//		driver.quit();
	}

}
