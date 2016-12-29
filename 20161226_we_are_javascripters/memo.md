# We Are JavaScripters! @2nd【初心者登壇歓迎！LT大会】
## 概要
> 「JSの勉強会って、登壇する人がハイレベルな人ばっかりだな〜」
> と思ったのがきっかけで作った勉強会、「We Are JavaScripters」！
>
> 自分が学んだこと/気づいたこと/面白かったこと/失敗したこと/ハマったこと/挑戦したこと/デバッグテクニック/オススメツール・開発環境/お気に入りのライブラリ/オレオレライブラリ/実はこうだった言語仕様/tips などなど、
>
> お酒を片手に、気軽に発信できる場を目指しています^^/
>
> ※誰でも怖がらずに登壇できる空気を作りたいだけだけなので、もちろん玄人のみなさんも登壇大歓迎です！！

- [We Are JavaScripters! @1st【初心者登壇歓迎！LT大会】](https://wajs.connpass.com/event/46636/)
- [ハッシュタグは#wajs #21cafeらしい](https://twitter.com/search?q=%23wajs%20%2321cafe&src=typd]

## 最初に
- [前回のまとめ](http://yamap55.hatenablog.com/archive/2016/12/01)
- 外部勉強で初の発表！
  - 緊張する人なので、お酒飲んで発表できるのは凄く助かりました。
- `We are javascripters`という名前は外国の方からすると違和感があるらしい。
  - 別名募集中とのこと

## LT
### LT.1：JSの基本的なことをちょっと掘り下げてみる話 Chapter02. 〜 デバッグ編 〜 - @ta__miyan
- [JSの基本的なことをちょっと掘り下げてみる話シリーズ Chapter02. 〜 デバッグ編 〜](http://www.slideshare.net/YukikoTamiya/js-chapter02-70466775)
- chrome developer toolsの話し。
- consoleオブジェクトのメソッド紹介。
- 参考↓
- [https://twitter.com/chikoski/status/813337242718453760:embed#console オブジェクトのマニュアルはこちら：https://t.co/X4BE37aurt #wajs #21cafe]
- breakpointsの紹介。
- VSCodeのこと
  - Chromeと連携すると色々できるらしい。

### LT.3：React.js と TypeScript で趣味に走った SPA 作ってみた - @bird_tummy
- [React.js と TypeScript で趣味に走った SPA 作ってみた](https://speakerdeck.com/bird_tummy/20161226-wajs2)
- 順番変わった？
- React.jsとTypeScript
- デザフェスというアートイベントの次回参加する人を探す！という部分をフォローするために作った。
- infernoという軽いライブラリがある。
- 開発環境作成が鬼門。
  - 正直、私のようなJavaScript初心者には環境作成のハードルは確かに高いのはわかる。。。
- 20個位ライブラリを使った。
- テストは是非書きましょう！
- moxiosというライブラリでリクエストをモックできる
- ライブラリ何を使うかは個人の好みによるところが大きい気がする
- 作りたいもの作ると勉強にもなって凄く良い

### LT.2：javascript開発のいろいろ - @chihiro_fujisawa_73
- sequalizeとnodeの話し。
  - sequalizeはO/Rマッパー
- データ量がちょっと増えたらめっちゃ遅くなった
  - SQL実行に時間がかかる
    - sequalizeが実際に投げるSQLがわかっていなかった。
  - Promiseの書き方が良くなかったため時々失敗するユニットテストが発生。
  - CIやっぱり重要。
    - 15分に一回単体テスト実行していたため時々失敗するユニットテストがみつかった

### LT.4：JavaScript is universal - @keita_moromizato
- [JavaScript is universal](https://prezi.com/0v7wnhrglrla/wajs2/)
- 99%JavaScriptでWebサービスを作る
- Sails.jsはヤヴァイ
- サーバサイドをTypeScriptで書くのはおすすめしない。
  - JavaScript書いているというより型を書く事になる。
- サーバレス
  - SWS Lambda
  - サーバ不要でバッチ処理、API公開
- GASは便利！
- Yahooが文章の自動レビューAPIを公開しているらしい
- JavaScript1つ知っていると、クライアント、サーバもできるし、APIも簡単に公開できる
- システムのほぼすべてをJavaScriptで書くのは現実的になりつつある
- どこでも動くJavaScriptを書くには若干のコツが必要

### LT.5：web会議システムをつくる試行錯誤 - @yuuta_moriyama_1
次発表なので緊張してて、殆ど頭に入らず。。。

- 汎用的なオンラインビデオ会議システムを作る
- 元々エンジニアではないが、作りたいので頑張った！

### LT.6：electron触ってみた - @yamap_55
- 自分の発表。
- スライド : [electron触ってみた](https://slideck.io/github.com/yamap55/Slide/20161226/electron.md)
- [スライド表示してたElectronのコード](https://github.com/yamap55/work/tree/master/20161207_electron/webview-timer)
  - 発表中にスライド表示してたやつです。言い忘れましたが。。。orz

### LT.7：dexijs - @RyotaSuzuki
発表後なので放心状態。あまり聞けず。

- [Dexiejs](http://www.slideshare.net/RyotaSuzuki1/dexiejs)
- indexedDBのラッパーライブラリ
- indexedDBは辛い
- indexedDBは辛い
- indexedDBは辛い
- Dexi.js使えば読める！

### LT.8：リスト処理と関数プログラミング - @chikoski
発表後なので放心状態。

- [リスト操作と関数プログラミング](https://speakerdeck.com/chikoski/list-processing-in-js-functional-programming-approach)
