# Pythonからs3fsを使用してS3を操作した際にハマった
## はじめに
PythonsからS3をいじる際に、これまではaws cliを直接叩いていたのですが、s3fsを使用するとexistsやlsみたいなわかりやすい名称で使えるというのをどこかで知ったので使ってみた。

同名でS3をマウントするもの（ https://github.com/s3fs-fuse/s3fs-fuse ）もあるようですが、本記事でのs3fs（ https://github.com/dask/s3fs ）とは別物です。
で、キャッシュのせいかファイルを置いた直後にexistsするとFalseだったりと整合性がとれなくてハマった話です。

## s3fsとは
[リポジトリTOP](https://github.com/dask/s3fs)には下記のように記載されています。

> S3FS builds on boto3 to provide a convenient Python filesystem interface for S3.

> S3FSはboto3をベースにしており、S3用の便利なPythonファイルシステムインタフェースを提供します。

との事です。（Google翻訳）

「はじめに」にも書きましたが、[同名でS3をマウントするもの](https://github.com/s3fs-fuse/s3fs-fuse)もあるようですが、別物です。
むしろ、GitHubの☆を見てもマウントする奴が3000以上、本題の方が130位なので、どちらかというとこの記事に書くライブラリの方が無名。

## 使い方

インストールして（[詳細](https://s3fs.readthedocs.io/en/latest/install.html)）

```sh
pip install s3fs
```

以下のように書けばOK

```python
import s3fs
fs = s3fs.S3FileSystem(anon=True)
list = fs.ls('my-bucket')
```

[ドキュメント](https://s3fs.readthedocs.io/en/latest/index.html)がしっかりしているので、以下に気をつければ？簡単に使えて良いです。
が、気をつけるなんて現実的ではないので個人的にはあまりオススメしません。

## ハマったことと対応方法
内容としては、ファイル配置後に上位のフォルダを見た際にFileNotFoundErrorになるという事。
で、キャッシュが悪さしているので、キャッシュを更新すれば正しく動作します。
（キャッシュ更新するにはlsのrefreshオプションをTrueにする）

具体的には下記のコード参照。
（実際に試したい場合には↓↓に全コード書いたので、そちらをオススメ。）

```python
import s3fs

fs = s3fs.S3FileSystem(key=key, secret=secret)
# 特定のPATHの下にフォルダを挟んでファイルを配置
output_path = '{}/hoge/{}'.format(s3_path, 'test.txt')
fs.put('./test.txt', output_path)

# ファイルを配置したフォルダではなく上位のフォルダでlsするとFileNotFoundError
try:
    fs.ls(s3_path) # FileNotFoundError
except FileNotFoundError:
    print('ls : FileNotFoundError')
# existsでも同様に取得できない
if not fs.exists(s3_path):
    print('exists : False')
# 上位のフォルダから再帰的に削除しようとしてもFileNotFoundError
try:
    fs.rm(s3_path, recursive=True) # FileNotFoundError
except FileNotFoundError:
    print('rm : FileNotFoundError')

# lsにrefreshオプションがあったので付与すると取得できる
try:
    fs.ls(s3_path, refresh=True) # refresh=Trueをつける
    print('ls : refreshしたら例外なし')
except FileNotFoundError:
    pass

# 1度refreshするとexistsも問題なし
if fs.exists(s3_path):
    print('exists : refresh後はTrue')

# 同様にrefresh後はlsにrefreshつけなくても例外なし    
try:
    fs.ls(s3_path)
    print('ls : FileNotFoundErrorは発生しない')
except FileNotFoundError:
    pass
```

## 考察とか
- [rmは存在確認でexistsを呼び出して](https://github.com/dask/s3fs/blob/14765aa0c3ea7f23e9c744e634a38435092d94f6/s3fs/core.py#L954)、[existsはlsをrefreshなしで呼び出しています。](https://github.com/dask/s3fs/blob/14765aa0c3ea7f23e9c744e634a38435092d94f6/s3fs/core.py#L749)
    - なので、挙動を変更したければforkしてexistsのls呼び出している所にrefresh=TrueとすればOK。
- そもそもキャッシュを使用しないようにする方法はなさそう。
    - 使用しているライブラリboto3側で設定ができそうですが未調査。
- そもそもS3はフォルダという概念はなくて格納PATHはただのKEY。
    - 参考 : [Amazon S3における「フォルダ」という幻想をぶち壊し、その実体を明らかにする](https://dev.classmethod.jp/cloud/aws/amazon-s3-folders/)）
    - なので、途中のPATHではFileNotFoundなのもまぁ間違いではない気もしなくもない。が、直感的ではない。

## 全コード
```python
import s3fs

key = 'xxxxxxxxxx'
secret = 'yyyyyyyyyyyyyyy'
s3_path = 'my-bucket/aaa/bbb'

# 前準備
try:
    fs.ls(s3_path, refresh=True)
    fs.rm(s3_path, recursive=True)
except FileNotFoundError:
    pass
try:
    fs.ls(s3_path)
except FileNotFoundError:
    pass
try:
    fs.ls('{}/{}'.format(s3_path, 'hoge'))
except FileNotFoundError:
    pass

# 特定のPATHの下にフォルダを挟んでファイルを配置
output_path = '{}/hoge/{}'.format(s3_path, 'test.txt')
fs.put('./test.txt', output_path)

# ファイルが置かれていることを確認
try:
    fs.ls(output_path)
    print('ls : FileNotFoundErrorは発生しない')
except FileNotFoundError:
    pass
if fs.exists(output_path):
    print('exists : True')

# ここから本番
# ファイルを配置したフォルダではなく上位のフォルダでlsするとFileNotFoundError
try:
    fs.ls(s3_path) # FileNotFoundError
except FileNotFoundError:
    print('ls : FileNotFoundError')
# existsでも同様に取得できない
if not fs.exists(s3_path):
    print('exists : False')
# 上位のフォルダから再帰的に削除しようとしてもFileNotFoundError
try:
    fs.rm(s3_path, recursive=True) # FileNotFoundError
except FileNotFoundError:
    print('rm : FileNotFoundError')

# lsにrefreshオプションがあったので付与すると取得できる
try:
    fs.ls(s3_path, refresh=True) # refresh=Trueをつける
    print('ls : refreshしたら例外なし')
except FileNotFoundError:
    pass

# 1度refreshするとexistsも問題なし
if fs.exists(s3_path):
    print('exists : refresh後はTrue')

# 同様にrefresh後はlsにrefreshつけなくても例外なし    
try:
    fs.ls(s3_path)
    print('ls : FileNotFoundErrorは発生しない')
except FileNotFoundError:
    pass
```

## 参考URL
- [リポジトリ](https://github.com/dask/s3fs)
- [公式ドキュメント](https://s3fs.readthedocs.io/en/latest/)

