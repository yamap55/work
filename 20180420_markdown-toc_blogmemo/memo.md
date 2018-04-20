# Atomのmarkdown-tocでタイトルが日本語の場合に動かない場合の対処
## はじめに
Markdown書いていると見出し（toc）が欲しくなる
↓
Atomのプラグイン探す
↓
[あった](https://atom.io/packages/markdown-toc)
↓
日本語タイトルで正しく動かない

っという人がまあまあいるらしいので対応方法を書いてみる。

## 結論
markdown-tocをforkして対応してくれてる人がいるので↓のコマンドでインストールしてATOM再起動。

```
apm install https://github.com/Sorix/markdown-toc/
```

以下は蛇足です。

## リンクが正しくされない詳細
- ```# hoge``` は ```[hoge](#hoge)``` と変換される（正常）
- 日本語の場合 ```# ほげ``` は ``[ほげ](#)``` と変換される（異常）

## 原因
- リンクのhash生成時にドカンと一発除去しているため
  - https://github.com/nok/markdown-toc/blob/master/lib/Toc.coffee#L205

## リポジトリ見てみると。
- この問題に対するissueはいっぱい！pull requestもいくつか
  - 各国の人が各言語対応してくれと言っていて割とカオス
- そんな中Sorixという人が、各言語まとめて指定してしたpull request作成してくれている
  - https://github.com/nok/markdown-toc/pull/103
- けど、対応してくれないっぽい。
  - 多分、作者がテストできないし、困ってないからから。
- なので、SorixさんがForkして、修正している奴をインストールすればOK。

## どんな修正？
- 一発ドカンではなく不正な文字？（記号か？）だけを除去している
  - https://github.com/nok/markdown-toc/pull/103/files#diff-972b47c9868ee2eda1da061da29ff49aR205
- この文字リストがどこからきたのかさっぱりわからないが、日本語、中国語、韓国語、ドイツ語？などは除去されなくなるっぽい。
- よって、 ```# ほげ``` は ```[ほげ](#ほげ)``` と変換されて正しく動作するようになる。

## 疑問
- 調べてる途中で、「#ほげ」というリンクは本来は正しくない（ブラウザがいい感じに対応してくれているだけ）というのを見た。
  - 正しく修正するならば「 ```encodeURIComponent(hash)``` 」とするのがいいのではないかと思った。
- このプラグインだとタイトルに連続する空白が含まれている場合には1つにまとめて「-」と置換する処理が入っているが、GitHubのwikiだと連続した「--」として扱うらしいので、正しくリンクされない。
  - これはビューワの仕様による気がする。（Markdown → HTML の正式な仕様ってあるのかしら？）
  - レアケースなので特に問題はないか？
  - 具体例 : ```# hoge  hoge``` は ```hoge-hoge``` と変換されるが、GitHubのwikiだと ```hoge--hoge``` となる。

## 参考
- [本家リポジトリ](https://github.com/nok/markdown-toc)
- [Sorixさん修正リポジトリ](https://github.com/Sorix/markdown-toc/)
- [atomのmarkdown-tocの設定変更方法と日本語リンクしない理由をcofeee-scriptで探る](http://blog.chaspy.me/entry/2016/03/15/120000)
