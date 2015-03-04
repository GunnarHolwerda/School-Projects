from Elements import moves
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
        return moves[0]


class RandomBot(Player):
    def play(self):
        return moves[random.randint(0, 4)]


class IterativeBot(Player):
    def __init__(self, name):
        Player.__init__(self, name)
        self._current_pos = -1

    def play(self):
        if self._current_pos < len(moves) - 1:
            self._current_pos += 1
        else:
            self._current_pos = 0
        return moves[self._current_pos]


class LastPlayBot(Player):
    def __init__(self, name):
        Player.__init__(self, name)
        self._first_move = True

    def play(self):
        if not(self._first_move):
            self._first_move = False
            return moves[random.randint(0, 4)]
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
            choice = input("Enter your move: ")
            if choice <= 0 or choice >= 6:
                print("Invalid move. Please try again.")
            else:
                accepted_input = True

        return moves[choice]


class MyBot(Player):
    def play(self):
        raise NotImplementedError("MYBOT SUCKS ASS")

players = (
    Human("Human"),
    StupidBot("Stupid Bot"),
    RandomBot("Random Bot"),
    IterativeBot("Iterative Bot"),
    LastPlayBot("Last Play Bot"),
    MyBot("My Bot")
)
