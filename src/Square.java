public class Square {
    /*a square in a board of the game - Snakes and Ladders*/

    public static final int VACANT = -1;


    private int target; /* If there's head of a snake or a base of a ladder in this square. the target is the
    number of the end square of the ladder/snake. -1 if there isn't one*/


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
