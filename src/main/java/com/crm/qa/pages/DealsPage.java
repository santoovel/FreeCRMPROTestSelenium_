package com.crm.qa.pages;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.crm.qa.base.TestBase;

public class DealsPage extends TestBase{
	private String today;
	 
	@FindBy(xpath = "//td[contains(text(),'Deals')]")
	WebElement dealsLabel;
	
	@FindBy(id="title")
	////input[@name='title' and @id='title']
	WebElement titleDeals;
	
	@FindBy(name="client_lookup")
	WebElement companyDeals;
	
	@FindBy(name="contact_lookup")
	WebElement primaryContact;
	
	@FindBy(name="amount")
	WebElement amountDeals;
	
	@FindBy(name="identifier")
	WebElement identifier;
	
	@FindBy(xpath="//select[@name='type' and @size='1']")
	WebElement typeDeals;
	
	@FindBy(xpath="//select[@class='select' and @id='close_date_day']")
	WebElement dayCloseDateDeals;
	
	@FindBy(xpath="//select[@class='select' and @id='close_date_month']")
	WebElement monthCloseDateDeals;
	
	@FindBy(xpath="//select[@class='select' and @id='close_date_year']")
	WebElement yearCloseDateDeals;
	
	@FindBy(xpath="//img[@id='f_trigger_c_close_date']")
	WebElement precidedatepicker;
	
	@FindBy(xpath="//img[@id='f_trigger_c_actual_close_date']")
	WebElement actualdatepicker;
	
	
	
	@FindBy(xpath="//select[@name='source' and @size='1']")
	WebElement sourceDeals;
	
	@FindBy(xpath="//table[@class='datacard'][2]//td[@class='datalistrow'][1]")
	WebElement tabledata;
	
	
	@FindBy(xpath = "//input[@name='cs_keyword' and @type='text']")
	WebElement txtKeyword;
	
	@FindBy(xpath = "//input[@type='submit' and @name='cs_submit']")
	WebElement btnSearch;
	
	
	@FindBy(xpath = "//input[@type='submit' and @value='Save']")
	WebElement saveBtn;
	
	
	
	
	
	public DealsPage(){
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyDealsLabel(){
		return dealsLabel.isDisplayed();
	}
	
	public static void selectElementByValue(WebElement ele,String value) {
		Select sel = new Select(ele);
		sel.selectByValue(value);
	}
	
	public static void selectElementByVisibleText(WebElement ele,String option) {
		Select sel = new Select(ele);
		sel.selectByVisibleText(option);
	}
	
public void createNewDeal(String title, String company, String primContact, String amount, String identity, String dealsType, String source, String closeddate,String actualdate){    
		titleDeals.sendKeys(title);
		companyDeals.sendKeys(company);
		primaryContact.sendKeys(primContact);
		amountDeals.sendKeys(amount);
		identifier.sendKeys(identity);
		selectElementByValue(typeDeals,dealsType);
		sourceDeals.sendKeys(source);
		
		/*//Romita code
		precidedatepicker.click();
		List<WebElement> columns = driver.findElements(By.xpath("//div[@class='calendar']//table//tbody//tr//td"));
		
		String[] date = closeddate.split("/");
		String day = date[1];
		String month = date[0];
		String year = date[2];
		
		System.out.println("day from Excel is=======> " +day);
		System.out.println("day from Excel is=======> " +date[1]);
		
		for (WebElement cell : columns)
		{
			String getday = cell.getText();
			System.out.println("getday from Calendar is====================> " +getday);
			
			if (getday.equals(day)) 
			{
				cell.click();
				break;
			}
		}*/
		
	
		// selecting Precised Closed date 
		System.out.println("Precised Closed is from excel is ===========>"+ closeddate);
		precidedatepicker.click();
		List<WebElement> allDates=driver.findElements(By.xpath("//div[@class='calendar']//table//tbody//tr//td"));
		for(WebElement ele:allDates)
		{
			String date=ele.getText();
			System.out.println("ele.getText() from calendar is  ===========>>"+ date);
			if(date.equals(closeddate))
			{
				ele.click();
				break;
			}
		}
		
		 today = getCurrentDay();
	     System.out.println("Today's number: " + today + "\n");
		// selecting Actual Closed date 
		System.out.println("Actual Closed is from excel is ===========>"+ today);
		actualdatepicker.click();
		List<WebElement> actualclosedallDates=driver.findElements(By.xpath("(//div[@class='calendar'][2])//table//tbody//tr//td"));
		for(WebElement ele:actualclosedallDates)
		{
			String date=ele.getText();
			System.out.println("ele.getText() from calendar is  ===========>>"+ date);
			if(date.equals(today))
			{
				ele.click();
				break;
			}
		}
		
		//saveBtn.click();
	}
	
	public void selectDealsByDealNumber(String dealNumber){
		txtKeyword.sendKeys(dealNumber);
		btnSearch.click();
	}
	
	public String getByDealNumberFromTable(){
		String dealNumber = tabledata.getText();
		return dealNumber;
	}
	
	//Creating a custom function
			public static void highLighterMethod(WebDriver driver, WebElement element){
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
			}

			
			 //Get The Current Day
		    private String getCurrentDay (){
		        //Create a Calendar Object
		        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		 
		        //Get Current Day as a number
		        int todayInt = calendar.get(Calendar.DAY_OF_MONTH);
		        System.out.println("Today Int: " + todayInt +"\n");
		 
		        //Integer to String Conversion
		        String todayStr = Integer.toString(todayInt);
		        System.out.println("Today Str: " + todayStr + "\n");
		 
		        return todayStr;
		    }
			
}
