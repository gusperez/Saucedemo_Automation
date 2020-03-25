package PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import BaseFramework.BasePage;

public class OverviewPage extends BasePage{
	

	public OverviewPage() 
	{
		InitPageElements();
	}
	
	@FindBy(css="a.btn_action.cart_button")
	public WebElement finish_btn;
	
	@FindBy(xpath="//h3[@data-test='error']")
	public WebElement errorText;
	
	@FindBy(className="cart_list")
	public WebElement cart_list;
	
	public OverviewPage DoClick(WebElement element) 
	{
		ClickElement(element);
		return this;
    }
	
	public String GetPageUrl() 
	{
		return GetCurrentUrl();
	}
	
	public String SearchElement(WebElement element, String ChildXpath, String ElementToSelect) 
	{
		String product = SearchElementFromList(element, ChildXpath, ElementToSelect);
		return product;
	}

}
