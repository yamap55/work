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
//options.addArguments("--headless")
def browser = new Browser(driver:new ChromeDriver(options))
browser.with {
    go "https://www.google.com/search?q=Google"
    $("#hdtb-msb-vis").find("div").find{it.text() == "画像" }.find("a").click()
//    $("#main").find("div").each {println it.attr("id")}
    println $("#main").children().getClass()
    println $("#main").children().find{it.attr("id") == ""}
    println $("#main").children().each{ println it.attr("id")}
  //  find("div").each {println it.attr("id")}

}
println "end ${new Date().format('yyyy/MM/dd HH:mm:ss')}"
