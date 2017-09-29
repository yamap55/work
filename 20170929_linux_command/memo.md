# lsとpwdをコマンドプロンプトで使う
```
c:\work\20170929>ls
'ls' は、内部コマンドまたは外部コマンド、
操作可能なプログラムまたはバッチ ファイルとして認識されていません。
```

## はじめに
コマンドプロンプトで「ls」や「pwd」と打って怒られたので、カッとなってやりました。

真面目に使いたいなら↓辺りを参考に色々使ってみれば良いと思います。

- [Windowsで使えるターミナルとシェルのまとめ](https://qiita.com/Ted-HM/items/9a60f6fcf74bbd79a904)

## 手順
1. 以下の内容を「ls.bat」というファイル名で保存。
```dosbatch
@echo off
dir %1
```

2. 以下の内容を「pwd.bat」というファイル名で保存。
```dosbatch
@echo off
@cd
```

3. 1,2で作成したファイルをパスの通った所に置く。
4. 使う。

```dosbatch
c:\work\20170929>ls
 ドライブ C のボリューム ラベルは OS です
 ボリューム シリアル番号は DE0A-E8AA です

 c:\work\20170929 のディレクトリ

2017/09/29  04:51    <DIR>          .
2017/09/29  04:51    <DIR>          ..
               0 個のファイル                   0 バイト
               2 個のディレクトリ  173,906,067,456 バイトの空き領域

c:\work\20170929>pwd
c:\work\20170929
```

## コマンドでやる
```dosbatch
mkdir c:\shortcuta
cd c:\shortcuta
echo @echo off >> ls.bat
echo dir %1 >> ls.bat
echo @echo off >> pwd.bat
echo @cd >> pwd.bat
set PATH=%PATH%;c:\shortcut
ls
pwd
```

環境変数に追加は手でやってください。

## 後書き
Windowsでもエイリアスの設定ができるらしいので、こっちが楽かも。
- 参考 : [Windowsのコマンドプロンプトでaliasを設定する (cmderの設定含む)](https://qiita.com/little_hand_/items/91d6bcb680eba10da835)
