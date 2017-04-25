@Grab('org.jsoup:jsoup:1.10.2')
import org.jsoup.Jsoup
import groovy.transform.ToString

println "start"

def resultPath = "/Users/yamap_55/Desktop/twithitter_memo.txt"
def resultFile = new File(resultPath)

def num = 100000

def createRandomStr = {
  def array = (0..9)+('a'..'z')+'_'
  def f = {
    new Random().with { (1..4).collect { array[nextInt(array.size())] }.join() }
  }
}()

def u = "https://twithitter.com/"
def errorCount = 0

num.times {
  if (!((it + 1) % 10)) {
    println "${new Date().format('yyyy/MM/dd HH:mm:ss')}, ${it+1}/${num}, error : ${errorCount}"
  }

  def url = new URL(u + createRandomStr())

  def doc = null
  try {
    doc = Jsoup.parse(url.text)
  } catch(e) {
    errorCount++
    return
  }
  def user = new User(doc)
  if (user.isTarget()) {
    def r = "${user.twitterId} : ${user.type} : ${user.status}"
    println r
    resultFile << "${r}\n"
  }
}
println "end"


@ToString(excludes = ["profileArea", "rank"])
class User {
  def profileArea
  def name
  def twitterId
  def throwBat
  def team
  def popularity

  def type
  def status

  def getSimpleStatus() {
    type == "打者" ? status[0..4] : status[0..2]
  }

  def isTarget() {
    if (type == "投手" && status[0..1].every{it == "Good?"}) {
      // スタミナ確認
      if (status[2] == "Good?") {
        // 先発
        return true
      } else {
        def s = status[3..(status.size()-1)]
        return (s.size() <= 2 ? s.every{it == "Good?"} : s.count{it == "Good?"} >= s.size() - 1)
      }
      return false
    } else {
      // 打者
      return getSimpleStatus().every{it == "Good?"}
    }
  }

  User(d) {
    profileArea = d.getElementsByClass("player-profile")[0]
    def f = {
      profileArea.getElementsByClass(it)[0].text()
    }
    name = f("name")
    twitterId = f("screen-name")
    throwBat = f("throw-bat")
    team = f("userteam-none-label")
    popularity = profileArea.getElementsByClass("userteam-popularity")[0].text().split(" ")[1]

    def batterStatusArea = d.getElementsByClass("batter-status")
    type = batterStatusArea ? "打者" : "投手"
    def pitcherStatusArea = d.getElementsByClass("pitcher-status")

    if (batterStatusArea) {
      def area = batterStatusArea[0]
      // ミート、パワー、走力、肩力
    //  def status1 = area.getElementsByClass("element")[0].getElementsByClass("rank-label")*.text()
    //  def status2 = area.getElementsByClass("fielding")[0].getElementsByClass("rank-label")*.text()
      // ミート、パワー、走力、肩力、捕、一、二、三、遊、左、中、右
      status = area.getElementsByClass("left-column")[0].getElementsByClass("rank-label")*.text()
    } else {
      def area = pitcherStatusArea[0]
      // 球速、制球、スタミナ、残りは変化球
      status = area.getElementsByClass("rank-label")*.text()
    }

  }
}
