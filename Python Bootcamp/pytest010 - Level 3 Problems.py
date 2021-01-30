# -*- coding: utf-8 -*-



#CHALLENGING PROBLEMS(honestly easier than level 2)



##1 SPY GAME: Write a function that takes in a list of integers
### and returns True if it contains 007 in order

### spy_game([1,2,4,0,0,7,5]) --> True
### spy_game([1,0,2,4,0,5,7]) --> False
### spy_game([1,7,2,0,4,5,0]) --> False

def agent007(mylist):
    x=0
    sz=len(mylist)
    while x<sz:
        if(mylist[x]==0):
            if(x<sz-1 and mylist[x+1]==0):
                x+=1
                if(x<sz and mylist[x+1]==7):
                    return True
        x+=1
    return False

mylist=[int(item) for item in input("Enter the numbers:").split()]
print(agent007(mylist))


##2 COUNT PRIMES: Write a function that returns the number of prime
### numbers that exist up to and including a given number

import sympy
n=int(input("Enter range: "))
print(list(sympy.primerange(0, n)))