# We Are JavaScripters! @10thに参加してきた #WeJS #yahoolodge
## はじめに
- 8月25日（金）に「We Are JavaScripters! @10th」という勉強会に参加してきた時のメモです。
- 全てLT、酒飲みながらのメモなので((特に後半))、興味のある発表については資料見たほうが早いかと思います。
  - 資料については公開され次第、随時追記します。
- 記念すべき10回目！
- ちなみに、登壇者で応募しましたが抽選で落ちました😂
- 6人枠の所に14人？とか。辛い。
- 登壇者9人中6人が、発表タイトルにReactが入っている。
  - 人数が↑と合わないのは、スポンサー枠、主催者枠のため。
- ハッシュタグ : #yahoolodge #WeJS

## 概要
- [We Are JavaScripters! @10th【初心者歓迎LT大会】](https://wajs.connpass.com/event/63502/)

```
「JSの勉強会って、登壇する人がハイレベルな人ばっかりだな〜」
と思ったのがきっかけで作った勉強会、「We Are JavaScripters」！

自分が学んだこと/気づいたこと/面白かったこと/失敗したこと/ハマったこと/挑戦したこと/デバッグテクニック/オススメツール・開発環境/お気に入りのライブラリ/オレオレライブラリ/実はこうだった言語仕様/tips などなど、

お酒を片手に、気軽に発信できる場を目指しています^^/

※誰でも怖がらずに登壇できる空気を作りたいだけだけなので、もちろん玄人のみなさんも登壇大歓迎です！！
```

## LT.1：Reactのフォーム画面開発で発生する問題をredux-formで解決する @kazuaki_okamoto
- 発表資料
  - []()
- redux-formはあまり実務で使用されていないらしい。
  - 手上げが少ない。
- http://redux-form.com/7.0.3/
- GitHub☆が7300↑
- React+Reduxより簡単完結。
- FieldArray便利っぽい。
  - 配列で取得。
- パフォーマンスのメリット
  - Field=Reduxのconnectのような扱い
  - V6で大幅に改善。
- 入力値のバリデーションとエラー状態管理

## LT.2：Reactに不変の愛を @boiyaa
- 発表資料
  - []()
- 主催者いじりが始まる。
- elmの話ｗ
- http://elm-lang.org/

## LT.3：Take into Accessibility in React ＠usagi-f
- 発表資料
  - []()
- 元デザイナーの方
- Accessibilityを意識したReactの話。
- 実は相性が良いReactとアクセシビリティ
  - UIが容易に制御
  - 既に多くの拡張がある
- WAI-ARIA
  - ```Web Accessibility Initiative - Accessible Rich Internet Applicationsの頭文字であり、HTMLやSVGで利用できるアクセシビリティ確保のための属性の仕様です。```
- aタグのhrefは必須じゃない
  - ```この属性は、プレースホルダーリンクを生成するために省略できます (HTML5)。プレースホルダーリンクは従来のハイパーリンクに似ていますが、どこにも移動しません。```
- ta11y?

## LT.4：CSSの嫌なところを解決できる。そうCSS Houdiniならね。 @fukaminmin
- 発表資料
  - []()
- CSSはブラウザ対応がだるい
- Houdini
  - 海外のマジシャン？
- https://twitter.com/mki_skt/status/901043986965569537
- Typed OM
  - px計算とかが楽。
- ブラウザの実装状況はひどい。

## LT.5：Chromeデベロッパーツールを自分色に染める @jumpei_ikegami
- 発表資料
  - []()
- ネットワークタブとか辛い
- 見たいものを見たい形で
- Vue.js devtools
- Chrome拡張でイジれるらしい
- デベロッパーツールに対してもデベロッパーツールを開ける
  - 地味に重要

## LT.6：flowがReactにもたらすもの @takanorip
- 発表資料
  - []()
- 技術書展出店予定
- flow?
  - 静的型チェッカー
- 小さく試せる
  - レガシー環境下でも使える
- AltJSではないのでESの機能すべて使える
- Reactと相性がいい
  - 作ってる所が一緒
- エラーが出た時に参考にできる情報がない
  - GitHubのissueを見る
- flowは後から入れても大丈夫なので、小さく入れてみると良い

## LT.7：ReactでPDF帳票を出力する @stakezaki
- 発表資料
  - []()
- ReactでPDF
- フルスタックエンジニアはいらない
  - HTMLとJSだけあればいい
- [vte.cx Advent Calendar 2016](http://qiita.com/advent-calendar/2016/vtecx)
- https://admin.1.vte.cx/
- 裏ではnode.jsではなくNashornを使っている

## LT.8：レガシーな環境にTypeScriptを導入した話 @ama
- 発表資料
  - []()
- レガシーな環境とは。
- 型があるって素晴らしい！
- ある程度は書き方が統一できる
- jQueryを使っていたら、jQueryの型定義が既にある。
- 独自の型定義をどこまで頑張るのか問題。
- Java、C#のエンジニアが多い場合にはTypeScriptがいい！

## LT.9：同じとは @chikoski
- 発表資料
  - []()
- 2と10は同じ
  - 偶数だから
- 同じというのは価値観によるので難しい
- 表みとけ
  - TODO 後でリンク
