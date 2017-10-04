// �Q�l
// https://qiita.com/riversun/items/25f64f285699223a992d

@Grapes(
    @Grab(group='org.riversun', module='slacklet', version='1.0.1')
)
import org.riversun.slacklet.*
import org.riversun.xternal.simpleslackapi.*

def token = System.getenv()["SLACK_TOKEN"]
SlackletService slackService = new SlackletService(token);

// slacklet��ǉ�����
slackService.addSlacklet(new Slacklet() {
  @Override
  public void onMessagePosted(SlackletRequest req, SlackletResponse resp) {
    def botName = slackService.getBot().getUserName()
    // ���b�Z�[�W�����[�U�[����|�X�g���ꂽ
    // ���b�Z�[�W�{�����擾
    String content = req.getContent();
    // ���b�Z�[�W���|�X�g���ꂽ�`�����l���ɑ΂��āABOT���烁�b�Z�[�W�𑗂�
    resp.reply("�u" + content + "�v�Č����܂����ˁB");
  }

  @Override
  public void onMentionedMessagePosted(SlackletRequest req, SlackletResponse resp) {
    println "onMentionedMessagePosted"
    String content = req.getContent();
    resp.reply("�u" + content + "�v�Č����܂����ˁB");
    if (content.contains("stop")) {
      resp.reply("�ustop�v���܂��B");
      slackService.stop();
    }
  }
});

// slackletservice���J�n(slack�ɐڑ�)
slackService.start();
