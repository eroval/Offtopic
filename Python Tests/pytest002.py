# -*- coding: utf-8 -*-
"""
Created on Thu Jan 28 21:17:12 2021

@author: Ass
"""

x=0

while x<5:
    print(f'The current value of x is {x}')
    x+=1
else:
    print("X IS NOT LESS THAN 5")
    
x=[1,2,3]

for item in x:
    #comment
    pass

print("end of my script")

mystring = "sammy"

for letter in mystring:
    if letter=='a':
        continue
    print(letter)
    
print('\n')
    
for letter in mystring:
    if letter=='m':
        break
    print(letter)

x=2

for x in range(2,10):
    if x==5:
        continue
    print(x)