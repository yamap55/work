# We Are JavaScripters! @3rd【初心者登壇歓迎！LT大会】
## 概要
```
「JSの勉強会って、登壇する人がハイレベルな人ばっかりだな〜」
と思ったのがきっかけで作った勉強会、「We Are JavaScripters」！

自分が学んだこと/気づいたこと/面白かったこと/失敗したこと/ハマったこと/挑戦したこと/デバッグテクニック/オススメツール・開発環境/お気に入りのライブラリ/オレオレライブラリ/実はこうだった言語仕様/tips などなど、

お酒を片手に、気軽に発信できる場を目指しています^^/

※誰でも怖がらずに登壇できる空気を作りたいだけだけなので、もちろん玄人のみなさんも登壇大歓迎です！！
```

## LT.1：JSの基本的なことをちょっと掘り下げてみる話（prototype編・・かも） - @ta__miyan
- prototypeというかコンストラクタ、Objectについて
- ネイティブオブジェクトは全てコンストラクタ関数から生成されている。
- JSの世界は全てオブジェクト？
- プリミティブ型にプロパティの参照やメソッド呼び出しを行った場合、一時的にオブジェクトに変換されている。
- 参照渡しと値渡しについてはググれ
- 次回はprototypeの話をする。

## LT.2：モテル駆動開発に基づくElmという選択 - @boiyaa
- Elmはモテる
- インストールだけすればOK。
- Elmは純粋関数型言語。
- やさしく関数の世界にいざなってくれる。
- 必ずモテる銀の弾丸ではない。
- 半年に一回ペースで破壊的アップデートあり。
- 純粋な？関数型言語用語？を使っていない。

## LT.3：Angular2のChangeDetectionを観察する - @yuuta_moriyama_1
- ディベートのオンラインシステムを開発。
  - 仲間募集中
- リファクタリングプラン。
- Immutable Objects
  - 通常は変更されたときに、全てのコンポーネントを見に行ってしまう。
  - 全てを見に行かないように制御する
- Component Lifecycle
  - Componentには生成されてから削除されるまでにそれぞれのフェーズでフック可能。

## LT.4：PrimeNGという選択肢 - @shinse
- [PrimeNGという選択肢について](https://speakerdeck.com/mrdshinse/primengtoiuxuan-ze-zhi-nituite)
- ライブラリ
- いわゆる大企業が業務フローを固めるために使うアプリケーション
- **Angular向けUIライブラリ**
- おじさん受けの良い機能が多い
- コンシュマーみたいなUIを使いたがっている。
  - そんなおじさま方にうける。

## LT.5：ES6で関数型プログラミング - @Kawabata_Lemon
- [ES6時代の関数型プログラミング](https://speakerdeck.com/kawabatalemon/es6shi-dai-falseguan-shu-xing-puroguramingu)
- 関数型とは。
- 値とどう向き合うか。
  - ある入力に対する出力がいつも同じ値を返す
- 変数の巻き上げ。
  - `var`は呼び出し時に宣言していなくても、エラーとならない。
- 関数型を使うと考え方が身につく
  - 問題が起こりにくい感覚がみにつく。

## LT.6：anime.jsがいいらしい - @711fumi
- [anime.jsがいいらしい](https://speakerdeck.com/711fumi/anime-dot-jsgaiirasii)
- ロゴが派手
- 数100行位のシンプルなライブラリ
- 複雑な動きはできない？
  - V2がでそう！
- 見た人に何を感じてほしいか考える
- http://anime-js.com/

## LT.7：サーバサイドエンジニアにこそ試して欲しいBaaS - @moongift
- [サーバサイドエンジニアにこそ使って欲しいBaaS](https://speakerdeck.com/ncmb/sabasaidoenzinianikososhi-tuteyu-siibaas)
- [MOONGIFT](http://www.moongift.jp/)の中の人。
- NCMBMania
- mbaas
- http://mb.cloud.nifty.com/about.htm

## LT.8：名前の話 - @chikoski
- JavaScriptのモジュールはES2015で定義されている。
- が、ブラウザでは実装は一切されていない。。。
- モジュールを使うと、名前が外に出ないので、適当な名前でOKｗ
