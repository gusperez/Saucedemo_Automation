package TestCases;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import BaseFramework.BaseJavaScript;
import MainContext.FrameworkContext;
import PageObjects.ConfirmationPage;
import PageObjects.InventoryItemPage;
import PageObjects.LoginPage;
import PageObjects.OverviewPage;
import PageObjects.ProductsPage;
import PageObjects.ShoppingCartPage;
import PageObjects.UserInformationPage;

import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class FrontEndTesting {
	
	LoginPage loginPage;
	ProductsPage productsPage;
	ShoppingCartPage shoppingCartPage;
	InventoryItemPage inventoryItemPage;
	UserInformationPage userInformationPage;
	OverviewPage overviewPage;
	ConfirmationPage confirmationPage;
	BaseJavaScript baseJavaScript;

	String currenturl;
	
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	
	/*-----------------------
	   * Data Driver from JSON file
     -----------------------*/
	  String username = FrameworkContext.GetJsonString("UserName");
	  String password = FrameworkContext.GetJsonString("Password");
	  String productsPageUrl = FrameworkContext.GetJsonString("ProductsPageUrl");
	  
	  String usernameinvalid = FrameworkContext.GetJsonString("UserNameInvalid");
	  
	  String errorMessage = FrameworkContext.GetJsonString("ErrorMessage");
	  String loginPageUrl = FrameworkContext.GetJsonString("LoginPageUrl");
	  String shoppingCartPageUrl = FrameworkContext.GetJsonString("ShoppingCartPage");
	  String productname_ChildXpath = FrameworkContext.GetJsonString("ProductName_ChildXpath");
	  
	  String productname_1 = FrameworkContext.GetJsonString("Product_1");
	  String productname_2 = FrameworkContext.GetJsonString("Product_2");
	  String productname_3 = FrameworkContext.GetJsonString("Product_3");
	  String productname_4 = FrameworkContext.GetJsonString("Product_4");
	  String productname_5 = FrameworkContext.GetJsonString("Product_5");
	  String productname_6 = FrameworkContext.GetJsonString("Product_6");
	  
	  String addtocartbtn_ChildXpath = FrameworkContext.GetJsonString("AddToCartButton_ChildXpath");
	  
	  String firstname_errormsg = FrameworkContext.GetJsonString("FirstName_ErrorMsg");
	  
	  String firstname = FrameworkContext.GetJsonString("FirstName");
	  String lastname = FrameworkContext.GetJsonString("LastName");
	  String zip = FrameworkContext.GetJsonString("Zip");
	  String overviewPageUrl = FrameworkContext.GetJsonString("OverviewPageUrl");
	  
	  String confirmationPageUrl = FrameworkContext.GetJsonString("ConfirmationPageUrl");
	
  @BeforeTest
  public void beforeTest() {
	  loginPage = new LoginPage ();
	  productsPage = new ProductsPage ();
	  shoppingCartPage = new ShoppingCartPage ();
	  inventoryItemPage = new InventoryItemPage ();
	  userInformationPage = new UserInformationPage ();
	  overviewPage = new OverviewPage ();
	  confirmationPage = new ConfirmationPage();
	  baseJavaScript = new BaseJavaScript();
	  
      //Extent Report
	  htmlReporter = new ExtentHtmlReporter("extent.html");
	  extent = new ExtentReports();
	  extent.attachReporter(htmlReporter);  
  }
  
  @Test(priority=1)
  public void LoginValidUser() {
	  
	  	ExtentTest test = extent.createTest("Scenario #1: Login with a valid user");
		
		  /*-----------------
		    * Login with a valid user
		    ----------------*/
		  loginPage
		  //Hit saucedemo page
		  .GoTo()
		  .DoEnterText(loginPage.usernameBox, username)
		  .DoEnterText(loginPage.passwordBox, password)
		  .DoSubmit(loginPage.loginBtn);
		  
		  //Validate the user navigates to the product's page
		  try {
			  Assert.assertEquals(productsPage.GetPageUrl(),productsPageUrl);
		  
			  test.log(Status.PASS, "Pass");
			  test.pass("Navigated to page: "+productsPage.GetPageUrl());
		  }
		  
		  catch (AssertionError e){
			  test.log(Status.FAIL, "Fail");
			  test.fail("Navigated to unexpected page: "+productsPage.GetPageUrl());
		  }
  }
  
  @Test(priority=2)
  public void LoginInvalidUser() {
	  
	      ExtentTest test = extent.createTest("Scenario #2: Login with an invalid user");
	  
		  /*-----------------
		    * Login with a valid user
		    ----------------*/
		  loginPage
		  .GoTo()
		  .DoEnterText(loginPage.usernameBox, usernameinvalid)
		  .DoEnterText(loginPage.passwordBox, password)
		  .DoSubmit(loginPage.loginBtn);
		  
		  //Validate error message is displayed"
		  String errorMsg = loginPage.errorText.getText();
		  
		  try {
			  Assert.assertEquals(errorMsg,errorMessage);
		  
			  test.log(Status.PASS, "Pass");
			  test.pass("Error message is displayed: "+errorMsg);
		  }
		  
		  catch (AssertionError e){
			  test.log(Status.FAIL, "Fail");
			  test.fail("Unexpected error message is displayed: "+errorMsg);
		  }
  }
  
  @Test(priority=3)
  public void LogoutFromProductPage() throws InterruptedException {
	  
	  	  ExtentTest test = extent.createTest("Scenario #3: Logout from product's page");
	  
		  /*-----------------
		    * Login with a valid user
		    ----------------*/
		  loginPage
		  .GoTo()
		  .DoEnterText(loginPage.usernameBox, username)
		  .DoEnterText(loginPage.passwordBox, password)
		  .DoSubmit(loginPage.loginBtn);
		  
		  productsPage
		  .DoClick(productsPage.burgerBtn);
		  TimeUnit.SECONDS.sleep(3);
		  productsPage
		  .DoClick(productsPage.logoutLnk);
		  TimeUnit.SECONDS.sleep(3);
		  
		  //Validate the user navigates to the login page. 
		  try {
			  Assert.assertEquals(loginPage.GetPageUrl(),loginPageUrl);
		  
			  test.log(Status.PASS, "Pass");
			  test.pass("Navigated to page: "+loginPage.GetPageUrl());
		  }
		  
		  catch (AssertionError e){
			  test.log(Status.FAIL, "Fail");
			  test.fail("Navigated to unexpected page: "+loginPage.GetPageUrl());
		  }
  }
  
  @Test(priority=4)
  public void NavigateToShoppingCart() throws InterruptedException {
	  
	      ExtentTest test = extent.createTest("Scenario #4: Navigate to the shopping cart");
	  
		  /*-----------------
		    * Login with a valid user
		    ----------------*/
		  loginPage
		  .GoTo()
		  .DoEnterText(loginPage.usernameBox, username)
		  .DoEnterText(loginPage.passwordBox, password)
		  .DoSubmit(loginPage.loginBtn);
		  TimeUnit.SECONDS.sleep(3);
		  productsPage
		  .DoClick(productsPage.shoppingCart);
		  TimeUnit.SECONDS.sleep(3);
		  
		  //Validate the user navigates to the login page.
		  try {
			  Assert.assertEquals(shoppingCartPage.GetPageUrl(),shoppingCartPageUrl);
		  
			  test.log(Status.PASS, "Pass");
			  test.pass("Navigated to page: "+shoppingCartPage.GetPageUrl());
		  }
		  
		  catch (AssertionError e){
			  test.log(Status.FAIL, "Fail");
			  test.fail("Navigated to unexpected page: "+shoppingCartPage.GetPageUrl());
		  }
  }
  
  @Test(priority=5)
  public void AddItemToShoppingCart() throws InterruptedException {
	  
	      ExtentTest test = extent.createTest("Scenario #5: Add a single item to the shopping cart");
	  
		  /*-----------------
		    * Login with a valid user
		    ----------------*/
		  loginPage
		  .GoTo()
		  .DoEnterText(loginPage.usernameBox, username)
		  .DoEnterText(loginPage.passwordBox, password)
		  .DoSubmit(loginPage.loginBtn);
		  TimeUnit.SECONDS.sleep(3);
		  
		  /*-----------------
		    * Add a single item to the shopping cart by clicking on the product Name
		    ----------------*/
		  productsPage
		  .AddProductFromList(productsPage.inventory,productname_ChildXpath,productname_1);
		  TimeUnit.SECONDS.sleep(3);
		  inventoryItemPage
		  .DoClick(inventoryItemPage.AddToCartBtn)
		  .DoClick(inventoryItemPage.shoppingCart);
		  
		  //Validate the item has been added to the shopping cart.
		  String searchProductByName = shoppingCartPage.SearchElement(shoppingCartPage.cart_list,productname_ChildXpath,productname_1);
		  
		  try {
			  Assert.assertEquals(searchProductByName,productname_1); 
		  
			  test.log(Status.PASS, "Pass");
			  test.pass("Item added on Product Page: "+productname_1+" / "+ "Item on Shopping Cart: "+searchProductByName);
		  }
		  
		  catch (AssertionError e){
			  test.log(Status.FAIL, "Fail");
			  test.fail("Item '"+productname_1+"' was not found on Shopping Cart");
		  }
		  
		  //Resert app state
		  productsPage
		  .DoClick(productsPage.burgerBtn)
		  .DoClick(productsPage.resetlnk);
  }
  
  @Test(priority=6)
  public void AddItemsToShoppingCart() throws InterruptedException {
	  
	      ExtentTest test = extent.createTest("Scenario #6: Add multiple items to the shopping cart");
	  
		  /*-----------------
		    * Login with a valid user
		    ----------------*/
		  loginPage
		  .GoTo()
		  .DoEnterText(loginPage.usernameBox, username)
		  .DoEnterText(loginPage.passwordBox, password)
		  .DoSubmit(loginPage.loginBtn);
		  TimeUnit.SECONDS.sleep(5);
		  
		  /*-----------------
		    * Add multiple items to the shopping cart by clicking on ADD TO CART button
		    ----------------*/
		  productsPage
		  .AddProductsToCart(productsPage.inventory, productname_ChildXpath, productname_1, addtocartbtn_ChildXpath)
		  .AddProductsToCart(productsPage.inventory, productname_ChildXpath, productname_2, addtocartbtn_ChildXpath)
		  .AddProductsToCart(productsPage.inventory, productname_ChildXpath, productname_3, addtocartbtn_ChildXpath);
		  TimeUnit.SECONDS.sleep(3);
		  productsPage.DoClick(productsPage.shoppingCart);
		  TimeUnit.SECONDS.sleep(3);

		  //Validate all the items that have been added to the shopping cart.
		  String searchProductByName1 = shoppingCartPage.SearchElement(shoppingCartPage.cart_list,productname_ChildXpath,productname_1);
		  String searchProductByName2 = shoppingCartPage.SearchElement(shoppingCartPage.cart_list,productname_ChildXpath,productname_2);
		  String searchProductByName3 = shoppingCartPage.SearchElement(shoppingCartPage.cart_list,productname_ChildXpath,productname_3);
		  
		  try {
			  Assert.assertEquals(searchProductByName1,productname_1); 
			  Assert.assertEquals(searchProductByName2,productname_2); 
			  Assert.assertEquals(searchProductByName3,productname_3);
		  
			  test.log(Status.PASS, "Pass");
			  test.pass("All items were added to the shopping cart");
		  }
		  
		  catch (AssertionError e){
			  test.log(Status.FAIL, "Fail");
			  test.fail("Not all the items were added to the shopping cart");
		  }	   	  
		  
		  //Resert app state
		  productsPage
		  .DoClick(productsPage.burgerBtn)
		  .DoClick(productsPage.resetlnk);
  }
  
  @Test(priority=7)
  public void UserInformationInvalid() throws InterruptedException {
	  
	      ExtentTest test = extent.createTest("Scenario #7: Continue with missing mail information");
	  
		  /*-----------------
		    * Login with a valid user
		    ----------------*/
		  loginPage
		  .GoTo()
		  .DoEnterText(loginPage.usernameBox, username)
		  .DoEnterText(loginPage.passwordBox, password)
		  .DoSubmit(loginPage.loginBtn);
		  TimeUnit.SECONDS.sleep(3);
		  
		  /*-----------------
		    * Add a single item to the shopping cart by clicking on ADD TO CART button
		    ----------------*/
		  productsPage
		  .AddProductsToCart(productsPage.inventory, productname_ChildXpath, productname_1, addtocartbtn_ChildXpath);
		  TimeUnit.SECONDS.sleep(3);
		  productsPage.DoClick(productsPage.shoppingCart);
		  TimeUnit.SECONDS.sleep(3);
		  
		  /*-----------------
		    * Continue with missing mail information
		    ----------------*/
		  shoppingCartPage.DoClick(shoppingCartPage.checkoutBtn);
		  userInformationPage.DoClick(userInformationPage.continue_btn);
		
		  //Validate error message is displayed on the user's information page"
		  String errorMsg = userInformationPage.errorText.getText();
		  
		  try {
			  Assert.assertEquals(errorMsg,firstname_errormsg); 
		
			  test.log(Status.PASS, "Pass");
			  test.pass("Expected error message '"+errorMsg+"' was displayed" );
		  }
		  
		  catch (AssertionError e){
			  test.log(Status.FAIL, "Fail");
			  test.fail("Unexpected error message was displayed: "+errorMsg);
		  }
		  
		  //Resert app state
		  productsPage
		  .DoClick(productsPage.burgerBtn)
		  .DoClick(productsPage.resetlnk);
  }
  
  @Test(priority=8)
  public void FillUserInformation() throws InterruptedException { 
	  
	      ExtentTest test = extent.createTest("Scenario #8: Fill user's information");
		  
		  /*-----------------
		    * Login with a valid user
		    ----------------*/
		  loginPage
		  .GoTo()
		  .DoEnterText(loginPage.usernameBox, username)
		  .DoEnterText(loginPage.passwordBox, password)
		  .DoSubmit(loginPage.loginBtn);
		  TimeUnit.SECONDS.sleep(2);
		  
		  /*-----------------
		    * Add a single item to the shopping cart by clicking on ADD TO CART button
		    ----------------*/
		  productsPage
		  .AddProductsToCart(productsPage.inventory, productname_ChildXpath, productname_1, addtocartbtn_ChildXpath);
		  TimeUnit.SECONDS.sleep(2);
		  productsPage.DoClick(productsPage.shoppingCart);
		  TimeUnit.SECONDS.sleep(2);
		  shoppingCartPage.DoClick(shoppingCartPage.checkoutBtn);
		  
		  /*-----------------
		    * Fill user's information
		    ----------------*/
		  userInformationPage
		  .DoEnterText(userInformationPage.firstNameBox, firstname)
		  .DoEnterText(userInformationPage.lastNameBox, lastname)
		  .DoEnterText(userInformationPage.zipBox, zip)
		  .DoClick(userInformationPage.continue_btn);
		  TimeUnit.SECONDS.sleep(3);
		  
		  //Validate the user navigate to the overview page once the data has been filled.
		  try {
			  Assert.assertEquals(overviewPage.GetPageUrl(), overviewPageUrl);
		
			  test.log(Status.PASS, "Pass");
			  test.pass("Navigated to the Page: "+overviewPage.GetPageUrl());
		  }
		  
		  catch (AssertionError e){
			  test.log(Status.FAIL, "Fail");
			  test.fail("Navigated to unexpected Page: "+overviewPage.GetPageUrl());
		  }
		  
		  //Resert app state
		  productsPage
		  .DoClick(productsPage.burgerBtn)
		  .DoClick(productsPage.resetlnk);
  }
  
  @Test(priority=9)
  public void FinalOrderItems() throws InterruptedException {
	  
	      ExtentTest test = extent.createTest("Scenario #9: Final order items");
	  
		  /*-----------------
		    * Login with a valid user
		    ----------------*/
		  loginPage
		  .GoTo()
		  .DoEnterText(loginPage.usernameBox, username)
		  .DoEnterText(loginPage.passwordBox, password)
		  .DoSubmit(loginPage.loginBtn);
		  TimeUnit.SECONDS.sleep(3);
		  
		  /*-----------------
		    * Add multiple items to the shopping cart by clicking on ADD TO CART button
		    ----------------*/
		  //baseJavaScript.ExecutedJavaScript("window.scrollBy(0,180)");
		  
		  productsPage
		  .AddProductsToCart(productsPage.inventory, productname_ChildXpath, productname_5, addtocartbtn_ChildXpath)
		  .AddProductsToCart(productsPage.inventory, productname_ChildXpath, productname_6, addtocartbtn_ChildXpath);
		 
		  TimeUnit.SECONDS.sleep(3);
		  productsPage.DoClick(productsPage.shoppingCart);
		  TimeUnit.SECONDS.sleep(3);
		  shoppingCartPage.DoClick(shoppingCartPage.checkoutBtn);
		  
		  /*-----------------
		    * Fill user's information
		    ----------------*/
		  userInformationPage
		  .DoEnterText(userInformationPage.firstNameBox, firstname)
		  .DoEnterText(userInformationPage.lastNameBox, lastname)
		  .DoEnterText(userInformationPage.zipBox, zip)
		  .DoClick(userInformationPage.continue_btn);
		  TimeUnit.SECONDS.sleep(3);
		  
		  //Validate items in the overview page match with the added items.
		  String searchProductByName5 = overviewPage.SearchElement(overviewPage.cart_list, productname_ChildXpath, productname_5);
		  String searchProductByName6 = overviewPage.SearchElement(overviewPage.cart_list, productname_ChildXpath, productname_6);
		 
		  try {
			  Assert.assertEquals(searchProductByName5, productname_5);
			  Assert.assertEquals(searchProductByName6, productname_6);  
		
			  test.log(Status.PASS, "Pass");
			  test.pass("All items added on the overview page are matching with the added items");
		  }
		  
		  catch (AssertionError e){
			  test.log(Status.FAIL, "Fail");
			  test.fail("Not all the Items added on the overview page are matching with the added items");
		  }
		  
		  //Resert app state
		  productsPage
		  .DoClick(productsPage.burgerBtn)
		  .DoClick(productsPage.resetlnk);
  }
  
  @Test(priority=10)
  public void CompletePurchase() throws InterruptedException {
	  
	      ExtentTest test = extent.createTest("Scenario #10: Complete a purchase");
		  
		  /*-----------------
		    * Login with a valid user
		    ----------------*/
		  loginPage
		  .GoTo()
		  .DoEnterText(loginPage.usernameBox, username)
		  .DoEnterText(loginPage.passwordBox, password)
		  .DoSubmit(loginPage.loginBtn);
		  TimeUnit.SECONDS.sleep(3);
		  
		  /*-----------------
		    * Add a single item to the shopping cart by clicking on ADD TO CART button
		    ----------------*/
		  productsPage
		  .AddProductsToCart(productsPage.inventory, productname_ChildXpath, productname_1, addtocartbtn_ChildXpath);
		  TimeUnit.SECONDS.sleep(3);
		  productsPage.DoClick(productsPage.shoppingCart);
		  TimeUnit.SECONDS.sleep(3);
		  shoppingCartPage.DoClick(shoppingCartPage.checkoutBtn);
		  
		  /*-----------------
		    * Fill user's information
		    ----------------*/
		  userInformationPage
		  .DoEnterText(userInformationPage.firstNameBox, firstname)
		  .DoEnterText(userInformationPage.lastNameBox, lastname)
		  .DoEnterText(userInformationPage.zipBox, zip)
		  .DoClick(userInformationPage.continue_btn);
		  TimeUnit.SECONDS.sleep(3);
		  overviewPage.DoClick(overviewPage.finish_btn);
		  
		  //Validate the user navigates to the confirmation page.
		  try {
			  Assert.assertEquals(confirmationPage.GetPageUrl(), confirmationPageUrl);
		
			  test.log(Status.PASS, "Pass");
			  test.pass("Navigated to the Page: "+confirmationPage.GetPageUrl());
		  }
		  
		  catch (AssertionError e){
			  test.log(Status.FAIL, "Fail");
			  test.fail("Navigated to unexpected Page: "+confirmationPage.GetPageUrl());
		  }
		  
		  //Resert app state
		  productsPage
		  .DoClick(productsPage.burgerBtn)
		  .DoClick(productsPage.resetlnk);
  }
  
  @AfterTest
  public void afterTest() {
	  
	  loginPage.QuitDriver();
	  extent.flush();
  }

}
