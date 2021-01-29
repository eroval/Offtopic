# -*- coding: utf-8 -*-
"""
Created on Fri Jan 29 12:47:35 2021

@author: Ass
"""

mylist = [1,2,3]

for num in range(1,11,3):
    print(num)
    
print(list(range(0,11,2)))

index_count=0

for letter in 'abcde':
    print('At index {} the letter is {}'.format(index_count,letter))
    index_count+=1
    
word='abcde'

for item, letter in enumerate(word):
    print(item)
    print(letter)
    print('\n')
    
mylist1=[1,2,3,4,5]
mylist2=['a','b','c']
mylist3=[100,200,300]

for item in zip(mylist1,mylist2,mylist3):
    print(item)
    #only goes to the minimum number of items[3 in this case]
    
print(list(zip(mylist1,mylist2)))

print('x' in [1,2,3])
print('x' in ['x','a','b'])

print('x' in "This world is fakex")

print('mykey' in {'mykey':345})

d={'mykey':345}

print(345 in d.keys())

#Min_Max
mylist=[10,20,30,40,100]
print(min(mylist))
print(max(mylist))

from random import shuffle
mylist=[1,2,3,4,5,6,7,8,9,10]
shuffle(mylist)

print(mylist)

from random import randint
print(randint(100,958))

#input is always string
result = input("Enter your favorite name: ")
print(result)


result = int(input('Enter a number: '))
print(result)
print("After incrementing the number by five it is: ")
result+=5;
print(result)