package com.scania.scaniaDevUIFramewok.util;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {

	private WebDriver driver;
	private JavascriptExecutor js;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		this.js = (JavascriptExecutor) driver;
	}

	public void staticWait(long timeOut) {
		try {
			Thread.sleep(timeOut);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 
	public WebElement getWebElement(By locator)
	{
		return driver.findElement(locator);
	}
	
	public String getTextUsingJS(By locator)
	{
		return(String) js.executeScript("return arguments[0].value", getWebElement(locator));
	}
	public void doClick(By locator)
	{
		getWebElement(locator).click();
	}
	


	public WebElement waitForElementVisibility(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	
	public void doSendKeysWithWait(By locator, int timeOut, String value)
	{
		WebElement ele = waitForElementVisibility(locator,timeOut);
		ele.sendKeys(Keys.chord(Keys.CONTROL, "a"),value);
	}
	 
	public static By getLocatorByLabelName(String label)
	{
		if(label != null)
		  return By.xpath("//label[text() ='"+label+"']/../following-sibling::div//input");
			
		return null;
	}
	
	
	public List<WebElement> waitForListOfWebElementsTobeVisible(int timeOut, By locator)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
		
	

		
}