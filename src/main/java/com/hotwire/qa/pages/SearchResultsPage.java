package com.hotwire.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hotwire.qa.base.TestBase;

public class SearchResultsPage extends TestBase {
	
	public boolean getSearchResults() {
		
		WebDriverWait wait = new WebDriverWait(driver, 40);
		List<WebElement> searchResults = driver.findElements(By.xpath("//div[@id='resultsContainer']//article"));
		wait.until(ExpectedConditions.visibilityOfAllElements(searchResults));
		
		if (searchResults.size() >= 1)
			return true;
		return false;
	}

}
