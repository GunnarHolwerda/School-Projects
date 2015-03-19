import random


class Element:

    def __init__(self, name):
        self._name = name
        self._lose_to = None        # An array to hold moves element loses against

    def name(self):
        return self._name

    def compareTo(self, element, outcome="Win"):
        raise NotImplementedError("Not yet implemented")


class Rock(Element):

    def __init__(self, name):
        Element.__init__(self, name)
        self._lose_to = [1, 4]

    def compareTo(self, element, outcome="Win"):
        result = {}
        # Do normal comparisons and build the results dictionary
        if element.name() == "Lizard":
            result['string'] = "Rock crushes Lizard"
        elif element.name() == "Scissors":
            result['string'] = "Rock crushes Scissors"
        elif element.name() == "Rock":
            result['string'] = "Rock equals Rock"
            outcome = "Tie"
        else:
            # If we haven't one, compare the other element to us, because they
            # must have won and set the outcome param to lose
            return element.compareTo(self, "Lose")

        result['outcome'] = outcome
        return result


class Paper(Element):

    def __init__(self, name):
        Element.__init__(self, name)
        self._lose_to = [3, 2]

    def compareTo(self, element, outcome="Win"):
        result = {}
        if element.name() == "Rock":
            result['string'] = "Paper covers Rock"
        elif element.name() == "Spock":
            result['string'] = "Paper disproves Spock"
        elif element.name() == "Paper":
            result['string'] = "Paper equals Paper"
            outcome = "Tie"
        else:
            return element.compareTo(self, "Lose")

        result['outcome'] = outcome
        return result


class Scissors(Element):

    def __init__(self, name):
        Element.__init__(self, name)
        self._lose_to = [0, 4]

    def compareTo(self, element, outcome="Win"):
        result = {}
        if element.name() == "Paper":
            result['string'] = "Scissors cuts Paper"
        elif element.name() == "Lizard":
            result['string'] = "Scissors decapitate Lizard"
        elif element.name() == "Scissors":
            result['string'] = "Scissors equals Scissors"
            outcome = "Tie"
        else:
            return element.compareTo(self, "Lose")

        result['outcome'] = outcome
        return result


class Lizard(Element):

    def __init__(self, name):
        Element.__init__(self, name)
        self._lose_to = [0, 2]

    def compareTo(self, element, outcome="Win"):
        result = {}
        if element.name() == "Spock":
            result['string'] = "Lizard poisons Spock"
        elif element.name() == "Paper":
            result['string'] = "Lizard eats Paper"
        elif element.name() == "Lizard":
            result['string'] = "Lizard equals Lizard"
            outcome = "Tie"
        else:
            return element.compareTo(self, "Lose")

        result['outcome'] = outcome
        return result


class Spock(Element):

    def __init__(self, name):
        Element.__init__(self, name)
        self._lose_to = [1, 3]

    def compareTo(self, element, outcome="Win"):
        result = {}
        if element.name() == "Scissors":
            result['string'] = "Spock smashes Scissors"
        elif element.name() == "Rock":
            result['string'] = "Spock vaporizes Rock"
        elif element.name() == "Spock":
            result['string'] = "Spock equals Spock"
            outcome = "Tie"
        else:
            return element.compareTo(self, "Lose")

        result['outcome'] = outcome
        return result

# Global moves array
moves = (
    Rock("Rock"),
    Paper("Paper"),
    Scissors("Scissors"),
    Lizard("Lizard"),
    Spock("Spock")
)


class Player:
    def __init__(self, name):
        self._name = name
        self._last_move = None          # Holds the last move a  player made
        self.previous_result = None     # Holds the previous result for the player

    def name(self):
        return self._name

    def play(self):
        raise NotImplementedError("Not yet implemented")

    # Method returns a new instance of each player class
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

    # Return random move
    def getRandMove(self):
        return moves[random.randint(0, 4)]


class StupidBot(Player):
    def play(self):
        self._last_move = moves[0]
        # Play rock over and over
        return moves[0]


class RandomBot(Player):
    def play(self):
        # Get a random move
        return Player.getRandMove(self)


class IterativeBot(Player):
    def __init__(self, name):
        Player.__init__(self, name)
        self._current_pos = -1          # Keeps track of its position in moves array

    def play(self):
        # Logic to check if we need to reset to 0
        if self._current_pos < len(moves) - 1:
            self._current_pos += 1
        else:
            self._current_pos = 0

        # Cast dictionary to list because Python sucks
        return moves[self._current_pos]


class LastPlayBot(Player):
    def __init__(self, name):
        Player.__init__(self, name)
        self._first_move = True

    def play(self, opponent):
        if self._first_move:
            # For first move play a random move
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

        # Play move selected by player
        return moves[choice - 1]


# MyBot when wins plays the same move as last time, if it loses plays a winning
# move against the opponents last move, if Tie it picks a random move
class MyBot(Player):
    def __init__(self, name):
        Player.__init__(self, name)
        self._first_move = True

    def play(self, opponent):
        if self._first_move:
            # For the first move play something random
            self._first_move = False
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

                return moves[my_move]


class Main:

    def __init__(self):
        self._welcome_msg = "Welcome to Rock, Paper, Scissors, Lizard, Spock, implemented by Gunnar Holwerda\n"
        self._num_rounds = 5
        self.player_one = None
        self.player_two = None

    def select_players(self):
        player_one_accept = False
        player_two_accept = False

        print("Pleast choose two players:")
        print("(1) Human")
        print("(2) StupidBot")
        print("(3) RandomBot")
        print("(4) IterativeBot")
        print("(5) LastPlayBot")
        print("(6) MyBot\n")

        while not(player_one_accept):
            self.player_one = eval(input("Select player 1: "))
            if self.player_one <= 0 or self.player_one >= 7:
                print("Please select a valid input")
            else:
                player_one_accept = True
        while not(player_two_accept):
            self.player_two = eval(input("Select player 2: "))
            if self.player_two <= 0 or self.player_two >= 7:
                print("Please select a valid input")
            else:
                player_two_accept = True

        self.player_one = Player.getPlayer(self.player_one)
        self.player_two = Player.getPlayer(self.player_two)

        print("\n{:s} vs. {:s}. Fight!\n".format(self.player_one.name(), self.player_two.name()))

    def play_game(self):
        p1_victories = 0
        p2_victories = 0
        print(self._welcome_msg)
        self.select_players()
        for x in range(1, self._num_rounds + 1):
            print("Round {:d}:".format(x))

            # Checks the types of the player objects if we are LastPlayBot or MyBot we
            # need to pass in the oponnent to the play method
            if type(self.player_one) != LastPlayBot and type(self.player_one) != MyBot:
                p1_choice = self.player_one.play()
            else:
                p1_choice = self.player_one.play(self.player_two)

            if type(self.player_two) != LastPlayBot and type(self.player_two) != MyBot:
                p2_choice = self.player_two.play()
            else:
                p2_choice = self.player_two.play(self.player_one)

            print("Player 1 chose {:s}".format(p1_choice.name()))
            print("Player 2 chose {:s}".format(p2_choice.name()))
            result = p1_choice.compareTo(p2_choice)
            print(result['string'])

            # Set the last move values
            self.player_one._last_move = p1_choice
            self.player_two._last_move = p2_choice

            # Build up seperate result for player two so that it says it loses
            # if it lost instead of being win in player one wins
            p2_result = {}
            if result['outcome'] == "Win":
                print("Player 1 won the round\n")
                p2_result = result.copy()
                p2_result['outcome'] = "Lose"
                p1_victories += 1
            elif result['outcome'] == "Lose":
                print("Player 2 won the round\n")
                p2_result = result.copy()
                p2_result['outcome'] = "Win"
                p2_victories += 1
            else:
                print("Round was a tie\n")
                p2_result = result.copy()

            # Set the previous results
            self.player_one.previous_result = result
            self.player_two.previous_result = p2_result

        print("The score is {:d} to {:d}".format(p1_victories, p2_victories))

        if p1_victories == p2_victories:
            print("Game was a draw")
        elif p1_victories > p2_victories:
            print("{:s}, Player one, wins the match!".format(self.player_one.name()))
        else:
            print("{:s}, Player two, wins the match!".format(self.player_two.name()))

# Run the actual game
main = Main()
main.play_game()
