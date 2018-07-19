# オブジェクトの配列をPandasのDataFrameに変換する
オブジェクト（エンティティとか、JavaBeansとか、DTOとか呼ばれる属性（フィールド）しか持たない奴）の配列をPandasのDataFrameに変更したメモです。
簡単にできそうだったけどできなかったので結局dictionaryに変換して突っ込みました。
PythonにもPandasにも詳しくないのですが、こういうケースはよくあると思ったのですが、あまりない？？

## コード

```python
import pandas as pd

class Hoge:
    c1 = ''
    c2 = ''
    c3 = ''

    def __init__(self, *args):
        self.c1 = args[0]
        self.c2 = args[1]
        self.c3 = args[2]

hoge1 = Hoge('c1_1','c2_1','c3_1')
hoge2 = Hoge('c1_2','c2_2','c3_2')
hoge3 = Hoge('c1_3','c2_3','c3_3')

ar = [hoge1, hoge2, hoge3]

# 最初こんなのでできるかなと思ってました。
df1 = pd.DataFrame(ar)
print(df1) # 1列3行でHogeオブジェクトが設定される（当たり前）

# 最終的にこうなりました。
df2 = pd.DataFrame(list(map(lambda hoge: hoge.__dict__, ar)))
print(df2)
```
