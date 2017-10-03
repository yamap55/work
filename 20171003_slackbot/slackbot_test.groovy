// 参考
// https://qiita.com/riversun/items/25f64f285699223a992d

@Grapes(
    @Grab(group='org.riversun', module='slacklet', version='1.0.1')
)
import org.riversun.slacklet.*
import org.riversun.xternal.simpleslackapi.*

def token = System.getenv()["SLACK_TOKEN"]
SlackletService slackService = new SlackletService(token);

// slackletを追加する
slackService.addSlacklet(new Slacklet() {
  @Override
  public void onMessagePosted(SlackletRequest req, SlackletResponse resp) {
    println "test"
    // メッセージがユーザーからポストされた
    // メッセージがポストされたチャンネルを取得する
    SlackChannel channel = req.getChannel();
    if ("test_yamashita".equals(channel.getName())) {
      // #randomチャンネルだった場合
      // メッセージ本文を取得
      String content = req.getContent();
      // メッセージがポストされたチャンネルに対して、BOTからメッセージを送る
      resp.reply("「" + content + "」て言いましたね。");
    }
  }
});

// slackletserviceを開始(slackに接続)
slackService.start();
