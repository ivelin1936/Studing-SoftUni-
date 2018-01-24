package com.company.commandEngine.interfaces;

public interface IAnimalCenterManager {

    void registerCleansingCenter(String name);

    void registerAdoptionCenter(String name);

    void registerDog(String name, int age, int learnedCommands, String adoptionCenterName);

    void registerCat(String name, int age, int intelligenceCoefficient, String adoptionCenterName);

    void sendForCleansing(String adoptionCenterName, String cleansingCenterName);

    void cleanse(String cleansingCenterName);

    void adopt(String adoptionCenterName);

    void printStatistics();

}
