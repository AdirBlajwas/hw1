public class SnakesAndLaddersGame {
    private final Die die;
    private final GameBoard gameBoard; // game board of Snakes and Ladders game
    private final Player[] players;  // array of players with max of 5
    private int numOfPlayers = 0; // counter for the number of players

    static final int MAX_PLAYERS = 5;
    static final int MIN_PLAYERS = 2;
    static final int DIVIDERS_NUM = 25; // used to print a divider between rounds
    static final String SPACE = " ";
    static final char DIVIDER = '-';
    static final String END = "end";
    static final String DEFAULT_STR = "";
    static final String TO = " -> "; // arrow shape to present the path of a player




    SnakesAndLaddersGame(int lowerBound, int upperBound){
        this.die = new Die(lowerBound,upperBound);
        this.players = new Player[MAX_PLAYERS];
        this.gameBoard = new GameBoard();
    }

    SnakesAndLaddersGame(){
        this(Die.DIE_MIN, Die.DIE_MAX);
    }


    /**
     * The function geta a parsed command (add player/ ladder/ snake) and executes it
     * @param targetObject the object for the command player/ ladder/ snake
     * @param value1 the name of the player / the base of the ladder / the head of te snake
     * @param value2 the color of the player's piece / the length of the ladder/snake
     */
    public void executeCommand(String targetObject, String value1, String value2){
        if(targetObject.equals("player")){
            this.addPlayer(value1, value2);
            return;
        }
        int value1_int = Integer.parseInt(value1),
            value2_int = Integer.parseInt(value2);
        if (targetObject.equals("ladder"))
            this.gameBoard.addLadder(new Ladder(value1_int,value2_int));
        else
            this.gameBoard.addSnake(new Snake(value1_int,value2_int));
    }

    /**
     * The function adds a player to the players array if it's name and color isn't taken.
     * @param name the name of the player
     * @param colorStr the color of the player piece (string)
     */
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
            if(currentName.equals(name) && currentColor == color){
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

    /**
     * The function initialize the game according to the user's input:
     * adding players to the game
     * adding snakes and ladders
     * sorting the players alphabetically
     */
    public void initializeGame(){
        System.out.println("Initializing the game...");
        String [] commandValues ;
        String command = DEFAULT_STR;
        while(!command.equals(END) || this.numOfPlayers < MIN_PLAYERS ){
            command = Main.scanner.nextLine();
            if(command.equals(END))
            {
                if(this.numOfPlayers < MIN_PLAYERS)
                    System.out.println("Cannot start the game, there are less then two players!");
                continue;
            }
            commandValues = command.split(SPACE);
            this.executeCommand(commandValues[1],commandValues[2], commandValues[3]);
        }
        this.sortPlayers();
    }

    /*prints a divider between rounds with the round number*/
    public static void printRoundNum(int round){
        for(int i = 0 ; i < DIVIDERS_NUM; i++)
            System.out.print(DIVIDER);
        System.out.print(" Round number " + round + SPACE);
        for(int i = 0 ; i < DIVIDERS_NUM; i++)
            System.out.print(DIVIDER);
        System.out.println();
    }

    /*Sort an array of players by name alphabetically */
    public void sortPlayers(){
        for (int i = 0; i < this.numOfPlayers; i++)
        {
            for (int j = 1 ; j < numOfPlayers -i ; j++)
            {
                if (this.players[j-1].getName().compareTo(this.players[j].getName())>0)
                {
                    Player temp = this.players[j];
                    this.players[j] = this.players[j-1];
                    this.players[j-1] = temp;
                }
            }
        }
    }

    /**
     * calculates the target of the player according to his position and the number he rolled
     * assumed target - position + roll
     * if the assumed target is passed the end the players go back thr number of steps it deviated
     * if the assumed target is negative the players go back to the start
     * @param roll - the die result
     * @param curPosition -the current position of the player
     * @param size - the size of the board
     * @return the target the player needs to move to
     */
    public static int calculateTarget(int roll, int curPosition, int size){
        int assumedTarget = curPosition + roll;
        if(assumedTarget < 1) return 1;
        if(assumedTarget > size)
            return size - (assumedTarget % size);
        return assumedTarget;
    }

    /**
     * Play a turn for the player:
     * rolls the die
     * move the game piece accordingly, climbing ladders / sliding snakes if needed
     * @param player the player who plays the turn
     * @return the log of the path he went in this turn
     */
    public String playTurn(Player player){
        int roll = this.die.roll(),
        curPosition = player.getPosition();
        int target = calculateTarget(roll,curPosition,this.gameBoard.getSize());
        String log = player.getName() + " rolled " + roll +
                ". The path to the next square: " + curPosition + TO + target;
        curPosition = target;
        while(this.gameBoard.getSquareTarget(curPosition) != Square.VACANT){
            curPosition = this.gameBoard.getSquareTarget(curPosition);
            log = log + TO + curPosition;
        }
        player.MovePiece(curPosition);
        return log;
    }


    /*prints the current status of the players position*/
    public void printStatus() {
        System.out.println("\nPlayers positions on the board:");
        for(int i=0; i< this.numOfPlayers; i++){
            this.players[i].printPosition();
        }
    }

    /**
     * plays the rounds of the game until there's a winner
     * @return the winner's name
     */
    public String start() {
        int round = 1, lastPosition;
        String winnerName = DEFAULT_STR, log;
        while (winnerName.equals(DEFAULT_STR)) {
            printRoundNum(round);
            for (int i = 0; i < this.numOfPlayers; i++) {
                log =  playTurn(this.players[i]);
                lastPosition = this.players[i].getPosition();
                System.out.println(log);
                if (lastPosition == this.gameBoard.getSize()) {
                    winnerName = this.players[i].getName();
                    break;
                }
            }
            round++;
            this.printStatus();
        }

            return winnerName;

    }



}
