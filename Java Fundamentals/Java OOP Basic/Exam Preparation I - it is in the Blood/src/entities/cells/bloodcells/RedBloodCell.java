package entities.cells.bloodcells;

public class RedBloodCell extends BloodCell {

    private int velocity; //that describes the speed of the cell

    public RedBloodCell(String id, int health, int positionRow, int positionCol, int velocity) {
        super(id, health, positionRow, positionCol);
        this.setVelocity(velocity);
    }

    public int getVelocity() {
        return this.velocity;
    }

    private void setVelocity(int velocity) {
        this.velocity = velocity;
    }
}
