package com.crm.qa.testcases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ClickGoogleLinks {
		
		@Test
		 public void seleniumLinkMethods() throws InterruptedException {
			WebDriverManager.chromedriver().setup();
			WebDriver driver = new ChromeDriver();
		  driver.get("https://www.google.co.in/");
		  Thread.sleep(3000);
		  
		  // Using tag name "a" , we get all the links
		  List<WebElement> links = driver.findElements(By.tagName("a"));
		  
		  // Get how many web links are present on a web page
		  int numberOfWebLinks = links.size();
		  
		  // Get the text from the links
		  for (WebElement link : links) {
		   System.out.println("The link is "+link.getText());
		  }
		  
		  // Using for loop
		  for (int i=0; i<numberOfWebLinks; i++) {
		   System.out.println(links.get(i).getText());
		   System.out.println(links.get(i).getAttribute("href"));
		  }
		  
		  // Using tag name "a" , we get all the links
		  List<WebElement> allLinks = driver.findElements(By.tagName("a"));
		  
		  // Click on a particular link and break it
		     for(WebElement singleLink : allLinks) {
		      if (singleLink.getText().equals("हिन्दी")) {
		       singleLink.click();
		       Thread.sleep(3000);
		       break;
		      }
		     }
		  
		  driver.close();    
		 }
	}


