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
print('d-----------')
print(len(df) == len(df['datetime'].unique()))
print('e-----------')
df.drop_duplicates()
print(df.shape)
print(df.describe())
print('f-----------')
df.set_index('datetime', inplace=True)
print(df.head())
print(df.index)
df.rename(columns={'y': 'sales'}, inplace=True)
print(df.head())
print(df.sort_values(by='sales', ascending=True).head())
print('g-----------')
print(df.sort_values(['sales', 'temperature'], ascending=False).head())
print(df.index)
df.index = pd.to_datetime(df.index, format='%Y-%m-%d')
print(df.index)
print(df.sort_index().head())
print('h-----------')
print(df.resample('m').mean())
df['month'] = list(pd.Series(df.index).apply(lambda x: x.month))
df['day'] = list(pd.Series(df.index).apply(lambda x: x.day))
print(df.head())
labels = ['上旬', '中旬', '下旬']
df['period'] = pd.cut(list(df['day']), bins=[0, 10, 20, 31], labels=labels, right=True)
print(df.head())
