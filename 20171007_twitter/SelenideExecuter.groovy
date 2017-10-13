import com.codeborne.selenide.*
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import org.openqa.selenium.By
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

class SelenideExecuter {

  Map getCapture(url, selectors) {

    def tempDirPath = File.createTempDir().absolutePath
    System.setProperty("chromeoptions.args","headless")
    Configuration.browser = WebDriverRunner.CHROME
    Configuration.reportsFolder = tempDirPath

    // Selenideでキャプチャを取得
    open(url);
    // def targetElment = $(".forecastCity").$("div")
    def targetElment  = selectors.inject($("html")) {elm,selector ->
      elm = elm.$(selector)
    }
    def location = targetElment.location
    def dimension = targetElment.size

    def fileName = new Date().format("yyyyMMddHHmmssSSS")
    Screenshots.takeScreenShot(fileName)
    close()
    return [
      file :new File("${tempDirPath}/${fileName}.png"),
      x : location.x,
      y : location.y,
      width : dimension.width,
      height : dimension.height,
    ]
    // 取得したキャプチャを切り抜き
    // def inputImage = ImageIO.read();
    // def outputImage = inputImage.getSubimage(location.x, location.y, dimension.width, dimension.height);
    // ImageIO.write(outputImage, "PNG", new File("${outputP}/output3.png"));
  }
}
