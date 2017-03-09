# [memo]はじめてのMac

## はじめに
年末にはじめてMacを購入しました。（MacBook Pro 2016 13インチ）

10年以上ずっとWindowsのみだった((業務でLinuxを触ることはありますし、Macもアプリケーションの動作確認程度ならばありますが。。。))私がMacを触ったら困ったことだらけだった上、基本的な事がわからない場合には、調べても出て事ないという事が結構ありました。

WindowsからMacを触る方の役に立つかもしれないので、私の経験を記載しておきます。

## 基本操作
- <kbd>ctrl + space</kbd> 日本語切り替え
  - 英字キーボードの場合は英数、かなキーがない((当たり前))
- <kbd>ctrl + クリック</kbd> 右クリックメニュー
- 2本指でスクロール
- アプリケーションのメニューバーは最上部
  - Windowの上ではなく、画面の最上部
- FinderがWindowsでいうExplorer
  - 特定のディレクトリを開きたい場合は、メニュー → 移動 → フォルダへ移動

### 超基本ショートカット
- <kbd>command + c</kbd> コピー
- <kbd>command + v</kbd> 貼り付け

### キーの読み方？
- command ⌘
- shift ⇧
- option ⌥
- control ⌃
- caps lock ⇪

### Windowsと比較した場合の修飾キーについて
- WindowsでCtrlは大体CommandでOK。たまにCtrlの場合も。
- WindowsでALTはOption

### Spotlite検索
- <kbd>command + space</kbd> Spotlite検索
  - これさえ覚えておけばアプリケーション名入れれば呼び出せる
  - これを知るまでターミナルの開き方すらわからず。。。（terminalを入力すれば出てくる。）

### インストール
- 何かアプリケーションをインストールする場合、Windowsのようなインストーラーが出た場合は問題ないが、↓のようなのが出る場合があります。
  - これが出たら、アイコンをドラッグしましょう。
  - アプリケーションフォルダにバイナリを移動している？

![install](./pic1.png)

### エスケープ文字
- `Option + ¥`
  - Macでは円マーク（¥）とバックスラッシュ（\）はちゃんと区別される。

## 英字キーボードで英数、かなキー
- [⌘英かな](https://ei-kana.appspot.com/)
- [Karabiner-Elements](https://github.com/tekezo/Karabiner-Elements)

### Karabiner-Elements
- どこでもかしこでも勧められている。
- 以下の事前インストールが必要
  - XCode
  - Boost
    - 調べても入れ方出てこない。
    - `brew install boost`

## Gitのインストール
- Homebrewインストール時に必要となるXCodeのCommandLine Toolsでインストールされる
- が、バージョン低かったりと使いにくいのでHomebrewで別途インストールがオススメ
  - `brew install git`

## ちなみに個人的な課題
- トラックパッドの使い方
- Windowsで言うHOME、END的なショートカット
  - アプリによってfnだったり、Commandだったり。。。
- Optionキーをほとんど使ってない。
- Dockの整理
  - っというかDock使ってない。
