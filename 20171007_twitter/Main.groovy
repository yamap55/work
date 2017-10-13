@Grab(group='org.twitter4j', module='twitter4j-core', version='4.0.6')
@Grab("org.codehaus.groovy.modules.http-builder:http-builder:0.7.1")
@Grab("org.apache.httpcomponents:httpmime:4.5.3")

import twitter4j.*

class Main {
  static void main(args) {
    def twitterMessage = new TwitterExecuter().tweetSearch("from:Yahoo_weather 東京の天気")
    println twitterMessage
  }
}
