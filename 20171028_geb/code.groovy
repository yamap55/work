@Grapes([
  @Grab("io.github.bonigarcia:webdrivermanager:1.7.2"),
  @Grab("org.gebish:geb-core:2.0"),
  @Grab("org.seleniumhq.selenium:selenium-support:3.6.0"),
  @Grab("org.seleniumhq.selenium:selenium-chrome-driver:3.6.0")
])

import io.github.bonigarcia.wdm.ChromeDriverManager
import geb.Browser

import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.DesiredCapabilities

println "start ${new Date().format('yyyy/MM/dd HH:mm:ss')}"

ChromeDriverManager.getInstance().setup()

def options = new ChromeOptions();
options.addArguments("--headless")
def browser = new Browser(driver:new ChromeDriver(options))
browser.with {
    go "http://gebish.org"

    assert title == "Geb - Very Groovy Browser Automation"
    assert $("h1")[0].text() == "What is it?"
}
println "end ${new Date().format('yyyy/MM/dd HH:mm:ss')}"
