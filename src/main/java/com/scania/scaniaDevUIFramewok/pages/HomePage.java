package com.scania.scaniaDevUIFramewok.pages;

import static com.scania.scaniaDevUIFramewok.constants.AppConstants.MEDIUM_DEFAULT_TIME_OUT;
import static com.scania.scaniaDevUIFramewok.constants.AppConstants.SMALL_DEFAULT_TIME_OUT;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.scania.scaniaDevUIFramewok.constants.AppConstants;
import com.scania.scaniaDevUIFramewok.util.ElementUtil;

public class HomePage {

	private WebDriver driver;
	private ElementUtil el;

	// ******Locators***
	private By pimLoc = By.xpath("//a[contains(@href, \"viewPimModule\")]");
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		el = new ElementUtil(driver);
	}

	public EmployeeInformationPage goToPIM()
	{
		el.waitForElementVisibility(pimLoc, SMALL_DEFAULT_TIME_OUT).click();
		return new EmployeeInformationPage(driver);
	}
	

}
