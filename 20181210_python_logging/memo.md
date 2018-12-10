# Pythonのloggingについて
## 概要
- Pythonでログを出力するための標準的な方法を調べたら、色々あってよくわからなかったのでまとめた。
- 「```logging.basicConfig```」で設定して「```logging.getLogger```」でインスタンス取得して使用する。

## 結論
### mainのスクリプト（main.py）
```python
import hoge
from logging import basicConfig, getLogger, DEBUG

# メインファイルに記載
# フォーマットの詳細はドキュメント参照（ https://docs.python.jp/3/library/logging.html#logrecord-attributes ）
FORMAT = '%(asctime)s %(levelname)s %(name)s %(message)s'
basicConfig(level=DEBUG, format=FORMAT)

# 日付フォーマットの指定が必要な場合はdatefmtを設定
# DATE_FORMAT = '%Y/%m/%d %H:%M:%S'
# basicConfig(level=DEBUG, format=FORMAT, datefmt=DATE_FORMAT)

# 使用する際に記載
logger = getLogger(__name__)

logger.debug('main dayo!')
hoge.huga()
```

### 下位のスクリプト（hoge.py）
```python
from logging import getLogger

logger = getLogger(__name__)


def huga():
    logger.debug('huga dayo!')
```

## 参考URL
- [ログ出力のための print と import logging はやめてほしい](https://qiita.com/amedama/items/b856b2f30c2f38665701)
    - 長いけど最後までしっかり読むこと。
- [Logging HOWTO](https://docs.python.jp/3/howto/logging.html)
    - 公式チュートリアル
    - ↑のQiitaの記事にある通り、最初の方だけ読んで終わりにしないこと。
- [logging — Python 用ロギング機能](https://docs.python.jp/3/library/logging.html)
    - 公式のlogging機能について
