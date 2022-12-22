package com.KuldeepSingh.AssignmentSelenium;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class PurchaseFromDemoblaze {
	WebDriver wd;
	WebDriverWait wDW;
	SoftAssert sA;

	@BeforeMethod
	public void setup() {
		System.setProperty("webdriver.edge.driver",
				"C:\\Users\\deepg\\Downloads\\Setup\\edgedriver_win64\\msedgedriver.exe");
		wd = new EdgeDriver();
		wDW = new WebDriverWait(wd, Duration.ofSeconds(10));
		sA = new SoftAssert();
		wd.get("https://www.demoblaze.com/index.html");
	}

	@Test
	public void purchaseItem() {
		sA.assertEquals(wd.getTitle(), "STORE", "You are on wrong page");
		wDW.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#tbodyid>div:nth-of-type(5)>div>div a")));
		wd.findElement(By.cssSelector("#tbodyid>div:nth-of-type(5)>div>div a")).click();
		wDW.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2")));
		WebElement productName = wd.findElement(By.cssSelector("h2"));
		WebElement productPrice = wd.findElement(By.cssSelector("h3"));
		sA.assertEquals(productName.getText(), "Iphone 6 32gb", "Wrong product Selected");
		sA.assertEquals(productPrice.getText(), "$790 *includes tax", "Wrong Product Selected");
		wd.findElement(By.cssSelector("div.row:nth-of-type(2) a")).click();
		wDW.until(ExpectedConditions.alertIsPresent());
		wd.switchTo().alert().accept();
		wd.findElement(By.cssSelector("div.container>div>ul>li:nth-of-type(4) a")).click();
		wDW.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#tbodyid>tr>td:nth-of-type(2)")));
		WebElement productcheckoutName = wd.findElement(By.cssSelector("#tbodyid>tr>td:nth-of-type(2)"));
		WebElement productcheckoutPrice = wd.findElement(By.cssSelector("#tbodyid>tr>td:nth-of-type(3)"));
		WebElement totalPrice = wd.findElement(By.cssSelector("h3"));
		sA.assertEquals((productcheckoutName.getText() + productcheckoutPrice.getText() + totalPrice.getText()),
				"Iphone 6 32gb790790");
		wd.findElement(By.cssSelector("button.btn.btn-success")).click();
		wDW.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#name")));
		wd.findElement(By.cssSelector("#name")).sendKeys("Kuldeep Singh");
		wd.findElement(By.cssSelector("#country")).sendKeys("Canada");
		wd.findElement(By.cssSelector("#city")).sendKeys("Brampton");
		wd.findElement(By.cssSelector("#card")).sendKeys("453737871234589");
		wd.findElement(By.cssSelector("#month")).sendKeys("04");
		wd.findElement(By.cssSelector("#year")).sendKeys("2024");
		wd.findElement(By.cssSelector("button[onclick='purchaseOrder()']")).click();
		wDW.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("div.sweet-alert.showSweetAlert.visible>h2")));
		WebElement text1 = wd.findElement(By.cssSelector("div.sweet-alert.showSweetAlert.visible>h2"));
		WebElement actualDisplayedMsg = wd.findElement(By.cssSelector("div.sweet-alert.showSweetAlert.visible>p"));
		boolean isActualMatch = (actualDisplayedMsg.getText())
				.contains("Amount: 790 USD\n" + "Card Number: 453737871234589\n" + "Name: Kuldeep Singh");
		sA.assertTrue(isActualMatch);
		sA.assertEquals(text1.getText(), "Thank you for your purchase!");
		wd.findElement(By.cssSelector("button.confirm.btn.btn-lg.btn-primary")).click();
		sA.assertEquals(wd.getTitle(), "STORE", "You are on wrong page");
		sA.assertAll();
	}

	@AfterMethod
	public void tearDown() {
		wd.quit();
	}

}
