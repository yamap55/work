# Windowsから定期的にSlackに投稿する
- WindowsからSlackにメッセージを投げたい事があって、面倒だろうなと思って調べてみたらあまりにも簡単だったのでメモ。
  - 自分通知用にSlackメッセージ投げたい事あるよね？ない？？
- 一言で言うと「Windowsのタスクスケジューラでbash上でシェルを動かして、Slackにメッセージを投げる。」だけ。
- Windows上でシェルが動くと楽で良い。

## もうちょっと詳しく
- 定期実行にはWindowsデフォルトのタスクスケジューラを使用。
- Slackにメッセージを投げるには[Incoming WebHooks](https://api.slack.com/incoming-webhooks)を使用して、規定のURLにPostする。
- Postするにはシェルを使用。
  - 素のバッチで実行するのは面倒っぽい。
- シェルを実行するにはWSL（Windows Subsystem for Linux 旧Bash on Ubuntu on Windows）でインストールされるbashを使用。

## 手順
- WSL（Windows Subsystem for Linux 旧Bash on Ubuntu on Windows）をインストール
  - 色々便利なので入っていない方はこの際入れましょう。
  - 参考 : [Windows Subsystem for Linuxをインストールしてみよう！](https://qiita.com/Aruneko/items/c79810b0b015bebf30bb)
- ↓からIncoming WebHooksを登録
  - [incoming webhook integration](https://my.slack.com/services/new/incoming-webhook/)
- 登録後に表示される「Webhook URL」を確認
- シェルを作成

```sh
#!/bin/sh
# Incoming WebHooksのURL
WEBHOOKURL="https://hooks.slack.com/services/XXXX/XXXX/XXXX"
# slack 送信チャンネル
CHANNEL="#test"
# slack 送信名
BOTNAME="my_bot"
# slack アイコン
FACEICON=":ghost:"

WEBMESSAGE="Hello Slack!"

#Incoming WebHooks送信
curl -s -S -X POST --data-urlencode "payload={\"channel\": \"${CHANNEL}\", \"username\": \"${BOTNAME}\", \"icon_emoji\": \"${FACEICON}\", \"text\": \"${WEBMESSAGE}\" }" ${WEBHOOKURL} > /dev/null
```

- タスクスケジューラ起動
  - Windowsキー → タスクスケジューラ検索
- 「基本タスクの作成」 or 「タスクの作成」
- 名前やトリガーは適切な値に設定
- 操作
  - 「プログラム/スクリプト」 : C:\Windows\System32\bash.exe
  - 「引数の追加」: 上記で作成したシェルのPATH

### 注意
- シェルの文字コードは「UTF-8」、改行コードは「LF」。
- シェルに色々書きたい人は、Ubuntuのデフォルトシェルがbashじゃなくてdashであることを頭に入れておいたほうが良いかも？

## Slackにメッセージ投げる部分抜粋
- Slackに投げるだけなら、curlが使える環境ならPostするだけなので超簡単。

```sh
curl -s -S -X POST --data-urlencode "payload={\"text\": \"test dayo\" }" https://hooks.slack.com/services/XXXX/XXXX/XXXX" > /dev/null
```

## Slackに投げる部分をもうちょっと汎用的にした奴
いくつか投げたい通知があったので、少し汎用的にして使っています。

```sh
#!/bin/sh

# Slack通知スクリプト
# 引数1（必須） : メッセージそのものか、メッセージが記載されたファイルPATH（PATHは/mnt/c/...形式）
# 引数2（任意） : 出力するユーザ名（未指定の場合は「mybot」）

MESSAGEFILE=$1

# slack 送信名
BOTNAME=$2
if [ "$BOTNAME" = "" ] ; then
  BOTNAME="mybot"
fi

ls ${MESSAGEFILE} > /dev/null 2>&1
if [ $? = 0 ] ; then
  WEBMESSAGE=`cat ${MESSAGEFILE}`
else
  WEBMESSAGE=${MESSAGEFILE}
fi
#WEBMESSAGE=`cat ${MESSAGEFILE}`

# Incoming WebHooksのURL
WEBHOOKURL="https://hooks.slack.com/services/XXXX/XXXX/XXXX""
# slack 送信チャンネル
CHANNEL="#test"
# slack アイコン
FACEICON=":ghost:"

#Incoming WebHooks送信
curl -s -S -X POST --data-urlencode "payload={\"channel\": \"${CHANNEL}\", \"username\": \"${BOTNAME}\", \"icon_emoji\": \"${FACEICON}\", \"text\": \"${WEBMESSAGE}\" }" ${WEBHOOKURL} > /dev/null
```

### 使う時
- shから

```sh
notification2slack.sh "ほげほげでした。" "ほげ通知"
```

- batから
```
bash "/mnt/c/tools/notification2slack.sh" "ほげほげでした。" "ほげ通知"
```

## 参考
- [[10分で出来る]シェルスクリプトの結果をslackに投稿](https://qiita.com/tt2004d/items/50d79d1569c0ace118d6)
  - 改行コード変換を行っているが、LFならば特に問題なく表示されたので、使用しませんでした。

