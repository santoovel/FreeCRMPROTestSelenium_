package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class TasksPage extends TestBase{

	@FindBy(xpath="//td[contains(text(),'Tasks')]")
	WebElement tasksLabel;

	@FindBy(xpath="//input[@name='title' and @id='title']")
	WebElement titletasks;
	
	@FindBy(name="prospect_lookup")
	WebElement dealLookUpTasks;
	
	@FindBy(name="client_lookup")
	WebElement companyTasks;
	
	@FindBy(name="contact_lookup")
	WebElement primaryContact;
	
	@FindBy(xpath = "//input[@name='cs_keyword' and @type='text']")
	WebElement txtKeyword;
	
	@FindBy(xpath = "//input[@type='submit' and @name='cs_submit']")
	WebElement btnSearch;
	
	@FindBy(xpath = "//input[@type='submit' and @value='Save']")
	WebElement saveBtn;
	
	@FindBy(xpath="//table[@class='datacard'][2]//td[@class='datalistrow'][1]")
	WebElement tabledata;
	
	public TasksPage(){
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyTaskLabel(){
		return tasksLabel.isDisplayed();
	}
	
	public void createNewTask(String title,String deal,String company,String pricontact){    
		titletasks.sendKeys(title);
		dealLookUpTasks.sendKeys(deal);
		companyTasks.sendKeys(company);
		primaryContact.sendKeys(pricontact);
		saveBtn.click();
	}

	public void selectTasksByTaskTitle(String taskTitle){
		txtKeyword.sendKeys(taskTitle);
		btnSearch.click();
	}
	
	public void selectTasksByTaskNumber(String taskNumber){
		txtKeyword.sendKeys(taskNumber);
		btnSearch.click();
	}
	public String getBytaskNumberFromTable(){
		String taskNumber = tabledata.getText();
		return taskNumber;
	}
	
}
