package com.scania.scaniaDevUIFramewok.pages;

import static com.scania.scaniaDevUIFramewok.util.ElementUtil.getLocatorByLabelName;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.scania.scaniaDevUIFramewok.constants.AppConstants;
import com.scania.scaniaDevUIFramewok.util.ElementUtil;

public class EmployeeInformationPage {
	private WebDriver driver;
	private ElementUtil el;

	public EmployeeInformationPage(WebDriver driver) {
		this.driver = driver;
		this.el = new ElementUtil(driver);
	}

	// ******Locators***

	private By addBttnLoc = By.xpath("//button[text()=' Add ']");
	private String empNameLabel = "Employee Name";
	private By searchBttnLoc = By.xpath("//button[text()=' Search ']");
	private By empRecordsListLoc = By.cssSelector("div.orangehrm-container div.oxd-table-body div.oxd-table-card");

	public AddEmployeePage goToAddEmployee() {
		el.waitForElementVisibility(addBttnLoc, AppConstants.SMALL_DEFAULT_TIME_OUT).click();
		return new AddEmployeePage(driver);
	}

	public List<WebElement> searchEmployee(String empName) {
		el.doSendKeysWithWait(getLocatorByLabelName(empNameLabel), AppConstants.SMALL_DEFAULT_TIME_OUT, empName);

		el.waitForElementVisibility(searchBttnLoc, AppConstants.SMALL_DEFAULT_TIME_OUT).click();

		return el.waitForListOfWebElementsTobeVisible(AppConstants.MEDIUM_DEFAULT_TIME_OUT, empRecordsListLoc);

	}

	public ViewPersonalDetailsPage GoToEmployeeDetails(String empName) {
		List<WebElement> employeeList = searchEmployee(empName);
		employeeList.get(0).click();
		return new ViewPersonalDetailsPage(driver);

	}
}
