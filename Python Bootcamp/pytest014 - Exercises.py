# -*- coding: utf-8 -*-

import math

### Create a class which accept coordinates as a pair of tuples
### and returns the slope and distance of the line

class Line:
    def __init__(self, coor1=(3.0,2.0), coor2=(8.0,10.0)):
        self.coor1=coor1
        self.coor2=coor2
    
    def slope(self):
        print("The slope is equal to: " + str(abs((self.coor2[1]-self.coor1[1])/(self.coor2[0]-self.coor1[0]))))
    
    def distance(self):
        print("The distance is equal to: " + str(math.sqrt((self.coor2[1]-self.coor1[1])**2+(self.coor2[0]-self.coor1[0])**2))) 
    
    def printLine(self):
        print(self.coor1)
        print(self.coor2)
            
myline=Line()
myline.printLine()
myline.slope()
myline.distance()

class Cylinder:
    def __init__(self, height=1.0, radius=1.0):
        self.height=height
        self.radius=radius
    
    def area(self):
        print("The area is equal to: " + str(2*math.pi*self.height*self.radius+2*math.pi*self.radius**2))
        
    
    def volume(self):
        print("The volume is equal to: " + str(self.height*math.pi*self.radius**2))
        
    def printCyliner(self):
        self.area()
        self.volume()
        

mycylinder = Cylinder(2.0,3.0)
mycylinder.printCyliner()
        