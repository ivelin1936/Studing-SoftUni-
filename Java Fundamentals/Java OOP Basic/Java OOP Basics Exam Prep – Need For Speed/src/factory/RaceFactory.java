package factory;

import entity.raceDto.CasualRace;
import entity.raceDto.DragRace;
import entity.raceDto.DriftRace;
import entity.raceDto.Race;

public final class RaceFactory {

    private RaceFactory() {
    }

    public static Race createRace(String type, int length, String route, int prizePool) {
        Race race = null;

        switch (type){
            case "Casual":
                race = new CasualRace(length, route, prizePool);
                break;
            case "Drag":
                race = new DragRace(length, route, prizePool);
                break;
            case "Drift":
                race = new DriftRace(length, route, prizePool);
                break;
        }

        return race;
    }
}
