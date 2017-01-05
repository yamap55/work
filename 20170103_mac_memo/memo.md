# 初めてのMacメモ
## ショートカット
- command + c コピー
- command + v 貼り付け
- command + space Spotlite検索
- ctrl + space 日本語切り替え
- command + ← HOME
- command + → END
- ctrl + クリック 右クリックメニュー
- command + Ctrl + f 最大化切り替え

### 公式メモ
- https://support.apple.com/ja-jp/HT201236
- とりあえずWindowsのCtrlをcommandに置き換えるといい感じかも。

## 基本操作？
- 2本指スクロールで画面スクロール
- ダウンロードしたら右下のゴミ箱横のアイコン
- アプリケーションのメニューバーは最上部
  - 画面の最上部
  - OSが出してる的な奴がアプリケーション毎に切り替わる
- Finder（Explorer的なやつ）で別のフォルダに移動する
  - メニュー → 移動 → フォルダへ移動
  - Shift + Command + G

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
