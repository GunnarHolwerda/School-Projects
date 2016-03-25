/**
 * Created by Gunnar on 3/22/2016.
 */
public class Bowling {

    private int[] rolls;
    private Frame[] frames;
    private int numberOfFrames, totalScore;

    Bowling(int[] rolls, int numberOfFrames) {
        this.rolls = rolls;
        this.frames = new Frame[numberOfFrames];
        this.numberOfFrames = numberOfFrames;
        buildFrames();
    }

    private void buildFrames() {
        Frame curFrame;
        int frameCount = 1;
        for (int i = 0; i < rolls.length && frameCount <= 10; i++) {
            curFrame = new Frame(frameCount);

            // Strike, set second frame to 10
            if (rolls[i] == 10) {
                curFrame.setRollTwo(rolls[i]);
            }
            // If this is the last roll, then it is a third role, add it as a third role to the previous frame
            else if (i == rolls.length - 1) {
                frames[frameCount - 1].setRollThree(rolls[i]);
            }
            // Normal frame
            else {
                curFrame.setRollOne(rolls[i]);
                curFrame.setRollTwo(rolls[i + 1]);
                i += 1;
            }

            frames[frameCount - 1] = curFrame;
            frameCount++;
        }
    }

    private void printFrame(int rollStart, int rollEnd) {
        System.out.print("+");
        String rollString = "|";
        String bottomString = "+";
        for (int i = rollStart; i < rollEnd; i++) {
            System.out.print("---+");
            rollString += " " + rolls[i] + " |";
            bottomString += "----";
        }
        System.out.println();
        System.out.println(rollString);
        System.out.printf("|%d|", totalScore);
        System.out.println(bottomString + "+");

    }

    private int calculateStrikeScore(int rollNumber) {
        int nextRole = rollNumber + 1 > rolls.length ? 0 : rolls[rollNumber + 1];
        int secondRole = rollNumber + 2 > rolls.length ? 0 : rolls[rollNumber + 2];

        return 10 + nextRole + secondRole;
    }

    private int calculateSpareScore(int rollNumber) {
        int nextRole = rollNumber + 1 > rolls.length ? 0 : rolls[rollNumber + 1];
        int secondRole = rollNumber + 2 > rolls.length ? 0 : rolls[rollNumber + 2];

        return 10 + nextRole + secondRole;
    }

    public void play() {
        System.out.println("GAME!");
        int frame = 1;
//        while (frame <= numberOfFrames) {
//            for (int i = 0; i < rolls.length; i++) {
//
//                //Strike scenario
//                if (rolls[i] == 10) {
//                    totalScore += calculateStrikeScore(i);
//                    frame++;
//                    printFrame(i, i);
//                }
//                else if ((rolls[i] + rolls[i + 1]) == 10) {
//                    totalScore += calculateSpareScore(i);
//                    frame++;
//                    printFrame(i, i + 1);
//                }
//                else {
//
//                }
//            }
//        }
//        for (int roll: frames) {
//            totalScore += calculateScore(frame);
//            printFrame(frame);
//        }
    }
}
