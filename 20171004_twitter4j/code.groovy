@Grab(group='org.twitter4j', module='twitter4j-core', version='4.0.6')
import twitter4j.*

/*
�ȉ��̊��ϐ���ݒ�
twitter4j.oauth.consumerKey
twitter4j.oauth.consumerSecret
twitter4j.oauth.accessToken
twitter4j.oauth.accessTokenSecret
*/

Twitter twitter = new TwitterFactory().getInstance();
Query query = new Query();
// �u#�����̓V�C�v�ł͂Ԃ₩�Ȃ��Ȃ����݂����B
query.setQuery("from:Yahoo_weather �����̓V�C");
query.setCount(1)

// �������s
QueryResult result = twitter.search(query);

System.out.println("�q�b�g�� : " + result.getTweets().size());
def tw = result.tweets[0]
println tw.text
println "${'*'*10}"
//println result.refreshURL
null
