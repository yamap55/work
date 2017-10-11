@Grab(group='org.twitter4j', module='twitter4j-core', version='4.0.6')

import twitter4j.*

class Main {
  def twitterMessage = new TwitterExecuter().tweetSearch("from:Yahoo_weather 東京の天気")
}
