package com.scania.scaniaDevUIFramewok.pages;

import static com.scania.scaniaDevUIFramewok.constants.AppConstants.SMALL_DEFAULT_TIME_OUT;
import static com.scania.scaniaDevUIFramewok.util.ElementUtil.getLocatorByLabelName;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.scania.scaniaDevUIFramewok.constants.AppConstants;
import com.scania.scaniaDevUIFramewok.util.ElementUtil;

public class ViewPersonalDetailsPage {
	private WebDriver driver;
	private ElementUtil el;

	public ViewPersonalDetailsPage(WebDriver driver) {
		this.driver = driver;
		this.el = new ElementUtil(driver);
	}

	// ******Locators***
	private By firstNameLoc = By.name("firstName");
	private By lastNameLoc = By.name("lastName");
	private By contactDetailsLoc = By.xpath("//a[text() ='Contact Details']");
	private String street1Label = "Street 1";
	private String street2Label = "Street 2";
	private String cityLabel = "City";
	private String codeLabel = "Zip/Postal Code";
	private String mobileLabel = "Mobile";
	private By contactDetailSaveBttnLoc = By.xpath("(//button[text() = ' Save '])[1]");
	private By pimLoc = By.xpath("//a[contains(@href, \"viewPimModule\")]");

	public void addAdditionalEmployeeDetails(String street1, String street2, String city, String code, String mobile) {
		el.waitForElementVisibility(contactDetailsLoc, AppConstants.SMALL_DEFAULT_TIME_OUT).click();
		el.staticWait(5000);

		el.doSendKeysWithWait(getLocatorByLabelName(street1Label), AppConstants.SMALL_DEFAULT_TIME_OUT, street1);
		el.doSendKeysWithWait(getLocatorByLabelName(street2Label), AppConstants.SMALL_DEFAULT_TIME_OUT, street2);
		el.doSendKeysWithWait(getLocatorByLabelName(cityLabel), AppConstants.SMALL_DEFAULT_TIME_OUT, city);
		el.doSendKeysWithWait(getLocatorByLabelName(codeLabel), AppConstants.SMALL_DEFAULT_TIME_OUT, code);
		el.doSendKeysWithWait(getLocatorByLabelName(mobileLabel), AppConstants.SMALL_DEFAULT_TIME_OUT, mobile);
		el.doSendKeysWithWait(getLocatorByLabelName(street1Label), AppConstants.SMALL_DEFAULT_TIME_OUT, street1);

		el.doClick(contactDetailSaveBttnLoc);
	}

	public EmployeeInformationPage goToPIM() {
		el.waitForElementVisibility(pimLoc, SMALL_DEFAULT_TIME_OUT).click();
        return new EmployeeInformationPage(driver);
	}

	public HashMap<String, String> getEmployeeDetails() {
		HashMap<String, String> empDetailsMap = new HashMap<String, String>();
		el.staticWait(5000);
		empDetailsMap.put("firstname", el.getTextUsingJS(firstNameLoc));
		empDetailsMap.put("lastname", el.getTextUsingJS(lastNameLoc));

		el.waitForElementVisibility(contactDetailsLoc, AppConstants.SMALL_DEFAULT_TIME_OUT).click();
		el.staticWait(5000);
		empDetailsMap.put("street1", el.getTextUsingJS(getLocatorByLabelName(street1Label)));
		empDetailsMap.put("street2", el.getTextUsingJS(getLocatorByLabelName(street2Label)));
		empDetailsMap.put("city", el.getTextUsingJS(getLocatorByLabelName(cityLabel)));
		empDetailsMap.put("code", el.getTextUsingJS(getLocatorByLabelName(codeLabel)));
		empDetailsMap.put("mobile", el.getTextUsingJS(getLocatorByLabelName(mobileLabel)));

		return empDetailsMap;

	}
}
