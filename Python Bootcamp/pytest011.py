# -*- coding: utf-8 -*-

def square(num):
    return num**2

my_nums=[1,2,3,4,5]

# Map function

for item in map(square,my_nums):
    print(item)
    
mylist=list(map(square,my_nums))

def splicer(mystring):
    if(len(mystring)%2==0):
        return 'EVEN'
    else:
        return mystring[0]
    
names=['Andy','Eve','Sally']

mylist = list(map(splicer,names))


# Filter function

def check_even(num):
    return num%2==0

mynums=[1,2,3,4,5,6]

evenlist = list(filter(check_even, mynums))

print(evenlist)


# Lambda

def square(num): 
    return num**2

### --->

square2 = lambda num: num**2

print(square2(5))

mylist2 = list(map(lambda num:num**2, mynums))
print(mylist2)

mylist3 = list(filter(lambda num:num%2==0, mynums))
print(mylist3)


print(list(map(lambda x:x[0], names)))