@Grapes([
  @Grab("com.codeborne:selenide:4.8"),
  @Grab("org.codehaus.groovy.modules.http-builder:http-builder:0.7.1"),
  @Grab("org.apache.httpcomponents:httpmime:4.5.3"),
])


class HatenaCounter {

  static void main(args) {
    def selenide = new SelenideExecuter()
    def u = "http://counting.hatelabo.jp/count/amgtleca46"
    //def u = http://counting.hatelabo.jp/count/xp2p2eyaa9"
    def captureInfo = selenide.getCapture(u, [".main-count"])
    // println captureInfo

    // TODO Macの場合には2倍する必要あり。理由わからず。
    def cap = ImageEditer.subImage(captureInfo.file, captureInfo.x * 2, captureInfo.y * 2, captureInfo.width * 2, captureInfo.height * 2)

    def gyazo = new Gyazo("token")
    def url = gyazo.upload(cap)
    println url
  }
}
