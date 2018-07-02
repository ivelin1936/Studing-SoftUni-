package entities.clusters;

import entities.cells.Cell;

import java.util.LinkedList;
import java.util.List;

public class Cluster {

    private String id; // describes the name of the cluster, unique property;
    private int rows; // describes how many rows the cluster has;
    private int cols; // describes how many columns the cluster has;
    private List<Cell> cells;

    public Cluster(String id, int rows, int cols, List<Cell> cells) {
        this.setId(id);
        this.setRows(rows);
        this.setCols(cols);
        this.setCells(cells);
    }

    public Cluster(String id, int rows, int cols) {
        this.id = id;
        this.rows = rows;
        this.cols = cols;
        this.cells = new LinkedList<>();
    }

    public String getId() {
        return this.id;
    }

    private void setId(String id) {
        this.id = id;
    }

    public int getRows() {
        return this.rows;
    }

    private void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return this.cols;
    }

    private void setCols(int cols) {
        this.cols = cols;
    }

    public List<Cell> getCells() {
        return this.cells;
    }

    public void setCells(List<Cell> cells) {
        this.cells = cells;
    }

    @Override
    public String toString() {
        //TODO....
        return "Cluster{" +
                "id='" + id + '\'' +
                ", rows=" + rows +
                ", cols=" + cols +
                ", cells=" + cells +
                '}';
    }
}
