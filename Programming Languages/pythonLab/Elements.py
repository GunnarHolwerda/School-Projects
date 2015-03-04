class Element:

    def __init__(self, name):
        self._name = name

    def name(self):
        return self._name

    def compareTo(self, element, outcome="Win"):
        raise NotImplementedError("Not yet implemented")


class Rock(Element):
    def compareTo(self, element, outcome="Win"):
        result = {}
        if element.name() == "Lizard":
            result['string'] = "Rock crushes Lizard"
        elif element.name() == "Scissors":
            result['string'] = "Rock crushes Scissors"
        elif element.name() == "Rock":
            result['string'] = "Rock equals Rock"
            outcome = "Tie"
        else:
            return element.compareTo(self, "Lose")

        result['outcome'] = outcome
        return result


class Paper(Element):
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


moves = (
    Rock("Rock"),
    Paper("Paper"),
    Scissors("Scissors"),
    Lizard("Lizard"),
    Spock("Spock")
)
