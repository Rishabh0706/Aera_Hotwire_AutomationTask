package com.hotwire.qa.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import com.hotwire.qa.util.TestUtil;
import com.hotwire.qa.base.TestBase;
import com.hotwire.qa.pages.HomePage;
import com.hotwire.qa.pages.SearchResultsPage;
import com.hotwire.qa.pages.VacationsPage;

public class VacationsPageTest extends TestBase {
	
	HomePage homePage;
	VacationsPage vacationsPage;
	SearchResultsPage searchResultsPage;
	
	String sheetName = "places";
	
	public VacationsPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialization();
		homePage = new HomePage();
	}
	
	@DataProvider
	public Object[][] getHotwireTestData(){
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}
	
	
	@Test(dataProvider="getHotwireTestData")
	public void searchResultsTest(String source, String destination) {

		vacationsPage =  homePage.clickOnVacationsLink();
		
		vacationsPage.selectFlightHotelCarButtons();
		
		vacationsPage.enterSourceDestination(source, destination);
		
		vacationsPage.enterTravelDate();
		
		vacationsPage.enterTraveltime();
		
		searchResultsPage = vacationsPage.clickDealButton();
		
		//asserting results
		Assert.assertTrue(searchResultsPage.getSearchResults(), "No search results returned");		
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
