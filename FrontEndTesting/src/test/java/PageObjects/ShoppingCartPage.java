package PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import BaseFramework.BasePage;

public class ShoppingCartPage extends BasePage{
	
	public ShoppingCartPage() 
	{
		InitPageElements();
	}
	
	@FindBy(className="cart_list")
	public WebElement cart_list;
	
	@FindBy(css="a.btn_action.checkout_button")
	public WebElement checkoutBtn;
	
	public String GetPageUrl() 
	{
		return GetCurrentUrl();
	}
	
	public ShoppingCartPage _GetListElements(WebElement element, String ChildXpath)
	{
		GetListElements(element, ChildXpath);
		return this;
	}
	public ShoppingCartPage DoClick(WebElement element) 
	{
		ClickElement(element);
		return this;
    }

	public String SearchElement(WebElement element, String ChildXpath, String ElementToSelect) 
	{
		String product = SearchElementFromList(element, ChildXpath, ElementToSelect);
		return product;
	}
	
	public ShoppingCartPage DoSubmit(WebElement element) 
	{
		SubmitElement(element);
		return this;
	}
}
