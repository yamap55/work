@Grab(group='org.twitter4j', module='twitter4j-core', version='4.0.6')
import twitter4j.*

/*
以下の環境変数を設定
twitter4j.oauth.consumerKey
twitter4j.oauth.consumerSecret
twitter4j.oauth.accessToken
twitter4j.oauth.accessTokenSecret
*/

Twitter twitter = new TwitterFactory().getInstance();
Query query = new Query();
// 「#東京の天気」ではつぶやかなくなったみたい。
query.setQuery("from:Yahoo_weather 東京の天気");
query.setCount(1)

// 検索実行
QueryResult result = twitter.search(query);

System.out.println("ヒット数 : " + result.getTweets().size());
def tw = result.tweets[0]
println tw.text
println "${'*'*10}"
//println result.refreshURL
null
