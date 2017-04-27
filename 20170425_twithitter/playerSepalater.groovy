// あ
// def path = "/Users/yamap_55/Desktop/twithitter_memo.txt"
def path = "/Users/yamap_55/Desktop/twithitter_pitcher.txt"
// def path = "/Users/yamap_55/Desktop/twithitter_batter.txt"
def f = new File(path)

def i = 0
f.text.eachLine {
  def m = it =~ /\[(.*)\]/
  def l = m[0][1].split(",").collect{it.trim()}

  // 投手で先発以外
  // def stamina = l.remove(2)
  // if (stamina == "Good?") {
  //   return
  // }
  // 投手で先発以外 ここまで
  if(l.every{it == "Good?"}) {
    println it
  }
}
