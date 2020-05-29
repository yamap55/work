# refs. https://qiita.com/youwht/items/b047225a6fc356fd56ee
import random
import time

# キーボードイベントの追加
import keyboard
import pyautogui
import pyperclip

# https://github.com/boppreh/keyboard#keyboardon_press_keykey-callback-suppressfalse

# トリプルクォートで改行ありのそのまま文字列になる。
# ここに任意のコード群を記載すれば、
# ホットキーを押下するたびに、そのテキストが記載される
# 日本語の疑似変換は未対応（対応は容易だが面倒なので）

my_str_list = [
    """!pip install "https://github.com/megagonlabs/ginza/releases/download/latest/ginza-latest.tar.gz"
""",
    """import pkg_resources, imp
imp.reload(pkg_resources)
""",
    """import spacy
nlp = spacy.load('ja_ginza')
""",
    """
doc = nlp("今日は東京でピザパーティー。権兵衛さんの赤ちゃんが風邪ひいた。")
""",
    """for s in doc.sents:
  for t in s:
    info = [t.orth_, t._.reading, t.tag_]
    print(info)
""",
    """from spacy import displacy
displacy.render(doc, style='dep', jupyter=True, options={'distance': 90})
""",
    """
displacy.render(doc, style='ent', jupyter=True, options={'distance': 90})
""",
    """doc = nlp("NHKから国民を守る党から国民を守る党から国民を守る党から国民を守る党")
displacy.render(doc, style='dep', jupyter=True, options={'distance': 90})
""",
    """doc1 = nlp('このラーメンは美味しいなあ')
doc2 = nlp('カレーでも食べに行こうよ')
doc3 = nlp('ごめん、同窓会には行けません')
""",
    """print(doc1.similarity(doc2))
print(doc2.similarity(doc3))
print(doc3.similarity(doc1))
""",
]

# グローバル変数
now_counter = 0


# 主要実行用関数（引数は未使用）
def my_auto_func(arg_val):
    global now_counter
    # ホットキー設定に使ったキーが押しっぱなしのまま処理が進むのを防止。少しwait
    # 特に、コントロールキー等を押しっぱなしの場合、別の動作が入りやすいので注意
    time.sleep(1.5)

    print("called: " + str(now_counter))

    # キーが押されて出力する際に、次の出力すべきものが無い場合は処理終了
    if now_counter >= len(my_str_list):
        print("END: finish")
        exit()

    cp_str = ""
    # コピペ版
    for my_char in my_str_list[now_counter]:
        # 緩急をつけるために、特定条件なら複数文字同時貼り付け使用にした。
        sl_time = random.uniform(-0.03, 0.10)
        cp_str += my_char
        if sl_time < 0:
            # 貼り付けを実行せず継続
            continue
        else:
            # クリップボードに文字をコピーしておく
            pyperclip.copy(cp_str)
            # すべての文字列は貼り付けて登録
            pyautogui.hotkey("ctrl", "v")
            # 貼り付けに使ったものはクリア
            cp_str = ""
            # 乱数で生じたスリープ分眠り
            time.sleep(sl_time)
    # ループから抜けたあと、残りがあれば貼り付け実行
    if len(cp_str) > 0:
        # クリップボードに文字をコピーしておく
        pyperclip.copy(cp_str)
        # すべての文字列は貼り付けて登録
        pyautogui.hotkey("ctrl", "v")
        # 貼り付けに使ったものはクリア
        cp_str = ""

    now_counter += 1
    print("END: my_auto_func : " + str(now_counter))


# オマケ。実演中に間違った際などに対応するための関数
# 一個前にカウンタを戻して再度同じコードを書いたり、終了させたり。
def my_sub_func(arg_val):
    global now_counter
    print("called: " + "for_before")
    now_counter -= 1

    # キーが押されて出力する際に、負数になっていた場合は処理終了
    if now_counter < 0:
        print("END: finish")
        exit()

    print("END: my_sub_func : " + str(now_counter))


# 以下、メインルーチン
# 途中で全角半角切り替えると、ホットキー追加がバグる模様なので触らないこと。
# （半角モードで実行する）
# 停止する時は、「ctrl +c」で本Python側を強制終了で良い。
if __name__ == "__main__":

    try:
        # ホットキーとそのイベントを追加するのは一回のみ。
        # メインホットキーの設定：使用するアプリの他のショートカットと重複しないように
        keyboard.add_hotkey("ctrl+q", my_auto_func, args=(1,))

        # サブホットキーの設定：使用するアプリの他のショートカットと重複しないように
        keyboard.add_hotkey("ctrl+i", my_sub_func, args=(1,))

        print("Press CTRL + ESC to stop.")

        # ESCだとjupyter notebook操作時に無意識に触ってしまうので CTRL+ESCに変更
        keyboard.wait("ctrl + esc")
        print("ctrl+esc-END")

    except Exception:
        import traceback

        print(traceback.format_exc())
        exit()

    exit()
