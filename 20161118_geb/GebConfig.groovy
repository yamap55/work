@Grapes([
    @Grab("org.gebish:geb-core:1.0"),
//    @Grab("org.seleniumhq.selenium:selenium-firefox-driver:2.52.0"),
//    @Grab("org.seleniumhq.selenium:selenium-support:2.52.0")
    @Grab("org.seleniumhq.selenium:selenium-support:3.0.1"),
    @Grab(group='org.seleniumhq.selenium', module='selenium-firefox-driver', version='3.0.1'),
    @Grab(group='org.seleniumhq.selenium', module='selenium-chrome-driver', version='3.0.1'),
    @Grab(group='org.seleniumhq.selenium', module='selenium-ie-driver', version='3.0.1'),
])
import geb.report.CompositeReporter
import geb.report.ScreenshotReporter

import org.openqa.selenium.chrome.ChromeDriver
//import org.openqa.selenium.ie.InternetExplorerDriver


//reportsDir = "./report"

//System.setProperty("webdriver.gecko.driver", ./driver/geckodriver.exe");

// IEはレジストリの変更が必要？
// http://bitwave.showcase-tv.com/selenium%E3%81%A7internet-explorer11%E3%82%92%E5%8B%95%E3%81%8B%E3%81%99%E6%96%B9%E6%B3%95/
//System.setProperty("webdriver.ie.driver", "./driver/IEDriverServer.exe");
System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe")

driver = {
    return new ChromeDriver()
}