package controllers;

import entities.Organism;
import factory.CellFactory;
import factory.ClusterFactory;
import factory.OrganismFactory;

import java.util.LinkedHashMap;
import java.util.Map;

public class HealthManager implements HealthManagerController {

    //TODO..The business logic of the program. This class hold the main functionality.
    private Map<String, Organism> organismDB;
    private CellFactory cellFactory;
    private ClusterFactory clusterFactory;
    private OrganismFactory organismFactory;

    public HealthManager() {
        this.organismDB = new LinkedHashMap<>();
        this.cellFactory = new CellFactory();
        this.clusterFactory = new ClusterFactory();
        this.organismFactory = new OrganismFactory();
    }

    @Override
    public String checkCondition(String organismName) {
        //TODO...
        /** RETURNS detailed information about the condition of the organism
         *  with the given name */
        return null;
    }

    @Override
    public String createOrganism(String name) {
        /** CREATES an organism with the given name
         o	RETURNS message "Created organism <name>"
         o	If an organism with the same name already exists, returns message "Organism <name> already exists"
         */
        if (this.organismDB.containsKey(name)) {
            return String.format("Organism %s already exists", name);
        } else {
            Organism organism = this.organismFactory.createOrganism(name);
            this.organismDB.putIfAbsent(name, organism);
            return String.format("Created organism %s", organism.getName());
        }
    }

    @Override
    public String addCluster(String organismName, String id, int rows, int cols) {
        //TODO...
        /** CREATES a cluster with the given id, rows and cols
         o	ADDS the cluster to the cluster collection of the organism with the given name
         o	If the organism already has a cluster with the same Id, nothing happens
         o	RETURNS message "Organism <organism name>: Created cluster <cluster id>";
         */
        return null;
    }

    @Override
    public String addCell(String organismName, String clusterId, String cellType, String cellId, int health, int positionRow, int positionCol, int additionalProperty) {
        //TODO...
        /** CREATES a cell of the given type with the given id, health, positionRow, positionCol, and the given additional property (size, velocity or virulense).
         o	FINDS the organism with given name, find the cluster with given id in the cluster collection of that organism and ADDS the cell to the cells collection of that cluster
         o	RETURNS message "Organism <organism name>: Created cell <cell id> in cluster <cluster id>"
         */
        return null;
    }

    @Override
    public String activateCluster(String organismName) {
        //TODO...
        /** FINDS the organism with the given name
         o	ACTIVATES the next cluster in order
         o	RETURNS message "Organism <organism name>: Activated cluster <cluster id>. Cells left: <cells count>"
         */
        return null;
    }
}
