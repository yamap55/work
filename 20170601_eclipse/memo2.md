# Spring Bootを使ってWebアプリケーションを作成する（開発環境から）
## はじめに
- 某所でお題として与えられ、楽勝！って思ったら完遂できなかったので、ハマった所を解消し、再挑戦した際のメモです。
- 最良、最適な方法ではないと思いますので、参考にする場合には自己責任でお願いします。((ツッコミ歓迎))
- コードは一部しか記載しませんので、全てを確認したい場合には参考用コミットとしてGitHubへのリンクを記載してますので、こちらを確認してください。

## 要件とか
- 下記の要件でWebアプリケーションを作成する。
- 少しでも早く作成する。
- 指定されていない事は想定する必要はない。
  - 最低限要件を満たした上で1秒でも早く作成する。

### 環境
- まっさらなWindows
  - 開発環境。
  - 実際はWindows10だったのですが、用意できないので7で再挑戦。((何も変わる事はないはず。))
- Linux
  - この環境でポート80でアクセスできるWebアプリケーションを動作させる。
  - 実際はAWSでIPと秘密鍵は用意されてました。（SSHでログインは可能。）
  - 用意できないので、別マシン上にDockerで用意。

### アプリケーションの要件
- 問い合わせフォームを作る
- 「 http://xxx.xxx.xxx.xxx 」でアクセスすると問い合わせフォーム画面が表示される。
  - ポートは80
- 問い合わせフォーム画面は、「名前」、「Eメール」、「問い合わせ内容」の3つの入力が可能。
- それぞれの入力にそれなりにValidationをかける。
- 送信ボタンを押下すると完了画面が表示される。
- 送信ボタン押下時にDBに入力内容を保存する。
  - DBはなんでも良い
  - テーブル構成もなんでも良い

## リポジトリ
- この記事のコードは以下に置いてあります。
  - [https://github.com/yamap55/example_minimum_form_for_springboot](https://github.com/yamap55/example_minimum_form_for_springboot)
  - 手順に沿ってコミットも分けていますので、見たい方はどうぞ。

## Pleiadesインストール((そもそもSTSはプラグイン版じゃなくて単体パッケージ版を使用した方が良いって誰かが言ってた気がする。[https://spring.io/tools/sts/all](https://spring.io/tools/sts/all)))

- [公式サイト](http://mergedoc.osdn.jp/)からダウンロード
  - Pleiades All in One 4.6.3.v20170422
  - Windows 64bit
  - Java
  - Standard Edition
- 解凍
  - Windowsの場合は[注意点](http://mergedoc.osdn.jp/index.html#/pleiades.html#zip-notice)を忘れないように。((これってWindows10でも発生する？))
- STSインストール（Spring Tool Suite）
  - メニュー → ヘルプ → Eclipse マーケットプレース
  - 検索 : STS
  - インストール
  - 再起動

## プロジェクト作成
- プロジェクトエクスプローラ上で右クリック → 新規 → プロジェクト
- Spring → Springスタータープロジェクト
- Spring Boot Version : 1.5.3
- Dependencies
  - DevTools
  - Web
  - Thymeleaf
  - H2
  - JPA
- 完了

- [参考用コミット](https://github.com/yamap55/example_minimum_form_for_springboot/commit/899b089af7f6123ba915e0c511ac0fac111bc821
)

## 問い合わせ画面を作成
- Controllerを追加
  - com.example.demo.DemoApplication.java
    - クラス分ける時間が惜しいので、アプリケーションを起動するクラスに追加してしまいます。
```java
@RequestMapping("/")
public String index(Model model) {
	return "index";
}
```
- 画面を作成
  - src/main/resources/templates/index.html
  - 特に特筆すべき事はないのでコードは割愛。
- アプリケーション起動
  - プロジェクト右クリック → 実行 → Spring Boot アプリケーション
- ブラウザで確認
  - http://localhost:8080

- [参考用コミット](https://github.com/yamap55/example_minimum_form_for_springboot/commit/46b295f5dc1eb0be864294684329b9dfb5ca1831)

## 完了画面を作成
- 問い合わせ画面でボタン押下したら完了画面が表示されるようにします。
- 完了画面のマッピングを追加
  - com.example.demo.DemoApplication.java
```java
@RequestMapping("result")
public String result(Model model) {
	// TODO 入力チェック
	// TODO DBに保存
	return "result";
}
```
- 完了画面に遷移するように問い合わせ画面を変更
  - src/main/resources/templates/index.html
```html
-	<form role="form" action="#">
+	<form role="form" th:action="@{/result}" method="post">
```
- 画面を作成
  - src/main/resources/templates/result.html
  - 特に特筆すべき事はないのでコードは割愛。
- ブラウザで確認
  - http://localhost:8080

- [参考用コミット](https://github.com/yamap55/example_minimum_form_for_springboot/commit/c183b72986906bf9022c4d4136488e6ac1bee441)

## 入力チェックを追加
- 問い合わせ画面でボタン押下時に入力チェックが行われるように設定。
- formを作成
  - src/main/java/com/example/demo/InputForm.java

```java
public class InputForm {
	@NotEmpty
	@Size(min = 1, max = 50)
	private String name;

	@Size(max = 50)
	private String email;

	@NotEmpty
	@Size(max = 100)
	private String message;
  // getter, setterは略
}
```

- 入力チェックでエラーとなった場合には問い合わせ画面に戻す処理を追加。
  - com.example.demo.DemoApplication.java

```java
 -	public String result(Model model) {
 -		// TODO 入力チェック
 +	public String result(@ModelAttribute("inputForm") @Valid InputForm form, BindingResult bindingResult, Model model) {
 +	    if (bindingResult.hasErrors()) {
 +	        return index(form, model);
 +	    }
```

- エラーメッセージを表示するように修正。
  - src/main/resources/templates/index.html

```html
-			<p>名前 : <input type="text" name="name" /></p>
+			<p>名前 : <input type="text" th:value="*{name}" name="name" /></p>
+			<p><span
+				th:if="${#fields.hasErrors('name')}" th:errors="*{name}"></span>
+			</p>
<!-- 以下略 -->
```

- ブラウザで確認
  - http://localhost:8080

- [参考用コミット](https://github.com/yamap55/example_minimum_form_for_springboot/commit/7f912c9b86f2a8567d8a3e6889294f1ca37b9ef1)

## DBに保存する
- DB（H2）に入力情報を保存する。
- ブラウザで確認
  - http://localhost:8080
- ドメインを作成
  - src/main/java/com/example/demo/domain/Message.java

```java
@Entity
@Table(name="message")
public class Message {
	public Message(InputForm form) {
    // 何か他にいい方法ありそうだけど、コンストラクタでformから入れ替える
		this.message = form.getMessage();
		this.email = form.getEmail();
		this.name = form.getName();
	}
	@Id
	@GeneratedValue
	private int id;

	@Column
	private String name;

	@Column
	private String email;

	@Column
	private String message;
  // getter, setterは略
}
```
- リポジトリ作成
  - src/main/java/com/example/demo/repository/MessageRepository.java
```java
public interface MessageRepository extends JpaRepository<Message, Integer> {
}
```

- 保存処理を追加
```java
public class DemoApplication {
 +	@Autowired MessageRepository repository;
// 略
 -		// TODO DBに保存
 +		repository.save(new Message(form));
```

- ブラウザで確認
  - http://localhost:8080

- [参考用コミット](https://github.com/yamap55/example_minimum_form_for_springboot/commit/79e71d22b69db0a390b5c89b950b95e6e993d2c4)

## プロパティファイルは好きじゃないのでYAMLに変更
- src/main/resources/application.properties → src/main/resources/application.yml

- [参考用コミット](https://github.com/yamap55/example_minimum_form_for_springboot/commit/0b1a9e56f18f94b3ed79f5a9b020f3ba38cb29d0)

## DBに保存されている事を確認するためh2コンソールの表示設定を追加
- src/main/resources/application.yml
```yml
spring:
   h2:
     console:
       enabled: true
   datasource:
     url: jdbc:h2:mem:test
```

- ブラウザで確認
  - http://localhost:8080
  - http://localhost:8080/h2-console

- [参考用コミット](https://github.com/yamap55/example_minimum_form_for_springboot/commit/fb7a00e4435564a0a406bcaf45a82d94c052e8c5)

## 起動するポート番号を変更
- src/main/resources/application.yml
```yml
+server:
 +  port: 80
```

- ブラウザで確認
  - アプリケーションの再起動が必要かも。
  - http://localhost
  - http://localhost/h2-console

- [参考用コミット](https://github.com/yamap55/example_minimum_form_for_springboot/commit/3268894a96c02089c9ef6aabb15304dda1990f98)

## jarで出力する
- プロジェクト右クリック → 実行 → Maven install
- target配下にjarが出力されていることを確認。

## jar単体で起動
- コマンドプロンプト起動。
  - ```java -jar demo-0.0.1-SNAPSHOT.jar```
- ブラウザで確認
  - http://localhost
  - http://localhost/h2-console

## 動作環境の整備
- Linuxにログイン
- Javaのインストール
  - ```sudo yum install java-1.8.0-openjdk.x86_64```
- ↑で作成したjarをLinuxに移動
- ファイアーウォールを止める
  - ```sudo service iptables stop```
- 起動
  - ```java -jar demo-0.0.1-SNAPSHOT.jar```
- ブラウザで確認
  - http://xxx.xxx.xxx.xxx
  - http://xxx.xxx.xxx.xxx/h2-console

## まとめ
