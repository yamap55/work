# WebAPI Example
- 2016/09/16の社内勉強会用

## ファイル概要
### index.html
- ログインアプリケーション
- htmlとjavascript（javascriptはHTMLに直書きしてます。）

### webapi_groovy
- Javaのフレームワークjerseyを使用しているProjectの一覧を返すWebAPIの実装。
- JavaとGroovyが入っていれば動くはずです。
- 詳細は[README](./webapi_groovy/README.md)を参照。

### webapi_node
- node.jsのexpressフレームワークを使用しているログインをするWebAPIの実装。
  - ちなみに、この動作を実現するだけならexpressは不要です。
- node.jsが入っていれば動くはずです。
- ライブラリなどは[README](./webapi_node/README.md)を参照。

## 実行のための前準備
- postgresqlを利用しているため、localにpostgresqlを作ってください。
- DB名は「webapi-example」で直指定してます。
- テーブル構成は以下の通り。

```sql
CREATE TABLE user_info
(
  id integer NOT NULL,
  name text,
  password text,
  CONSTRAINT user_pk_key PRIMARY KEY (id)
);
CREATE TABLE project
(
  id integer NOT NULL,
  user_id integer NOT NULL,
  project_name text NOT NULL,
  CONSTRAINT key_id PRIMARY KEY (id)
);
```
