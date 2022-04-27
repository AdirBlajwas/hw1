public class Ladder {
    private int base;
    private int head;

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
