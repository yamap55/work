# PowerShellでJava実行する際にシステムプロパティを設定する際の注意点
## 概要
- PowerShellで ```java -Dsystem.language=ja Sample``` みたいな形でシステムプロパティを指定して実行すると正しく実行されない。
- 正しく実行する際には ```java "-Dsystem.language=ja" Sample``` のようにダブルクォーテーションで囲う必要がある。
- PowerShellは「-」の後に「.」が来ると区切りと判定するらしい

## 経緯とか
- 日本語版Stack Overflowで[質問発見](https://ja.stackoverflow.com/questions/50045)。
- ↓のJavaコマンドを投げても正しく動かないらしい。
  - ```java -cp libtensorflow-1.11.0.jar;. -Djava.library.path=.\jni HelloTensorFlow```
- エラーメッセージを見るとクラスが正しく認識でされてないっぽいので、コマンドのどこかにスペース入ってるんじゃね？っと思ったら違うらしい。
  - エラーメッセージ : ```エラー: メイン・クラス.library.path=.\jniが見つからなかったかロードできませんでした```
- やり取りしていたら、PowerShell上ではオプションが正しく設定されないという事が発覚。
- 調べてみたらPowerShellの仕様っぽい。

### echoの例
- 正常系1

```
PS C:\work\20181108> echo a b
a
b
```

- 正常系2

```
PS C:\work\20181108> echo a.b
a.b
```

- 想定外1

```
PS C:\work\20181108> echo -a.b
-a
.b
```

- 想定外2

本題ではないですが、「,」を入れてみたら分割されました。

```
PS C:\work\20181108\j> echo a,b
a
b
```

## 参考URL
- [Why does PowerShell split arguments containing hyphens and periods?](https://stackoverflow.com/questions/28704867)
  - 本家Stack Overflow
- [period in arguments](https://social.technet.microsoft.com/Forums/en-US/62d48584-fe25-43d5-8693-4c9b946cc1d4)
  - MicrosoftのQAサイト？
- [PowerShellコマンド環境で引っかかったこと](http://d.hatena.ne.jp/torutk/20160517/p1)
  - 日本語情報（↑↑のStack Overflowにたどり着いています）
