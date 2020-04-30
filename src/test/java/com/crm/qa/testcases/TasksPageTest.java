package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.pages.TasksPage;
import com.crm.qa.util.TestUtil;
import com.gargoylesoftware.htmlunit.javascript.host.media.presentation.PresentationConnection;

public class TasksPageTest extends TestBase{
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	TasksPage taskPage;

	String sheetName = "tasks";

	public TasksPageTest(){
		super();
	}

	@BeforeMethod
	public void setUp() {
		initialization();
		testUtil = new TestUtil();
		loginPage = new LoginPage();
		taskPage= new TasksPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		testUtil.switchToFrame();
		taskPage = homePage.clickOnTasksLink();
	}

	@Test(priority=1)
	public void verifyTasksPageLabel(){
		Assert.assertTrue(taskPage.verifyTaskLabel(), "Tasks label is missing on the page");
	}

	@DataProvider
	public Object[][] getCRMTestData(){
		Object[][] data = TestUtil.getTestData(sheetName);
		return data;
	}

	@Test(priority=2,dataProvider="getCRMTestData")
	public void validateCreateNewTask(String title, String deal,String company,String pricontact){		
		homePage.clickOnNewTaskLink();
		taskPage.createNewTask(title,deal,company,pricontact);
	}

	@Test(priority=3)
	@Parameters({"taskTitle","tasksNumber"})
	public void selecttasksByTasksNumber(String taskTitle,String tasksNumber) throws Exception{
		
		//taskPage.selectTasksByTaskTitle(taskTitle);
		Thread.sleep(5000);
		System.out.println("Task Number From Table is ===========>>>"+taskPage.getBytaskNumberFromTable());
		Assert.assertEquals(tasksNumber,taskPage.getBytaskNumberFromTable(),"**********  TaskNumber doesn't exist in Tasks Table ***********" );
	}
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}



}
