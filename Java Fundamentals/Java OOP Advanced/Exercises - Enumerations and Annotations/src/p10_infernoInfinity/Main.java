package p10_infernoInfinity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Map<String, Weapon> weaponsMap = new HashMap<>();

        String line;
        while (!"END".equalsIgnoreCase(line = reader.readLine())) {
            String[] tokens = line.split(";");
            commandDispatcher(tokens, weaponsMap);
        }
    }

    private static void commandDispatcher(String[] tokens, Map<String, Weapon> weaponsMap) {
        switch (tokens[0]) {
            case "Create":
                executeCreate(tokens, weaponsMap);
                break;
            case "Add":
                executeAddGem(tokens, weaponsMap);
                break;
            case "Remove":
                executeRemoveGem(tokens, weaponsMap);
                break;
            case "Print":
                executePrint(tokens[1], weaponsMap);
                break;
        }
    }

    private static void executePrint(String weaponName, Map<String, Weapon> weaponsMap) {
        System.out.println(weaponsMap.get(weaponName));
    }

    private static void executeRemoveGem(String[] tokens, Map<String, Weapon> weaponsMap) {
        String weaponName = tokens[1];
        int socketIndex = Integer.parseInt(tokens[2]);
        if (weaponsMap.containsKey(weaponName)) {
            weaponsMap.get(weaponName).removeGems(socketIndex);
        }
    }

    private static void executeAddGem(String[] tokens, Map<String, Weapon> weaponsMap) {
        String weaponName = tokens[1];
        int socketIndex = Integer.parseInt(tokens[2]);
        String gemType = tokens[3];
        try {
            if (weaponsMap.containsKey(weaponName)) {
                weaponsMap.get(weaponName).addGems(socketIndex, gemType);
            }
        } catch (IllegalArgumentException ignored) {
            ;
        }
    }

    private static void executeCreate(String[] tokens, Map<String, Weapon> weaponsMap) {
        String weaponType = tokens[1];
        String weaponName = tokens[2];
        try {
            Weapon weapon = new Weapon(weaponName, weaponType);
            weaponsMap.put(weaponName, weapon);
        } catch (IllegalArgumentException ignored) {
            ;
        }
    }
}
