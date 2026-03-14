package testBase;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;  //Log4j
import org.apache.logging.log4j.Logger;  //Log4j
import org.testng.annotations.Parameters;


public class BaseClass {

    public Logger logger;
    public static WebDriver driver;
    public Properties p;

    @BeforeClass(groups={"sanity","master","regression"})
    @Parameters({"browser","os"})
    public void setUp(String br,String os) throws IOException {
        p=new Properties();
        FileInputStream file=new FileInputStream(System.getProperty("user.dir")+"\\config.properties");
        p.load(file);
        System.out.println(p.getProperty("url2"));

        logger=LogManager.getLogger(this.getClass());  //Log4j
        if(p.getProperty("execution_env").equalsIgnoreCase("local")) {
            System.out.println("Execution in local");
            switch (br.toLowerCase()) {
                case "chrome":
                    driver = new ChromeDriver();
                    break;
                case "edge":
                    driver = new EdgeDriver();
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                default:
                    System.out.println("invalid");
                    return;
            }
        }
        if(p.getProperty("execution_env").equalsIgnoreCase("remote")){
            System.out.println("execution in Remote");
            DesiredCapabilities dc=new DesiredCapabilities();

            if(os.equalsIgnoreCase("windows")){
                dc.setPlatform(Platform.WIN10);
            }else if(os.equalsIgnoreCase("linux")){
                dc.setPlatform(Platform.LINUX);
            }else{
                System.out.println("os not supported");
                return;
            }
            switch(br){
                case "chrome":
                    dc.setBrowserName("chrome");
                    break;
                case "edge":
                    dc.setBrowserName("MicrosoftEdge");
                    break;
                case "firefox":
                    dc.setBrowserName("firefox");
                    break;
                default:
                    System.out.println("invalid browser");
                    return;
            }
            URL url_l=new URL("http://localhost/wd/hub");
//            URL url=new URL("http://192.168.1.43:4444/wd/hub");
            driver=new RemoteWebDriver(url_l,dc);

        }
        driver.get(p.getProperty("url2"));
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterClass(groups={"sanity","master","regression"})
    public void tearDown(){
//        driver.close();
    }

    public String randomString(){
        String alpha= RandomStringUtils.randomAlphabetic(5);
        return alpha;
    }

    public String randomNumber(){
        String num=RandomStringUtils.randomNumeric(10);
        return num;
    }
    public String randomAlphaNum(){
        String alp=RandomStringUtils.randomAlphabetic(3);
        String num=RandomStringUtils.randomNumeric(3);
        String res=alp+"@"+num;
        return res;
    }

    public String captureScreenshot(String rname){
        SimpleDateFormat df=new SimpleDateFormat("yyyyMMddmmss");
        Date d=new Date();
        String currdate= df.format(d);

        TakesScreenshot ts= (TakesScreenshot) driver;
        File source= ts.getScreenshotAs(OutputType.FILE);
        String targetFilepath=System.getProperty("user.dir")+"\\screenshots\\"+rname+"_"+currdate+"_"+".png";
        File target=new File(targetFilepath);

        source.renameTo(target);
        return targetFilepath;

    }
}
