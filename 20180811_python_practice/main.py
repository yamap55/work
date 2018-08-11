
import random
str_list = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ'

for n in range(0, 10):
    _str = random.choices(str_list, k=5)
    print(''.join(_str))
