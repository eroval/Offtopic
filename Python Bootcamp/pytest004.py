# -*- coding: utf-8 -*-
"""
Created on Fri Jan 29 14:02:21 2021

@author: Ass
"""

#method 1:
mystring = 'hello'
mylist=[]

for letter in mystring:
    mylist.append(letter)

print(mylist)

#method 2:
mylist=[letter for letter in mystring]

print(mylist)

mylist=[x for x in 'word']
print(mylist)

results = [x if x%2==0 else 'ODD' for x in range(0,11)]
print(results)

results = [x**2 if x%2==0 else x for x in range(0,11)]
print(results)

mylist=[]

for x in [2,4,6]:
    for y in [1,10,1000]:
        mylist.append(x*y)

print(mylist)