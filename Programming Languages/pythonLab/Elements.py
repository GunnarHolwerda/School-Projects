class Element:

    def __init__(self, name):
        self._name = name

    def name(self):
        return self._name

    def compareTo(self, element, outcome = "Win"):
        raise NotImplementedError("Not yet implemented")

class Rock(Element):
    def compareTo(self, element, outcome = "Win"):
        if element.name() == "Lizard":
            return "Rock crushes Lizard"
        elif element.name() == "Scissors":
            return "Rock crushes Scissors"
        elif element.name() == "Rock":
            return "Rock equals Rock"
        else:
            return element.compareTo(self, "Lose")

class Paper(Element):
    def compareTo(self, element, outcome = "Win"):
        if element.name() == "Rock":
            return "Paper covers Rock"
        elif element.name() == "Spock":
            return "Paper disproves Spock"
        elif element.name() == "Paper":
            return "Paper equals Paper"
        else:
            return element.compareTo(self, "Lose")

class Scissors(Element):
    def compareTo(self, element, outcome = "Win"):
        if element.name() == "Paper":
            return "Scissors cuts Paper"
        elif element.name() == "Lizard":
            return "Scissors decapitate Lizard"
        elif element.name() == "Scissors":
            return "Scissors equals Scissors"
        else:
            return element.compareTo(self, "Lose")

class Lizard(Element):
    def compareTo(self, element, outcome = "Win"):
        if element.name() == "Spock":
            return "Lizard poisons Spock"
        elif element.name() == "Lizard":
            return "Lizard eats Paper"
        elif element.name() == "Lizard":
            return "Lizard equals Lizard"
        else:
            return element.compareTo(self, "Lose")

class Spock(Element):
    def compareTo(self, element, outcome = "Win"):
        if element.name() == "Scissors":
            return "Spock smashes Scissors"
        elif element.name() == "Lizard":
            return "Spock vaporizes Rock"
        elif element.name() == "Spock":
            return "Spock equals Spock"
        else:
            return element.compareTo(self, "Lose")

rock = Rock("Rock")
paper = Paper("Paper")
print rock.compareTo(paper)
print paper.compareTo(rock)
print rock.compareTo(rock)