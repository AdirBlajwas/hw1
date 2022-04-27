//import GamePiece;

public class Player {

    private String name ;
    private GamePiece gamePiece;

    Player(String name, Color color){
        this.name = name;
        this.gamePiece = new GamePiece(color);
    }

    public void MovePiece(int target){
        this.gamePiece.setPosition(target);
    }

    public String getName() {
        return this.name;
    }

    public int getPosition(){
        return this.gamePiece.getPosition();
    }

    public Color getColor(){
        return this.gamePiece.getColor();

    }
}
