public class Die {
    /*A die of a game with selected range of numbers*/
    
    public static final int DIE_MIN = 1;
    public static final int DIE_MAX = 6;


    private final int min; // the maximum number the die can roll
    private final int max; // the minimum number the die can roll

    Die(int min, int max){
        this.min = min;
        this.max = max;
    }

    Die(){
        this(DIE_MIN,DIE_MAX);
    }

    public int roll(){
        return Main.rnd.nextInt(this.max - this.min + 1) + this.min;
    }
}
