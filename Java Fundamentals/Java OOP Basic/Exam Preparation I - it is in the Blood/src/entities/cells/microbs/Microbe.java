package entities.cells.microbs;

import entities.cells.Cell;

public abstract class Microbe extends Cell {

    private int virulence; //describes the ability of a microbe to infect other cells

    public Microbe(String id, int health, int positionRow, int positionCol, int virulence) {
        super(id, health, positionRow, positionCol);
        this.setVirulence(virulence);
    }

    public int getVirulence() {
        return this.virulence;
    }

    private void setVirulence(int virulence) {
        this.virulence = virulence;
    }
}
