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
-
