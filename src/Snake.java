public class Snake {
    private int head;
    private int tail;

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
