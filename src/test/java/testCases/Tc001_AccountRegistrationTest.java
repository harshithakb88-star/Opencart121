package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class Tc001_AccountRegistrationTest extends BaseClass{


	@Test(groups={"Regression", "Master"})
	public void verify_account_registration() {
		logger.info("*****starting Tc001_AccountRegistrationTescase *****");
		try {
		HomePage hp= new HomePage(driver);
		hp.clickMyaccount();
		logger.info("*****clicked on my account link*****");
        hp.clickRegister();
        logger.info("*****clicked on register link *****");
AccountRegistrationPage ap= new AccountRegistrationPage(driver);
logger.info("*****Providing customer details*****");
ap.setFirstname(randomeString().toUpperCase());
ap.setLastname(randomeString().toUpperCase());
ap.setEmail(randomeString()+"@gmail.com");
ap.setTelephone(randomeNumber());
String password=randomeAlphanumeric();

ap.setPassword(password);
ap.confirmPwd(password);
ap.setPolicy();
ap.clickContinue();
logger.info("*****validating expected message *****");
String confmsg=ap.getConfirmationMsg();

Assert.assertEquals(confmsg, "Your Account Has Been Created!");
	
	}
	catch(Exception e) {
	logger.error("test failed..");	
	logger.debug("debug logs...");
	Assert.fail();
	}

	logger.info("*****finished Tc001_AccountRegistrationTescase *****");
}}