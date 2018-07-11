# SQLAlchemyのException
## 経緯とか
- 調査依頼を受けて調べてみた結果を記載。
- どこかの誰かは助かるかも的な奴。

## 調査依頼の内容
- SQLAlchemyでselectしようとしたら変な例外で落ちた。
- SQLを実行する前に落ちている。
- よくわからないオブジェクトでよくわからない例外が発生している。

## 概要
- 変なオブジェクトを挿入しようとしたので例外が発生。
- なんで変なオブジェクト挿入しようとしたのかというと、autoincrementされた値を取得したいため。

## 詳細
- insertしようとして、intのカラムにオブジェクトを設定する。
- オブジェクトを設定した際には型チェックしてくれないのでそのまま設定される。
- addしただけではDBに挿入されず、次のフラッシュでデータベースに反映されるため、例外は発生しない。
  - [公式ドキュメント](http://docs.sqlalchemy.org/en/latest/orm/session_api.html#sqlalchemy.orm.session.Session.add)
- selectを行う際にフラッシュの処理が走る。
- そこでaddを実際にinsertを行うための前処理？が走る。
- 設定された値の文字コードを変換する処理が走る。
- 「設定された値 = オブジェクト」なので、変換できない。
- 例外発生。

## 環境
- Windows10
- MySQL 5.7（Cloud SQL）
- Python 3.6.6
- PyMySQL 0.8.1
- SQLAlchemy 1.2.0

## 例外
```
Traceback (most recent call last):
  File "test.py", line 55, in <module>
    for row in session.query(tablea).all():
  File "C:\Users\username\AppData\Local\Programs\Python\Python36\lib\site-packages\sqlalchemy\orm\query.py", line 2726, in all
    return list(self)
  File "C:\Users\username\AppData\Local\Programs\Python\Python36\lib\site-packages\sqlalchemy\orm\query.py", line 2877, in __iter__
    self.session._autoflush()
  File "C:\Users\username\AppData\Local\Programs\Python\Python36\lib\site-packages\sqlalchemy\orm\session.py", line 1428, in _autoflush
    self.flush()
  File "C:\Users\username\AppData\Local\Programs\Python\Python36\lib\site-packages\sqlalchemy\orm\session.py", line 2237, in flush
    self._flush(objects)
  File "C:\Users\username\AppData\Local\Programs\Python\Python36\lib\site-packages\sqlalchemy\orm\session.py", line 2363, in _flush
    transaction.rollback(_capture_exception=True)
  File "C:\Users\username\AppData\Local\Programs\Python\Python36\lib\site-packages\sqlalchemy\util\langhelpers.py", line 66, in __exit__
    compat.reraise(exc_type, exc_value, exc_tb)
  File "C:\Users\username\AppData\Local\Programs\Python\Python36\lib\site-packages\sqlalchemy\util\compat.py", line 187, in reraise
    raise value
  File "C:\Users\username\AppData\Local\Programs\Python\Python36\lib\site-packages\sqlalchemy\orm\session.py", line 2327, in _flush
    flush_context.execute()
  File "C:\Users\username\AppData\Local\Programs\Python\Python36\lib\site-packages\sqlalchemy\orm\unitofwork.py", line 391, in execute
    rec.execute(self)
  File "C:\Users\username\AppData\Local\Programs\Python\Python36\lib\site-packages\sqlalchemy\orm\unitofwork.py", line 556, in execute
    uow
  File "C:\Users\username\AppData\Local\Programs\Python\Python36\lib\site-packages\sqlalchemy\orm\persistence.py", line 181, in save_obj
    mapper, table, insert)
  File "C:\Users\username\AppData\Local\Programs\Python\Python36\lib\site-packages\sqlalchemy\orm\persistence.py", line 866, in _emit_insert_statements
    execute(statement, params)
  File "C:\Users\username\AppData\Local\Programs\Python\Python36\lib\site-packages\sqlalchemy\engine\base.py", line 948, in execute
    return meth(self, multiparams, params)
  File "C:\Users\username\AppData\Local\Programs\Python\Python36\lib\site-packages\sqlalchemy\sql\elements.py", line 269, in _execute_on_connection
    return connection._execute_clauseelement(self, multiparams, params)
  File "C:\Users\username\AppData\Local\Programs\Python\Python36\lib\site-packages\sqlalchemy\engine\base.py", line 1060, in _execute_clauseelement
    compiled_sql, distilled_params
  File "C:\Users\username\AppData\Local\Programs\Python\Python36\lib\site-packages\sqlalchemy\engine\base.py", line 1200, in _execute_context
    context)
  File "C:\Users\username\AppData\Local\Programs\Python\Python36\lib\site-packages\sqlalchemy\engine\base.py", line 1416, in _handle_dbapi_exception
    util.reraise(*exc_info)
  File "C:\Users\username\AppData\Local\Programs\Python\Python36\lib\site-packages\sqlalchemy\util\compat.py", line 187, in reraise
    raise value
  File "C:\Users\username\AppData\Local\Programs\Python\Python36\lib\site-packages\sqlalchemy\engine\base.py", line 1193, in _execute_context
    context)
  File "C:\Users\username\AppData\Local\Programs\Python\Python36\lib\site-packages\sqlalchemy\engine\default.py", line 507, in do_execute
    cursor.execute(statement, parameters)
  File "C:\Users\username\AppData\Local\Programs\Python\Python36\lib\site-packages\pymysql\cursors.py", line 168, in execute
    query = self.mogrify(query, args)
  File "C:\Users\username\AppData\Local\Programs\Python\Python36\lib\site-packages\pymysql\cursors.py", line 147, in mogrify
    query = query % self._escape_args(args, conn)
  File "C:\Users\username\AppData\Local\Programs\Python\Python36\lib\site-packages\pymysql\cursors.py", line 127, in _escape_args
    return dict((key, conn.literal(val)) for (key, val) in args.items())
  File "C:\Users\username\AppData\Local\Programs\Python\Python36\lib\site-packages\pymysql\cursors.py", line 127, in <genexpr>
    return dict((key, conn.literal(val)) for (key, val) in args.items())
  File "C:\Users\username\AppData\Local\Programs\Python\Python36\lib\site-packages\pymysql\connections.py", line 846, in literal
    return self.escape(obj, self.encoders)
  File "C:\Users\username\AppData\Local\Programs\Python\Python36\lib\site-packages\pymysql\connections.py", line 839, in escape
    return converters.escape_item(obj, self.charset, mapping=mapping)
  File "C:\Users\username\AppData\Local\Programs\Python\Python36\lib\site-packages\pymysql\converters.py", line 27, in escape_item
    val = encoder(val, mapping)
  File "C:\Users\username\AppData\Local\Programs\Python\Python36\lib\site-packages\pymysql\converters.py", line 118, in escape_unicode
    return u"'%s'" % _escape_unicode(value)
  File "C:\Users\username\AppData\Local\Programs\Python\Python36\lib\site-packages\pymysql\converters.py", line 73, in _escape_unicode
    return value.translate(_escape_table)
AttributeError: 'ResultProxy' object has no attribute 'translate'
```

## 再現コード
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

    id = Column(INTEGER, primary_key=True, autoincrement=True)
    name = Column(TEXT)

class tableb(Base):
    """テーブルの定義"""
    __tablename__ = 'tableb'

    id = Column(INTEGER, primary_key=True, autoincrement=True)
    tablea_id = Column(INTEGER)

if __name__ == "__main__":
    # MySQLに接続。
    url = 'mysql+pymysql://user:password@hostname/dbname?charset=utf8'
    engine = create_engine(url, echo=True)

    engine.execute('DROP TABLE IF EXISTS {}'.format("tablea"))
    engine.execute('DROP TABLE IF EXISTS {}'.format("tableb"))

    # テーブル作成
    Base.metadata.create_all(engine)

    # セッションの作成
    Session = sessionmaker(bind=engine)
    session = Session()

    # autoincrementのidカラムには何も指定せずに挿入
    tablea1 = tablea(name='太郎')
    session.add(tablea1)

    # autoincrementされたidを取得するために、MySQLのlast_insert_idを使用する
    lastindex = session.execute("select last_insert_id() as id")

    # 取得したIDを別テーブルに挿入
    # ※取得したオブジェクトをそのまま設定しているが、設定時、add時共にエラーとならない
    tablea2 = tableb(tablea_id=lastindex)
#    tablea2 = tableb(tablea_id=10)
    session.add(tablea2)

    # 全件出力
    print("全件出力")
    # 全然関係ないselect投げる際に例外発生
    for row in session.query(tablea).all():
        print(row.id, row.name)

#    session.commit() # commitしなければrollbackされる
```

## 学んだこととか
- 型が恋しい。
  - intのカラムにオブジェクト突っ込んだ時に例外として欲しい。
- session.query()はflushするが、session.execute()はflushしない？
  - 上記のコードを正しく動かすために、「```session.execute("select last_insert_id() as id").fetchone().id```」とすると動作はするが、意図した値にならない。
    - 0が返ってくる。（本当は1が返ってきて欲しい）
  - もし、このコードのまま使うのであればflush()してからlast_inset_id()を実行する必要がある。
  - ただ、flush()すると、挿入時に使用したオブジェクトに値を設定してくれるので、わざわざlast_insertで取得する必要はない。（TODO 参考）
- スタックトレースをよく見る。
  - SQLAlchemyを動作させるところから1時間位？かかってしまったがよくスタックトレースみたら、「ResultProxy」をエンコードしようとして落ちてる事、flush時に発生している事がわかるので、どこかで「ResultProxy」を値に突っ込んでいる事が原因とわかったはず。
  - あるあるだが、よく見ること大事。
