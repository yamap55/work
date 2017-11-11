@Grapes(
    @Grab(group='com.amazonaws', module='aws-java-sdk-polly', version='1.11.228')
)
import com.amazonaws.services.polly.model.SynthesizeSpeechRequest
import com.amazonaws.services.polly.AmazonPollyClientBuilder
import com.amazonaws.regions.Regions

// 環境変数でAWS_ACCESS_KEY_ID と AWS_SECRET_ACCESS_KEYを設定するほうが良い
System.setProperty("aws.accessKeyId", "accessKeyId")
System.setProperty("aws.secretKey", "secretKey")

def text = "こんにちは"

// リクエストの作成
def request = new SynthesizeSpeechRequest();
request.setVoiceId("Mizuki"); // 日本語はTakumi,Mizuki
request.setOutputFormat("mp3");
request.setText(text);
request.setTextType("text");

// Polly を使って音声化
def polly = AmazonPollyClientBuilder.standard().withRegion(Regions.AP_NORTHEAST_1 ).build()
def result = polly.synthesizeSpeech(request)
def stream =  result.getAudioStream()

new File("/Users/yamap_55/github/work/20171111_polly/output/${new Date().format('yyyyMMddHHmmss')}.mp3") << stream
