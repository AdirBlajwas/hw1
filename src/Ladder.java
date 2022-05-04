public class Ladder {
/*a ladder in a game of Snakes and Ladders*/


    private final int base;// the square's number on board of the base of the ladder (start)
    private final int head;// the square's number on board of the head of the ladder (end)

    Ladder(int height, int base){
        this.base = base;
        this.head = base + height;
    }

    public int getHead() {
        return this.head;
    }

    public int getBase() {
        return this.base;
    }
}
