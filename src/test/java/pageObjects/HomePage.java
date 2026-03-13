package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

    //constructor
    public HomePage(WebDriver driver){
        super(driver);
    }

    //locators
    @FindBy(xpath="//span[text()='My Account']")
    WebElement btnMyAccount;

    @FindBy(xpath = "//a[text()='Register']")
    WebElement btnRegister;

    @FindBy(xpath="//a[text()='Login']")
    WebElement btnLogin;

    //actions
    public void clkMyAccount(){
        btnMyAccount.click();
    }

    public void clkRegister(){
        btnRegister.click();
    }

    public void clkLogin(){
        btnLogin.click();
    }
}
