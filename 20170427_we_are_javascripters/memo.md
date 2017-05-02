# We Are JavaScripters! @6thに参加してきた #WeJS #21cafe

## はじめに
4月27日（木）に「We Are JavaScripters! @6th」という勉強会に参加してきた時の自分用メモです。((公開が遅れて申し訳ありません。（書いたとばっかり思ってました。。。）)

全てLTなので、資料見たほうが早いかと思いますｗ

## 概要
-
[We Are JavaScripters! @6th【初心者登壇歓迎！LT大会】](https://wajs.connpass.com/event/54667/)

```
「JSの勉強会って、登壇する人がハイレベルな人ばっかりだな〜」
と思ったのがきっかけで作った勉強会、「We Are JavaScripters」！

自分が学んだこと/気づいたこと/面白かったこと/失敗したこと/ハマったこと/挑戦したこと/デバッグテクニック/オススメツール・開発環境/お気に入りのライブラリ/オレオレライブラリ/実はこうだった言語仕様/tips などなど、

お酒を片手に、気軽に発信できる場を目指しています^^/

※誰でも怖がらずに登壇できる空気を作りたいだけだけなので、もちろん玄人のみなさんも登壇大歓迎です！！
```

- イベント申込時の主催者アンケートだと、JavaScript初心者が半分くらいだったらしいです。
  - 個人的には怪しいと思ってますがｗ

## LT.1：JSの基本的なことをちょっと掘り下げてみる話 prototype編(やっと) [@ta__miyan](https://twitter.com/ta__miyan)
- [JSの基本的なことをちょっと掘り下げてみる話シリーズ Chapter04. 〜 prototype編(やっと..!) 〜](https://www.slideshare.net/YukikoTamiya/js-chapter04-prototype)
  - 発表資料
- [図で理解するJavaScriptのプロトタイプチェーン](http://qiita.com/howdy39/items/35729490b024ca295d6c)を参考にしたとのこと。
- __proto__ってES2015まで非標準だった。
- __proto__はプロトタイプチェーンを辿るためのオブジェクト。

## LT.2：Elmの話(仮) [@boiyaa](https://twitter.com/boiyaa)
- [JSに不変の愛を](https://speakerdeck.com/boiyaa/jsnibu-bian-falseai-wo)
  - 発表資料
- [elm公式サイト](http://elm-lang.org/)
- 「jsは値を束縛するが、我々を束縛できない」
- テストとコンパイルが通れば期待通りの動作をする。

https://twitter.com/YamaHilo/status/857556552709947393

## LT.3：==と=== [@IganinTea](https://twitter.com/IganinTea)
- [==と===を調べてみた](https://speakerdeck.com/iganin/equals-equals-to-equals-equals-equals-wodiao-betemita)
  - 発表資料
- [JavaScriptの==の中身を知る](http://qiita.com/IganinTea/items/6a148d69b764df11ad40)
  - 発表者による補完資料
- 曖昧な比較と厳密な比較
  - 1 == '1'
- まとめると「==」を使うなって話。

https://twitter.com/zuckey_17/status/857556345695854593
https://twitter.com/YamaHilo/status/857557244531036161

## LT.4：そろそろwebpackと真剣に向き合ってみる。 [@Nao-bt](https://twitter.com/Nao-bt)
- [公式ページ](http://webpack.github.io/)
- 大量のJSを書くと死ぬ。
- 大量のJSのコードをモジュールごとに分ける。
- 言語仕様としてはモジュールをサポートしていない。
- ↓の4個を覚えとけ
- Entry
  - アプリの中で最初に読み込むファイル
- Output
  - ビルドの結果を出力する設定
- Loaders
  - ビルドの際にモジュールのソースコードに適用される変換を指定する。
  - testって書かないとエラーとなるのがハマりどころらしい。
- Plugins
  - ビルド時の設定を行う。
  - ビルドの際にファイルの圧縮だったり、コンパイルエラーを無視？するとか。

## LT.5：TypeScriptでDDD~RepositoryとEntity編~ [@mrdShinse](https://twitter.com/mrdShinse)
- [TypeSctipt公式](https://www.typescriptlang.org/)
- DDD = ドメイン駆動設計
- LineのBotを作成するのに、Bot作成用のフレームワークを使わず、あえてExpress.jsを使用して開発。
- TypeScriptのGenericsはいい

https://github.com/mrdShinse/eve-linebot

## LT.6：jsのGCについて [@brn0227](https://twitter.com/brn0227)
- [Javascript Garbage Collector overview](https://www.slideshare.net/ssuser6f246f/javascript-garbage-collector-overview)
  - 発表資料
- 関数は会議
- Stackはホワイトボード
  - 関数単位で割り当てられるので、終了すると自動で廃棄
- Heapは議事録
  - プログラムで制御

## LT.7：はじめてのReact: ES2015の実用 [@Jay](https://twitter.com/Jay)
- はじめてのReact
- HelloWorldの代わりになる自分のやったことの発表
- セミコロンレスで書いたらしい。

## LT.8：オブジェクトの作成とコンストラクタの話 [@chikoski](https://twitter.com/chikoski)
- [An implementation of new operator in JS](https://speakerdeck.com/chikoski/an-implementation-of-new-operator-in-js)
  - 発表資料
- [Gist](https://gist.github.com/chikoski/1a1fab7e66005b167504da92396333a3)
  - サンプルコード
- newは何をしているのか。
- newを使わないでnewを再現する。
