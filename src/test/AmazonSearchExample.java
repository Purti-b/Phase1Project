package test;


import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class AmazonSearchExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);

		WebElement search = driver.findElement(By.id("twotabsearchtextbox"));
		search.sendKeys("samsung");

		WebElement go = driver.findElement(By.id("nav-search-submit-button"));
		go.click();

		List<WebElement> allProduct = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']//h2/a"));
		List<WebElement> allProductPrice = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']//span[@class='a-price']"));


		for(int i=0; i<allProduct.size();i++) {
			System.out.println("Product Name and price: " + allProduct.get(i).getText() + " " + allProductPrice.get(i).getText());
		}

		allProduct.get(0).click();

		String parentWin = driver.getWindowHandle();
		String ExpectedTitle = allProduct.get(0).getText();		

		Set<String> allwin = driver.getWindowHandles();

		for(String win : allwin) {

			if(!win.equals(parentWin))
			{
				driver.switchTo().window(win);

			}
		}
		WebElement actualTitle = driver.findElement(By.id("productTitle"));
		String title = actualTitle.getText();

		if(title.equals(ExpectedTitle)) {
			System.out.println("TC Passed: Title is Matching");
		}
		else {
			System.out.println("TC Failed: Title is not Matching");
		}


		driver.quit();
	}

}