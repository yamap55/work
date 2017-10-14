@Grapes([
  @Grab("com.codeborne:selenide:4.8"),
  @Grab("org.twitter4j:twitter4j-core:4.0.6"),
  @Grab("org.codehaus.groovy.modules.http-builder:http-builder:0.7.1"),
  @Grab("org.apache.httpcomponents:httpmime:4.5.3"),
  @Grab("org.riversun:slacklet:1.0.1")
])

import twitter4j.*

import org.riversun.slacklet.*
import org.riversun.xternal.simpleslackapi.*

class Main {
  static void main(args) {
    // def selenide = new SelenideExecuter()
    // def captureInfo = selenide.getCapture("https://weather.yahoo.co.jp/weather/13/4410.html", [".forecastCity","div"])
    // println captureInfo
    //
    // // TODO Macの場合には2倍する必要あり。理由わからず。
    // def cap = ImageEditer.subImage(captureInfo.file, captureInfo.x * 2, captureInfo.y * 2, captureInfo.width * 2, captureInfo.height * 2)
    //
    // def gyazo = new Gyazo("token")
    // def url = gyazo.upload(cap)
    //
    // println url
    //
    // def twitterMessage = new TwitterExecuter().tweetSearch("from:Yahoo_weather 東京の天気")
    // println twitterMessage
    def slack = new SlackJoin("token")
    slack.execute()
    slack.start()

  }
}
