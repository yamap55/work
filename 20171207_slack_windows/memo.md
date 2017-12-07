# Windowsから定期的にSlackに投稿する
Windowsのタスクスケジューラでbash上でシェルを動かして、Slackにメッセージを投げる。

## もうちょっと詳しく
- 定期実行にはWindowsデフォルトのタスクスケジューラを使用。
- Slackに投げ込むにはシェルを使用。
  - バッチで実行するのは面倒っぽかったので。。。
- Slackにメッセージを投げるには[Incoming WebHooks](https://api.slack.com/incoming-webhooks)を使用。
- シェルを実行するにはWSL（Windows Subsystem for Linux 旧Bash on Ubuntu on Windows）でインストールされるbashを使用。

## 手順
- WSL（Windows Subsystem for Linux 旧Bash on Ubuntu on Windows）をインストール
  - 色々便利なので入っていない方はこの際入れましょう。
  - 参考 : [Windows Subsystem for Linuxをインストールしてみよう！](https://qiita.com/Aruneko/items/c79810b0b015bebf30bb)
- ↓からIncoming WebHooksを登録
  - [incoming webhook integration](https://my.slack.com/services/new/incoming-webhook/)
  - 2017/12/07現在のWindowsのクライアントからだとどこにリンクがあるのかわからず。
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

- 実行

## 参考
- [[10分で出来る]シェルスクリプトの結果をslackに投稿](https://qiita.com/tt2004d/items/50d79d1569c0ace118d6)

