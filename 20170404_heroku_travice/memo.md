# Grailsアプリケーションをtravis CI、Herokuと連携する
## アジェンダ
[:contents]

## 概要
- タイトルの通りの事をやろうとしたら、結構戸惑ったためメモ。
  - Grails、Gradle、Travis CI、Heroku全てが全くわかっていなかったという事を実感。。。
- 尚、Herokuの設定については[公式にドキュメントがあった](https://devcenter.heroku.com/articles/deploying-gradle-apps-on-heroku#using-grails-3)上、[サンプルリポジトリ](https://github.com/kissaten/grails3-example)まである事に全部終わってから気づきました。こちらを参照した方が良いかと思います。
- 特に難しいことはしていませんが、誤っている点、もっと簡単な方法等がありましたらコメント、[ブコメ](http://yamap55.hatenablog.com/entry/2017/03/23/231132)、[Twitter](https://twitter.com/yamap_55)などで教えてください。

## 環境
- Grails Version: 3.2.8
- Groovy Version: 2.4.10
- JVM Version: 1.8.0_121

## 前準備
- Javaのインストール
  - Windowsの場合
    - [ここ](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)からインストーラを取得して実行
  - Macの場合
    - SDKMANでインストール
      - ```sdk install java```
    - HomeVrewでインストール
      - ```vrew install java```
- Grailsのインストール
  - Windowsの場合
    - ここからバイナリを取得して解凍
    - 必要があればPATHにbinを追加
  - Macの場合
    - SDKMANでインストール
      - ```sdk install grails```
- Herokuアカウント作成
  - https://www.heroku.com/
- Heroku CLI（Heroku Command Line Interface）のインストール
  - 元々はToolbeltという名称。（[参考](https://devcenter.heroku.com/articles/heroku-cli)）
  - Windowsの場合
    - [ここから](https://devcenter.heroku.com/articles/heroku-cli)インストーラ取得して実行。
  - Macの場合
    - ```brew install heroku```
- travis CIアカウント作成
  - https://travis-ci.org/
- travisのインストール
  - rubyが必要。
  - ```gem install travis```
- Gitのインストール
  - 手順は割愛
- GitHubの登録
  - 手順は割愛

## Grailsのデフォルトアプリを作成
- grailsコマンドでアプリケーションを作成。
```sh
grails create-app grails-heroku-example2
```
- アプリケーションを起動
```sh
cd grails-heroku-example2
./grailsw run-app
```
- [http://localhost:8080](http://localhost:8080)にアクセス
- 以下のような画面が表示されればOK
[f:id:yamap_55:20170405005011p:plain]

## Herokuで動作させる
- ```build.gradle``` にstageタスクを追記
  - [GradleでSpringBoot、Ratpack以外を使用する場合はstageタスクを実行するらしい](https://devcenter.heroku.com/articles/deploying-gradle-apps-on-heroku#verify-that-your-build-file-is-set-up-correctly)
```
task stage {
  dependsOn build
}
```
- Procfileをカレントに作成。
  - herokuでアプリケーションを実行するコマンドを記載する。
  - 今回の場合、javaコマンドで作成されたwarを起動する。（[Scriptとしてwarを起動する方法もあるらしい。](https://www.ntt-tx.co.jp/column/nakano_blog/20161209/)）
```
web: java -Dserver.port=$PORT $JAVA_OPTS -jar build/libs/grails-heroku-example2-0.1.war
```
- Gitで管理する
```sh
git init
git add .
git commit -m "first commit"
```
- Heroku　CLIでログイン
  - ```heroku login```
  - メールアドレスとパスワード入力。
- Heroku上にアプリケーションを作成
  - ```heroku create grails-heroku-example2```
  - アプリ名は省略も可能。
  - アプリ名はHeroku全体でユニークである必要があるので注意。
- リモートリポジトリが追加されていることを確認。
  - ```git remote -v```
```
heroku	https://git.heroku.com/grails-heroku-example2.git (fetch)
heroku	https://git.heroku.com/grails-heroku-example2.git (push)
```
- Herokuへデプロイ
  - ```git push heroku master```
  - ```BUILD SUCCESSFUL```的なメッセージが表示されればOK
- 確認
  - ```heroku open```
  - ローカルで実行時と同じ画面が出ればOK

## GitHubにpush
- GitHubにリポジトリを作成
- GitHubにpush
```sh
git remote add origin git@github.com:yamap55/grails-heroku-example2.git
git push -u origin master
```

## Travis CIと連携
- [Travis CI](https://travis-ci.org/)にログイン
- ↑でGitHubに作ったリポジトリをEnableにする。
  - 2017/04/04現在だと、Mｙ Repositoriesの横の+か、右上の自分のアイコンをクリックすることで自分のリポジトリの一覧が表示される。
  - リポジトリが表示されない場合には右上の「Sync account」を押下することで再同期されます。
- 空の連携設定ファイルをカレントに作成
```
touch .travis.yml
```
- ```.travis.yml``` を設定
  - ```travis setup heroku```
- ```.travis.yml``` に設定を追加
  - ```language: groovy```
  - ファイルの先頭に追加。
- 設定例
```
language: groovy
deploy:
  provider: heroku
  api_key:
    secure: （略）
  app: grails-heroku-example2
  on:
    repo: yamap55/grails-heroku-example2
```
- GitHubにpush
```sh
git add .
git commit -m "comment"
git push origin
```
- 確認
  - ```heroku open```
  - herokuのログを確認

## メモ
- [GrailsのwarをScriptとして起動する方法](https://www.ntt-tx.co.jp/column/nakano_blog/20161209/)もあるらしい。

## 参考URL
- [Deploying Gradle Apps on Heroku](https://devcenter.heroku.com/articles/deploying-gradle-apps-on-heroku)
  - Herokuの公式ドキュメント
- [Heroku Deployment](https://docs.travis-ci.com/user/deployment/heroku/)
  - Gravis CIの公式ドキュメント
- [Grails入門して、travis-ciとherokuの連携をしたった](http://kyokomi.hatenablog.com/entry/2014/05/10/171310)
- [Java1.7のplay2.2でTravis CIからherokuデプロイまでやった](http://kyokomi.hatenablog.com/entry/2014/03/30/213746)
- [Gradleでherokuアプリケーションを作成する](http://mike-neck.hatenadiary.com/entry/2014/06/14/202008)
