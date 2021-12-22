import java.util.*;
import java.util.random.*;
//-------------------------------------Player-----------------------------------------------

class Player{
    private String name ;
    private int id;
    private int position;
    public Player(String n, int id ){
        this.name = n;
        this.id =id ;
        
        

    }

    public void updatePosition(){
         this.position += Dice.getDiceValue() ;}
   
    public  String getName(){
            return this.name;
    }
    }


//-----------------------Dice--------------------------------
class Dice{
    private static int diceValue;
    public static int rollDice(){
        Random r = new Random();
        diceValue = r.nextInt(7);
        return diceValue;
    }
    public static int getDiceValue(){
        return diceValue;
    }
}
//---------------------game board------------------------
class gameBoard{
    private Queue<Player> nextTurn;
    private List<heads_tails> snakes;
    private List<heads_tails> ladders;
    private HashMap<String, Integer> pCurrentPosition;
    int boardSize;
    public gameBoard(Queue<Player> nextTurn , List<heads_tails> snakes , List<heads_tails> ladders , HashMap<String, Integer> currentPosition,int boardsize ){
            this.nextTurn = nextTurn;
            this.snakes = snakes;
            this.ladders = ladders;
            this.pCurrentPosition = currentPosition;
            this.boardSize = boardsize;

    }
    public void startGame(){
        boolean flag = false;
        
        while(nextTurn.size() >1){
            Player player = nextTurn.poll();
            int diceValue = Dice.rollDice();
            int currentPosiiton = pCurrentPosition.get(player.getName());
            int nextCell = currentPosiiton + diceValue;
            if(nextCell > boardSize){
                nextTurn.offer(player);

            }
            else if(nextCell == boardSize){
                System.out.println(player.getName()+ " won the game .");
                
                break;
            }
            else{
                boolean b = false;
                boolean c = false;
                for(heads_tails i : snakes){
                    if(i.startPoint == nextCell){
                        nextCell = i.endPoint;
                        b = true;
                    }
                

                }
                if(b== true){
                    System.out.println(player.getName()+" bitten by snake.");
                }
                for(heads_tails i : ladders){
                    if(i.startPoint == nextCell){
                        nextCell = i.endPoint;
                        c =true;
                    }
                }
                if(c ==true){
                    System.out.println(player.getName()+" took a ladder.");
                } 
                if(nextCell == boardSize){
                    System.out.println(player.getName()+" won the game.");
                    flag = true;
                    break;
                }
                else{
                    pCurrentPosition.put(player.getName(), nextCell);
                    System.out.println(player.getName()+" is at position "+nextCell);
                    nextTurn.offer(player);
                }
            }

            
         


        }
    } 
}

//---------------------heads_tails------------------------
class heads_tails{
    int startPoint;
    int endPoint;
    public heads_tails(int s, int e){
        this.startPoint =s;
        this.endPoint=e;

    }
}

//---------------------Play_Snakes_ladders----------------------------
public class snakes_ladders {
  
    public static void main(String[]args){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the Player 1 name : ");
        Player p1 =  new Player(in.nextLine(), 1);

        System.out.println("Enter the Player 2 name : ");
        Player p2 = new Player(in.nextLine(),2);

        Queue<Player> allPlayers = new LinkedList<>();
        allPlayers.offer(p1);
        allPlayers.offer(p2);
        
           heads_tails snake1 = new heads_tails(38,18);
           heads_tails snake2 = new heads_tails(54,9);
           heads_tails snake3 = new heads_tails(94,29);
           heads_tails snake4 = new heads_tails(98,57);
        List<heads_tails> snakes = new ArrayList<>();
        snakes.add(snake1);
        snakes.add(snake2);        
        snakes.add(snake3);
        snakes.add(snake4);

        heads_tails    ladder1 = new heads_tails(11,49);
        heads_tails    ladder2 = new heads_tails(24,58);
        heads_tails    ladder3 = new heads_tails(40,79);
        heads_tails    ladder4 = new heads_tails(66,85);
        heads_tails    ladder5 = new heads_tails(53,93);
        List<heads_tails> ladders = new ArrayList<>();
            ladders.add(ladder1);
            ladders.add(ladder2);
            ladders.add(ladder3);
            ladders.add(ladder4);
            ladders.add(ladder5);
        HashMap<String,Integer> pCurrentPosition = new HashMap<>();
        pCurrentPosition.put(p1.getName(),0);
        pCurrentPosition.put(p2.getName(), 0);
        gameBoard gb = new gameBoard(allPlayers, snakes, ladders, pCurrentPosition, 100 );
        gb.startGame();

}}
