
## 前提
- Python3がインストールされている
- Twitterのアカウントを持っている
- AWSアカウントを持っている

## OpenWeatherMapAPIのAPIキーを取得
- [OpenWeatherMap](https://openweathermap.org/api)にアクセス → [Sign Up](https://home.openweathermap.org/users/sign_up)
  - 既に登録済みの人はSign In
- [API keys](https://home.openweathermap.org/api_keys)でKeyを確認
- 検索したい都市名を取得
  - [ここのJSON](http://bulk.openweathermap.org/sample/)から調べる
  - 面倒なら「tokyo」、「osaka」、「nagoya-shi」、「london」とか
- ブラウザで試す
  - ↓のURLに都市名とAPI Keyを入れこんで確認
  - ```http://api.openweathermap.org/data/2.5/weather?q=${都市名}&appid=${API Key}```

## Python3からOpenWeatherMapAPIを叩く
- ```requests``` というモジュールを使う例が多かったのでpipで入れる
  - 後でLambdaに乗せる時ために、「-t」オプションをつけてスクリプトと同じ場所にインストール
  - ```pip install requests -t .```
- コードを書いて実行

```python
import requests
import json

SEARCH_CITY = "tokyo"
OPEN_WEATHER_MAP_API_KEY = "取得したAPI Key"
API_URL = "http://api.openweathermap.org/data/2.5/weather?q={city}&APPID={key}"

# URIスキーム
url = API_URL.format(city=SEARCH_CITY, key=OPEN_WEATHER_MAP_API_KEY)
r = requests.get(url)

# 結果はJSON形式なのでデコードする
data = json.loads(r.text)
print(data)
```

## TwitterのAPI Tokenを取得
- Twitter Appsを登録
  - https://apps.twitter.com/
- Tokenの作成
  - Keys and Access Tokens → Create my Access Token
- 下記の4つをメモ
  - Consumer Key (API Key)
  - Consumer Secret (API Secret)
  - Access Token
  - Access Token Secret

## Twitterで名前更新
- ```twitter``` というモジュールを使う例が多かったのでpipで入れる
  - 後でLambdaに乗せる時ために、「-t」オプションをつけてスクリプトと同じ場所にインストール
  - ```pip install twitter -t .```
- コードを書いて実行

```python
import twitter

# メモしたキーとアクセストークンを設定
auth = twitter.OAuth(consumer_key="Consumer Key (API Key)",
                     consumer_secret="Consumer Secret (API Secret)",
                     token="Access Token",
                     token_secret="Access Token Secret")

t = twitter.Twitter(auth=auth)
t.account.update_profile(name="山ｐです。")
```

### ちなみに
- Tweetする場合
  - ```t.statuses.update(status="ついーとするないよう")```

## 天気を含めてTwitterの名前更新
```python
import requests
import json
import twitter


SEARCH_CITY = "tokyo"
OPEN_WEATHER_MAP_API_KEY = "取得したAPI Key"
API_URL = "http://api.openweathermap.org/data/2.5/weather?q={city}&APPID={key}"

# https://openweathermap.org/weather-conditions
WEATHER_EMOJI_MAP = {
    "01d": "☀",  # clear sky
    "02d": "🌤",  # few clouds
    "03d": "☁",  # scattered clouds
    "04d": "⛅",  # broken clouds
    "09d": "🌧",  # shower rain
    "10d": "🌦",  # rain
    "11d": "🌩",  # thunderstorm
    "13d": "🌨",  # snow
    "50d": "🌁"  # mist
}


def get_weather_icon():

    # URIスキーム
    url = API_URL.format(city=SEARCH_CITY, key=OPEN_WEATHER_MAP_API_KEY)
    r = requests.get(url)
    # 結果はJSON形式なのでデコードする
    data = json.loads(r.text)
    return data["weather"][0]["icon"].replace("n", "d")


def get_weather_emoji():
    return WEATHER_EMOJI_MAP.get(get_weather_icon())


def update_twitter_screen_name(name):

    # メモしたキーとアクセストークンを設定
    auth = twitter.OAuth(consumer_key="Consumer Key (API Key)",
                        consumer_secret="Consumer Secret (API Secret)",
                        token="Access Token",
                        token_secret="Access Token Secret")

    t = twitter.Twitter(auth=auth)
    t.account.update_profile(name=name)


def main():
    emoji = get_weather_emoji()
    twitter_name = "山ｐ" + emoji
    update_twitter_screen_name(twitter_name)


if __name__ == '__main__':
    main()
```

- 天気を絵文字に変換するのがかなり面倒なので、ここでは種類が少ないiconを使って変換してます。
- 正確に天気が欲しい場合には↓を見て変換すれば良いと思います。
  - https://openweathermap.org/weather-conditions

## Lambadaに乗せるために準備
### コードを変更
- mainではなく、eventとcontextを受け取るhandler関数を作成
  - 名前はなんでもいいけどとりあえず。
- 環境変数を設定できるのでコードからKeyなどを除去

```python
import requests
import json
import twitter
import os

SEARCH_CITY = os.environ["SEARCH_CITY"]
OPEN_WEATHER_MAP_API_KEY = os.environ["OPEN_WEATHER_MAP_API_KEY"]
TWITTER_CONSUMER_KEY = os.environ["TWITTER_CONSUMER_KEY"]
TWITTER_CONSUMER_SECRET = os.environ["TWITTER_CONSUMER_SECRET"]
TWITTER_TOKEN = os.environ["TWITTER_TOKEN"]
TWITTER_TOKEN_SECRET = os.environ["TWITTER_TOKEN_SECRET"]

API_URL = "http://api.openweathermap.org/data/2.5/weather?q={city}&APPID={key}"

# https://openweathermap.org/weather-conditions
WEATHER_EMOJI_MAP = {
    "01d": "☀",  # clear sky
    "02d": "🌤",  # few clouds
    "03d": "☁",  # scattered clouds
    "04d": "⛅",  # broken clouds
    "09d": "🌧",  # shower rain
    "10d": "🌦",  # rain
    "11d": "🌩",  # thunderstorm
    "13d": "🌨",  # snow
    "50d": "🌁"  # mist
}


def get_weather_icon():

    # URIスキーム
    url = API_URL.format(city=SEARCH_CITY, key=OPEN_WEATHER_MAP_API_KEY)
    r = requests.get(url)
    # 結果はJSON形式なのでデコードする
    data = json.loads(r.text)
    return data["weather"][0]["icon"].replace("n", "d")


def get_weather_emoji():
    return WEATHER_EMOJI_MAP.get(get_weather_icon())


def update_twitter_screen_name(name):
    # 取得したキーとアクセストークンを設定する
    auth = twitter.OAuth(consumer_key=TWITTER_CONSUMER_KEY,
                         consumer_secret=TWITTER_CONSUMER_SECRET,
                         token=TWITTER_TOKEN,
                         token_secret=TWITTER_TOKEN_SECRET)

    t = twitter.Twitter(auth=auth)
    t.account.update_profile(name=name)


def handler(event, context):
    emoji = get_weather_emoji()
    twitter_name = "山ｐ" + emoji
    update_twitter_screen_name(twitter_name)
```

### Zip圧縮
- ここでわざわざライブラリをスクリプトと同じ場所に置いていることが生きてくる
- フォルダ毎ではなくて、ファイルを直で圧縮
  - コマンド例 : ```zip -r upload.zip *```

## Lambadaに乗せる
- AWS Lambdaを開く
  - https://ap-northeast-1.console.aws.amazon.com/lambda/home
- 関数の作成
  - 名前 : 関数の名前
  - ランタイム : Python 3.6
  - ロール : 新しいロールを作成して選択
- とりあえず「Designer」は後回し
- 関数コード
  - コードエントリタイプ : .ZIPファイルをアップロード
  - ランタイム : Python 3.6
  - ハンドラ : スクリプト名.関数名
    - 例 : スクリプト名が「main.py」で、関数が↑の通り「handler」の場合「main.handler」
- 環境変数
  - SEARCH_CITY
  - OPEN_WEATHER_MAP_API_KEY
  - TWITTER_CONSUMER_KEY
  - TWITTER_CONSUMER_SECRET
  - TWITTER_TOKEN
  - TWITTER_TOKEN_SECRET
- テストイベントの選択
  - 今回はイベントの中身を何も使わないので「Hello World」でも何でも適当に作成
- **保存**
  - 忘れがち😉
- テスト！！
- 正しく実行されたことを確認
- Twitterの名前が変更されていることを確認
- Designer
  - CloudWatch Events
  - 新規ルール作成
  - ルールタイプ : スケジュール式
  - スケジュール式 : cron(1 * * * ? *)
    - ↑は1時間毎の設定
    - 詳細 : https://docs.aws.amazon.com/ja_jp/AmazonCloudWatch/latest/events/ScheduledEvents.html
- **保存**

## 参考
- [Weather Conditions](https://openweathermap.org/weather-conditions)
  - OpenWeatherMapAPIで返ってくる天気の一覧（多い）
- [Twitterの名前を5分毎に東京の天気☼☂☃と連動させるサーバレスプログラムを書いたら色々知らないことが出てきた話](https://qiita.com/issei_y/items/ab641746be2704db98be)
- [twitterモジュールのGitHub](https://github.com/sixohsix/twitter)
- [twitterモジュールのドキュメント](https://pypi.org/project/twitter/)
- [本家Twitter APIのドキュメント](https://developer.twitter.com/en/docs/accounts-and-users/manage-account-settings/api-reference/post-account-settings)
- [【AWS】Lambdaでpipしたいと思ったときにすべきこと](https://qiita.com/Hironsan/items/0eb5578f3321c72637b4)
