import twitter4j.*

/*
以下の環境変数を設定
twitter4j.oauth.consumerKey
twitter4j.oauth.consumerSecret
twitter4j.oauth.accessToken
twitter4j.oauth.accessTokenSecret
*/

class TwitterExecuter {

  def twitter = new TwitterFactory().getInstance();
  def query = new Query();

  def tweetSearch(query) {
    // 「#東京の天気」ではつぶやかなくなったみたい。
    // query.setQuery("from:Yahoo_weather 東京の天気");
    this.query.query = query
    this.query.count = 1

    // 検索実行
    def result = twitter.search(this.query);

    System.out.println("ヒット数 : " + result.getTweets().size());

    result.tweets[0].text
  }
}
