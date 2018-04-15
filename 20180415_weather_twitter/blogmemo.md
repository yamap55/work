
## OpenWeatherMapAPIのAPIキーを取得
- [OpenWeatherMap](https://openweathermap.org/api)にアクセス → [Sign Up](https://home.openweathermap.org/users/sign_up)
  - 既に登録済みの人はSign In
- [API keys](https://home.openweathermap.org/api_keys)でKeyを確認
- 検索したい都市名を取得
  - [ここのJSON](http://bulk.openweathermap.org/sample/)から調べる
  - 面倒なら「tokyo」、「osaka」、「nagoya-shi」、「london」とか
- ↓のURLに入れこんで確認
  - ```http://api.openweathermap.org/data/2.5/weather?q=${都市名}&appid=${API Key}```
