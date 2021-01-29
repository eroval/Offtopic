# -*- coding: utf-8 -*-

loc = 'Bank'

if loc=='Auto Shop':
    print('Cars are Cool!')
elif loc=='Bank':
    print('Money is Cool!')
else:
    print('Everything is Cool!')
    
mylist = [1,2,3,4,5,6,7,8,9,10]

for num in range(10):
    print(num)
    
mylist=[(1,2),(3,4),(5,6),(7,8)]
len(mylist)

for a in mylist:
    print(a)

print("\n")    

for (a,b) in mylist:
    print(a)
    print(b)
    
print("\n")

for a,b in mylist:
    print(b)

print("\n")

mylist = [(1,2,3),(5,6,7),(8,9,10)]

for item in mylist:
    print(item)

print("\n")
    
for a,b,c in mylist:
    print(a)
    print(b)
    print(c)
    
print("\n")

d={'k1':1,'k2':2,'k3':3}

#Loop through keys and items
for item in d.items():
    print(item)
        
print("\n")

#Loop through values with detupeling
for item,value in d.items():
    print(value)
    
print("\n")

#Loop through values
for values in d.values():
    print(values)
    
    
    
