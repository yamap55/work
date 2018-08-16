
def hoge(func):
    def ff(*args, **kwargs):
        print('start')
        print(*args)
#        print(**kwargs)
        func(*args, **kwargs)
        print('end')
    return ff

@hoge
def test(s1, s2):
    print('hello decorator : ' + s1)

test('aa', s2='bb')
