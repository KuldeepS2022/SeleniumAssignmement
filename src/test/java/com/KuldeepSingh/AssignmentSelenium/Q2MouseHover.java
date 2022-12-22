package com.KuldeepSingh.AssignmentSelenium;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Q2MouseHover {
	WebDriver wd;
	WebDriverWait wDW;
	SoftAssert sA;
	Actions action;

	@BeforeMethod
	public void setup() {
		System.setProperty("webdriver.edge.driver",
				"C:\\Users\\deepg\\Downloads\\Setup\\edgedriver_win64\\msedgedriver.exe");
		wd = new EdgeDriver();
		wDW = new WebDriverWait(wd, Duration.ofSeconds(10));
		sA = new SoftAssert();
		action = new Actions(wd);
		wd.get("http://seleniumpractise.blogspot.com/2016/08/how-to-perform-mouse-hover-in-selenium.html");
	}

	@Test
	public void openLinks() {
		String parentHandle = wd.getWindowHandle();
		// clicking on Selenium and verifying title
		action.moveToElement(wd.findElement(By.cssSelector("div.dropdown>button"))).perform();
		wDW.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.cssSelector("div.dropdown>div a:first-of-type")));
		wd.findElement(By.cssSelector("div.dropdown>div a:first-of-type")).click();
		Set<String> windowHandels = wd.getWindowHandles();
		String handle;
		for (String handles : windowHandels) {
			if (!handles.equals(parentHandle)) {
				handle = handles;
				wd.switchTo().window(handle);
			}
		}
		sA.assertEquals(wd.getTitle(), "Selenium Webdriver Tutorial - Selenium Tutorial for Beginners",
				"Wrong title/on Wrong Page");

		// Switch to home page
		wd.switchTo().window(parentHandle);

		// clicking on TestNG and verifying title
		wDW.until(ExpectedConditions.visibilityOf(wd.findElement(By.cssSelector("div.dropdown>button"))));
		action.moveToElement(wd.findElement(By.cssSelector("div.dropdown>button"))).perform();
		wDW.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.cssSelector("div.dropdown>div a:nth-of-type(2)")));
		wd.findElement(By.cssSelector("div.dropdown>div a:nth-of-type(2)")).click();
		sA.assertEquals(wd.getTitle(), "TestNG Tutorials for Selenium Webdriver with Real Time Examples",
				"You are on wrong page");
		wd.navigate().back();

		// clicking on Appium and verifying title
		wDW.until(ExpectedConditions.visibilityOf(wd.findElement(By.cssSelector("div.dropdown>button"))));
		action.moveToElement(wd.findElement(By.cssSelector("div.dropdown>button"))).perform();
		wDW.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.cssSelector("div.dropdown>div a:nth-of-type(3)")));
		wd.findElement(By.cssSelector("div.dropdown>div a:nth-of-type(3)")).click();
		sA.assertEquals(wd.getTitle(), "Complete Ultimate Appium tutorial for beginners using JAVA for Selenium",
				"You are on wrong page");
	}

	@AfterMethod
	public void tearDown() {
		wd.quit();
	}

}
