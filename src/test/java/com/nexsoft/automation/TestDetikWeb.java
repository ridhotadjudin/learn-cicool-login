package com.nexsoft.automation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)//membuat all membaca non static
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestDetikWeb {

	public WebDriver driver;
	
	
	@BeforeAll
	public void createDriver() {
		System.setProperty("webdriver.chrome.driver","D:\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
	}
	
	@Order(1)
	@DisplayName("Login Web with Username and Password ")
	@Test
	public void gotoWeb_andLogin_withUsername_withPassword() {
		driver.get("http://localhost/cicool");
//		String teks = driver.findElement(By.cssSelector("h1#homeHeading")).getText();
//		System.out.println(teks);
		driver.findElement(By.cssSelector("i.fa.fa-sign-in")).click();
//		driver.findElement(By.cssSelector("i.fa.fa-github")).click();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.cssSelector("input[placeholder='Email']")).clear();
		driver.findElement(By.cssSelector("input[placeholder='Email']")).sendKeys("ridhotadjudin@gmail.com");

		driver.findElement(By.cssSelector("input[placeholder='Password']")).clear();
		driver.findElement(By.cssSelector("input[placeholder='Password']")).sendKeys("123456");

		driver.findElement(By.cssSelector("button[type='submit']")).click();
		String username = driver.findElement(By.cssSelector("span[class='hidden-xs']")).getText();
			
		assertEquals("Ridho", username);	
//		driver.close();
	}
	
	@Disabled
	@DisplayName("Login Web with Username and Password Error")
	@Test
	public void gotoWeb_andLogin_withUsername_withPassword_error() {
		driver.get("http://localhost/cicool");
		driver.findElement(By.cssSelector("i.fa.fa-sign-in")).click();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.cssSelector("input[placeholder='Email']")).clear();
		driver.findElement(By.cssSelector("input[placeholder='Email']")).sendKeys("ridhotadjudin@gmail.com");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.cssSelector("input[placeholder='Password']")).clear();
		driver.findElement(By.cssSelector("input[placeholder='Password']")).sendKeys("123456");

		driver.findElement(By.cssSelector("button[type='submit']")).click();
		
		String message = driver.findElement(By.cssSelector("div[class='callout callout-error'] p")).getText();
		String expected = "E-mail, Username or Password do not match.";
		
		assertEquals(expected, message);
		
//		driver.close();
	}
	
	@Disabled
	@DisplayName("Login Web with Wrong Username and Password")
	@Test
	public void gotoWeb_andLogin_withUsername_withPassword_wrong() {
		driver.get("http://localhost/cicool/administrator/auth/logout");
		driver.findElement(By.xpath("//a[@class='page-scroll']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Email']")).clear();
		driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys("ridhotadjudin@gmail.com");
		driver.findElement(By.xpath("//input[@placeholder='Password']")).clear();
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("123");
		//
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		//
		String errorMsg = driver.findElement(By.xpath("//html/body/div[1]/div[2]/div/p")).getText();
//		String expected = "E-mail Address and Password do not match.";
		
		List<WebElement> error = driver.findElements(By.xpath("//html/body/div[1]/div[2]/div/p"));
		
		for (WebElement element : error) {
			System.out.println(element.getText());
		}

//		assertEquals(expected, errorMsg);
		
	}
	
	@Order(2)
	@DisplayName("Insert New Absensi")
	@Test
	public void gotoWeb_insertData() {
		driver.get("http://localhost/cicool/administrator/crud");
		driver.findElement(By.cssSelector("a.label-default")).click();
		driver.findElement(By.cssSelector("#btn_add_new")).click();

		driver.findElement(By.xpath("//input[@placeholder='Username']")).clear();
		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("kamu");

		driver.findElement(By.xpath("//input[@placeholder='Email']")).clear();
		driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys("kamu@gmail.com");

		driver.findElement(By.xpath("//input[@placeholder='Location']")).clear();
		driver.findElement(By.xpath("//input[@placeholder='Location']")).sendKeys("jakarta");
		//
		driver.findElement(By.xpath("/html/body/div[1]/div[1]/section[2]/div/div/div/div/div/form/div[5]/a[1]")).click();
//		driver.findElement(By.cssSelector("#btn_save")).click();
		
		driver.get("http://localhost/cicool/administrator/absensi");
		
//		List<WebElement> getNama = driver.findElements(By.xpath("/html/body/div[1]/div[1]/section[2]/div/div/div/div/div/form/div[2]/table/tbody/tr[2]/td[2]"));
//		List<WebElement> getNama = driver.findElements(By.xpath("/html/body/div[1]/div[1]/section[2]/div/div/div/div/div/form/div[2]/table/tbody/td[1]"));
		
		/// dari pak dewa
		List<WebElement> lstUsername = driver
	    		.findElements(By.xpath("/html[1]/body[1]/div[1]/div[1]/section[2]/div[1]/div[1]/div[1]/div[1]/div[1]/form[1]/div[2]/table[1]/tbody[1]/tr/td/span[@class='list_group-username']"));
//		List<WebElement> lstUsername = driver
//	    		.findElements(By.xpath("/html[1]/body[1]/div[1]/div[1]/section[2]/div[1]/div[1]/div[1]/div[1]/div[1]/form[1]/div[2]/table[1]/tbody[1]/tr/td/span[@class='list_group-email']"));

		
		//udah bener ini punya sendiri
		for (WebElement element : lstUsername) {
			System.out.println(element.getText());
		}
		
		System.out.println("Nama terakhir : "+lstUsername.get(lstUsername.size()-1).getText());
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

//		driver.get("http://localhost/cicool/administrator/auth/logout");
//		driver.close();
	}
	
	@Order(3)
	@DisplayName("Insert New Absensi Using CSV File")
	@ParameterizedTest
	@CsvFileSource(resources = "data.csv",delimiter=',',numLinesToSkip = 1)
	public void gotoWeb_insertData_fromCSV(String inStr1,String inStr2,String inStr3) {
		driver.get("http://localhost/cicool/administrator/crud");
		driver.findElement(By.cssSelector("a.label-default")).click();
		driver.findElement(By.cssSelector("#btn_add_new")).click();

		driver.findElement(By.xpath("//input[@placeholder='Username']")).clear();
		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys(inStr1);

		driver.findElement(By.xpath("//input[@placeholder='Email']")).clear();
		driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys(inStr2);

		driver.findElement(By.xpath("//input[@placeholder='Location']")).clear();
		driver.findElement(By.xpath("//input[@placeholder='Location']")).sendKeys(inStr3);
		//
		driver.findElement(By.xpath("/html/body/div[1]/div[1]/section[2]/div/div/div/div/div/form/div[5]/a[1]")).click();
//		driver.findElement(By.cssSelector("#btn_save")).click();
		
		driver.get("http://localhost/cicool/administrator/absensi");
	}
	
	
	
}
