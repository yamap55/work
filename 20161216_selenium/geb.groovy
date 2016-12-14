@Grapes([
    @Grab("org.seleniumhq.selenium:selenium-support:3.0.1"),
    @Grab(group='org.seleniumhq.selenium', module='selenium-chrome-driver', version='3.0.1'),
    @Grab("org.gebish:geb-core:1.0"),
])

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import geb.*

println "start ${new Date().format('yyyy/MM/dd HH:mm:ss')}"

System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe")

driver = {
    return new ChromeDriver()
}

Browser.drive {
    to FirstPage
    println logo
    println topSectionName
    println "リンクをクリック！"
    linkClick()

    at SecondPage

    println logo
    println topSectionName
}

println "end ${new Date().format('yyyy/MM/dd HH:mm:ss')}"

class FirstPage extends geb.Page {
  static url = "http://gebish.org"
  static at = { title == "Geb - Very Groovy Browser Automation" }
  static content = {
    logoField { $("h1").first() }
    topSectionTitle { $("h1")[1] }
    link { $("#sidebar .sidemenu a", text: "jQuery-like API") }
  }

  def getLogo() {
    logoField.text()
  }

  def getTopSectionName() {
    topSectionTitle.text()
  }

  def linkClick() {
    link.click()
  }
}

class SecondPage extends geb.Page {
  static at = { waitFor { title == "Geb - Very Groovy Browser Automation" } }
  static content = {
    logoField { $("h1").first() }
    topSectionTitle { $("h1")[1] }
  }

  def getLogo() {
    logoField.text()
  }

  def getTopSectionName() {
    topSectionTitle.text()
  }
}
