// http://lab.astamuse.co.jp/entry/geb_test_01
@Grab('org.gebish:geb-core')
@Grab('org.seleniumhq.selenium:selenium-java')

import geb.Browser
import org.openqa.selenium.firefox.MarionetteDriver

{->
  def isWindows = System.properties['os.name'].toLowerCase().contains('windows')
  def home = System.properties['user.home']
  System.setProperty('webdriver.gecko.driver',
  isWindows ?
    /c:\tools\geckoDriver\marionette.exe/:
    "${home}/tools/geckoDriver/marionette"
  )
}()

Browser.drive(driver:new MarionetteDriver()) {
    go 'https://www.google.co.jp'
}