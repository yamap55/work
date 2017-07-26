# JJUG ナイトセミナー 「Java O/Rマッパー特集」にさんかしてきました #jjug
久しぶりに？JJUGナイトセミナーに参加してきましたその際のメモになります

あくまでもメモなので、抜けている箇所や誤っている箇所がある可能性があります各スライドなどをご参照ください

## 概要
- [【東京】JJUG ナイトセミナー 「Java O/Rマッパー特集」 7/26(水)開催](https://jjug.doorkeeper.jp/events/63161)

- タイムテーブル

|- 時間 -|-	内容 -|
|---|---|
| 19:00-19:05 | オープニング |
| 19:05-19:30 | 「25分でわかるJPA」 |
| 19:30-19:55 | 「MyBatis を利用した Web Application 開発についてのご紹介」 |
| 19:55-20:05 | 休憩 |
| 20:05-20:30 | 「ざっくりわかるDoma」 |
| 20:30-20:55 | 「Reladomo入門」 |
| 20:55-21:00 | クロージング |
| 21:00- | 懇親会 |


## 25分でわかるJPA
- スピーカー
  - 多田真敏 ([@suke_masa](https://twitter.com/twit_hitter/suke_masa))
  - 株式会社カサレアル
- 発表資料
  - https://speakerdeck.com/masatoshitada/jpa-in-25min
- 25分でわかるJPA → 25分で（難しさが）わかるJPA
- JPAとは
  - JAVA EEの一部（仕様のみ）
  - JPA実装ライブラリ
    - 仕様のみなので実装が必要
    - 実装によって挙動がぜんぜん違う
    - Hibernate
      - 今回はこちらを使用して説明する
    - EclipseLink
  - SQLはかかない
- 実際に実行されるSQLは絶対にログに出せ！
- エンティティの状態
  - めっちゃ重要
  - 永続化コンテキストに含まれるか否か
  - EntityManagerのメソッドで状態を変更する
- エンティティの状態遷移のスライドが最大に重要
- mergeメソッドの引数のオブジェクトは状態変更されない
  - 返り値のオブジェクトが状態変更されている
- JPQLとは
  - SELECT,UPDATE,DEKETEのみ
  - INSERT、集合演算、FROMでの副問合せはできない
  - 殆どSQLと一緒だが、ちょっと違う
- リレーションとN+1問題
  - フェッチは基本LAZY
    - EAGERは不要なデータを読み込むため
  - LAZYからEAGERから動的に変更も可能
    - JOIN FETCH文
    - DISTINCTを使用する
      - SQLに加えてListの重複を取り除く
- Spring Data JPA
  - 超おすすめ
  - 便利機能満載
- JPAは使えるのか？
  - 使える
  - ただし、正しい知識、シチュエーションを選ぶ必要がある
- JPAを使っていい条件
  - DBを新規に設計できる
  - 集合演算やFROM区
  - パーフェクトJava EEを読破した人がプロジェクトに1人以上いる
  - ↑が1つでも当てはまらないならば他のORマッパーを使ったほうが良い

## MyBatis を利用した Web Application 開発についてのご紹介
- スピーカー
  - Tokuhiro Matsuno ([@tokuhirom](https://twitter.com/tokuhirom))
  - LINE Corp.
- 発表資料
  - https://www.slideshare.net/tokuhirom/mybatis-web-application
- MyBatisとは
  - 永続化フレームワーク
- SQLは直接書く
  - interfaceを通じて呼び出す
- 結果をBeanにMappingする
- シンプルなので学習コストが低い
- どんなスキーマでも使える
  - 手でSQLを書くため
  - 主キーがなくても
  - サブクエリなど複雑なクエリも簡単に使える
- 完全にDB製品に依存する
  - 手でSQLを書くため
  - JPAとか使っていても結局、なんだかんだあってそのまま移行は難しい
- 発行されるクエリが人間に読みやすい
  - 手で書くため
- 3つの書き方
  - XML
    - 主流
  - アノテーション
    - 最近、増えてきている
  - Java DSL（Statement builder）
    - これは異端とのこと
  - LineではXMLが73%アノテーションが27%の使用率
- 困ったときに検索するとStack Overflowに引っかかるので大丈夫
- Intellij IDEAがあれば、色々補完が効くので結構大丈夫
- Spring bootのサポートが手厚い
- Spring Initializrってのがかなりいいらしい
- 利点
  - SQLが直接書いてある
    - コードレビューが容易
    - スロークエリのリカバリが容易
  - ドキュメントが充実
    - Qiitaの記事が結構いい
  - コードがシンプル
- 欠点
  - XML前提で書かれているので、アノテーションで書く人はドキュメントが読みにくい

## ざっくりわかるDoma
- スピーカー
  - うらがみ (https://github.com/backpaper0)
  - 関西Javaエンジニアの会
- 発表資料
  - http://backpaper0.github.io/ghosts/doma-zakkuri/
- Domaとは
  - Pluggable Annotation Processiong APIを利用
  - ORMというよりResultSet Mapper
  - SelectはSQLファイルを書く
  - Java8対応
    - Optionalとかいい感じに使える
- DaoとエンティティでDB操作
- SQLはMETA-INF配下に置く
  - ない場合にはコンパイル時にエラーにしてくれる
- SQLにロジックもかけたりする（後が辛い
- Daoでできること
- Streamで複数件検索する場合はStreamを直接返すメソッドは警告がFunctionを返すメソッドを使用する
- forUpdate対応
- 挿入、更新、削除でSQLファイルを書くことも可能
- Domaができないこと
  - Selectクエリの自動生成
  - 親子などの構造を持ったエンティティへのマッピング
- ドメインクラス
  - StringやIntegerなどの基本型だらけになりがちだけれども、値オブジェクトを使用可能
  - 引数の順番間違えでもコンパイルエラーになるからわかりやすい
  - シグネチャに現れる
  - IDEの補完がきく
- ドメインクラスのデメリット
    - クラスが多くなる
    - 作るの面倒
- コンパイル時検査
  - 割といい感じにコンパイル時にエラー出してくれる
  - SQLファイル内で変数をバインドしているのに使ってないなどもエラー
  - タイポ検出が結構嬉しい
- Domaだけで使える
  - 依存jarがない
- Spring Bootにプラグインありで使いやすいとのこと
- Domaは日本語ドキュメントあり
- DomaはSeasarからの派生

## Reladomo入門
- スピーカー
  - 伊藤博志 ([@itohiro73](https://twitter.com/itohiro73))
  - 株式会社FOLIO
- 発表資料
  - https://www.slideshare.net/itohiro73/reladomo-jjug-jjug
- 「りらども」と読むらしい
- Relational Domain Object
- ゴールドマン・サックスが2016/9に公開
  - OSSとしては日が浅いが、2004年に開発開始している
- XMLからコード/DDLの自動生成
- 強力に型付けられたクエリ言語
  - SQLは書かない
- 具象クラス
- Finderクラスを用いてOperationを生成
  - 複雑なクエリを型安全で柔軟に書ける
- キャッシュ
  - 複数のキャッシュを持つ
  - キャッシュはかなり賢い
  - あまり深く考えなくても自動でDBアクセスを最小に保てる
- セッターに値渡すといきなり更新が走る
  - まとめたい場合にはトランザクションを作成する
- Listで一括で複数更新も可能
- 関連はXMLで定義
- N+1問題
  - deepFetchというAPIを利用する
  - 取得したい関連をdeepFetch指定
- みんなでアホになってフレームワークに任せましょう
- GS Collectionsのサポートが手厚い
- Eclipse Collectionsは現在サポートされていない
  - まもなくサポートされる予定
- JUnitサポート
  - h2でいい感じに使える
- バイテンポラルモデルサポート
  - キラーコンテンツ
  - 2つの時間概念を同時に扱うことができる
    - 有効時間
    - トランザクション時間
  - 詳細はJJUG CCCの資料をみて。
  - 関連も自由自在
