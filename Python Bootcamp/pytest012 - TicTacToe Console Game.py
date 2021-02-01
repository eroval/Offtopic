# -*- coding: utf-8 -*-

import random

def Player1(Board):
    circle = int(input("Where do you want to place 'O'? (1-9): "))
    while(circle>9 or circle<1):
        circle = int(input("You must choose between 1 and 9: "))
    while(Board[circle-1]=='O' or Board[circle-1]=='X'):
        circle = int(input("You can't place 'O' there. Choose another place: "))
        while(circle>9 or circle<1):
            circle = int(input("You must choose between 1 and 9: "))
    Board[circle-1]='O'
    
    

def Player2(Board):
    cross = int(input("Where do you want to place 'X'? (1-9): "))
    while(cross>9 or cross<1):
        cross = int(input("You must choose between 1 and 9: "))
    while(Board[cross-1]=='O' or Board[cross-1]=='X'):
        cross = int(input("You can't place 'X' there. Choose another place: "))
        while(cross>9 or cross<1):
            cross = int(input("You must choose between 1 and 9: "))
    Board[cross-1]='X'



def Over(Board):
    counter = 0
    for symbol in Board:
        if(symbol=='X' or symbol=='O'):
            counter+=1
    return counter>8



def Player1AI(Board):
    o = random.randint(0, 8)
    while(Board[o]=='O' or Board[o]=='X'):
        o = random.randint(0, 8)
    Board[o]='O'
    
    
    
def Player2AI(Board):
    x = random.randint(0, 8)
    while(Board[x]=='O' or Board[x]=='X'):
        x = random.randint(0, 8)
    Board[x]='X'



def hasWon(Board):
    if((Board[0]=='O' and Board[4]=='O' and Board[8]=='O') or
       (Board[0]=='X' and Board[4]=='X' and Board[8]=='X') or
       (Board[2]=='O' and Board[4]=='O' and Board[6]=='O') or 
       (Board[2]=='X' and Board[4]=='X' and Board[6]=='X')):
        return True
    else:
        symbol = 0
        while symbol<9:
            if(Board[symbol]=='O'):
                if(Board[symbol+1]=='O'):
                    if(Board[symbol+2]=='O'):
                        return True
                        break
            else:
                if(Board[symbol]=='X'):
                    if(Board[symbol+1]=='X'):
                        if(Board[symbol+2]=='X'):
                            return True
                            break
            symbol+=3
    return False
    


def printBoard(Board):
    symbol=0
    while symbol < len(Board):
        print("-------------\n| {} | {} | {} |".format(Board[symbol], Board[symbol+1], Board[symbol+2]))
        symbol+=3
    print("-------------")



def update(Board):
    Player1(Board)
    printBoard(Board)
    if(hasWon(Board)):
        print("Player 1 has Won!")
        return True
    if(Over(Board)):
        print("Tie!")
        return True
    Player2(Board)
    printBoard(Board)    
    if(hasWon(Board)):
        print("Player 2 has Won!")
        return True
    return False



def updateAI1(Board):
    Player1AI(Board)
    print("Player 1 has taken turn: ")
    printBoard(Board)
    if(hasWon(Board)):
        print("Player 1 has Won!")
        return True
    if(Over(Board)):
        print("Tie!")
        return True
    Player2(Board)
    printBoard(Board)    
    if(hasWon(Board)):
        print("Player 2 has Won!")
        return True
    return False



def updateAI2(Board):
    Player1(Board)
    printBoard(Board)
    if(hasWon(Board)):
        print("Player 1 has Won!")
        return True
    print("Player 2 has taken turn: ")
    if(Over(Board)):
        print("Tie!")
        return True
    Player2AI(Board)
    printBoard(Board)    
    if(hasWon(Board)):
        print("Player 2 has Won!")
        return True
    return False
    


def start():
    Board = ['1', '2', '3',
             '4', '5', '6',
             '7', '8', '9']

    printBoard(Board)
    
    against=input("Would you like to play against a player or AI? (player/AI) ").lower()
    while(against!='player' and against!='ai'):
        against=input("You have to enter either 'player' or 'AI': ").lower()
    if(against=='player'):
        while(not update(Board)):
            continue
    else:
        player=input("Would you like to play with O or X? ").upper()
        while(player!='X' and player!='O'):
            player=input("You must choose either X or O: ").upper()
        if(player=='O'):
            while(not updateAI2(Board)):
                continue
        else:
            while(not updateAI1(Board)):
                continue
                
            
        
def play():
    Game="yes"
    while(Game=="yes"):
        Game="no"
        start()
        Game=input("Would you like to play another game? (yes/no) ").lower()
    
    
play()

    
    
    