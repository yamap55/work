@Grapes([
  @Grab("com.codeborne:selenide:4.8"),
  @Grab("org.twitter4j:twitter4j-core:4.0.6"),
  @Grab("org.codehaus.groovy.modules.http-builder:http-builder:0.7.1"),
  @Grab("org.apache.httpcomponents:httpmime:4.5.3"),
])

import twitter4j.*

class Main {
  static void main(args) {
    def selenide = new SelenideExecuter()
    def captureInfo = selenide.getCapture("https://weather.yahoo.co.jp/weather/13/4410.html", [".forecastCity","div"])
    println captureInfo

    def gyazo = new Gyazo("token")
    def url = gyazo.upload(captureInfo.file,)

    println url

    def twitterMessage = new TwitterExecuter().tweetSearch("from:Yahoo_weather 東京の天気")
    println twitterMessage
  }
}
