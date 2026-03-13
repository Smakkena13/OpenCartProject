package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.RegistrationPage;
import testBase.BaseClass;

public class TC01_AccountRegistration extends BaseClass {

    @Test(groups = "sanity")
    void testAccountRegistration(){
//        logger.info("*****ACCOUNT REGISTRATION STARTS*******");
        HomePage hp=new HomePage(driver);
        hp.clkMyAccount();
        hp.clkRegister();

        RegistrationPage rp=new RegistrationPage(driver);
        rp.firstname(randomString());
        rp.lastname(randomString());
        rp.email(randomString()+"@gmail.com");
        rp.telephone(randomNumber());

        String pwd=randomAlphaNum();
        rp.password(pwd);
        rp.cnfpassword(pwd);

        rp.policy();
        rp.submit();
        String actualmsg=rp.Msg();
//        logger.info("==========ACCOUNT REGISTRATION ENDS==========");
        Assert.assertEquals(actualmsg,"Your Account Has Been Created!");

    }

}
