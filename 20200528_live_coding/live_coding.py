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
    """\
class O:
    def __init__(self, name, value):
        self.name = name
        self.value = value
    def hoge(self):
        return 'hoge'
""",
    """\
obj1 = O('tokyo', 'yoitoko')
print(obj1.name)
print(obj1.value)
obj1.value = 'waruitoko'
print(obj1.value)
""",
    """\
o = globals()['O']
obj2 = o('japan', 'maxamaxa')
""",
    """\
print(getattr(obj2, 'name'))
print(getattr(obj2, 'value'))
""",
    """\
setattr(obj2, 'value', 'yoitoko')
print(getattr(obj2, 'value'))
""",
    """\
assert hasattr(obj2, 'hoge')
""",
    """\
func = getattr(obj2, 'hoge')
print(func())
""",
    """\
import pandas as pd
data = {'col1': [1, 2], 'col2': [3, 4]}
df = pd.DataFrame(data=data)
df
""",
    """\
import importlib
pd = importlib.import_module('pandas')
df = getattr(pd, 'DataFrame')(data=data)
df
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
