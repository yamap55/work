# Fork後に歴史を消したリポジトリで歴史を復活させたい

stackoverflowで質問してみた。
https://ja.stackoverflow.com/q/36735/13022

---

ある程度コミットログがあるリポジトリを、Clone後にコミットログを消して1からログを積み上げているというリポジトリがあります。
このリポジトリに、Clone元のコミットログを追加（復元？）する方法を探しております。

説明ができているか不安なので、下記にやりたい事の手順を記載致しました。
不明点などありましたらなるべく早く回答するように致します。
良い方法がありましたら教えてください。

## 再現手順
1. Gitでリポジトリ「repo1」を作り、いくつかコミットをした後にGitHubにpushします。
2. 「repo1」を「repo2」をcloneし、「.git」を消します。
3. 「repo2」を再度Git管理します。（git init）
4. 「repo2」でいくつかコミットを行います。
  - first commitは「repo1」からcloneした時から変更はありません。
  - 歴史が1から作られます。
5. 「repo2」に「repo1」のコミットログを追加（復元？）したいです。

## 試したこと
- 一応以下の手順で復元ができることは確認できましたが、もっと良い方法（簡単な方法）があるのではないかと思っております。
- 「repo2」のFirst Commitの前に「repo1」のLast Commitを紐付けたいだけなので、方法がありそうだなと思っております。

```sh
git clone git@github.com:XXX/repo1.git repo1
cd repo1

git remote add git@github.com:XXX/repo2.git repo2

git checkout -b repo1_work
git branch remote/repo2/master repo2_work

git merge -Xtheirs --allow-unrelated-histories repo2_work
```
