import java.util.ArrayList;
import java.util.Scanner;
public class unoSim {
    private static String[] colors = new String[] {"Blue","Green","Yellow","Red"}; //initializes the card colors
    private static String getCardString(int cardNum) { //This method converts card values to proper strings for output
        if (cardNum == 44) {
            return "+4 card";
        }
        else {
            if (cardNum % 11 == 10) {
                return colors[cardNum / 11] + " +2";
            } else {
                return colors[cardNum / 11] + " " + cardNum % 11;
            }
        }
    }
    public static void main(String[] args) {
        int playerCount, cardChoice, curCard = -1; //Initializes variables that will be used later in the program
        String check;
        System.out.println("Welcome to Uno!");
        System.out.println("How many players will there be?");
        Scanner input = new Scanner(System.in); // Sets up a scanner
        playerCount = Integer.parseInt(input.nextLine()) ; //Gets the player count
        ArrayList<ArrayList<Integer>> cardList = new ArrayList<ArrayList<Integer>>(); //Creates an ArrayList matrix
        for (int i = 0; i < playerCount; i++){ //This loop initializes the first 7 cards that each player will start with
            cardList.add(new ArrayList<Integer>());
            for (int j = 0; j < 7; j++){
                cardList.get(i).add((int) (Math.random()*(45))); //adds a random card value (each color has 11 cards in it, and an extra +4 for 44)
            }
        }
        ArrayList<Integer> curPlayerCardList; //Initializes a temporary list that will be used for easier understanding later in the code
        while (true){ //Uses a while true loop because it's not possible to have it as a condition due to having to check each players cards
            for (int i = 0; i < playerCount; i++){ //Goes through player rotation
                for (int j = 0; j < 10; j++){ //Creates a block so other players can't see the previous players cards when switching
                    System.out.println("|||||||||||||||||||||||||||||||||||||||");
                }
                System.out.println("Press enter when you have switched to player " + (i+1) + ":");
                check = input.nextLine(); //Continues after something has been inputted
                System.out.println("Player " + (i+1) + " here are your cards:");
                curPlayerCardList = cardList.get(i); //Sets the temporary list
                for (int j = 0; j < curPlayerCardList.size(); j++){ //Outputs all cards with a corresponding number
                    System.out.println(j+1 + ". " + getCardString(curPlayerCardList.get(j)));
                }
                System.out.println("0. Draw a card");
                if (curCard == -1 || curCard == 44){ //If no card has been placed yet, you are able to place any card
                    System.out.println("Place any card (Use the corresponding number):");
                }
                else{ //Otherwise outputs the current card an prompts them to place a new card
                    System.out.println("The current card is: " + getCardString(curCard));
                    System.out.println("Choose a card to place (Use the corresponding number):");
                }
                cardChoice = Integer.parseInt(input.nextLine()); //Sets the card choice
                //This next while loop catches if an invalid card is inputted
                while (cardChoice > cardList.get(i).size() || cardChoice != 0 && cardList.get(i).get(cardChoice-1) / 11 != curCard / 11 && cardList.get(i).get(cardChoice-1) % 11 != curCard % 11 && curCard != -1 && curCard != 44 && cardList.get(i).get(cardChoice-1) != 44){
                    System.out.println("Please choose a valid card.");;
                    cardChoice = Integer.parseInt(input.nextLine());
                }
                if (cardChoice == 0){ // Checks if a card has been drawn
                    System.out.println("Player " + (i + 1) + " drew a card");
                    cardList.get(i).add((int) (Math.random()*(45))); //Adds a card to the players card array
                }
                else{
                    curCard = cardList.get(i).get(cardChoice-1);
                    cardList.get(i).remove(cardChoice-1);
                    System.out.println("Player " + (i+1) + " placed a " + getCardString(curCard));
                    if (curCard == 44){ //Checks for a +4 and adds card accordingly if so
                        for (int addCard = 0; addCard < 4; addCard++){
                            if (i+1 == playerCount){ //Checks if the last player is adding a +4 so it can give cards to player 0
                                cardList.get(0).add((int) (Math.random()*(45)));
                            }
                            else {
                                cardList.get(i + 1).add((int) (Math.random() * (45)));
                            }
                        }
                    }
                    else if (curCard % 11 == 10){ //Checks for +2 and adds card accordingly
                        for (int addCard = 0; addCard < 2; addCard++){
                            if (i+1 == playerCount){
                                cardList.get(0).add((int) (Math.random()*(45)));
                            }
                            else {
                                cardList.get(i+1).add((int) (Math.random() * (45)));
                            }
                        }
                    }
                }
                if (cardList.get(i).size() == 0){ //Checks if someone has won, if so it will completely stop the program
                    System.out.println("Player " + (i + 1) + " wins!");
                    System.exit(0); //Uses system.exit because break would only break the for loop and not the while true loop
                }
            }
        }
    }
}
