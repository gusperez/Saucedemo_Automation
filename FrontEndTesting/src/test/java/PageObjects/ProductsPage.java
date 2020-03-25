package PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import BaseFramework.BasePage;

public class ProductsPage extends BasePage{

	public ProductsPage() 
	{
		InitPageElements();
	}
	

	@FindBy(className="bm-burger-button")
	public WebElement burgerBtn;
	
	@FindBy(id="logout_sidebar_link")
	public WebElement logoutLnk;
	
	@FindBy(id="reset_sidebar_link")
	public WebElement resetlnk;
	
	@FindBy(id="shopping_cart_container")
	public WebElement shoppingCart;
	
	@FindBy(className="inventory_container")
	public WebElement inventory;
	
	@FindBy(className="inventory_list")
	public WebElement inventory_list;
	
	
	public ProductsPage DoClick(WebElement element) 
	{
		ClickElement(element);
		return this;
    }
	
	public ProductsPage _GetListElements(WebElement element, String ChildXpath)
	{
		GetListElements(element, ChildXpath);
		return this;
	}
	
	public ProductsPage AddProductFromList(WebElement element, String ChildXpath, String ElementToSelect) 
	{
		SelectFromList(element, ChildXpath, ElementToSelect);
		return this;
	}
	
	public WebElement FindElement(String Type, String Locator)
	{
	    return FindMyElement (Type, Locator);
	    
	}
	
	public ProductsPage AddProductsToCart(WebElement element, String ChildXpath, String ElementToSelect, String ButtonXpath) 
	{
		AddToCart(element, ChildXpath, ElementToSelect, ButtonXpath);
		return this;
	}
	
	public String GetPageUrl() 
	{
		return GetCurrentUrl();
	}
	
}
