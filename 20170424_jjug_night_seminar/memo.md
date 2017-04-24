# JJUGナイトセミナーに参加してきた（4/24） #JJUG

4/24に開催されたJJUGナイトセミナーに参加してきた際のメモ。

# 【東京】JJUG ナイト・セミナー 「テスティング特集」
## 概要
- 名前
  - 【東京】JJUG ナイト・セミナー 「テスティング特集」
- 日時
  - 2017-04-24（月）19:00 - 21:00
- 会場
  - 日本オラクル株式会社 本社 13階 東京都港区北青山2-5-8
- 募集ページ
  - [【東京】JJUG ナイト・セミナー 「テスティング特集」 4/24(月)開催](https://jjug.doorkeeper.jp/events/59187)
- 概要
  - ```今回はテスティング特集と称しまして、JUnit他についてその道のプロの方々にお話いただきます！```

## 「Modern unit testing with JUnit 5」
### 概要
```
JUnit is the tool of choice when it comes to unit testing in Java. The current version JUnit 4 has been working reliably for many years but despite some advancements it has now reached its limits. JUnit version 5 is a complete rewrite and will be released soon. In the session, we will discuss what is new in JUnit 5 and what needs to be considered when upgrading.
```

- 発表者
  - [@sbley](https://twitter.com/sbley)

### メモ

- JUnit5の説明。英語セッション。
- JUnit4と互換性がとられているらしい。
  - そのままで動くとのこと
- パラメーター渡すテストや、DisplayNameはかなり便利そう。
  - ParameterizedTest
  - 直接値渡す以外に、CSVで渡すこともできる。
- 逆にタグ付けや、繰り返しは使うタイミングは少なそう。
- インターフェイスのテストの箇所はよくわからなかったので後で復習。
- IDEは対応してくれているみたい。
  - NetBeansは？ってなってたｗ後で誰か補足してくれるかな？
- Maven、Gradle共にJUnitチームがプラグインを開発しているらしい。
- Jenkins対応は？ってなってた。XML出力すれば勝手に読んでくれるような気がするけど。。。
  - カバレッジという言葉もしゃべっていた気がした。
- 現時点ではM4
- 7月にリリース予定？けど遅れそうなのかな？（笑いが出てた）

## 「アジャイルテスティング -バグ埋め込みを年間1件にまで減らした戦略-」
### 概要
```
アジャイル開発におけるソフトウェアテストへの態度に関する発表です。4年間開発しているチームにおける品質向上の取り組みで、次のスライドの再演になります
https://speakerdeck.com/kyonmm/aziyairutesuteingu-bagumai-meip-miwonian-jian-1jian-nimadejian-rasitazhan-lue-number-nagoyatesting
```

- 発表者
  - [@kyon_mm](https://twitter.com/kyon_mm)

- スライド : [アジャイルテスティング -バグ埋め込みを年間1件にまで減らした戦略- #NagoyaTesting](https://speakerdeck.com/kyonmm/aziyairutesuteingu-bagumai-meip-miwonian-jian-1jian-nimadejian-rasitazhan-lue-number-nagoyatesting)

### メモ
- バグの数を年1件にした話。
- バグの根源はムリからきている。そのバグをテストで取り除こうとするのはムダ。
- やったこと
  - バグの分析
  - チームの分析
  - 理想像を構築
  - チームを帰る情熱と理論を構築、共有
- ↑の普通のことをどう進めるのか。
- ビジョンをはっきり持つ。
  - そのためのリスクは許容する。
  - なんども伝える。（400ー800位言う
- 基本に立ち返る。
  - 失敗しているなら、まずは基本と違うところを基本に戻してから考える。
  - 基本に立ち返ると比較しやすい。
- 積極的な暗黙知
  - これはちょっと同意できないけど、どうなんだろうか？
  - チームメンバーのレベルが高いといけるのか？
- 凄い高品質なドキュメントがあった（作成した）としても、それをうまく使う方法論はない。
- 最小6分、最大1時間の単位でタスクを全て書き出している。
  - 12ー18分のタスクが多いらしい。

## 「pact-jvmではじめるコンシューマー駆動契約」
### 概要
```
マイクロサービスアーキテクチャーの普及を始めとする近年の開発トレンドの風潮により、サービスのコンポーネントをテストする上でサービス間の依存関係を考慮することが必要とし、テストの難易度が増してきています。このセッションではサービス相互のインターフェースの仕様と実装の妥当性を検証する方法としてコンシューマー駆動契約にスポットをあて、その実装であるpact-jvmを例にして解説します。
```

- 発表者
  - [@setoazusa](https://twitter.com/setoazusa)

### メモ
- [pact-jvm](https://github.com/DiUS/pact-jvm)
- よくある話
  - モックが多すぎる
  - テストのメンテナンスが辛い
- マイクロサービス化の流れがきているが、三層アーキテクチャー（MVC）に縛られすぎている。
- ストーリーではなくジャーニーをテストする
  - 外部に対するサービス呼び出しをモック化。
- pact-jvmとはPactのJVM言語向け移植版
- テスト自動化ピラミッドの考え方はコンシューマー駆動契約を導入しなくても有効。
- ユニットテスト、エンドツーエンドテスト双方を補完する。
