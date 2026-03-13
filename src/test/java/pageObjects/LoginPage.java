package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

    public LoginPage(WebDriver driver){
        super(driver);
    }

    //locators
    @FindBy(id = "input-email")
    WebElement txtemail;

    @FindBy(id="input-password")
    WebElement txtpswd;

    @FindBy(xpath = "//input[@value='Login']")
    WebElement btnlogin;

    //action methods
    public void setTxtemail(String s){
        txtemail.sendKeys(s);
    }

    public void setTxtpswd(String s){
        txtpswd.sendKeys(s);
    }

    public void clkLogin(){
        btnlogin.click();
    }
}
