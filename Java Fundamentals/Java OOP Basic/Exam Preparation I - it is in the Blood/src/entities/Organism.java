package entities;

import entities.clusters.Cluster;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Organism {

    private String name;
    private List<Cluster> clusters;

    public Organism(String name) {
        this.setName(name);
        this.clusters = new LinkedList<>();
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public List<Cluster> getClusters() {
        return Collections.unmodifiableList(this.clusters);
    }

    public void setClusters(List<Cluster> clusters) {
        this.clusters = clusters;
    }

    public void addCluster(Cluster cluster) {
        this.clusters.add(cluster);
    }

    @Override
    public String toString() {
        //TODO - not sure this work
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Organism - %s", this.getName()))
                .append(System.lineSeparator())
                .append(String.format("--Clusters: %d", this.getClusters().size()))
                .append(System.lineSeparator());

        int count = 0;
        for (Cluster c: this.getClusters()) {
            count += c.getCells().size();
        }
        sb.append(String.format("--Cells: %d", count));

        return sb.toString();

    }
}
