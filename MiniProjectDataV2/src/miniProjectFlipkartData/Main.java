/******************************************************************************************************************************************
 *  To demonstrate use of data driven concepts, code executes search for laptop, TV, washing machine apart from required search for mobiles
 *  *************************************************************************************************************************************** */
package miniProjectFlipkartData;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Main{

	static WebDriver driver; // declaring global WebDriver variable

	public static void main(String[] args) throws InterruptedException, FileNotFoundException {

		String filePath = System.getProperty("user.dir") + "\\resources\\data"; // define excel sheet path
		ArrayList<ArrayList<String>> dataArray = new ArrayList<ArrayList<String>>(); // ArrayList containing excel sheet
		try {
			dataArray = ReadFromExcel.readExcel(filePath, "searchProducts.xlsx", "sheet1"); // extract data from excel sheet
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Chrome or Firefox or Opera or Edge?");
		Scanner s = new Scanner(System.in);
		String browser = s.nextLine();  // input from user for choosing browser
		s.close();
		driver = DriverSetup.getDriver(browser); // launch browser

		allMethods.closeLogin(driver);	//close login request
		
		//loop through array list to get product and price from sheet and invoke all methods serially
		for (int i = 1; i < dataArray.size(); i++) {
			String maxPrice; // maximum price allowed
			String searchQuery; // product to be searched
			try {
				maxPrice = dataArray.get(i).get(1);	
				searchQuery = dataArray.get(i).get(0);
			} catch (IndexOutOfBoundsException e) {
				break; //to stop loop when all data finishes
			}

				Thread.sleep(8000);

				allMethods.enterSearch(searchQuery, driver); // enter the search query in search box

				allMethods.validateTitle(searchQuery, driver); // validate the title of the page

				allMethods.closeLogin(driver);

				allMethods.selectPrice(maxPrice, driver); // set max price

				Thread.sleep(10000);

				allMethods.sortNewestFirst(driver); // sort according to newest products

				Thread.sleep(3000);

			List<WebElement> names = allMethods.getProductNames(driver); // extract names of first 5 products
			List<WebElement> prices = allMethods.getProductPrices(driver); // extract prices of first 5 products
			
			Thread.sleep(3000);
			
			allMethods.displayResults(names, prices, driver); // display names of first 5 products and price of first product

			allMethods.validatePrice(prices, maxPrice, driver); // validate price of first product
		}
		driver.close(); // close the driver instance

	}
}
