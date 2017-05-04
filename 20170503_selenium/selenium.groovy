@Grapes([
    @Grab("org.seleniumhq.selenium:selenium-support:3.4.0"),
    @Grab("org.seleniumhq.selenium:selenium-chrome-driver:3.4.0"),
    @Grab("com.codeborne:phantomjsdriver:1.4.2"),
])

import java.util.concurrent.TimeUnit

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Main {
  public static void main(String[] args) {
    println "start ${new Date().format('yyyy/MM/dd HH:mm:ss')}"

    // System.setProperty("webdriver.chrome.driver", "./driver/chromedriver");
    System.setProperty(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "./driver/phantomjs");
    // ChromeDriver driver = new ChromeDriver();
    PhantomJSDriver driver = new PhantomJSDriver();
    // driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    driver.get("https://qiita.com/");

    sleep(1000)
    // submit
    // List<WebElement> h1 = driver.findElements(By.tagName("input")).get(2).click();
    // driver.findElements(By.tagName("input")).get(2).click();
    driver.findElements(By.tagName("a")).get(3).click();
    // driver.findElements(By.className("mb20")).get(0).click();
    // sleep(1000)

    driver.findElement(By.id("username_or_email")).sendKeys("aaaaa");
    driver.findElement(By.id("password")).sendKeys("aaaaaa");

    sleep(1000)

    // $("#username_or_email").value(browser.config.rawConfig.loginTwitterId)
    // $("#password").value(browser.config.rawConfig.loginTwitterPassword)




    // FirstPage firstPage =  new FirstPage(driver)
    // WebDriverWait<WebDriver> wait = new WebDriverWait(driver, 30);
    //
    // System.out.println("FirstPage Title : " + driver.getTitle());
    // System.out.println("FirstPage Logo : " + firstPage.getLogo())
    // System.out.println("FirstPage Top Section Name : " + firstPage.getTopSectionName())
    //
    // System.out.println("リンクをクリック！")
    // SecondPage secondPage = firstPage.linkClick()
    //
    // System.out.println("SecondPage Title : " + driver.getTitle());
    // System.out.println("SecondPage Logo : " + firstPage.getLogo())
    // System.out.println("SecondPage Top Section Name : " + secondPage.getTopSectionName())

    println "end ${new Date().format('yyyy/MM/dd HH:mm:ss')}"
  }
}

public class FirstPage {
  WebDriver driver;

  public FirstPage(WebDriver driver) {
    this.driver = driver;
    driver.get("http://gebish.org");
  }

  public SecondPage linkClick()  {
    List<WebElement> alink = driver.findElements(By.xpath("//*[@id='sidebar']/ul/li[2]/a"))
    alink[0].click()
    return new SecondPage(this.driver);
  }

  public String getTopSectionName() {
    List<WebElement> h1 = driver.findElements(new By.ByTagName("h1"));

    return h1[1].getText()
  }

  public String getLogo() {
    List<WebElement> h1 = driver.findElements(new By.ByTagName("h1"));

    return h1[0].getText()
  }
}

public class SecondPage {
  WebDriver driver;
  public SecondPage(WebDriver driver) {
    this.driver = driver;
  }

  public String getLogo() {
    List<WebElement> h1 = driver.findElements(new By.ByTagName("h1"));

    return h1[0].getText()
  }

  public String getTopSectionName() {
    List<WebElement> h1 = driver.findElements(new By.ByTagName("h1"));

    return h1[1].getText()
  }

}
