import sys
import hoge

from logging import basicConfig, getLogger, DEBUG, StreamHandler

# メインファイルに記載
FORMAT = '%(asctime)s %(levelname)s %(name)s %(message)s'
# handlersでstdoutを指定しないとstrerrに出力される
basicConfig(level=DEBUG, format=FORMAT, handlers=[StreamHandler(sys.stdout)])

# 日付フォーマットの指定が必要な場合はdatefmtを設定
# DATE_FORMAT = '%Y/%m/%d %H:%M:%S'
# basicConfig(level=DEBUG, format=FORMAT, handlers=[StreamHandler(sys.stdout), datefmt=DATE_FORMAT)

# 使用する際に記載
logger = getLogger(__name__)

logger.debug('main dayo!')

hoge.huga()
