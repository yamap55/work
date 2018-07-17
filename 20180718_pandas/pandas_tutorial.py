import pandas as pd

df = pd.read_csv('./lunch_box.csv', sep=',')
print(df.head(3))
print(df.head())
print(df.tail())

print('----------')

print('dataframeの行数・列数の確認==>\n', df.shape)
print('indexの確認==>\n', df.index)
print('columnの確認==>\n', df.columns)
print('dataframeの各列のデータ型を確認==>\n', df.dtypes)

print('-----------')
print(df[['name', 'kcal']].head())
print('-----------')
print(df[100:106].head())
print('-----------')
print(df.loc[100])
print(df.iloc[[1,2,4],[0,2]])
print('a-----------')
print(df[df['kcal'] > 450])
print('b-----------')
print(df[['name','kcal']].query('kcal > 450 and name == "豚肉の生姜焼"'))
print('c-----------')
print(df['remarks'].unique())

