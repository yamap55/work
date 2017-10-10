@Grab("org.codehaus.groovy.modules.http-builder:http-builder:0.7.1")
@Grab("org.apache.httpcomponents:httpmime:4.5.3")

import static groovyx.net.http.ContentType.*
import static groovyx.net.http.Method.POST
import static org.apache.http.entity.ContentType.TEXT_PLAIN
import static java.nio.charset.StandardCharsets.UTF_8
import groovyx.net.http.HTTPBuilder
import org.apache.http.entity.mime.MultipartEntityBuilder
import org.apache.http.util.EntityUtils
import groovy.json.*

// 確認↓
// https://gyazo.com/captures

// 環境変数に「gyazo.access.token」という名前でgyazoのTokenを設定しておく

def gyazoToken = System.getenv()["gyazo.access.token"]

def http = new HTTPBuilder("https://upload.gyazo.com")

def p = $/c:\work\20171005\selenide\output3.png/$
http.request( POST, TEXT ) { req ->
    uri.path = "/api/upload"
    req.entity = MultipartEntityBuilder
        .create()
        .setCharset(UTF_8)
        .addTextBody("access_token", gyazoToken, TEXT_PLAIN)
        .addBinaryBody('imagedata', new File(p))
        .build()

    response.success = { res, reader ->
      def root = new JsonSlurper().parseText(EntityUtils.toString(res.getEntity()))
      println root.url
    }
//    response.failure = { res, reader ->
//        // 失敗時
//    }
}


