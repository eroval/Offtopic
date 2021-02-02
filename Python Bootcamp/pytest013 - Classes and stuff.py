# -*- coding: utf-8 -*-

class Dog():
    def __init__(self):
        self.breed=str(input("Enter a breed: "))
        self.name=str(input("Enter a name: "))
        self.spots=bool(input("Enter wether spots exist: "))
   
    def printDog(self):
        print(self.breed)
        print(self.name)
        print(self.spots)

class Book():
    def __init__(self, title, author, pages):
        self.title=title
        self.author=author
        self.pages=pages
    def __str__(self):
        return "The book \"" + self.title + "\" by "+self.author + " is " + str(self.pages) +" pages long."
    def __add__(self, num):
        return self.pages + num
    def __int__(self):
        return self.pages

#mydog = Dog()
#mydog.printDog()

mybook = Book("The King in Yellow","Robert Chambers", 200)
print(int(mybook))
print(mybook + 20)

deleteBook=input("Would you like to delete the book? (yes/no) ").lower()
while(deleteBook!='yes' and deleteBook!='no'):
    deleteBook=input("You must eneter either \"yes\" or \"no\" ")

if(deleteBook=='yes'):
    del mybook

print(mybook)