package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Expedia {
	WebDriver driver;
	String browserType = "chrome";
	String city = "New York, NY";
	String checkin = "11/01/2019";
	String checkout = "11/08/2019";
	String numOfChild= "2";
	String stars= "star-3";//4 star
	
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
		driver.findElement(By.id(stars)).click();
//		driver.findElements(By.cssSelector(".property-class-input.all-grid-nowrap.uitk-grid span")).get(3).click();
		
		// 3. Analyze the result and make our selection

		// 4. Book reservation
		
		// 5. Fill out contact / billing
		
		// 6. Get confirmation
	}
	
	@BeforeMethod
	public void setUp() {
		driver = utilities.DiverFactory.open(browserType);
		driver.manage().window().maximize();
		driver.get("https://www.expedia.com/");
	}
	
	@AfterMethod
	public void tearDown() {
//		driver.quit();
	}

}
