@Grapes([
    @Grab("org.gebish:geb-core:1.1.1"),
    @Grab("org.seleniumhq.selenium:selenium-support:3.4.0"),
    @Grab("org.seleniumhq.selenium:selenium-chrome-driver:3.4.0"),
])
import geb.report.CompositeReporter
import geb.report.ScreenshotReporter

import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.DesiredCapabilities

System.setProperty("webdriver.chrome.driver", "./driver/chromedriver")

driver = {
  def options = new ChromeOptions()
  def capabilities = DesiredCapabilities.chrome()
  options.addArguments("headless")
  capabilities.setCapability(ChromeOptions.CAPABILITY, options)
  new ChromeDriver(capabilities)
}
