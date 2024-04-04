package com.Orange;

import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class OrangeHRM {

	public String baseUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
	public WebDriver driver;

	@BeforeTest
	public void setup() {
		System.out.println("Before Test");
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	// LOGIN VALID CREDENTIALS
	@Test(priority = 2)
	public void loginTestWthvalidData() {

		driver.findElement(By.name("username")).sendKeys("admin");
		driver.findElement(By.name("password")).sendKeys("admin123");

		driver.findElement(By.tagName("button")).click();

		String pageTitle = driver.getTitle();
		if (pageTitle.equals("OrangeHRM")) {
			System.out.println("Login Success");
		} else {
			System.out.println("Unsuccessful");
		}
	}

	@Test(priority = 1)
	// LOGIN INVALID CREDENTIALS
	public void loginWithInvalidData() throws InterruptedException {
		driver.findElement(By.name("username")).sendKeys("admin");
		driver.findElement(By.name("password")).sendKeys("admin1232");

		driver.findElement(By.tagName("button")).click();

		String message_expected = "Invalid credentials";
		String message_actual = driver.findElement(By.xpath("//p[text()='Invalid credentials']")).getText();
		Assert.assertTrue(message_actual.contains(message_expected));
		Thread.sleep(2000);

	}

	//ADD EMPLOYEE
	@Test(priority = 3)
	public void addEmployee() throws InterruptedException {

		driver.findElement(By.xpath("//a[.='PIM']")).click();
		Thread.sleep(3000);

		driver.findElement(By.xpath("//a[.='Add Employee']")).click();
		Thread.sleep(3000);


		//enter FirstName
		WebElement first = driver.findElement(By.name("firstName"));
		first.clear();
		first.sendKeys("alpha");

		//Enter MiddleName
		WebElement middle = driver.findElement(By.name("middleName"));
		middle.clear();
		middle.sendKeys("beta");

		//Enter LastName
		WebElement last = driver.findElement(By.name("lastName"));
		last.clear();
		last.sendKeys("gama");


		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[.=' Save ']")).click();
		
		Thread.sleep(3000);
		String confirmMessage = driver.findElement(By.xpath("//h6[.='Personal Details']")).getText();
		
		if(confirmMessage.contains("Personal Details")) {
			System.out.println("Employee Added");
		}
		else
		{
			System.out.println("Employee Added Failed");
		}

	}

/*	
	@AfterTest
	public void terDown() throws InterruptedException {
		Thread.sleep(5000);
		driver.close();
		driver.quit();
	}
*/

}
