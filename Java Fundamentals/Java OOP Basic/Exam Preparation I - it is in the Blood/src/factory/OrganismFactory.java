package factory;

import entities.Organism;

public class OrganismFactory {

    public OrganismFactory() {
    }

    public Organism createOrganism(String name) {
        return new Organism(name);
    }
}
