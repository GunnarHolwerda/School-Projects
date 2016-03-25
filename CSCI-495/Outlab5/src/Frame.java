/**
 * Created by Gunnar on 3/22/2016.
 */
public class Frame {
    private int rollOne;
    private int rollTwo;
    private int rollThree;
    private int frameNumber;

    Frame(int frameNumber) {
        this.rollThree = -1;
        this.frameNumber = frameNumber;
    }

    public int getFrameNumber() {
        return frameNumber;
    }

    public int getRollOne() {
        return rollOne;
    }

    public int getRollTwo() {
        return rollTwo;
    }

    public int getRollThree() {
        return rollThree;
    }

    public void setRollOne(int rollOne) {
        this.rollOne = rollOne;
    }

    public void setRollTwo(int rollTwo) {
        this.rollTwo = rollTwo;
    }

    public void setRollThree(int rollThree) {
        this.rollThree = rollThree;
    }

    public boolean hasRollThree() {
        return rollThree != -1;
    }

    public boolean isStrike() {
        return rollTwo == 10;
    }

    public boolean isSpare() {
        return (rollOne + rollTwo) == 10;
    }

    public int getScore() {
        if (this.hasRollThree()) {
            return rollOne + rollTwo + rollThree;
        }

        if (this.isStrike() || this.isSpare()) {
            return 10;
        }

        return rollOne + rollTwo;
    }
}
