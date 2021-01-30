# -*- coding: utf-8 -*-


#The *args(can be anything really *spam, *root
#the '*' implicates multiple arguements):
def myfunc(*args):
    # Returns 5% of the sum of a and b
    return sum(args)*0.05

print(myfunc(40,60))

#kwargs is once again arbitrary choice
def myfunc(**kwargs):
    if 'fruit' in kwargs:
        print('My fruit of choice is {}'.format(kwargs['fruit']))
    else:
        print('I did not find any fruit here')

myfunc(fruit='banana', veggie='carrot')

def myfunc(*args, **kwargs):
    print('I would like {} {}'.format(args[0],kwargs['food']))
    
myfunc(10,20,30,fruit='orange',food='eggs',animal='dog')