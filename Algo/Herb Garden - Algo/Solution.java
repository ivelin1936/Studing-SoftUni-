import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {

    private static final int DEFAULT_NEW_LEAVES = 2;
    private static final String PLANT_SPLIT_PATTERN = ", ";

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //Example plants input: 2, 7, 5, 3
        int[] plants = Arrays.stream(reader.readLine().split(PLANT_SPLIT_PATTERN))
                .mapToInt(Integer::parseInt)
                .toArray();

        int numberOfDays = Integer.parseInt(reader.readLine());

        int totalHarvest = herbGarden(plants, numberOfDays);

        //the output should be An integer representing the maximum total number
        //of leaves you can harvest in the given number of days
        System.out.println(totalHarvest);
    }

    private static int herbGarden(int[] plants, int days) {
        int totalHarvestedLeaves = 0;

        for (int currentDay = 1; currentDay <= days; currentDay++) {
            totalHarvestedLeaves += harvest(plants);
            growsPlants(plants);
        }

        return totalHarvestedLeaves;
    }

    private static int harvest(int[] plants) {
        //Find index of the biggest plant
        int biggestPlantIndex = findBiggestPlantIndex(plants);

        //harvest half of its leaves (rounded down)
        int harvestedLeaves = (int) Math.floor(plants[biggestPlantIndex] / 2D);
        plants[biggestPlantIndex] = plants[biggestPlantIndex] - harvestedLeaves;

        return harvestedLeaves;
    }

    private static void growsPlants(int[] plants) {
        //every night, each of the plants grows 2 new leaves
        for (int plantIndex = 0; plantIndex < plants.length; plantIndex++) {
            plants[plantIndex] += DEFAULT_NEW_LEAVES;
        }
    }

    private static int findBiggestPlantIndex(int[] plants) {
        int biggestPlantIndex = 0;

        for (int currentPlantIndex = 1; currentPlantIndex < plants.length; currentPlantIndex++) {
            if (plants[currentPlantIndex] > plants[biggestPlantIndex]) {
                biggestPlantIndex = currentPlantIndex;
            }
        }

        return biggestPlantIndex;
    }
}
