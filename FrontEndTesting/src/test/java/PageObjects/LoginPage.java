package PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import BaseFramework.BasePage;
import TestInitializer.TextContext;

public class LoginPage extends BasePage{
	
	public LoginPage() 
	{
		InitBrowser();
		InitPageElements();
	}

	@FindBy(id="user-name")
	public WebElement usernameBox;
	
	@FindBy(id="password")
	public WebElement passwordBox;
	
	@FindBy(className="btn_action")
	public WebElement loginBtn;
	
	@FindBy(xpath="//h3[@data-test='error']")
	public WebElement errorText;
	
	//******  Methods (Element Actions) ******************
	
	public LoginPage GoTo() 
	{
		GoTo(TextContext.GetUrl());
		return this;
	}
	
	
	public LoginPage DoEnterText(WebElement element, String Text) 
	{
		EnterText(element, Text);
		return this;
	}
	
	public LoginPage DoSubmit(WebElement element) 
	{
		SubmitElement(element);
		return this;
	}
	
	public String GetPageUrl() 
	{
		return GetCurrentUrl();
	}
	
	public LoginPage QuitDriver() 
	{
		Quit();
		return this;
	}
	
}
