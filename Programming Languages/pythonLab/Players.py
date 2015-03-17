from Elements import moves_array
from Elements import moves_dict
import random


class Player:
    def __init__(self, name):
        self._name = name
        self._last_move = None
        self.previous_result = None

    def name(self):
        return self._name

    def play(self):
        raise NotImplementedError("Not yet implemented")

    @staticmethod
    def getPlayer(number):
        if number == 1:
            return Human("Human")
        elif number == 2:
            return StupidBot("Stupid Bot")
        elif number == 3:
            return RandomBot("Random Bot")
        elif number == 4:
            return IterativeBot("Iterative Bot")
        elif number == 5:
            return LastPlayBot("Last Play Bot")
        else:
            return MyBot("My Bot")

    def getRandMove(self):
        return moves_array[random.randint(0, 4)]


class StupidBot(Player):
    def play(self):
        self._last_move = moves_array[0]
        return moves_array[0]


class RandomBot(Player):
    def play(self):
        return Player.getRandMove(self)


class IterativeBot(Player):
    def __init__(self, name):
        Player.__init__(self, name)
        self._current_pos = -1

    def play(self):
        if self._current_pos < len(moves_array) - 1:
            self._current_pos += 1
        else:
            self._current_pos = 0

        return moves_array[self._current_pos]


class LastPlayBot(Player):
    def __init__(self, name):
        Player.__init__(self, name)
        self._first_move = True

    def play(self, opponent):
        if self._first_move:
            self._first_move = False
            return Player.getRandMove(self)
        else:
            return opponent._last_move
            # Play the move last played by the other player


class Human(Player):
    def play(self):
        accepted_input = False
        print("(1) : Rock")
        print("(2) : Paper")
        print("(3) : Scissors")
        print("(4) : Lizard")
        print("(5) : Spock")
        while not(accepted_input):
            choice = eval(input("Enter your move: "))
            if choice <= 0 or choice >= 6:
                print("Invalid move. Please try again.")
            else:
                accepted_input = True

        return moves_array[choice - 1]


class MyBot(Player):
    def __init__(self, name):
        Player.__init__(self, name)

    def play(self, opponent):
        if self.previous_result is None:
            return Player.getRandMove(self)
        else:
            if self.previous_result['outcome'] == "Win":
                #If we won, play the same thing
                return self._last_move
            elif self.previous_result['outcome'] == "Tie":
                #If it was a tie play a random move
                return Player.getRandMove(self)
            else:
                #If we lost, play the winning move against their last move
                opp_last_move = opponent._last_move
                my_move = opp_last_move._lose_to[random.randint(0, 1)]

                return moves_dict[my_move]
