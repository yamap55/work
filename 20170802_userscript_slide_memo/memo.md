

## 面倒な仕事を少し楽にするユーザスクリプト

## 自己紹介

## 色々あるけど、すぐ仕事で使えますか？
- 普段はJava
- インフラ屋さんです。
- DBのプロです
- 営業です。
- Excel方眼紙作成

## ブラウザ使いますよね？

## 明日から使えます。

## 作ったもの
- 作成しているWebサービスのログイン
- 作成しているWebサービスでいい感じに入力
  - 登録画面とか
- 勤怠入力の補助

## サンプル
```javascript
// ==UserScript==
// @name         easy login.
// @namespace    http://tampermonkey.net/
// @version      1.0
// @description  簡易ログイン
// @author       yamap_55
// @match        http://localhost:8080/login
// @grant        none
// ==/UserScript==

(function() {
    'use strict';

    const loginUsers = [
        {id:"cpmadmin",pass:"cpmcpmcpm"},
        {id:"hoge",pass:"huga"},
    ];

    const div = document.createElement('div');
    const ul = document.createElement('ul');
    div.appendChild(ul);

    loginUsers.forEach((obj) => {
        const li = document.createElement('li');
        li.textContent = obj.id;
        li.addEventListener('click', (evt)=>{
            document.getElementById("accountId").value=obj.id;
            document.getElementById("loginPassword").value=obj.pass;
            document.getElementById("login_user").click();
        });
        li.style["text-decoration"] = "underline";
        li.style.cursor = "pointer";
        ul.appendChild(li);
    });
    document.getElementById("contents").appendChild(div);
})();
```

## 外部ライブラリの読み込みも可能
```javascript
// ==UserScript==
// @name         easy login for jQuery sample.
// @namespace    http://tampermonkey.net/
// @version      1.0
// @description  簡易ログイン
// @author       yamap_55
// @match        http://localhost:9000/login
// @require http://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js
// @grant        none
// ==/UserScript==

(function() {
    'use strict';

    const loginUsers = [
        {id:"cpmadmin",pass:"cpmcpmcpm"},
        {id:"hoge",pass:"huga"},
    ];

    loginUsers.forEach((obj) => {
        $("<li>").text(obj.id).on("click",()=>{
            $("#accountId").val(obj.id);
            $("#loginPassword").val(obj.pass);
            $("#login_user").click();
        }).css({"text-decoration":"underline","cursor":"pointer"}).appendTo("<ul>").appendTo("<div>").appendTo($("#contents"));
    });
})();
```
