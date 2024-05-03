import java.util.Scanner;

public class Game {
    private static boolean turn = true; //pOne is true, pTwo is false
    private static boolean winAchieved = false;

    private static boolean passOne = false, passTwo = false;
    
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        System.out.println(DisplayIntro());

        System.out.println("First Player Name: ");
        String name1 = input.nextLine();

        System.out.println("Second Player Name: ");
        String name2 = input.nextLine();

        Player p1 = new Player(name1);
        Player p2 = new Player(name2);

        Play(p1, p2);
    }

    public static void Play(Player pOne, Player pTwo){
        System.out.println("\nPrinting beginning decks");
        pOne.printDeck(false);
        pTwo.printDeck(false);
        
        System.out.println("To start, please type \"draw\"");

        Scanner input = new Scanner(System.in);
        String val;
        //ISSUE: GET HELP WILL NOT GET INPUT

        while(!winAchieved){
            //Constantly checks for input after every turn
            System.out.println(currentTurn(turn, pOne, pTwo));
            val = input.nextLine();

            switch(val.toLowerCase()){
                case "draw":
                    if(turn){ //Player1 goes first by default, so if turn is true, player 1 draws
                        System.out.println(pOne.getName() + " has drawn!");
                        pOne.drawCard();
                        if(passTwo) //if playerTwo made a pass on the previous turn, and player1 draws, then passTwo turns back to false
                                    //This is because we want to evaluate the result when both players have decided to pass
                            passTwo = false;
                        turn = false; //Switch the turn to player two
                    } else{ //Player2 goes now and draws a card
                        System.out.println(pTwo.getName() + " has drawn!");
                        pTwo.drawCard();
                        if(passOne) //If player1 made a pass on the last turn, then make it false since player 2 is drawing
                            passOne = false;
                        turn = true; //Switch the turn to player one
                    }
                    break;
                case "pass":
                    if(turn){ //if player1's turn and he makes pass
                        passOne = true; //set his pass to true, in order to see if second player makes pass too
                        turn = false; //switch the turn over to player 2
                    } else{ //if player2's turn and they make pass
                        passTwo = true; //set their pass to true
                        turn = true; //switch the turn over to player 1
                    }   
                    break;
            }

            if(passOne && passTwo){
                winAchieved = true;
            }

            pOne.printDeck(false);
            pTwo.printDeck(false);
        }

        pOne.printDeck(true);
        pTwo.printDeck(true);



        if(pOne.findDifference() < pTwo.findDifference() && winAchieved)
            System.out.println(pOne.getName() + " has won!");
        else if(pTwo.findDifference() < pOne.findDifference() && winAchieved)
            System.out.println(pTwo.getName() + " has won!");
        else
            System.out.println("Tie!");

        input.close();
    }

    public static String DisplayIntro(){
        return """
                Welcome to a simple BlackJack program.
                This program requires two players to compete against each other
                Please input your names consecutively:\s
                """;
    }

    public static String currentTurn(boolean t, Player one, Player two){
        if(t)
            return one.getName() + "'s turn: ";
        else
            return two.getName() + "'s turn: ";
    }
}
