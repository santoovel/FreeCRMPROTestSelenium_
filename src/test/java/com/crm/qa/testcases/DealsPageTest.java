/*
 * @author Naveen Khunteta
 * 
 */

package com.crm.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.DealsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class DealsPageTest extends TestBase{

	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	DealsPage dealspage;
	
	String sheetName = "deals";
	
	   
	public DealsPageTest(){
			super();	
	}
	
	
	@BeforeMethod
	public void setUp() {
		initialization();
		testUtil = new TestUtil();
		loginPage = new LoginPage();
		contactsPage = new ContactsPage();
		dealspage= new DealsPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		testUtil.switchToFrame();
		//dealspage = homePage.clickOnDealsLink();
	}
		/*@Test(priority=1)
	public void verifyDealsPageLabel(){
		Assert.assertTrue(dealspage.verifyDealsLabel(), "deals label is missing on the page");
	}
*/
	@DataProvider
	public Object[][] getCRMTestData(){
		Object[][] data = TestUtil.getTestData(sheetName);
		return data;
	}
	
	
	
	/*@Test
	public void validateCreateNewDeal()
	{
		homePage.clickOnNewDealsLink();
		WebElement day=driver.findElement(By.xpath("//select[@class='select' and @id='close_date_day']"));
		day.click();
		highLighterMethod(driver,day);
		
		Select sel = new Select(day);
		sel.selectByVisibleText("2");
	
		
	}*/
	
	
	

	@Test(dataProvider="getCRMTestData")
	public void validateCreateNewDeal(String title, String companydeals,String pricontact, String amount, String identifierdeals,String dealsType, String source, String closeddate, String actualdate){		
		homePage.clickOnNewDealsLink();
		dealspage.createNewDeal(title, companydeals, pricontact, amount,identifierdeals,dealsType,source,closeddate,actualdate);
	}
	
	/*@Test
	@Parameters({"dealsNumber"})
	public void selectDealsByDealNumber(String dealsNumber) throws Exception{
		dealspage.selectDealsByDealNumber(dealsNumber);
		Thread.sleep(5000);
		System.out.println("Deals Number from table is "+dealspage.getByDealNumberFromTable());
		Assert.assertEquals(dealsNumber,dealspage.getByDealNumberFromTable(),"DealNumber doesn't exist in DealsTable" );
	}*/
	
	@AfterMethod
	public void tearDown(){
		//driver.quit();
	}
	
	
	
	
}
