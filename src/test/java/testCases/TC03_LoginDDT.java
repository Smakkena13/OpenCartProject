package testCases;

import Utilities.DataProviders;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class TC03_LoginDDT extends BaseClass {

    @Test(dataProvider = "LoginData", dataProviderClass= DataProviders.class, groups={"sanity","master"})
    void testLoginDDT(String email,String pswd,String result) throws InterruptedException {
        HomePage hp=new HomePage(driver);
        hp.clkMyAccount();
        hp.clkLogin();

        LoginPage lp=new LoginPage(driver);
        lp.setTxtemail(email);
        lp.setTxtpswd(pswd);
        lp.clkLogin();

        AccountPage ap=new AccountPage(driver);
        boolean res=ap.setMsgAccount();

//        valid-->login success-->test passed-->logot
//             -->login unsuccessfull-->test failed

//        invalid-->login failed-->test passed
//               -->login success-->test failed--> logout

        if(result.equalsIgnoreCase("valid")){
            if(res==true){
                System.out.println("valid- login success");
                ap.clkLogout();
                Thread.sleep(1000);
                ap.clkLogin();
                Assert.assertEquals(res,true);
            }else{
                System.out.println("valid- login unsuccessfull");
                Assert.fail();
            }
        }
        if(result.equalsIgnoreCase("invalid")){
            if(res==false){
                System.out.println("invalid- login unsuccessfull");
                Assert.assertTrue(true);
            }else{
                System.out.println("invalid- login success");
                ap.clkLogout();
                Thread.sleep(1000);
                ap.clkLogin();
                Assert.assertEquals(res,true);
            }
        }

    }
}
