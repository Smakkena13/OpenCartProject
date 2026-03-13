package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage{

    public RegistrationPage(WebDriver driver){
        super(driver);
    }

    @FindBy(id="input-firstname")
    WebElement txtFn;

    @FindBy(id="input-lastname")
    WebElement txtLn;

    @FindBy(id="input-email")
    WebElement txtEmail;

    @FindBy(id="input-telephone")
    WebElement txtTelephone;

    @FindBy(id="input-password")
    WebElement txtPwd;

    @FindBy(id="input-confirm")
    WebElement txtCnfPwd;

    @FindBy(xpath="//input[@name='agree']")
    WebElement chkPP;

    @FindBy(xpath = "//input[@value='Continue']")
    WebElement btnSubmit;

    @FindBy(xpath="//div[@id='content']/h1")
    WebElement message;

    //action methods
    public void firstname(String s){
        txtFn.sendKeys(s);
    }
    public void lastname(String s){
        txtLn.sendKeys(s);
    }
    public void email(String s){
        txtEmail.sendKeys(s);
    }
    public void telephone(String s){
        txtTelephone.sendKeys(s);
    }
    public void password(String s){
        txtPwd.sendKeys(s);
    }
    public void cnfpassword(String s){
        txtCnfPwd.sendKeys(s);
    }
    public void policy(){
        chkPP.click();
    }
    public void submit(){
        btnSubmit.click();
    }

    public String Msg(){
        try{
            return message.getText();
        }catch(Exception e){
            return e.getMessage();
        }
    }

}
