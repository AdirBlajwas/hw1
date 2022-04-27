public class Square {
    private int target;

    public static final int VACANT = -1;


    Square(){
        this.target = VACANT;
    }

    public void setTarget(int target){
        this.target = target;
    }

    public int getTarget() {
        return target;
    }

}
