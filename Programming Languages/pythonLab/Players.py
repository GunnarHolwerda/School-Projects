import Elements
import random

class Player:
    def __init__(self, name):
        self._name = name

    def name(self):
        return self._name

    def play(self):
        raise NotImplementedError("Not yet implemented")

class StupidBot(Player):
    def play(self):
        return moves['Rock']

class RandomBot(Player):
    def play(self):
        return random.choice(moves.keys())

class IterativeBot(Player):
    keys = moves.keys()
    current_pos = 0
    
    def play(self):
        return moves[keys[current_pos++]]

class LastPlayBot(Player):
    first_move = True
    def play(self):
        if !first_move:
            first_move = False
            return random.choice(moves.keys())
        else:
            # Play the move last played by the other player

class Human(Player):
    def play(self):
        accepted_input = False
        print "(1) : Rock"
        print "(2) : Paper"
        print "(3) : Scissors"
        print "(4) : Lizard"
        print "(5) : Spock"
        while !accepted_input:
            choice = input("Enter your move: ")
            if choice <= 0 or choice >= 6:
                print "Invalid move. Please try again."
            else:
                accepted_input = True

        return moves[choice]        

class MyBot(Player):
    def play(self):