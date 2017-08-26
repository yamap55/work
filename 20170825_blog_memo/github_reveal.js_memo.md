# MarkdownでSlide作成してGitHub Pagesで公開する
## 忙しい人用まとめ
- SlideをMarkdownで書いて、GitHubで管理する。
- 以下のindex.htmlを追加する。
  - [reveal.js](https://cdnjs.com/libraries/reveal.js)の[index.html](https://raw.githubusercontent.com/hakimel/reveal.js/master/index.html)を元に、リクエストパラメータからMarkdownを読み込むように修正しています。
- リポジトリをGitHub Pageとして公開。
  - [参考](https://www.google.co.jp/search?q=github+pages)（Google検索）
- pushしたindex.htmlにアクセス。
  - http://${ユーザ名}.github.io/${リポジトリ名}/index.html?slide=${マークダウンのPATH}
  - 例1 : http://yamap55.github.io/Slide/index.html?slide=20170827/example1.md
  - 例2 : http://yamap55.github.io/Slide/index.html?slide=20170827/example2.md
  - [例に使用したリポジトリ](https://github.com/yamap55/Slide/tree/master/20170827)


## はじめに
私はスライド資料をPowerPointやKeynoteで作るのが嫌なのでMarkdownで作成しGitHubで管理しています。((どこでもアクセス可能、履歴、差分見れる、芝生えるとメリットいっぱい))今までは、そのスライドを[Slideck](https://slideck.io/)というサービス((ボケてなどを作ってる、ゆーすけべーさんが作成。 http://blog.yusuke.be/entry/2016/03/18/150640 ))でスライド表示していたのですが、たまに繋がらない事があったり、HTTPSで警告が出るようになったりと、最近はあまり満足できていませんでした。

代替サービスも色々探してはいたのですが中々しっくりくるものに巡り会えず。（一応下の方にまとめておきます。）自分で作成しようとなった次第です。

Markdownをスライド表示する[reveal.js](https://github.com/hakimel/reveal.js/)は以前少しだけ触ったことがあったので、外部ファイルからファイルを読み込める事ができることは知っていました。なので、GitHubにあるMarkdownファイルを直接読ませたら表示できるのでは？っと読ませたら普通にスライドが表示されました。((raw表示を行うURLを渡しましょう。 https://raw.githubusercontent.com/... ))

という事で、最終的にはGitHub Pagesとして作成してみました。

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

## 残課題
- スライドで画像などを使用している場合、読み込み元のファイルからの相対パスになります。
  - 階層が異なると、Slide作っている時には画像が確認できません。
  - Slide内のPATHを取ってきて置き換えればできそうですが。。。

## PDF出力をする場合
- [公式](https://github.com/hakimel/reveal.js/#instructions-1)にも記載がありますが、一応記載しておきます。
- URL末尾に「&print-pdf」を付与して表示。
  - 例 : http://yamap55.github.io/Slide/index.html?slide=20170827/example1.md&print-pdf
- ブラウザの印刷設定
- PDFに保存、横向き、背景のグラフィックにチェック
- 保存
