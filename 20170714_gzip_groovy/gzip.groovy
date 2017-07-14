/*
カレントディレクトリにある「.tsv」ファイルをgzip圧縮します。
元ファイルは「bk_yyyyMMddHHmmss」、圧縮後のファイルは「output_yyyyMMddHHmmss」に出力されます。

実行コマンド
groovy ../gzip.groovy
*/

def dir = $/./$
def now = new Date().format('yyyyMMddHHmmss')
def bkDir = new File("${dir}/bk_${now}")
def outputDir = new File("${dir}/output_${now}")
if (!bkDir.exists()) {
  bkDir.mkdir()
}
if (!outputDir.exists()) {
  outputDir.mkdir()
}

new File(dir).eachFile {f ->
  if (f.isDirectory() || !(f.name =~ /\.tsv$/)) {
    return
  }
  println "${outputDir.absolutePath}/${f.name.replaceAll(/\.tsv$/,'')}"
  new AntBuilder().gzip(destfile:"${outputDir.absolutePath}/${f.name.replaceAll(/\.tsv$/,'')}") {
    tarfileset(file : f.absolutePath)
  }
  f.renameTo(new File(bkDir.absolutePath, f.getName())); 
}
