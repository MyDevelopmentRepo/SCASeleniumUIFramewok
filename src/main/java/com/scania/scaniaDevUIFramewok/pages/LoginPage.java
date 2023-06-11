package com.scania.scaniaDevUIFramewok.pages;

import static com.scania.scaniaDevUIFramewok.constants.AppConstants.SMALL_DEFAULT_TIME_OUT;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.scania.scaniaDevUIFramewok.constants.AppConstants;
import com.scania.scaniaDevUIFramewok.util.ElementUtil;

public class LoginPage {
	private WebDriver driver;
	ElementUtil el;

	// ******Locators***

	private By nameLoc = By.name("username");
	private By passwordLoc = By.name("password");
	private By loginBttnLoc = By.className("orangehrm-login-action");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		el = new ElementUtil(driver);
	}

	public HomePage LogMeIn(String userName, String password) {
		el.waitForElementVisibility(nameLoc, SMALL_DEFAULT_TIME_OUT).sendKeys(userName);
		el.waitForElementVisibility(passwordLoc, SMALL_DEFAULT_TIME_OUT).sendKeys(password);
		el.doClick(loginBttnLoc);
		return new HomePage(driver);
	}
	


}
