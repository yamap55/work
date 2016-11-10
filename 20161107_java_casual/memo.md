# Java Casual
## 日時
- 日時 : 2016/11/07 19:00-21:30
- 場所 : 渋谷ヒカリエ27階 LINE株式会社
- ハッシュタグ #javacasual

## 概要
> Java についてカジュアルに語るゾイ

## 関連URL
- [Java Casual #2](http://java-casual.connpass.com/event/42461/)
  - 告知ページ
- [イベント資料一覧](http://java-casual.connpass.com/event/42461/presentation/)

## 19:30-19:35	@tokuhirom	開会、注意事項伝達等
- Line Liveで配信。
- トイレから帰れなくなったらツイートしてください。

## 19:35-20:05	@sugarlife	診断ツール jcmd を使いこなそう！ (+チョットHeapStats)

### Slide
  - [jcmd #javacasual](http://www.slideshare.net/YujiKubota/jcmd-68318298)

### メモ
- JDK9からの診断ツール
- 今までのjmapなどのツールはExperimental。
  - サポートなどは提供されていない。
- jcmdはExperimentalではない。
- これらはServiceabilitry Tool（jcmdを含む）

- jcmdとは
  - CLIのLOCAL JVMの process 診断ツール

- PerfCounter
  - JVMCounterを出力
- ManagementAgent
- Thread
  - Thread dump
- GC
  - GC機構（Heap/GC/Finalization）
  - ヒープダンプ、クラスメタデータ一覧など
- VM
  - ランタイム周りの情報出力系。
  - Fatal Error Logの出力
  - JVMの触っているメモリの状態を記録。
  - ランタイム周り。ログ。
    - 今までのログ出力オプションは全部変更される！！
- Compiler
  - コンパイラがどう動いているかがでる？
  - JITが最適化されているかどうかを見たりするときに見るらしい。
- JVMTI
  - JVMTI Agent 制御

---

- HeapStats
  - CM
  - OutOfMemoryErrorの調査などでどうぞ。

---

- とりあえず怪しいと思ったら以下を実行して欲しい
  - 色々なものがどばっと出力される。
```sh
jcmd <pid> VM.info
```

## 20:05-20:15	@nabedge	foo.properties, foo_ja.propertiesをfoo.yml, foo_ja.ymlに変えようと思った動機と案外面倒だったという話
### Slide
[properties, yaml, and me ](http://www.slideshare.net/nabedge/properties-yaml-and-me)

### メモ
- propertiesファイルのプルリクエスト飛んできてもdiff見てもわからない！！
- Eclipseは小文字でエンコード、intelliJ IDEAは大文字でエンコードする。
  - IDEによって差分が出てしまう！
- Java6以降からnative2asciiは不要。
- YAML
  - SnakeYamlなどで読みこむ
- ResourceBundleを使用して国際化しているときにハマる。
  - ResourceBundleを継承してロケールを判別したファイルを読み込むように自前で書く。

## 20:15-20:30		休憩
## 20:30-20:50	@hdkshjm	Jenkins and Maven Repo Tips
### Slide
- [SCM, CI and Maven Repo](http://www.slideshare.net/hideakiishijima/scm-ci-and-maven-repo)

### メモ
- タイトル変わった？？
- GitHub Enterprise
  - Repository1万
  - サイズで数百GB
  - git flowか、GitHub Flow
- Jenkins
  - 1つのJenkinsを共有
  - build時のJDKはOracke JDK
  - Job Template
    - Viewで正規表現でList upできて便利
    - Repositoryが消滅してもjob名から推察できる。
- Repo : Nexus Repository Pro
  - 外部RepoをReverse Proxy
    - Cacheしてくれるので早い。
    - 外部RepoがURL変更/消滅になっても、既存のpom/build.gradleは影響ない
  - Upload専用のinternal/nightly
    - JenkinsからのみRelease Artifactをupload可能。
    - Local PCから間違ってuploadする事件を予防
    - build方法の一子相伝の香典継承を予防
    - Maven Centralにないものは管理者に依頼。
  - Download専用のreleases/snapshot
    - Local PC or JenkinsからSNAPSHOTをupload可能
    - 定期的に古いSNAPSHOTは削除。
      - NEXUS内でそういう設定があるので楽に消せる。
- 開発プロセスを一気通貫で行えるようにしている。

- 1つのJenkinsで頑張る。
  - プラグインなどの競合は起きるけど、管理者が頑張ってる。
  - 改善点らしい。

## 20:50-20:55	@oklahomers	how to cook lettuce?
### Slide
- [How to cook lettuce](http://www.slideshare.net/Oklahomer/how-to-cook-lettuce-java-casual)

### メモ
- lettuce
  - Redis Client
  - Redis Cluseterをサポート
  - ノード情報のキャッシュやコネクションの適切な管理

## メモ補足
- Redisとは、メモリ上にKey-Valueストア（KVS）を構築することができるソフトウェアの一つ。

## 20:55-21:10	@komamitsu	Fluency - yet another fluent logger
### Slide
- [Fluency - Yet another fluent logger](https://www.slideshare.net/mitsunorikomatsu/fluency-yet-another-fluent-logger)

### メモ
- Fluencyとは？
  - fluent-loggerより4倍早い

## メモ補足
- Fluentdとは？
  - ログ収集をいい感じにやってくれる。
  - 分類とか、出力とか。

## 21:10-21:20	@tokuhirom	openjdkをいじるに当たっての心意気の話
### Slide
- [Openjdk 入門してみた話](https://www.slideshare.net/tokuhirom/openjdk)

### メモ
- Oracle JDKはOpen JDKを元に作成されている。
- 触るのは簡単。
- @sugarlife さんのスライドを読めばOK
- シンタックスとか簡単に作れるよー！

## [まとめ](https://slideck.io/github.com/yamap55/work/20161107_java_casual/matome.md)
