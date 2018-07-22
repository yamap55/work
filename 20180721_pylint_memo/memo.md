# VSCodeで静的コード解析ツールPylintを使用する
## はじめに
PythonではPEP8というコード規約が一般的に使用されているようです。（[PEP 8 -- Style Guide for Python Code](https://www.python.org/dev/peps/pep-0008/)）

この規約に準拠したツールがいくつかあり、これ！っというものは特にないという印象です。
で、代表的なものとして、同名の[pep8](https://pypi.org/project/pep8/)があるのでややこしい。
ちなみにツールの方のpep8は名前を変更して[pycodestyle])(https://github.com/PyCQA/pycodestyle)となっていますので注意。（pep8は1.7.1が最終更新のようです。）
詳細はQiitaにまとまっている記事がありましたのでこちらを参照してください。（[pep8 が pycodestyle に変わった話](https://qiita.com/tell-k/items/da52229749a7242b4440)）

他にも[flake8](http://flake8.pycqa.org/en/latest/)や今回紹介する[Pylint](https://www.pylint.org/)が競合としてあるようです。
で、今回はPylintを使用することになっていたので、こちらをVSCodeで使用する方法について調べたメモです。

## 手順
- VSCodeでPythonを入れる際にはまず使用するであろう拡張機能[ms-python.python](https://marketplace.visualstudio.com/items?itemName=ms-python.python)を入れる
- 設定を確認
  - ```"python.linting.enabled"``` が ```true``` になっていること
  - ```"python.linting.pylintEnabled"``` が ```true``` になっていること
  - ```"python.linting.lintOnSave"``` はお好みで
  - ```"python.linting.pylintPath"``` このPATHでコマンドが実行されるので、PATHが通ってない場合には修正が必要です。
- Pylintの設定ファイルを作成
  - デフォルトだと警告が出てくれない事、厳しすぎる規約を変更したい事から設定ファイルを作成します。
  - 下記のコマンドでデフォルトの設定ファイルが作成されます。
  - ```pylint --generate-rcfile > .pylintrc```
    - **【注意】私の環境だとUTF-16で出力されたのでUTF-8に変換の必要がありました。**
  - 設定ファイルの場所は[公式ドキュメント](https://pylint.readthedocs.io/en/latest/user_guide/run.html#command-line-options)を参照。
    - workingdirectoryにpylntrcか、.pylintrcを置いておけばよいかと。
    - [日本語onlyの人は訳はここにありました](http://dackdive.hateblo.jp/entry/2016/01/25/123631)
- 指摘を確認
  - 問題ビューに指摘が表示されます。（macだとshift + command + m）
  - 明らかにおかしいコードで表示されない場合には、設定ファイルを一旦renameして指摘されるか確認しましょう。
- 設定ファイルを調整
- システム全体で警告を無効化する場合は設定ファイルを修正。
  - あるコードのみ例外的に無視したい場合にはコメントとして↓のように無視したいコードを入力すれば無視してくれます。
    - ```# pylint: disable=C0330```
    - あまり調べていないのですがファイル全体で無効化されるようなので注意。
  - 好みですが、私は以下を修正しました。
  - max-line-lengthを120
    - デフォルトの100だと厳しいと感じました。
  - good-namesにf,vを追加
    - スコープに関係なく、1から2文字の変数を許してくれません。
    - 実際のプロジェクトでは分析でよく使用される変数名を追加しています。

## 参考URL
- [Pylintのコード一覧](http://pylint-messages.wikidot.com/all-codes)
