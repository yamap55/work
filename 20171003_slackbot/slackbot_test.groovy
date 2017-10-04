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
    def botName = slackService.getBot().getUserName()
    // メッセージがユーザーからポストされた
    // メッセージ本文を取得
    String content = req.getContent();
    // メッセージがポストされたチャンネルに対して、BOTからメッセージを送る
    resp.reply("「" + content + "」て言いましたね。");
  }

  @Override
  public void onMentionedMessagePosted(SlackletRequest req, SlackletResponse resp) {
    println "onMentionedMessagePosted"
    String content = req.getContent();
    resp.reply("「" + content + "」て言いましたね。");
    if (content.contains("stop")) {
      resp.reply("「stop」します。");
      slackService.stop();
    }
  }
});

// slackletserviceを開始(slackに接続)
slackService.start();
