# WSLでgoogle-images-downloadを使用する
## 概要
- WindowsのWSL上でgoogle-images-downloadを使用して、画像を集める

## 用語
- WSL
  - Windows Subsystem for Linux
  - Windows上でLinux動かすやつ
- google-images-download
  - Google画像検索を使用して画像を取得してくれる。
  - Seleniumを使用してスクレイピングで取得する。
  - Google画像検索は普通に取ろうとすると100枚しか取れないらしいので、画像集める際にはかなり有用。
  - https://github.com/hardikvasa/google-images-download

## 前提
- WSL上にPythonがインストールされていること
  - Python2でも3でも良いが3が推奨との事（私は3.5.2を利用）
- Windows上にChrome最新版がインストールされていること
  - 最新版でない場合にはChromeDriver取得の際に、適切なバージョンを選択してください。

## 手順
- WSL上にgoogle-images-downloadをclone
  - ```git clone https://github.com/hardikvasa/google-images-download.git```
- setup
  - ```cd google-images-download && sudo python setup.py install```
  - 私の場合は ```python3 setup.py install``` で実行
- 試す
  - ```googleimagesdownload --keywords "りんご"```
- 確認
  - ```downloads/りんご``` 配下に100枚弱のファイルがあればOK
- webdriverを取得
  - 100枚を超える場合にはSeleniumで取得する必要がある
  - ↓から **Windows用** のdriverを取得
    - http://chromedriver.chromium.org/downloads
  - Windows上のChromeをWSLから起動するため、Windows用のChromeDriverを使用する。
- 適当な所に解凍
- Chromeを呼び出すための設定
  - 「chrome」固定で呼び出すようなので、PATH設定してエイリアスにchromeを設定する。
    - Seleniumのオプションで指定できたと思うが、割愛。
  - ```export PATH=$PATH:"/mnt/c/Program Files (x86)/Google/Chrome/Application"```
  - ```alias chrome='chrome.exe'```
  - WSL上で ```chrome``` でWindows上でChromeが起動したらOK
- 実行
  - ChromeDriverのパスは適切なものに変更してください↓
  - ```googleimagesdownload --keywords "apple" --limit 1000 --chromedriver /mnt/c/work/github/google-images-download/chromedriver_win32/chromedriver.exe```
- 確認
  - ```downloads/apple``` 配下に1000枚弱のファイルがあればOK

## 感想
- 最初、自力でスクレイピングするつもりだったので、簡単に取得できて凄い。
- が、多分Google側も構成変更したりと対応するだろうから、定期的に実行するのであればリポジトリは常に最新にしておく必要はあると思われる。
- Chromedriverの手動ダウンロードが面倒。
  - Chromeがバージョンアップするたび？にChromeDriverの更新が必要。
  - Javaでもあったけど、[selene](https://github.com/yashaka/selene)で使用してた[webdriver_manager](https://github.com/SergeyPirogov/webdriver_manager)を組み込んだら喜ばれるのだろうか？
- Mac環境で ```setup.py install``` したらうまく行かなかった。。。わからないままやっている部分が多いので、Pythonの環境設定は嫌な感じ。
