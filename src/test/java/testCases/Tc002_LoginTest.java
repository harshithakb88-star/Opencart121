package testCases;

import static org.testng.Assert.fail;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccount;
import testBase.BaseClass;

public class Tc002_LoginTest extends BaseClass{

	
	@Test(groups={"Sanity", "Master"})
	void verifylogin() {
		logger.info("***Starting the testcase tc002 ***");
		//Home page
		try {
		HomePage hp= new HomePage(driver);
		hp.clickMyaccount();
hp.clickLogin();
//Login page
LoginPage lp= new LoginPage(driver);

lp.setEmail(p.getProperty("email"));

lp.setPassword(p.getProperty("password"));


lp.clickLogin();
//Myaccount
MyAccount ma= new MyAccount(driver);
boolean targetPage=ma.isMyaccountpageexists();
Assert.assertEquals(targetPage, true, "Login failed");
		}
		catch(Exception e) {
			Assert.fail();
		}
		logger.info("***finished the testcase tc002 ***");
	}
	
	
}
