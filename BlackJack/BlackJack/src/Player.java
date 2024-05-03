import java.lang.reflect.Array;
import java.util.ArrayList;
import java.lang.Math;

public class Player {
    private ArrayList<Integer> playerDeck;
    private int limit;
    private String name;

    public Player(String name){
        playerDeck = new ArrayList<Integer>();
        limit = 21;

        this.name = name;

        playerDeck.add((int) (1 + (Math.random() * 11)));
    }

	//Adds new random card to the deck
    public void drawCard(){
        int[] viableCards = {1,2,3,4,5,6,7,8,9,10,11};
        ArrayList<Integer> drawableCards = new ArrayList<Integer>();

        for(int c : viableCards){
            if(!findCardInDeck(c))
                drawableCards.add(c);
        }

        if(drawableCards.size() > 1) {
            int index = (int) (Math.random() * drawableCards.size());
            playerDeck.add(drawableCards.get(index));
        } else
            playerDeck.add(drawableCards.get(0));
        //IMPLEMENTATION REQUIRED
    }

    //Helper method for drawCard()
    private boolean findCardInDeck(int card){
        for(int c : playerDeck){
            if(c == card)
                return true;
        }

        return false;
    }
	
	//Used for printing out the player's deck
    public void printDeck(boolean isRevealed){
        System.out.println("--------------------------------------------");
        System.out.print(name + "\n");
        for(int i = 0; i <= playerDeck.size() - 1; i++){
            if(i == 0 && !isRevealed)
                System.out.print("? | ");
            else
                System.out.print(playerDeck.get(i) + " ");
        }
        if(isRevealed)
            System.out.print("= " + sumCards());

        System.out.print("\n--------------------------------------------\n");
    }
	
	//Finding the the total value in player's deck
    public int sumCards(){
        int total = 0;
        for(int c : playerDeck){
            total += c;
        }
        return total;
    }
	
	//Finds the player's distance to 21
    public int findDifference() {
        return Math.abs(sumCards() - getLimit());
    }
	
	//Returns limit (which is basically 21)
    public int getLimit() {return limit;}
	
	//Returns the name of the player
    public String getName() {return name;}
}
