# -*- coding: utf-8 -*-



#Level 1:
    
##1 Write a function that capitalizes the first and fourth letters of a name

def myfunc(name):
    if(len(name)<4):
        print("Your name is too short!")
        return None
    else:
        mylist=list(name)
        mylist[0]=mylist[0].upper()
        mylist[3]=mylist[3].upper()
        mylist = listToString(mylist)
        return mylist
        
def listToString(mylist):
    name=''
    for letter in mylist:
        name+=letter
    return name
        
name = input("Enter name: ")
name = myfunc(name)
print(name) 

##2 Given a sentence, return a sentence with the words reversed

def transformSentence(text):
    '''
    OUTPUT: returns reversed
    '''
    return text[::-1]

text=input("Enter sentence: ")
text=transformSentence(text)
print(text)

##3 Given an integer n, return True if n is withing 10 of either 100 or 200

def myfunc(number):
    if(abs(100-number)<=10 or abs(200-number)<=10):
        print('The number is within 10 of 100 or 200.')
    else:
        print("The number isn't within 10 of 100 or 200.")

number=int(input("Enter a number: "))
myfunc(number)