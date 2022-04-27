public class GamePiece {
    private Color color;
    private int position;

    GamePiece(Color color){
        this.color = color;
        this.position = 1;
    }


    public int getPosition() {
        return position;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
