package com.hotwire.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.hotwire.qa.base.TestBase;

public class HomePage extends TestBase {
	
	// Page Factory or Object Repository:
	@FindBy(xpath="//a[@class='nav-link' and text()='Vacations']")
	WebElement vacationsLink;
	
	
	// Initializing the Page Objects:
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	public VacationsPage clickOnVacationsLink() {
		vacationsLink.click();
		// Redirects to vacations Page
		return new VacationsPage();
	}

}
