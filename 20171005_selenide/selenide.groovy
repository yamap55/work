// selenide4.7ではguavaの21.0、selenideが依存しているselenium-javaが使用しているのは23.0
// 22で追加されたメソッドを使用しようとして落ちるので、23.0依存関係に加えることで回避する
@Grab("com.google.guava:guava:23.0")
@Grab("com.codeborne:selenide:4.7")

import com.codeborne.selenide.*
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import org.openqa.selenium.By

Configuration.browser = WebDriverRunner.MARIONETTE 
open("http://www.google.com");
$("#lst-ib").setValue("selenide");
$(By.name("btnK")).click();
close()