import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static ArrayList<Integer> dicebag = new ArrayList<Integer>();
    static ArrayList<Integer> playersHand = new ArrayList<Integer>();
    static ArrayList<Dice> diceSet = new ArrayList<Dice>();
    static int playersScore = 0;
    static int currentScore = 0;
    static int totalScore = 0;
    static int playersShots = 0;
    static Scanner scanner = new Scanner(System.in);
    static Boolean session;
    static Boolean turn = true;
    static Player[] playerArray;
    static int playersTurn;
    
    public static void main(String[] args) {
        generateDiceSet();
        playerArray = generatePlayers();
        playersTurn = 1;
        session = true;
        while(session) {
            playersRound();
            turn = true;
        }
        
        
    }

    public static void playersRound() {
        
        generateDicebag(); // A set of 13 Dice needed for the game is created
        printDiceBag(); // The bag is printed on screen (dicecolors)
        currentScore = 0;
        System.out.println(playerArray[playersTurn-1].playerName+"'s turn!");
        while(turn) {
            playersScore = playerArray[playersTurn-1].playerScore; // The player's score
            newPlayersHand(); // 3 Dice are picked from the bag at random
            printPlayersHand(); // The colors of the Dice picked from the bag are printed on screen
            currentScore = currentScore + rollDices(playersScore); // The current score is updated
            totalScore = currentScore + playersScore; // The current score from the player's turn is added to the total score
            clearPlayersHand(); // The hand is cleared, i.e. the Dices are put back in the bag
            printDiceBag();
            System.out.println("Players score: "+totalScore);
            System.out.println("Players shots: "+playersShots);
            if(playersShots>=3) {
                System.out.println("3 SHOTS, Your round is over");
                playersShots=0;
                playersTurn = nextPlayer(); // The current player moves on to be the next player
                turn=false; // The turn ends
            }
            else if(totalScore>=13) { // If the player reaches a score of 13, the game is over and the player won
                System.out.println("You're winner!");
                session=false;
                turn=false;
            }
            else { // The player is offered to continue or break
                currentScore = makeSelection(currentScore); 
            }
            
        }
        dicebag.clear(); // The dicebag is cleared to be created again when the next player's turn begins
    }

    /**
     * This help-function determines which the next player is.
     * The function is used when the current player's turn is over and the next player's turn is about to begin.
     * It uses the order in the player-array and moves one further if possible, else back to the beginning of the array
     * @return
     */
    private static int nextPlayer() {
        if(playersTurn<playerArray.length) {
            playersTurn=playersTurn+1;
        }
        else {
            playersTurn=1;
        }
        return playersTurn;
    }

    /**
     * This function generates players. It creates Player-objects and places them in the playerArray
     * @return
     */
    private static Player[] generatePlayers() {
        Player player1 = new Player(1,"Alexander",0);
        Player player2 = new Player(2, "Mikaela",0);
        playerArray = new Player[2];
        playerArray[0] = player1;
        playerArray[1] = player2;
        return playerArray;
    }

    /**
     * This help-function handles the different options a player can make after a diceroll 
     * @param currentScore
     * @return
     */
    public static int makeSelection(int currentScore) {
        System.out.println("Proceed? (Y/N)");
        String answer = scanner.next();
        if(answer.equals("y")) {
            System.out.println("Proceeding...");
        }
        else if(answer.equals("n")) {
            turn=false;
            playersShots=0;
            totalScore = playersScore + currentScore;
            playerArray[playersTurn-1].playerScore = totalScore;
            currentScore = 0;
            playersTurn = nextPlayer();
        }
        else {
            System.out.println("Try again");
        }
        return currentScore;
    }

    /**
     * This is the function where the actual game takes place. The Dices are rolled, and depending on their results, score or shots are increased
     * @param playersScore
     * @return
     */
    public static int rollDices(int playersScore) {
        currentScore = 0;
        System.out.println();
        System.out.println("Score before: "+playersScore);
        System.out.println("Your rolled:");
        for(int index=0;index<Math.min(3, playersHand.size());index++) {
            int side = rollDice();
            FaceValues face = diceSet.get(playersHand.get(index)).sides.get(side);
            if(face.equals(FaceValues.BRAIN)) {
                currentScore+=1; // Score variable is increased by 1
                String diceColor = diceSet.get(playersHand.get(index)).diceColor;
                System.out.print(diceColor+" "+face+"   ");
            }
            else if(face.equals(FaceValues.SHOT)) {
                playersShots +=1; // Shots variable is increased by 1
                String diceColor = diceSet.get(playersHand.get(index)).diceColor;
                System.out.print(diceColor+" "+face+"   ");
            }
            else if(face.equals(FaceValues.RUNNER)) {
                String diceColor = diceSet.get(playersHand.get(index)).diceColor;
                System.out.print(diceColor+" "+face+"   ");
                playersHand.add(diceSet.get(playersHand.get(index)).diceNo); // If The player has a runner, a copy is created at the end of the array. 
                                                                            // When the player's hand is cleared, only the first three dices are removed. 
                                                                            // This way, the runner is kept until next time.
            }
        }
        System.out.println();
        System.out.println();
        return currentScore;
        
        
    }

    /**
     * This help-function clears the players hand. It'only' clears three dices, so if additional dices are added, as in the case of a runner, they are still kept
     */
    private static void clearPlayersHand() {
        for(int i=0;i<3;i++) {
            playersHand.remove(0);
        }
    }

    /**
     * This help-function generates a number between 1-6, which represents the side of the Dice
     * @return
     */
    private static int rollDice() {
        Random diceroll = new Random();
        int result = diceroll.nextInt(6)+1;
        return result;
    }

    /**
     * This function creates a set of Dices: 6 Green, 4 Yellow and 3 Red
     */
    private static void generateDiceSet() {
        for(int i=0;i<14;i++) {
            Dice dice = new Dice(i);
            diceSet.add(dice);
        }
    }

    /**
     * This function prints which Dices the player picked from the bag
     */
    private static void printPlayersHand() {
        System.out.println("You picked these dices from the bag:");
        for(int i=0;i<playersHand.size();i++) {
            System.out.print(diceSet.get(playersHand.get(i)).diceColor+" ");
        }
        System.out.println();
    }

    /**
     * This function prints the (remaining) Dices in the bag
     */
    private static void printDiceBag() {
        System.out.println("Dice in the bag:");
        for(int i=0;i<dicebag.size();i++) {
            System.out.print(diceSet.get(dicebag.get(i)).diceColor+" ");
        }
        System.out.println();
    }

    /**
     * This function allows the Player to pick Dices from the bag. It takes into account the (rare) occurence when there are fewer than 3 Dices left
     */
    private static void newPlayersHand() {
        for(int i=playersHand.size();i<3;i++) {
            Random dice = new Random();
            if(dicebag.size()>0) {
                int random = dice.nextInt(dicebag.size());
                playersHand.add(dicebag.get(random));
                dicebag.remove(random); 
            }
            else {
                i=3;
            }
            
            
        }
    }

    /**
     * This function generates the 13 numbers to be used as Dices
     */
    private static void generateDicebag() {
        for(int i=1;i<14;i++) {
            dicebag.add(i);
        }
    }

}
