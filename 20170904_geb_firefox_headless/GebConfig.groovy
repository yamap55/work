@Grapes([
    @Grab("org.gebish:geb-core:1.1.1"),
    @Grab("org.seleniumhq.selenium:selenium-support:3.5.3"),
    @Grab("org.seleniumhq.selenium:selenium-firefox-driver:3.5.3"),
])
import geb.report.CompositeReporter
import geb.report.ScreenshotReporter

import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions
import org.openqa.selenium.remote.DesiredCapabilities

System.setProperty("webdriver.firefox.driver", "./driver/wires")

driver = {
  def options = new FirefoxOptions()
  def capabilities = DesiredCapabilities.firefox()
  options.addArguments("headless")
  capabilities.setCapability(FirefoxOptions.CAPABILITY, options)
  new FirefoxDriver(capabilities)
//new FirefoxDriver()
}
