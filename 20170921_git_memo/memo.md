# git merge squashの中に含まれてしまったコミットを分割する
起こったこと、内容はそんなに難しいことではないけど、言葉にするのは難しい。

## 現象
- masterからブランチAを作成。
- ブランチAでいくつかコミット。
- ブランチAからブランチBを作成。
- ブランチBでいくつかコミット。
- ブランチAからmaster、ブランチBからmasterにそれぞれプルリクエストを作成。
  - ブランチBはブランチAのコミットを全て含んでいる
- 本来であればブランチAをマージ後にブランチBをマージして欲しかったのだけど、ブランチBを先に「squash」マージしてしまった。

## 絵で表現
### 初期状態
```
*    branchBの修正コミット2（branchBのHEAD）
|
*    branchBの修正コミット1
|
*    branchAの修正コミット2（branchAのHEAD）
|
*    branchAの修正コミット1
|
*    master
```

### 結果

```
  *  branchA、branchBの修正全てを含んだコミット（masterのHEAD）
  |
* |  branchBの修正コミット2（branchBのHEAD）
| |
* |  branchBの修正コミット1
| |
* |  branchAの修正コミット2（branchAのHEAD）
| |
* |  branchAの修正コミット1
|/
*
```

### 理想

```
  *  branchBの修正全てを含んだコミット（masterのHEAD）
  |
  *  branchAの修正全てを含んだコミット
  |
* |  branchBの修正コミット2（branchBのHEAD）
| |
* |  branchBの修正コミット1
| |
* |  branchAの修正コミット2（branchAのHEAD）
| |
* |  branchAの修正コミット1
|/
*
```

## 操作
- GitHubにリポジトリ作成
- ローカルで↓実行
```
git clone git@github.com:xxxxx/repo1.git
cd repo1
git checkout -b branchA
echo "commit 1" >> commit1.txt
git add commit1.txt
git commit -m "commit1"
git checkout -b branchB
echo "commit 2" >> commit2.txt
git add commit2.txt
git commit -m "commit2"
git push origin --all
```
- GitHub上でbranchA, branchB共にプルリク作成
- branchBのプルリク上でsquashマージ

## memo
- squashオプション付きでマージしているので、戻すのは難しい？
- 多分、masterを戻して、プルリク出し直すのが一番早い？
- こういった場合、branchAから生えてるbranchBはbranchAに向けてプルリクすべき？
