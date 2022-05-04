public class GameBoard {
    private final Square[] board;
    private final int size;

    public static final int DEFAULT_SIZE = 100;


    GameBoard(int size) {
        this.size = size;
        board = new Square[size + 1];
        for (int i = 1; i <= size; i++) {
            board[i] = new Square();
        }
    }

    GameBoard() {
        this(DEFAULT_SIZE);
    }

    public void addTarget(int origin, int target) {
        if (origin == target) return;
        if (origin > this.size || origin < 1) {
            System.out.println("The square is not within the board's boundaries!");
            return;
        }
        if (target > this.size) {
            System.out.println("The ladder is too long!");
            return;
        }
        if (target < 1) {
            System.out.println("The snake is too long!");
            return;
        }
        int prevTarget = this.board[origin].getTarget();
        if (prevTarget == Square.VACANT)
            this.board[origin].setTarget(target);
        else if (prevTarget < origin)
            System.out.println("This square contains a head of a snake!");
        else System.out.println("This square already contains a bottom of a ladder!");
    }

    public void addLadder(Ladder ladder) {
        addTarget(ladder.getBase(), ladder.getHead());
    }

    public void addSnake(Snake snake) {
        int origin = snake.getHead();
        if (origin == this.size) {
            System.out.println("You cannot add a snake in the last square!");
            return;
        }
        addTarget(origin, snake.getTail());
    }

    public int getSize() {
        return this.size;
    }


    public int getSquareTarget(int squareIdx){
        return this.board[squareIdx].getTarget();
    }
}
