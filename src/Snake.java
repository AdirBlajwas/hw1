public class Snake {
    /*a snake in a game of Snakes and Ladders*/

    private final int head;// the number square on board of the head of a snake - start
    private final int tail; // the number square on board of the tail of a snake - end

    Snake(int len, int head){
        this.head = head;
        this.tail = head - len;
    }

    public int getHead() {
        return this.head;
    }

    public int getTail() {
        return this.tail;
    }

}
