public class Die {
    private int min;
    private int max;
    public static final int DIE_MAX = 6;
    public static final int DIE_MIN = 1;

    Die(int min, int max){
        this.min = min;
        this.max =max;
    }

    Die(){
        this(DIE_MIN,DIE_MAX);
    }

    public int roll(){
        return Main.rnd.nextInt(this.max-this.min) + this.min;
    }

}
