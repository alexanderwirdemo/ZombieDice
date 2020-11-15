import java.util.HashMap;

public class Dice {
    HashMap<Integer, FaceValues> sides = new HashMap<Integer, FaceValues>();
    int diceNo;
    String diceColor;
    public Dice(int diceNo){
        this.diceNo = diceNo;
        if(diceNo<=6) {
            diceColor = "Green";
            sides.put(1, FaceValues.BRAIN);
            sides.put(2, FaceValues.BRAIN);
            sides.put(3, FaceValues.BRAIN);
            sides.put(4, FaceValues.RUNNER);
            sides.put(5, FaceValues.RUNNER);
            sides.put(6, FaceValues.SHOT);
        }
        else if(diceNo>6 && diceNo<=10) {
            diceColor = "Yellow";
            sides.put(1, FaceValues.BRAIN);
            sides.put(2, FaceValues.BRAIN);
            sides.put(3, FaceValues.RUNNER);
            sides.put(4, FaceValues.RUNNER);
            sides.put(5, FaceValues.SHOT);
            sides.put(6, FaceValues.SHOT);
        }
        else if(diceNo>10 && diceNo<=13) {
            diceColor = "Red";
            sides.put(1, FaceValues.BRAIN);
            sides.put(2, FaceValues.RUNNER);
            sides.put(3, FaceValues.RUNNER);
            sides.put(4, FaceValues.SHOT);
            sides.put(5, FaceValues.SHOT);
            sides.put(6, FaceValues.SHOT);
        }
    }

}
