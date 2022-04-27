import java.util.Scanner;
public class SnakesAndLaddersGame {
    private Die die;
    private GameBoard gameBoard;
    private Player[] players;
    private int numOfPlayers = 0;

    static final int MAX_PLAYERS = 5;
    static final int MIN_PLAYERS = 2;
    static final int DIVIDERS_NUM = 25;
    static final int COMMAND_START_IDX = 4;
    static final int COMMAND_NUM_OF_VALUES = 2;
    static final char SPACE = ' ';
    static final char DIVIDER = '-';
    static final String END = " ";
    static final String DEFAULT_STR = "";
    static final String TO = "->";




    SnakesAndLaddersGame(int lowerBound, int upperBound){
        this.die = new Die(lowerBound,upperBound);
        this.players = new Player[MAX_PLAYERS];
        this.gameBoard = new GameBoard();
    }

    SnakesAndLaddersGame(){
        this(Die.DIE_MIN, Die.DIE_MAX);
    }

    public static String parseCommand(String command, String[] targetArr){
        String command_attributes =command.substring(COMMAND_START_IDX);
        int spaceIdx= command_attributes.indexOf(SPACE);
        String targetObject = command_attributes.substring(0,spaceIdx);
        command_attributes =command.substring(spaceIdx+1);
        spaceIdx= command_attributes.indexOf(SPACE);
        targetArr[0] = command_attributes.substring(0,spaceIdx);
        targetArr[1] = command_attributes.substring(spaceIdx+1);
        return  targetObject;
    }



    public void executeCommand(String targetObject, String value1, String value2){
        int value1_int = Integer.parseInt(value1);
        int value2_int = Integer.parseInt(value2);
        if(targetObject.equals("player"))
            this.addPlayer(value1,value2);
        else if (targetObject.equals("ladder"))
            this.gameBoard.addLadder(new Ladder(value1_int,value2_int));
            else
                this.gameBoard.addSnake(new Snake(value1_int,value2_int));
    }


    public void addPlayer(String name, String colorStr){
        Color color = Color.valueOf(colorStr.toUpperCase());
        String currentName;
        Color currentColor;
        if(this.numOfPlayers == MAX_PLAYERS){
            System.out.println("The maximal number of players is five !");
            return;
        }
        for(int i = 0; i < this.numOfPlayers; i++) {
            currentName = this.players[i].getName();
            currentColor = this.players[i].getColor();
            if (!currentName.equals(name) && currentColor != color ) continue;
            else if(currentName.equals(name) && currentColor == color){
                System.out.println("The name and the color are already taken!");
                return;
            } else if(currentName.equals(name)){
                System.out.println("The name is already taken!");
                return;
            } else {
                System.out.println("The color is already taken!");
                return;
            }
            }
            players[numOfPlayers] = new Player(name,color);
            this.numOfPlayers++;
        }


    public void initializeGame(){
        System.out.println("Initializing the game...");
        String [] commandValues = new String[COMMAND_NUM_OF_VALUES];
        String command = DEFAULT_STR;
        String targetObject;
        while(!command.equals(END) || this.numOfPlayers < MIN_PLAYERS ){
            command = Main.scanner.nextLine();
            if(command.equals(END) && this.numOfPlayers < MIN_PLAYERS)
            {
                System.out.println("Cannot start the game, there are less then two players!");
                continue;
            }
            targetObject = parseCommand(command,commandValues);
            this.executeCommand(targetObject,commandValues[0], commandValues[1]);
        }
    }

    public static void printRoundNum(int round){
        for(int i = 0 ; i < DIVIDERS_NUM; i++)
            System.out.print(DIVIDER);
        System.out.print(" Round number " + round + SPACE);
        for(int i = 0 ; i < DIVIDERS_NUM; i++)
            System.out.print(DIVIDER);
    }
    public static int calculateTarget(int roll, int curPosition, int size){
        int assumedTarget = curPosition + roll;
        int target = Math.min(assumedTarget, size) - (assumedTarget % size);
        return target;
    }

    public int playTurn(Player player, String log){
        int roll = this.die.roll(),
        curPosition = player.getPosition(),
        target = calculateTarget(roll,curPosition,this.gameBoard.getSize());
        log = player.getName() + " rolled " + roll +
                ". The path to the next square: " +curPosition + TO + target;
        while(this.gameBoard.getBoard()[target].getTarget() != Square.VACANT){
            target = this.gameBoard.getBoard()[target].getTarget();
            log += TO + target;
        }
        player.MovePiece(target);
        return target;
    }

    public static void printStatus(Player[] players,int numOfPlayers)
    {
        for(int i=0; i< numOfPlayers; i++){
            System.out.println("\n :board the on positions P");
        }

    }

    public String start() {
        int round = 1, lastPosition;
        String winner = DEFAULT_STR, log;
        while (winner.equals(DEFAULT_STR)) {
            printRoundNum(round);
            for (int i = 0; i < this.numOfPlayers; i++) {
                log = DEFAULT_STR;
                lastPosition = playTurn(players[i], log);
                System.out.println(log);
                if (lastPosition == this.gameBoard.getSize()) {
                    winner = players[i].getName();
                    break;
                }
            }
            printStatus
        }

            return winner;

    }



}
