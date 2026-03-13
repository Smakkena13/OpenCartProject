package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class TC02_LoginTC extends BaseClass {

    @Test(groups={"master","regression"})
    void testLogin() throws InterruptedException {
        HomePage hp=new HomePage(driver);
        hp.clkMyAccount();
        hp.clkLogin();

        LoginPage lp=new LoginPage(driver);
        lp.setTxtemail(p.getProperty("email"));
        lp.setTxtpswd(p.getProperty("password"));
        lp.clkLogin();

        AccountPage ap=new AccountPage(driver);
        boolean res=ap.setMsgAccount();
        ap.clkLogout();
        Thread.sleep(1000);
        ap.clkLogin();
        Assert.assertEquals(res,true);
    }
}
