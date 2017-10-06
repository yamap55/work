@Grapes([
  @Grab("com.google.guava:guava:23.0"),
  @Grab("com.codeborne:selenide:4.7"),
])

import com.codeborne.selenide.*
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import org.openqa.selenium.By
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

System.setProperty("chromeoptions.args","headless")

def outputP = $/C:\work\20171005\selenide/$
def tempDirPath = File.createTempDir().absolutePath

Configuration.browser = WebDriverRunner.CHROME
Configuration.reportsFolder = tempDirPath

// Selenideでキャプチャを取得
open("https://weather.yahoo.co.jp/weather/13/4410.html");
def targetElment = $(".forecastCity").$("div")
def location = targetElment.location
def dimension = targetElment.size

Screenshots.takeScreenShot("img")
close()

// 取得したキャプチャを切り抜き
def inputImage = ImageIO.read(new File("${tempDirPath}/img.png"));
def outputImage = inputImage.getSubimage(location.x, location.y, dimension.width, dimension.height);
ImageIO.write(outputImage, "PNG", new File("${outputP}/output3.png"));
