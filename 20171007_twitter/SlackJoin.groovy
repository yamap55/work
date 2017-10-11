// 参考
// https://qiita.com/riversun/items/25f64f285699223a992d

@Grapes(
    @Grab(group='org.riversun', module='slacklet', version='1.0.1')
)
import org.riversun.slacklet.*
import org.riversun.xternal.simpleslackapi.*

class SlackJoin {
  def token = System.getenv()["SLACK_TOKEN"]
  def execute() {
    SlackletService slackService = new SlackletService(token)

    // slackletを追加する
    slackService.addSlacklet(new Slacklet() {
      @Override
      public void onMessagePosted(SlackletRequest req, SlackletResponse resp) {
        // メッセージがユーザーからポストされた
        // メッセージ本文を取得
        String content = req.getContent();
        // メッセージがポストされたチャンネルに対して、BOTからメッセージを送る
        resp.reply("「" + content + "」て言いましたね。");
      }

      @Override
      public void onMentionedMessagePosted(SlackletRequest req, SlackletResponse resp) {
        String content = req.getContent();
        resp.reply("「" + content + "」て言いましたね。");
        if (content.contains("stop")) {
          resp.reply("「stop」します。");
          slackService.stop();
          System.exit(0)
        } else if (content.contains("天気")) {

        }
      }
    });

    // slackletserviceを開始(slackに接続)
    slackService.start();
  }
}
