@Grab('org.codehaus.groovy.modules.http-builder:http-builder:0.7.1')
@Grab('org.jsoup:jsoup:1.8.3')
import groovyx.net.http.HTTPBuilder
import java.net.URLEncoder
import org.jsoup.Jsoup


def url = "http://wikiwiki.jp"
def http = new HTTPBuilder(url)



// ListにcollectWithIndexを追加。
List.metaClass.collectWithIndex = {body->
    def i=0
    delegate.collect { body(it, i++) }
}


// 効果から各ステータスを抜き出すための正規表現
def statusRegex = [/攻撃力(-?[.0-9]+)$/,/命中精度(-?[.0-9]+)$/,/防御力(-?[.0-9]+)$/,/回避能力(-?[.0-9]+)$/,/魔法攻撃力(-?[.0-9]+)$/,/魔法防御力(-?[.0-9]+)$/,/必殺率(-?[.0-9]+%)$/,/攻撃回数(-?[.0-9]+)$/,/最大HP(-?[.0-9]+)$/,/罠解除能力(-?[.0-9]+)$/,/魔法回復量(-?[.0-9]+)$/]

// 最終的なHEAD
// ID,種別,種別詳細,No.,名称,読みがな,ドロップ,効果,価格,
// 攻撃力,命中精度,防御力,回避能力,魔法攻撃力,魔法防御力,必殺率,攻撃回数,最大HP,罠解除能力,魔法回復量
def masterHead = ["ID", "種別", "種別詳細", "NO.", "名称", "読みがな", "ドロップ", "効果", "価格", "攻撃力", "命中精度", "防御力", "回避能力", "魔法攻撃力", "魔法防御力", "必殺率", "攻撃回数", "最大HP", "罠解除能力", "魔法回復量"]
def statusIndex = masterHead.indexOf("攻撃力")
def createRecode = {head, data ->
  def result = masterHead.collect{""}
  head.eachWithIndex {d, i ->
    if (d == "効果") {
      def a = []
      data[i].split("　").each {str ->
        def flag = false
        statusRegex.eachWithIndex { regex, j ->
          def m = str =~ regex
          if (m) {
            result[j + statusIndex] = m[0][1] - "%"
            flag = true
          }
        }
        if (!flag) {
          a << str
        }
      }
      result[masterHead.indexOf(d)] = a ? a.join("　") : ""
    } else {
      // テーブルが結合されていてnullを返すことがあるためデータがない場合には空文字に変換。
      result[masterHead.indexOf(d)] = data[i] ?:""
    }
  }
//  // 指定情報は古いItemInfoから取得
//  def name = result[masterHead.indexOf("名称")]
  // def oldInfo = getOldItemInfo(name)
  // if (oldInfo) {
  //   def f = {n ->
  //     result[masterHead.indexOf(n)] = oldInfo[n]
  //   }
  //   ["種別", "種別詳細", "読みがな"].each{f(it)}
  // }
  result
}

http.get([path : "/guildmono2/", queryString:URLEncoder.encode("アイテム一覧", "EUC-JP")]) { resp, reader ->
  println(reader.toString())//text())
  println("-" * 10)//text())
  println(resp.getData())//text())
  // println(reader.toString())//text())
  // def doc = Jsoup.parse(reader.text())
  def doc = Jsoup.parse(resp.getData())
  // def doc = Jsoup.parse(new File(wikiHtml), "EUC-JP")
  println ("-" * 10)
  println doc
  def l = []
  doc.select(".style_table").each {
    def head = it.select("thead th").collect{it.text()}
    def body = it.select("tbody tr").each {
      def data = it.select("td").collectWithIndex {v, i ->
        def d = v.text()
        if(head[i] == "価格" || head[i] == "価値") {
          head[i] = "価格" // 価格に統一
          d = d.contains(",")?"\"${d}\"":d
        } else if (head[i] == "効果") {
          // 効果の中に改行を含んでいることがあるため。
          d = v.html().replaceAll('<br class="spacer">', "　")
        } else if (head[i] == "NO.") {
          d = d as int
        } else if (head[i] == "備考"){
          head[i] = "ドロップ"
        }
        d
      }
      println createRecode(head, data)
    }
  }
}
