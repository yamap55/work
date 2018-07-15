from sqlalchemy import Column
from sqlalchemy.dialects.mysql import INTEGER,TEXT,BOOLEAN
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker

import pandas as pd

Base = declarative_base()

class tablea(Base):
    """テーブルの定義"""
    __tablename__ = 'tablea'

    id = Column(INTEGER, primary_key=True, autoincrement=True)
    name = Column(TEXT)
    flag = Column(BOOLEAN, default=False)
    c1 = Column(TEXT)
    c2 = Column(TEXT)
    c3 = Column(TEXT)


if __name__ == "__main__":
    # MySQLに接続。
    url = 'mysql+pymysql://user:pass@host/db?charset=utf8'
    engine = create_engine(url, echo=True)

    engine.execute('DROP TABLE IF EXISTS {}'.format("tablea"))

    # テーブル作成
    Base.metadata.create_all(engine)

    # セッションの作成
    Session = sessionmaker(bind=engine)
    session = Session()

    tablea1 = tablea(name='太郎', c1='a', c2='b', c3='c')
    tablea2 = tablea(name='次郎', c1='aa', c3='cc')
    session.add(tablea1)
    session.add(tablea2)
    session.flush()

    rows = session.query(tablea).all()

    features = pd.DataFrame(['1','2','3'])

    for row in rows:        
        for i, _ in features.iterrows():
            col_name = 'c{}'.format(i+1)
            # print(type(row))
            if getattr(row, col_name) is None:
                print(col_name, getattr(row, col_name), row.c1, row.c2, row.c3)
                row.flag = True
                # setattr

    session.commit() # commitしなければrollbackされる