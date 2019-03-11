package org.softuni.cardealer.domain.models.service;

public class SupplierServiceModel extends BaseServiceModel {

    private String id;
    private String name;
    private boolean isImporter;

    public SupplierServiceModel() {
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isImporter() {
        return isImporter;
    }

    public void setImporter(boolean importer) {
        isImporter = importer;
    }
}
