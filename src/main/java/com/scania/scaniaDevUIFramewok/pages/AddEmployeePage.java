package com.scania.scaniaDevUIFramewok.pages;

import static com.scania.scaniaDevUIFramewok.constants.AppConstants.SMALL_DEFAULT_TIME_OUT;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.scania.scaniaDevUIFramewok.constants.AppConstants;
import com.scania.scaniaDevUIFramewok.util.ElementUtil;
import static com.scania.scaniaDevUIFramewok.util.ElementUtil.getLocatorByLabelName;

public class AddEmployeePage {
	private WebDriver driver;
	private ElementUtil el;

	public AddEmployeePage(WebDriver driver) {
		this.driver = driver;
		this.el = new ElementUtil(driver);
	}

	// ******Locators***
	private By firstNameLoc = By.name("firstName");
	private By lastNameLoc = By.name("lastName");
	private By personalDetailSaveBttnLoc = By.xpath("//button[@type='submit']");

	public ViewPersonalDetailsPage AddEmployee(String firstName, String lastName) {
		el.doSendKeysWithWait(firstNameLoc, AppConstants.MEDIUM_DEFAULT_TIME_OUT, firstName);
		el.doSendKeysWithWait(lastNameLoc, AppConstants.MEDIUM_DEFAULT_TIME_OUT, lastName);

		el.doClick(personalDetailSaveBttnLoc);

		return new ViewPersonalDetailsPage(driver);

	}

}
