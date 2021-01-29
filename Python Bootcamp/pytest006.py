# -*- coding: utf-8 -*-

def helloGuy(name):
    print("Hello, "+name+"!")

helloGuy("Denis")

def Sum( valuex,valuey):
    '''
    INPUT: num1, num2\n
    OUTPUT: num3
    '''
    return valuex+valuey

print(Sum(10,20))

def cat_check(mystring):
    return 'cat' in mystring.lower()

print(cat_check('cat versus dog'))



#PigLatin Transformer

def listToString(mylist):
    mystring = ''
    for word in mylist:
        mystring+=word
        mystring+=' '
    mystring+='\n'
    return mystring

def pigLatin(mystring):
    if not mystring:
        print("Your string is empty!\n")
        return None
    else:
        mystring=mystring.split()
        for index, word in enumerate(mystring):
            if( word[0].lower() in 'aeiou'):
                word+='ay'
            else:
                word=word[1:]+word[0]+'ay'
            mystring[index]=word.strip();
        return listToString(mystring)
        

mystring=input("Enter words: ");
mystring = pigLatin(mystring)
print(mystring)