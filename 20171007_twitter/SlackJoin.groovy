// 参考
// https://qiita.com/riversun/items/25f64f285699223a992d

import org.riversun.slacklet.*
import org.riversun.xternal.simpleslackapi.*

class SlackJoin {
  def slackService
  SlackJoin(token) {
    this.slackService = new SlackletService(token)
  }

  def start() {
    // slackletserviceを開始(slackに接続)
    slackService.start();
  }

  def stop() {
    slackService.stop();
  }

  def execute() {
    // slackletを追加する
    slackService.addSlacklet(new Slacklet() {
      @Override
      public void onMessagePosted(SlackletRequest req, SlackletResponse resp) {
        // メッセージがユーザーからポストされた
        // メッセージ本文を取得
        String content = req.getContent();
        def sender = req.sender
        if (sender.realName == "slackbot") {
          return
        }
        // メッセージがポストされたチャンネルに対して、BOTからメッセージを送る
        resp.reply("「${sender.realName}」が「${content}」て言いましたね。");
      }

      @Override
      public void onMentionedMessagePosted(SlackletRequest req, SlackletResponse resp) {
        String content = req.getContent();
        def sender = req.sender
        if (sender.realName == "slackbot") {
          return
        }
        resp.reply("「${sender.realName}」が「${content}」て話しかけましたね。");
        if (content.contains("stop")) {
          resp.reply("「stop」します。");
          stop();
          // System.exit(0)
        } else if (content.contains("天気")) {
          def selenide = new SelenideExecuter()
          def captureInfo = selenide.getCapture("https://weather.yahoo.co.jp/weather/13/4410.html", [".forecastCity"])
          // println captureInfo

          // TODO Macの場合には2倍する必要あり。理由わからず。
          def cap = ImageEditer.subImage(captureInfo.file, captureInfo.x * 2, captureInfo.y * 2, captureInfo.width * 2, captureInfo.height * 2)

          def gyazo = new Gyazo("token")
          def url = gyazo.upload(cap)
          // println url

          def twitterMessage = new TwitterExecuter().tweetSearch("from:Yahoo_weather 東京の天気")
          // println twitterMessage

          resp.reply("${twitterMessage}\n${url}");
          println req.channel.name
        }
      }
    });

  }
}
