# 初めてのMacメモ
## ショートカット
### 前提
- command ⌘
- shift ⇧
- option ⌥
- control ⌃
- caps lock ⇪

### ショートカットキー
- <kbd>command + c</kbd> コピー
- <kbd>command + v</kbd> 貼り付け
- <kbd>command + space</kbd> Spotlite検索
- <kbd>ctrl + space</kbd> 日本語切り替え
- <kbd>command + ←</kbd> HOME
- <kbd>command + →</kbd> END
- <kbd>ctrl + クリック</kbd> 右クリックメニュー
- <kbd>command + Ctrl + f</kbd> 最大化切り替え

### Apple公式ショートカット
- https://support.apple.com/ja-jp/HT201236
- ショートカットキーの記載もあり。（「^」がコントロールとか。）
- とりあえずWindowsのCtrlをcommandに置き換えるイメージがいい感じかも。

## 基本操作？
- 2本指スクロールで画面スクロール
- ダウンロードしたら右下のゴミ箱横のアイコン
- アプリケーションのメニューバーは最上部
  - 画面の最上部
  - OSが出してる的な奴がアプリケーション毎に切り替わる
- Finder（Explorer的なやつ）で別のフォルダに移動する
  - メニュー → 移動 → フォルダへ移動
  - <kbd>Shift + Command + G</kbd>

## 日本語入力の設定
- 設定 → キーボード → 入力ソース → 日本語
- ライブ変換のチェックを外す
- Windows風のキー操作にチェック

## memo
- Spotlite検索でターミナル起動
- safariでhomebrew検索
    - ターミナルにインストールコマンドぶち込む
    - コマンドラインデベロッパーツールを入れろというのでインストール
        - ↓のXcode入れればOKだったっぽい
    - もう一回コマンド貼り付け
  ``      - 途中パスワード入れろとくるので入力
        - （/user/localにディレクトリ作るよってことかな？）
- Xcode
    - App Storeからインストール
    - MacやiPhoneのアプリを開発できるツールらしい。
    - homebrewやKarabiner-Elementsを入れる際に必要。
- アイコン変更
    - システム環境設定 → ユーザとグループ → アイコンに画像ドラッグ
- ショートカットキーとして記載されている「↑」はSHIFTキー
    - 言われてみたらiPhoneもそのように表示されていた気がする
- インストーラを起動時にアイコンだけ表示されているWindowが表示されていたらダブルクリックすることでWindows的にインストールが開始される、
- インストールしたいアプリケーションのアイコンと「→」とアプリケーションフォルダが表示されていたら、インストールしたいアプリケーションのアイコンをアプリケーションフォルダへドラッグ。
    - その通りで、実行ファイルをアプリケーションフォルダに移動してくれるっぽい
- Karabiner-Elements
  - [github](https://github.com/tekezo/Karabiner-Elements)のHow to buildをしっかり読む！！
  - Xcodeは公式から、
  - BoostはHomebrewでインストールすると楽
    - `brew search boost` で確認すると色々あるけど、`brew install boost`でインストールすると最新が入るっぽい。
  - Toggleの設定ができない（2017/01/05時点）
- ⌘英かな
  - [⌘英かな](https://ei-kana.appspot.com/)
  - Toggleの設定が可能。
- git
  - XCodeのCommandLine Toolsに含まれる？gitでも問題はないが、homebrewのgitに切り替えた方が色々便利。
  - 具体的な作業
    1. homebrewのgitに切り替え
    2. `.bashrc`に補完とブランチ名表示するように色々記載
    3. `.bashrc`を読み込むように`.bash_profile`に記載
  - [MacのHomeBrewでGitを2.7.0にアップデートしよう](http://qiita.com/suzutan/items/44bcf20df711675c525c)
    - タイトルと違う意味で参考。
  - [MacのGitで補完を効かせたりブランチ名を表示する設定(Homebrewでgitをインストールした場合)](http://qiita.com/koyopro/items/3fce94537df2be6247a3)
  - [本当に正しい .bashrc と .bash_profile の使ひ分け](http://qiita.com/magicant/items/d3bb7ea1192e63fba850)
- エスケープ文字
  - `Option + ¥`
  - Macでは円マーク（¥）とバックスラッシュ（\）はちゃんと区別される。


## 疑問点
- 一回大文字のみ入力可能になったけど、どうやって直せばよかったのか。
  - caps lockは解除済み

## インストール（インストーラ使用）
Homebrew-cask でインストール可能らしいが、色々不便なところもあるらしいので、とりあえずインストーラでインストール。

- Chrome
- Firefox
- Atom
- Google日本語入力
- jdk
- Sdkman
- Dropbox
- Clipy
