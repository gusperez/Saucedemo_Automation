package PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import BaseFramework.BasePage;

public class InventoryItemPage extends BasePage{
	
	public InventoryItemPage() 
	{
		InitPageElements();
	}
	
	@FindBy(xpath="//button[@class='btn_primary btn_inventory']")
	public WebElement AddToCartBtn;
	
	@FindBy(xpath="//button[@class='inventory_details_back_button']")
	public WebElement BackBtn;
	
	@FindBy(id="shopping_cart_container")
	public WebElement shoppingCart;
	
	public InventoryItemPage DoClick(WebElement element) 
	{
		ClickElement(element);
		return this;
    }

}
