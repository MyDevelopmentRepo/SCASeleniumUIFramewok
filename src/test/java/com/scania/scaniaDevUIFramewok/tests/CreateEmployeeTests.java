package com.scania.scaniaDevUIFramewok.tests;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.scania.scaniaDevUIFramewok.base.BaseTest;
import com.scania.scaniaDevUIFramewok.util.ExcelUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;

@Epic("Epic-1: Employees creation and verification.")
@Story("US-1: Handle creation of single and bulk employees.")
public class CreateEmployeeTests extends BaseTest {
   private String empName ;
	@BeforeClass
	public void setupEmployeeCreationTests() {
		homePage = loginPage.LogMeIn(prop.getProperty("user"), prop.getProperty("password"));
		employeeInformationPage = homePage.goToPIM();

	}

	@DataProvider
	public Object[][] getDataForCreateEmployeeAndVerify() {
		return new Object[][] { { "Automation", "Selenium", "solna centrum", "solna", "stockholm","19072", "876789879" }

		};
	}

	// EmployeeInfoPage--> AddEmployeePage--> ViewPersonalDetaisPage
	@Test(dataProvider = "getDataForCreateEmployeeAndVerify", enabled = true)
	@Description("Create an employee .")
	public void CreateEmployeeAndVerifyIfExists(String firstName, String lastName, String street1, String street2, String city,
			String code, String mobile) {
		empName = firstName+System.currentTimeMillis();
		addEmployeePage = employeeInformationPage.goToAddEmployee();
		viewPersonalDetailsPage = addEmployeePage.AddEmployee(empName, lastName);
		viewPersonalDetailsPage.addAdditionalEmployeeDetails(street1, street2, city, code, mobile);
		employeeInformationPage = viewPersonalDetailsPage.goToPIM();
		List<WebElement> noOfEmployees= employeeInformationPage.searchEmployee(empName);
		Assert.assertEquals(noOfEmployees.size(), 1);
	}
	
	@Test(dependsOnMethods = "CreateEmployeeAndVerifyIfExists", dataProvider = "getDataForCreateEmployeeAndVerify", enabled = true)
	@Description("Verify the details of the created employee.")
	public void VerifyEmployeeDetails(String firstName, String lastName, String street1, String street2, String city,
			String code, String mobile) {
		viewPersonalDetailsPage = employeeInformationPage.GoToEmployeeDetails(empName);
		HashMap<String, String> empDetailsMap = viewPersonalDetailsPage.getEmployeeDetails();
		
		String actfirstName = empDetailsMap.get("firstname");
		String actlastName = empDetailsMap.get("lastname");
		String actstreet1 = empDetailsMap.get("street1");
		String actstreet2 = empDetailsMap.get("street2");
		String actcity = empDetailsMap.get("city");
		String actcode = empDetailsMap.get("code");
		String actmobile = empDetailsMap.get("mobile");
		
		softAssert.assertEquals(actfirstName, empName);
		softAssert.assertEquals(actlastName, lastName);
		softAssert.assertEquals(actstreet1, street1);
		softAssert.assertEquals(actstreet2, street2);
		softAssert.assertEquals(actcity, city);
		softAssert.assertEquals(actcode, code);
		softAssert.assertEquals(actmobile, mobile);
		
		softAssert.assertAll();
	}
	
	
	
	
	
	
	
	
	
	
	 @DataProvider
	 public Object[][] getEXCELdataForCreateEmployeesInBulk()
	 {
		 Object[][] UsersData = ExcelUtil.getTestdata("UserDetails");
		 return UsersData;
	 }
	 
	 @Test(dataProvider = "getEXCELdataForCreateEmployeesInBulk", enabled = true)
	 @Description("Create an multiple employees from excel.")
	public void CreateEmployeesInBulk(String firstName, String lastName, String street1, String street2, String city,
			String code, String mobile)
	{
			addEmployeePage = employeeInformationPage.goToAddEmployee();
			viewPersonalDetailsPage = addEmployeePage.AddEmployee(firstName, lastName);
			viewPersonalDetailsPage.addAdditionalEmployeeDetails(street1, street2, city, code, mobile);
			employeeInformationPage = viewPersonalDetailsPage.goToPIM();
	}

}
