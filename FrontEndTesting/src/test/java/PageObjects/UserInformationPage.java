package PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import BaseFramework.BasePage;

public class UserInformationPage extends BasePage{
	
	public UserInformationPage() 
	{
		InitPageElements();
	}
	
	@FindBy(css="input.btn_primary.cart_button")
	public WebElement continue_btn;
	
	@FindBy(xpath="//h3[@data-test='error']")
	public WebElement errorText;
	
	@FindBy(id="first-name")
	public WebElement firstNameBox;
	
	@FindBy(id="last-name")
	public WebElement lastNameBox;
	
	@FindBy(id="postal-code")
	public WebElement zipBox;
	
	
	public UserInformationPage DoClick(WebElement element) 
	{
		ClickElement(element);
		return this;
    }
	
	public UserInformationPage DoEnterText(WebElement element, String Text) 
	{
		EnterText(element, Text);
		return this;
	}
	

}
