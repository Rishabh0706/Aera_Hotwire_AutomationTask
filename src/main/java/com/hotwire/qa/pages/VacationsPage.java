package com.hotwire.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.hotwire.qa.base.TestBase;
import com.hotwire.qa.util.TestUtil;

public class VacationsPage extends TestBase {
	
	// Page Factory or Object Repository:
		@FindBy(xpath="//button[text()='Flight']")
		WebElement flightButton;
		
		@FindBy(xpath="//button[text()='Hotel']")
		WebElement hotelButton;
		
		@FindBy(xpath="//button[text()='Car']")
		WebElement carButton;
		
		@FindBy(xpath="//input[@id='farefinder-package-origin-location-input']")
		WebElement sourceBox;
		
		@FindBy(xpath="//input[@id='farefinder-package-destination-location-input']")
		WebElement destinationBox;
		
		@FindBy(xpath="//input[@id='farefinder-package-startdate-input']")
		WebElement departingDateBox;
		
		@FindBy(xpath="//input[@id='farefinder-package-enddate-input']")
		WebElement returningDateBox;
		
		@FindBy(id="farefinder-package-pickuptime-input")
		WebElement departingTimebox;
		
		@FindBy(id="farefinder-package-dropofftime-input")
		WebElement returningTimeBox;
		
		@FindBy(id="farefinder-package-search-button")
		WebElement findDealButton;
		
		
		// Initializing the Page Objects:
		public VacationsPage() {
			PageFactory.initElements(driver, this);
		}
		
		public void selectFlightHotelCarButtons() {
			
			if (!flightButton.isSelected()) {
				flightButton.click();
			}
			
			if (!hotelButton.isSelected()) {
				hotelButton.click();
			}
			
			if (!carButton.isSelected()) {
				carButton.click();
			}
		}
		
		public void enterSourceDestination(String source, String destination) {
			
			try {
				sourceBox.sendKeys(source);
				WebElement from = sourceBox.findElement(By.xpath("following-sibling::ul//li/a[contains(@title,"+source+")]"));
				from.click();
				
				destinationBox.sendKeys(destination);
				WebElement to = destinationBox.findElement(By.xpath("following-sibling::ul//li/a[contains(@title,"+destination+"-)]"));
				to.click();
			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		}
		
		public void enterTravelDate() {
			
			String departingDate = TestUtil.getNextDayDepartingDate();
			departingDateBox.clear();
			departingDateBox.sendKeys(departingDate);
			
			try {
				String returningDate = TestUtil.getReturningDate(departingDate, Integer.parseInt(prop.getProperty("travel_period_in_days")));
				returningDateBox.clear();
				returningDateBox.sendKeys(returningDate);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			
		}
		
		public void enterTraveltime() {
			
			Select drpDepartTime = new Select(departingTimebox);
			
			if (!drpDepartTime.getFirstSelectedOption().getText().equals("Evening")) {
				drpDepartTime.selectByVisibleText("Evening");
			}
			
			Select drpReturnTime = new Select(returningTimeBox);
			if (!drpReturnTime.getFirstSelectedOption().getText().equals("Morning")) {
				drpReturnTime.selectByVisibleText("Morning");
			}
		}
		
		public SearchResultsPage clickDealButton() {
			
			findDealButton.click();
			
			return new SearchResultsPage();
		}

}
