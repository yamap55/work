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
    println "test"
    // ���b�Z�[�W�����[�U�[����|�X�g���ꂽ
    // ���b�Z�[�W���|�X�g���ꂽ�`�����l�����擾����
    SlackChannel channel = req.getChannel();
    if ("test_yamashita".equals(channel.getName())) {
      // #random�`�����l���������ꍇ
      // ���b�Z�[�W�{�����擾
      String content = req.getContent();
      // ���b�Z�[�W���|�X�g���ꂽ�`�����l���ɑ΂��āABOT���烁�b�Z�[�W�𑗂�
      resp.reply("�u" + content + "�v�Č����܂����ˁB");
    }
  }
});

// slackletservice���J�n(slack�ɐڑ�)
slackService.start();
