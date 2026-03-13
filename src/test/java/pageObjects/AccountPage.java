package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPage extends BasePage{
    public AccountPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//h2[text()='My Account']")
    WebElement msgAccount;

    @FindBy(xpath = "(//a[text()='Logout'])[2]")
    WebElement btnLogout;

    @FindBy(xpath = "(//a[text()='Login'])[2]")
    WebElement btnLogin;

    public boolean setMsgAccount(){
        try{
            Boolean flag= msgAccount.isDisplayed();
            return flag;
        }catch(Exception e){
            return false;
        }
    }

    public void clkLogout(){
        btnLogout.click();
    }

    public void clkLogin(){
        btnLogin.click();
    }
}
