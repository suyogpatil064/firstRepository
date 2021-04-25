package miniProjectFlipkartData;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class allMethods {
	// close the login pop-up if it appears
	WebDriver driver;
		public static void closeLogin(WebDriver driver) {
			// declaring a list of close button class that can be checked whether empty or not
			List<WebElement> isPresentList = driver.findElements(By.cssSelector("button[class='_2KpZ6l _2doB4z']"));
			if (isPresentList.size() != 0) {
				driver.findElement(By.cssSelector("button[class='_2KpZ6l _2doB4z']")).click();
				driver.findElement(By.name("q")).click();
			}
		}

		// enter the search query in search box
		public static void enterSearch(String searchQuery, WebDriver driver) {
			driver.findElement(By.name("q")).clear();
			driver.findElement(By.name("q")).sendKeys(searchQuery + Keys.ENTER);
		}

		// validate the title of the page
		public static void validateTitle(String searchQuery, WebDriver driver) throws InterruptedException {
			Thread.sleep(8000);
			String title = driver.getTitle();
			if (!(title.equalsIgnoreCase(
					searchQuery + "- Buy Products Online at Best Price in India - All Categories | Flipkart.com"))) {
				System.out.println("Page title incorrect!");
				System.out.println(title);
			} else {
				System.out.println("Title correct!");
			}
		}
		
		// set max price
		public static void selectPrice(String maxPrice, WebDriver driver) {
			WebElement price = driver.findElement(By.className("_3uDYxP")); // elements which have price drop downs

			Select priceDropDown = new Select(price.findElement(By.className("_2YxCDZ"))); // drop down element which selects higher price
			priceDropDown.selectByValue(maxPrice); 
		}

		// sort according to 'newest first'
		public static void sortNewestFirst(WebDriver driver) throws InterruptedException {

			List<WebElement> sortOpt = driver.findElements(By.className("_10UF8M")); // List of elements of all sorting options
			// loop through the list to find 'newest first' option
			for (int i = 0; i < sortOpt.size(); i++) {
				if (sortOpt.get(i).getText().equals("Newest First")) {
					sortOpt.get(i).click();
				}
			}
		}

		// get names of all products
		public static List<WebElement> getProductNames(WebDriver driver) {
			return driver.findElements(By.className("_4rR01T"));
		}

		// get List of all prices
		public static List<WebElement> getProductPrices(WebDriver driver) {
			return driver.findElements(By.cssSelector("div[class='_30jeq3 _1_WHN1']"));
		}

		// validate whether price of first product is below max price
		public static void validatePrice(List<WebElement> prices, String maxPrice, WebDriver driver) {
			// remove all symbols from price string to compare it with max price
			String firstPriceS = prices.get(0).getText();
			firstPriceS = firstPriceS.substring(1); // remove rupees symbol
			for (int i = 0; i < firstPriceS.length(); i++) {
				if (firstPriceS.charAt(i) == ',') {
					firstPriceS = firstPriceS.substring(0, i) + firstPriceS.substring(i + 1); // remove comma (,)
				}
			}
			int firstPriceI = Integer.parseInt(firstPriceS);
			// check whether price is below max price
			if (firstPriceI > Integer.parseInt(maxPrice)) {
				System.out.println("Price of first product is greater than " + maxPrice);
			} else {
				System.out.println("Price correctly below " + maxPrice);
			}
		}

		// display names of first 5 products and price of first product
		public static void displayResults(List<WebElement> names, List<WebElement> prices, WebDriver driver) {
			System.out.println("Names of first 5 products are:");
			for (int i = 1; i < 6; i++) {
				System.out.println(i + ". " + names.get(i - 1).getText());
			}
			System.out.println("Price of first product is: ");
			System.out.println(prices.get(0).getText().substring(1));
		}
}
