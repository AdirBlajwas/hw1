public class Player {
    /*a player in a board game*/

    private final String name ;
    private final GamePiece gamePiece;

    Player(String name, Color color){
        this.name = name;
        this.gamePiece = new GamePiece(color);
    }

    /*move the piece of the player to a given target*/
    public void MovePiece(int target){
        this.gamePiece.setPosition(target);
    }

    public String getName() {
        return this.name;
    }

    /*returns the position of the player's game piece*/
    public int getPosition(){
        return this.gamePiece.getPosition();
    }

    /*prints the position of the player's game piece*/
    public void printPosition(){
        System.out.println(this.name + " is in square number " + this.getPosition());
    }

    public Color getColor(){
        return this.gamePiece.getColor();

    }
}
