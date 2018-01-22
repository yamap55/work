# coding: utf-8
# Your code here!
import datetime
from datetime import timedelta

today = datetime.date.today() # 2018-01-02
tyear = today.year
tday = today.day
tmonth = today.month

with open("list.txt", "r") as f:
    for line in f.readlines():
        ar = line.replace('\n', '').split(",")
        birthday = ar[0]
        message = ar[1]
        tdatetime = datetime.datetime.strptime(birthday, '%Y/%m/%d')

        if tdatetime.day == tday and tdatetime.month == tmonth:
            y = tyear - tdatetime.year
            print message.format(y)
