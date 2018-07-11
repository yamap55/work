# SQLAlchemyでAUTO_INCREMENTされた値を取得したい
## 結論
addした後にflushすると、addしたオブジェクトに設定されている。

## 環境
- MySQL 5.7（Cloud SQL）
- Python 3.6.6
- PyMySQL 0.8.1
- SQLAlchemy 1.2.0

## コード
```python
from sqlalchemy import Column
from sqlalchemy.dialects.mysql import INTEGER,TEXT
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker

Base = declarative_base()

class tablea(Base):
    """テーブルの定義"""
    __tablename__ = 'tablea'

    id = Column(INTEGER, primary_key=True)
    id2 = Column(INTEGER, primary_key=True, autoincrement=True)
    name = Column(TEXT)

if __name__ == "__main__":
    # MySQLに接続。
    url = 'mysql+pymysql://user:password@hostname/dbname?charset=utf8'
    engine = create_engine(url, echo=True)

    engine.execute('DROP TABLE IF EXISTS {}'.format("tablea"))

    # テーブル作成
    Base.metadata.create_all(engine)

    # セッションの作成
    Session = sessionmaker(bind=engine)
    session = Session()

    tablea1 = tablea(id=100, name='太郎')
    tablea2 = tablea(id=200, name='次郎')
    session.add(tablea1)
    session.add(tablea2)

    # addしただけではDBに反映されない
    # autoincrementされるid2についても値はNone
    print('add後のid2の値')
    print(tablea1.id2) # None
    print(tablea2.id2) # None
    session.flush()
    print('flush後のid2の値')
    print(tablea1.id2) # 1
    print(tablea2.id2) # 2

    # 全件出力
    print("全件出力")
    for row in session.query(tablea).all():
        print(row.id, row.id2, row.name)

#    session.commit() # commitしなければrollbackされる
```
