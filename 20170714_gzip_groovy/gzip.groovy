/*
�J�����g�f�B���N�g���ɂ���u.tsv�v�t�@�C����gzip���k���܂��B
���t�@�C���́ubk_yyyyMMddHHmmss�v�A���k��̃t�@�C���́uoutput_yyyyMMddHHmmss�v�ɏo�͂���܂��B

���s�R�}���h
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
