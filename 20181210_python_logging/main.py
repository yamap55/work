import hoge

from logging import basicConfig, getLogger, DEBUG

# メインファイルに記載
FORMAT = '%(asctime)s %(levelname)s %(name)s %(message)s'
basicConfig(level=DEBUG, format=FORMAT)

# 日付フォーマットの指定が必要な場合はdatefmtを設定
# DATE_FORMAT = '%Y/%m/%d %H:%M:%S'
# basicConfig(level=DEBUG, format=FORMAT, datefmt=DATE_FORMAT)

# 使用する際に記載
logger = getLogger(__name__)

logger.debug('main dayo!')

hoge.huga()
