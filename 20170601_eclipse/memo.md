#

# Pleiades4.6.3でSpring Bootの開発環境作成
- 完全な個人的なメモです。
  - 某所でハマった。
- そもそもSTSはプラグイン版じゃなくて単体パッケージ版を使用した方が良いって誰かが言ってた気がします。
  - [https://spring.io/tools/sts/all](https://spring.io/tools/sts/all)

## インストール
- Pleiadesインストール
  - [公式サイト](http://mergedoc.osdn.jp/)からダウンロード
    - Pleiades All in One 4.6.3.v20170422
    - Windows 64bit
    - Java
    - Standard Edition
  - 解凍
    - Windowsの場合は[注意点](http://mergedoc.osdn.jp/index.html#/pleiades.html#zip-notice)を忘れないように。((これってWindows10でも発生するの？))
- マーケットプレイスをインストール((今までEclipseのデフォルト機能だと思ってました))
  - メニュー → ヘルプ → 新規ソフトウェアのインストール
  - 作業対象 : Neon
  - 一般用ツール → マーケットプレース・クライアント
  - 再起動
- STSインストール（Spring Tool Suite）
  - メニュー → ヘルプ → Eclipse マーケットプレース
  - 検索 : STS
  - インストール
  - 再起動

## プロジェクト作成
- プロジェクトエクスプローラ上で右クリック → 新規 → プロジェクト
- Spring → Springスタータープロジェクト
- 設定はそのまま((ここでGradleを選んでいたらこの記事を書くこともなかった。))
- Spring Boot Version : 1.5.3
- Dependencies
  - DevTools
  - Web
  - Thymeleaf
  - H2
  - JPA
- 完了

## pom.xmlがエラーとなっているので解決する
- 「 **ライフサイクル構成でカバーされていないプラグインの実行** 」とのこと。
  - ググると以下の2つが出てくるはず
  - [Eclipseで「ライフサイクル構成でカバーされていないプラグインの実行」というエラーが出た場合](http://tyru.hatenablog.com/entry/20140905/eclipse_lifecycle_error)
    - この記事はpleiades4.4の時に参照されて、4.4.1では「m2e-apt」がデフォルトで入っているみたいです。((4.5.2, 4.6では入っていない。))
      - [[Pleiades] Java 8 正式対応！Eclipse 4.4 Luna リリース](http://qiita.com/cypher256/items/39a3b54522e2f1cf4aa5#m2e-apt-%E3%83%97%E3%83%A9%E3%82%B0%E3%82%A4%E3%83%B3%E8%BF%BD%E5%8A%A0)
  - [eclipse上で生成したSpring Rooプロジェクトのpom.xmlでエラー](http://blog.livedoor.jp/pg_spr/archives/1023860035.html)
-
