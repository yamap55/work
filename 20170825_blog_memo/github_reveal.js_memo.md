# MarkdownでSlide作成して自分のGitHub Pagesで公開する
MarkdownでSlide作成して自分のGitHub Pagesで公開する奴を作りました。
資料がアチラコチラに散らばることがなく、テキストでGitなので差分が見れたりと結構便利です。
動きがあるスライドなどは辛いですが、さくっと作る時には良いかと思いますので良かったら使ってみてください。

## 忙しい人用まとめ
- SlideをMarkdownで書いて自分のGitHubで管理して、自分のGitHub Pagesで表示できるようにした。
  - reveal.js version 3.5.0を使用
- 毎回HTMLとか書きたくないので、Markdownのパスを指定すればスライドが表示されるようにした。
- 真似したい人は、リポジトリをForkやCloneして、GitHub Pagesで公開すればすぐ使えます。（↓に使い方書いてます）
  - https://github.com/yamap55/SlideWithGitHubPages
- スライドサンプル
  - http://yamap55.github.io/SlideWithGitHubPages/index.html?slide=example/slide1.md
  - http://yamap55.github.io/SlideWithGitHubPages/index.html?slide=example/slide2.md

## 経緯とか
私はスライド資料をPowerPointやKeynoteで作るのが嫌なのでMarkdownで作成しGitHubで管理しています。((どこでもアクセス可能、履歴、差分見れる、芝生えるとメリットいっぱい))今までは、そのスライドをSlideck(`https://slideck.io/`)というサービス((ボケてなどを作ってる、ゆーすけべーさんが作成。 http://blog.yusuke.be/entry/2016/03/18/150640 ))でスライド表示していたのですが、たまに繋がらない事があったり、HTTPSで警告が出るようになったりとしていて、最近サイト自体が消えてドメインが別の方のものになってました。😂

代替サービスも色々探してはいたのですが中々しっくりくるものに巡り会えず。（一応下の方にまとめておきます。）自分でなんとかしようとなった次第です。

Markdownをスライド表示する[reveal.js](https://github.com/hakimel/reveal.js/)は以前少しだけ触ったことがあったので、外部ファイルからファイルを読み込める事ができることは知っていました。なので、GitHubにあるMarkdownファイルを直接読ませたら表示できるのでは？っと読ませたら普通にスライドが表示されました。((raw表示を行うURLを渡しましょう。 https://raw.githubusercontent.com/... ))

という事で、最終的にはGitHub Pagesとして作成してみました。

## 使い方
### 初回
- git clone or forkして自分のGitHubにリポジトリ作成
- プロジェクトのsettings
- [GitHub Pagesとして公開](https://www.google.com/search?q=github+pages)
- 以下にアクセス
  - http://${userId}.github.io/${repositoryName}/index.html?slide=example/slide1.md

### スライド追加
- Markdownでスライドを書く
  - 書き方は[検索](https://www.google.com/search?q=revelal.js+markdown)するか、[サンプル](https://raw.githubusercontent.com/yamap55/SlideWithGitHubPages/master/example/slide1.md)見てください。
- GitHubにpush
- 以下にアクセス
  - http://${userId}.github.io/${repositoryName}/index.html?slide=${markdownPath}

## PDF出力をする場合
- [公式](https://github.com/hakimel/reveal.js/#instructions-1)にも記載がありますが、一応記載しておきます。
- URL末尾に「&print-pdf」を付与して表示。
  - 例 : http://yamap55.github.io/SlideWithGitHubPages/index.html?slide=example/slide1.md&print-pdf
- ブラウザの印刷設定
- PDFに保存、横向き、背景のグラフィックにチェック
- 保存

## 注意点
- GitHub Pagesなので更新が遅い場合があります
  - ブラウザにキャッシュされている場合もある（キャッシュクリアや別ブラウザ、シークレットモードなどで確認）
- 古いブラウザは考慮していません
- オフラインでは見れません（↓のPDF化参照）

## 調査した代替サービス（覚えているものだけ）
- [GitPitch](https://gitpitch.com/)
  - ○ GitHubで管理
  - × リポジトリ1つが1スライド
  - × ヘッダやフッタに色々ついてくるっぽい
- [Qiita](http://qiita.com/)
  - ○ Qiitaの記事をスライドで表示可能
  - × GitHub管理ではない（資料が分散してしまう、芝が生えない）
  - ○ たくさんの人に見てもらえる
- [Marp](https://yhatt.github.io/marp/)
  - × クライアントアプリケーション
    - 好きなアプリケーションを入れられない場合があるかも？そもそも自分のPCが使えない場合もあるかも？
  - ○ 結構盛り上がってるっぽい : [参考](http://qiita.com/yhatt/items/0bf65699a538d5508c33)
  - ○ GitHubに☆がいっぱい！ : [参考](https://github.com/yhatt/marp/)
- [WikiNote](https://wikinote.net/)
  - △ 匿名
  - × 誰でも変更可能
  - × GitHub管理ではない（資料が分散してしまう、芝が生えない）
  - × スライドモードでスライドが2枚づつ作成されている？
- [Generate Google Slides from markdown](https://github.com/googlesamples/md2googleslides)
  - 最近発見したのであまり調べてないが、最終的にこれでGoogleSlideに変換してSlideShareなどで公開するのもありかな？
  - 参考 : [MarkdownファイルをGoogleスライドに変換](http://www.moongift.jp/2016/12/md2googleslides-markdown%E3%83%95%E3%82%A1%E3%82%A4%E3%83%AB%E3%82%92google%E3%82%B9%E3%83%A9%E3%82%A4%E3%83%89%E3%81%AB%E5%A4%89%E6%8F%9B/)

------------


- 以下のindex.htmlを追加する。
  - [reveal.js](https://cdnjs.com/libraries/reveal.js)の[index.html](https://raw.githubusercontent.com/hakimel/reveal.js/master/index.html)を元に、リクエストパラメータからMarkdownを読み込むように修正しています。
- リポジトリをGitHub Pageとして公開。
  - [参考](https://www.google.co.jp/search?q=github+pages)（Google検索）
- pushしたindex.htmlにアクセス。
  - http://${ユーザ名}.github.io/${リポジトリ名}/index.html?slide=${マークダウンのPATH}
  - 例1 : http://yamap55.github.io/Slide/index.html?slide=20170827/example1.md
  - 例2 : http://yamap55.github.io/Slide/index.html?slide=20170827/example2.md
  - [例に使用したリポジトリ](https://github.com/yamap55/Slide/tree/master/20170827)


-----


## 手順
1. [reveal.jsのリポジトリ](https://github.com/hakimel/reveal.js/)から[index.html](https://raw.githubusercontent.com/hakimel/reveal.js/master/index.html)をコピー
2. JavaScriptやCSSをCDNに変更
  - CDN : https://cdnjs.com/libraries/reveal.js
  - 参考 : https://github.com/yamap55/Slide/commit/6735dc934d2c17752738943c693d2230bcc43d6e
3. [この辺り](https://github.com/hakimel/reveal.js/#external-markdown)を参考に別ファイルから読み込むように変更
4. 別ファイルを動的（リクエストパラメータ）から取得できるように変更
  - 参考 : https://github.com/yamap55/Slide/blob/54154dd/index.html#L15
5. GitHubにpush
6. github pagesとして公開
  - [参考](https://www.google.co.jp/search?q=github+pages)（Google検索）
7. pushしたindex.htmlにアクセス！
  - http://${ユーザ名}.github.io/${リポジトリ名}/index.html?slide=${リポジトリ名}

---

## 注意点（残課題）
- GitHub Pagesだからしょうがないのかもしれませんが、Markdownファイルをpush後、反映が遅い場合があります。発表直前に微調整を繰り返す人は別の手段を選択するか、発表後や完成後にWebに公開する用途で使用したほうが良いかもしれません。
  - 特に何度もpushしているとその傾向がある。
  - 遅い場合には数時間かかる時も。
- スライドで画像指定に、相対パスを使用している場合、読み込み元のファイルからの相対パスになります。
  - 階層が異なると、Slide作っている時には画像が確認できません。
  - ルートパス（「/」で始まるパス）で記載すると、スライド作成時にも確認できるため良いかと思います。
    - Atomでプロジェクトフォルダがリポジトリのルートの場合に確認。
    - 例 : ```![猫](/Slide/20170830/pic/cat.jpeg)```
