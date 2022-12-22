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

public class Q3MultipleTabs {
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
		wd.get("http://seleniumpractise.blogspot.com/2017/07/multiple-window-examples.html");
	}

	@Test
	public void openLinks() {
		sA.assertEquals(wd.findElement(By.cssSelector("div.post-outer h3")).getText(), "Multiple window examples",
				"Wrong webPage");
		String parentWindowHandle = wd.getWindowHandle();
		wd.findElement(By.cssSelector("div.post-body.entry-content>a:first-of-type")).click();
		// Finding the window handle for google
		// verifying google page title and switching back to parent page
		Set<String> windowHandles = wd.getWindowHandles();
		String googleHandle = null;
		for (String handel : windowHandles) {
			if (!handel.equals(parentWindowHandle)) {
				googleHandle = handel;
				wd.switchTo().window(googleHandle);
			}
		}
		sA.assertEquals(wd.getTitle(), "Google", "Wrong Page");
		wd.switchTo().window(parentWindowHandle);
		wDW.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("div.post-body.entry-content>a:nth-of-type(2)")));
		wd.findElement(By.cssSelector("div.post-body.entry-content>a:nth-of-type(2)")).click();
		//// Finding the window handle for facebook
		// verifying facebook page title and switching back to parent page

		Set<String> windowHandlesUpd = wd.getWindowHandles();
		String facebookHandle = null;
		for (String handel : windowHandlesUpd) {
			if (!handel.equals(parentWindowHandle) || !handel.equals(googleHandle)) {
				facebookHandle = handel;
				wd.switchTo().window(facebookHandle);
			}
		}
		sA.assertEquals(wd.getTitle(), "Facebook - log in or sign up", "Wrong Page");
		wd.switchTo().window(parentWindowHandle);
		wDW.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("div.post-body.entry-content>a:nth-of-type(3)")));
		wd.findElement(By.cssSelector("div.post-body.entry-content>a:nth-of-type(3)")).click();

		// Finding the window handle for Yahoo
		// verifying Yahoo page title and switching back to parent page
		Set<String> windowHandlesUpdate = wd.getWindowHandles();
		String yahooHandle = null;
		for (String handle : windowHandlesUpdate) {
			if (!handle.equals(parentWindowHandle) || !handle.equals(googleHandle) || !handle.equals(facebookHandle)) {
				yahooHandle = handle;
				wd.switchTo().window(yahooHandle);
			}
		}		
		sA.assertEquals(wd.getTitle(),
				"Yahoo | Mail, Weather, Search, News, Finance, Sports, Shopping, Entertainment, Video", "Wrong Page");
		wd.switchTo().window(parentWindowHandle);
		wd.findElement(By.cssSelector("div.post-body.entry-content>a:nth-of-type(4)")).click();
		Set<String> windowHandlesUpdates = wd.getWindowHandles();
		for (String string : windowHandlesUpdates) {
			System.out.println(string);
		}
		System.out.println("parentWindowHandle=" + parentWindowHandle + "\ngoogleHandle=" + googleHandle
				+ "\nfacebookHandle=" + facebookHandle + "\nyahooHandle=" + yahooHandle);
		sA.assertEquals(wd.getTitle(), "Facebook - log in or sign up", "Wrong Page");
		sA.assertAll();
	}

	@AfterMethod
	public void tearDown() {
		wd.quit();
	}

}
