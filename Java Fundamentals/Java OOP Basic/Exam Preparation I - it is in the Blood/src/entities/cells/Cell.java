package entities.cells;

public abstract class Cell {

    private String id; //nonunique property;
    private int health; // a positive integer;
    private int positionRow; // a positive integer;
    private int positionCol; // a positive integer;

    public Cell(String id, int health, int positionRow, int positionCol) {
        this.setId(id);
        this.setHealth(health);
        this.setPositionRow(positionRow);
        this.setPositionCol(positionCol);
    }

    public String getId() {
        return id;
    }

    private void setId(String id) {
        this.id = id;
    }

    public int getHealth() {
        return health;
    }

    private void setHealth(int health) {
        this.health = health;
    }

    public int getPositionRow() {
        return positionRow;
    }

    private void setPositionRow(int positionRow) {
        this.positionRow = positionRow;
    }

    public int getPositionCol() {
        return positionCol;
    }

    private void setPositionCol(int positionCol) {
        this.positionCol = positionCol;
    }

    @Override
    public String toString() {
        //TODO....
        return "Cell{" +
                "id='" + id + '\'' +
                ", health=" + health +
                ", positionRow=" + positionRow +
                ", positionCol=" + positionCol +
                '}';
    }
}
