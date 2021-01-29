# -*- coding: utf-8 -*-

#Assessment Test

#1 - Print all words in a text that start with 's'
text=input("Enter text: ")
allWords = text.split()
print(allWords)

for words in allWords:
    if(words[0]=='s' or words[0]=='S'):
        print(words)
        
#2 - Print all even number in range from 0 to 10
for x in range(0,11,2):
    print(x)

#3 - Use a list comprehension to create a list of all numbers between 1 and 50 that are %3

results = [x for x in range(3,51,3)]
print(results)

#4 - Find if the length of the words in a string are even
text = input("Enter text: ")
allWords=text.split()

for words in allWords:
    if(len(words)%2==0):
        print("The word \"{}\" is even".format(words))

#5 - Program that prints numbers from 0 to 100 and prints "Fizz" for multiples of 3 instead of the number
#    for multiples of both 3 and 5 print "FizzBuzz"

for x in range(1,101):
    if(x%3==0):
        if(x%5==0):
            print("FizzBuzz")
        else: print("Fizz")
    else:
        print(x)
        
#6 - Use list comprehensions to create a list of the first letters of every words in an inputted string

text=input("Enter text: ")
mylist=[words[0] for words in text.split()]
print(mylist)
