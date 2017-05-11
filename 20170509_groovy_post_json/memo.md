# GroovyでJSONをPOSTする

## 概要
- ↓をGroovyでやりたい。
```
curl -v -H "Accept: application/json" -H "Content-type: application/json" -X POST -d '{"id":"abcdef","user":{"name":"tarou","age":20,"email":"example@example.com","result":true}}' https://script.google.com/macros/s/xxxxxxxxx/exec
```
- ```groovy.json.JsonBuilder``` を利用してJSONを作成。
- ```groovyx.net.http.HTTPBuilder``` を利用してPOSTする。

## コード
```groovy
@Grab("org.codehaus.groovy.modules.http-builder:http-builder:0.7.1")

import groovyx.net.http.HTTPBuilder
import groovy.json.JsonBuilder
import static groovyx.net.http.ContentType.*

def json = new JsonBuilder()
json {
  id "abcdef"
  user {
    name "tarou"
    age 20
    email "example@example.com"
    result true
  }
}
def postBody = json.toString()

assert postBody == $/{"id":"abcdef","user":{"name":"tarou","age":20,"email":"example@example.com","result":true}}/$
/*
{
  "id":"abcdef",
  "user":{
    "name":"tarou",
    "age":20,
    "email":"example@example.com",
    "result":true
  }
}
*/

def http = new HTTPBuilder("https://script.google.com/macros/s/xxxxxxxxx/")

http.post( path: 'exec', body: postBody,
           contentType: JSON ) { resp ->
  println "POST Success: ${resp.statusLine}"
}
```

- 昨日の記事「[JSONをPOSTしてGoogle SpreadSheetに書き込む](http://yamap55.hatenablog.com/entry/2017/05/09/025658)」で作成したGASにPOSTしてます。

## 参考
- [POST Examples](https://github.com/jgritman/httpbuilder/wiki/POST-Examples)
- [GroovyでJSONを使おう（その２）](http://npnl.hatenablog.jp/entry/20110226/1298729619)
