package com.scania.scaniaDevUIFramewok.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;
import com.scania.scaniaDevUIFramewok.factory.DriverFactory;
import com.scania.scaniaDevUIFramewok.pages.AddEmployeePage;
import com.scania.scaniaDevUIFramewok.pages.EmployeeInformationPage;
import com.scania.scaniaDevUIFramewok.pages.HomePage;
import com.scania.scaniaDevUIFramewok.pages.LoginPage;
import com.scania.scaniaDevUIFramewok.pages.ViewPersonalDetailsPage;

public class BaseTest {

	public WebDriver driver;
	public DriverFactory df;
	public Properties prop;
	public SoftAssert softAssert;
	public LoginPage loginPage;
	public HomePage homePage;
	public ViewPersonalDetailsPage viewPersonalDetailsPage;
	public EmployeeInformationPage employeeInformationPage;
	public AddEmployeePage addEmployeePage;

	@Parameters({ "browser" })
	@BeforeTest
	public void setup(String browserName) {
		this.df = new DriverFactory();
		this.prop = df.initProp();
		if (browserName != null) {
			prop.setProperty("browser", "chrome");
		}
		this.driver = df.initDriver(prop.getProperty("browser"));
		loginPage = new LoginPage(driver);
		this.softAssert = new SoftAssert();
	}

	@AfterTest
	public void tearDown() {
		 driver.quit();
	}

}
