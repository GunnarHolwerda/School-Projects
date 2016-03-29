import java.util.ArrayList;

/**
 * Created by Gunnar on 3/22/2016.
 */
public class Bowling {

    private int[] rolls;
    private int numberOfFrames, totalScore;

    Bowling(int[] rolls, int numberOfFrames) {
        this.rolls = rolls;
        this.numberOfFrames = numberOfFrames;
    }

    private void printFrame(ArrayList<Integer> frame, int frameNumber) {
        String rollString = "|";
        String topString = "+";
        String bottomString = "+";
        String bottomRollString = "|";
        for (int i = 0; i < frame.size(); i++) {
            topString += "---+";
            if (frameIsStrike(frame)) {
                topString += "---+";
                rollString += "   |";
                rollString += " X |";
                bottomRollString += "---+";
                bottomString += "----";
            }
            else if (frameIsSpare(frame) && i == frame.size() - 1) {
                rollString += " / |";
            }
            else {
                if (frame.get(i) == 10) {
                    rollString += " X |";
                }
                else if (i >= 1 && frame.size() == 3 && frame.get(i - 1) + frame.get(i) == 10) {
                    rollString += " / |";
                }
                else {
                    rollString += " " + frame.get(i) + " |";
                }
            }
            bottomRollString += "---+";
            bottomString += "----";
        }
        System.out.println(topString + "    Frame" + frameNumber);
        System.out.println(rollString);
        System.out.println(bottomRollString.substring(0, bottomRollString.length() - 1) + "|");
        int spacesNeeded = bottomString.length() - 2;
        String spaces = "";
        for (int i = 0; i < spacesNeeded - (Integer.toString(totalScore) + " ").length(); i++) {
            spaces += " ";
        }
        System.out.printf("|%s%d |\n", spaces, totalScore);
        System.out.println(bottomString.substring(0, bottomString.length() - 1) + "+");

    }

    private int frameTotal(ArrayList<Integer> list) {
        int total = 0;
        for (Integer num: list) {
            total += num;
        }

        return total;
    }

    private void calculateScoreForFrame(ArrayList<Integer> frame, int currentRoll) {
        if (frameIsStrike(frame)) {
            if (currentRoll >= rolls.length - 2) {
                totalScore += 10;
            }
            else {
                totalScore += 10 + rolls[currentRoll + 1] + rolls[currentRoll + 2];
            }
        }
        else if (frameIsSpare(frame)) {
            if (currentRoll >= rolls.length - 2) {
                totalScore += 10;
            }
            else {
                totalScore += 10 + rolls[currentRoll + 1];
            }
        }
        else {
            totalScore += frameTotal(frame);
        }
    }

    private boolean frameIsSpare(ArrayList<Integer> list) {
        return list.size() == 2 && frameTotal(list) == 10;
    }

    private boolean frameIsStrike(ArrayList<Integer> list) {
        return list.size() == 1 && frameTotal(list) == 10;
    }

    public void play() {
        ArrayList<Integer> currentFrame = new ArrayList<Integer>();
        int frameNumber = 0;
        for (int currentRoll = 0; currentRoll < rolls.length; currentRoll++) {
            currentFrame.add(rolls[currentRoll]);

            // Check to get the 3 roll frame
            if (currentRoll == rolls.length - 3 && currentFrame.size() == 1) {
                frameNumber++;
                currentFrame.add(rolls[rolls.length - 2]);
                currentFrame.add(rolls[rolls.length - 1]);
                calculateScoreForFrame(currentFrame, currentRoll);
                printFrame(currentFrame, frameNumber);
                currentFrame.clear();
                break;
            }
            else if (frameTotal(currentFrame) == 10) {
                frameNumber++;
                calculateScoreForFrame(currentFrame, currentRoll);
                printFrame(currentFrame, frameNumber);
                currentFrame.clear();
            }
            else if(currentFrame.size() == 2 && currentRoll < rolls.length) {
                frameNumber++;
                calculateScoreForFrame(currentFrame, currentRoll);
                printFrame(currentFrame, frameNumber);
                currentFrame.clear();
            }
        }
    }
}
