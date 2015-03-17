from Players import *


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

            self.player_one.previous_result = result
            self.player_two.previous_result = p2_result

        print("The score is {:d} to {:d}".format(p1_victories, p2_victories))

        if p1_victories == p2_victories:
            print("Game was a draw")
        elif p1_victories > p2_victories:
            print("{:s}, Player one, wins the match!".format(self.player_one.name()))
        else:
            print("{:s}, Player two, wins the match!".format(self.player_two.name()))

main = Main()
main.play_game()
