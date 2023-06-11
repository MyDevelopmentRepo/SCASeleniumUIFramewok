package com.scania.scaniaDevUIFramewok.factory;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 
 * @author kanchan
 *
 */

public class DriverFactory {

	public static WebDriver driver;
	public Properties prop;

	/**
	 * This method is used to initialize the driver based on browser name.
	 * 
	 * @param browserName
	 * @return driver
	 */
	public WebDriver initDriver(String browserName) {
		

		if (browserName.equalsIgnoreCase("chrome")) {

			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		} else if (browserName.equalsIgnoreCase("firefox")) {

			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		} else if (browserName.equalsIgnoreCase("edge")) {

			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();

		} else {

		}

		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().deleteAllCookies();
		driver.navigate().refresh();
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));

		return driver;
	}

	public Properties initProp() {
		try {
			this.prop = new Properties();
			FileInputStream ip = null;

			ip = new FileInputStream("./src/test/resources/config/config.properties");
			this.prop.load(ip);

		} catch (IOException e) {
			
		}

		return prop;
	}


}
