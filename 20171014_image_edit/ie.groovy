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

def outputP = "/Users/yamap_55/work/20171014"
// x:367, y:351, width:314, height:174]
// 取得したキャプチャを切り抜き
println "${outputP}/pic,png"
def inputImage = ImageIO.read(new File("${outputP}/pic.png"));
def outputImage = inputImage.getSubimage(734, 702, 628, 348);
ImageIO.write(outputImage, "PNG", new File("${outputP}/${new Date().format('yyyyMMddHHmmss')}.png"));
