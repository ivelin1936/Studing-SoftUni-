package entities.cells.bloodcells;

public class WhiteBloodCell extends BloodCell {

    private int size;

    public WhiteBloodCell(String id, int health, int positionRow, int positionCol, int size) {
        super(id, health, positionRow, positionCol);
        this.setSize(size);
    }

    public int getSize() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }
}
