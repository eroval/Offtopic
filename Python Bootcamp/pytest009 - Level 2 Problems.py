# -*- coding: utf-8 -*-


#Level 2




##1 Given a list of ints, return True if the array contains a 3 next to a 3 somewhere.

def has33(mylist):
    for item in mylist:
        if '33' in item:
            print("33 is containted in the list")
            return None
    print("33 is not contained in the list.")
    return None
    
mylist=[item for item in input("Enter the numbers: ").split()]
has33(mylist)




##2 Define a function which triples every character in a string

### paper_doll('Hello') --> 'HHHeeellllllooo'
### paper_doll('Mississippi') --> 'MMMiiissssssiiippppppiii'

def triple(mystring):
    str2=''
    for letter in mystring:
        if(letter!='!' and letter!='.' and letter!='?' and letter!='-' and
           letter!=' ' and letter!=',' and letter!='~'):
            for x in range(3):
                str2+=letter
        else:
            str2+=letter
    return str2
    
mystring = input('Enter text: ')
mystring = triple(mystring)
print(mystring)




##3 BLACKJACK: Given three integers between 1 and 11, if their sum is less 
### than or equal to 21, return their sum. If their sum exceeds 21 and there's
### an eleven, reduce the total sum by 10. Finally, if the sum (even after adjustment)
### exceeds 21, return 'BUST

### blackjack(5,6,7) --> 18
### blackjack(9,9,9) --> 'BUST'
### blackjack(9,9,11) --> 19

def blackjack(num1, num2, num3):
    if(num1<1 or num1>11 or num2<1 or num2>11 or num3<1 or num3>11):
        return "Your numbers aren't in range."
    else:
        Sum=num1+num2+num3
        if (Sum>21 and (num1==11 or num2==11 or num3==11)):
            Sum-=10
          
        if(Sum<=21):
            return Sum  
        else:
            return "'BUST"
        

print(blackjack(10,11,11))




##4 SUMMER OF '69: Return the sum of the numbers in the array, except ignore sections
### of numbers starting with a 6 and extending to the next 9 (every 6 will be followed
### by at least one 9). Return 0 for no numbers.

### summer_69([1, 3, 5]) --> 9
### summer_69([4, 5, 6, 7, 8, 9]) --> 9
### summer_69([2, 1, 6, 9, 11]) --> 14

def sum69(numlist):
    Sum=0
    x=0
    while x<len(numlist):
        if numlist[x]==6:
            x+=1
            while(numlist[x]!=9):
                x+=1
                continue
        else:
            Sum+=numlist[x]
        x+=1
    return Sum
        

numlist=[int(item) for item in input("Enter the numbers: ").split()]
print(sum69(numlist))












