# 20181101_python_flake8
## preparation 前準備
```
pip install flake8
flake8 --install-hook git
vi ../.git/hooks/pre-commit
git config --bool flake8.strict true
```
## 設定の追加
- gitのpre-commitを設定した場合には、projectのルートに「.flake8」を置く

## 設定
- [ドキュメント](http://flake8.pycqa.org/en/latest/user/configuration.html#user-configuration)を読むのが一番
- とりあえず設定したのは↓

```
[flake8]
max-line-length = 120
```
