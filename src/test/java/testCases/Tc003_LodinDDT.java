package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccount;
import testBase.BaseClass;
import utilities.DataProviders;

/* Data is valid -login sucess testpass-logout
 * data is valid- login failed- failed test
 * 
 * data is invalid- login sucess- test fail- logout
 * data is invalid - failed- test fail
 */
public class Tc003_LodinDDT extends BaseClass {
	
	@Test(dataProvider="Login Data", dataProviderClass= DataProviders.class) // getting data provider from different class
	public void verifyloginddt(String email, String pwd, String exp) {
		logger.info("*** starting tc003 loginddt***");
		
		//Home page
	try {	
		HomePage hp= new HomePage(driver);
		hp.clickMyaccount();
hp.clickLogin();
//Login page
LoginPage lp= new LoginPage(driver);
lp.setEmail(email);
lp.setPassword(pwd);
lp.clickLogin();
//Myaccount
MyAccount ma= new MyAccount(driver);
boolean targetPage=ma.isMyaccountpageexists();

if(exp.equalsIgnoreCase("valid")) {
	if(targetPage==true) {
		ma.clickLogout();
		Assert.assertTrue(true);
		
		
	}
	else{
		Assert.assertTrue(false);
	}
}
if(exp.equalsIgnoreCase("invalid")) {
	if(targetPage==true) {
		ma.clickLogout();
		Assert.assertTrue(false);
		
	}
	else {
		Assert.assertTrue(true);
	}
}} catch(Exception e) {
	Assert.fail();
}
logger.info("*** finished tc003 loginddt***");
		}
}
