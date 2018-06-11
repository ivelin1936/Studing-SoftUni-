import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Genome {

    private static final String STOPER = "Stop!";
    private static final String FULL_MATCH_REGEX = "^([!@#$\\?A-Za-z]+)[=](\\d+)[-]{2}(\\d+)[<]{2}([A-Za-z]+)\\b";
    private static final String NAME_REGEX = "([a-z]+)";
    private static final String LENGTH_OF_THE_NAME_REGEX = "[=](\\d+)";
    private static final String COUNT_OF_GENES_REGEX = "[-]{2}(\\d+)";
    private static final String ORGANISM_REGEX = "[<]{2}([A-Za-z]+)";

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Map<String, Integer> mapDB = new LinkedHashMap<>();

        String line;
        while (!STOPER.equals(line = reader.readLine())) {
            if (line.matches(FULL_MATCH_REGEX)) {
                int nameLength = getName(line);
                int searchingLength = getValue(line, LENGTH_OF_THE_NAME_REGEX);

                if (nameLength != searchingLength) {
                    continue;
                }

                int countOfGenes = getValue(line, COUNT_OF_GENES_REGEX);
                String orgamismName = getOrganismName(line);

                if (mapDB.containsKey(orgamismName)) {
                    int newValue = mapDB.get(orgamismName) + countOfGenes;
                    mapDB.replace(orgamismName, newValue);
                }
                mapDB.putIfAbsent(orgamismName, countOfGenes);
            }
        }

        printResult(mapDB);
    }

    private static void printResult(Map<String,Integer> mapDB) {
        mapDB.entrySet().stream()
                .sorted((o1, o2) -> o2.getValue().compareTo(o1.getValue()))
                .forEach( org -> {
                    System.out.println(String.format("%s has genome size of %d", org.getKey(), org.getValue()));
                });
    }

    private static String getOrganismName(String line) {
        Pattern pattern = Pattern.compile(Genome.ORGANISM_REGEX);
        Matcher matcher = pattern.matcher(line);
        matcher.find();

        return matcher.group(1);
    }

    private static int getValue(String line, String regex) {
        int number = 0;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            number += Integer.parseInt(matcher.group(1));
        }

        return number;
    }

    private static int getName(String line) {
        StringBuilder nameBuilder = new StringBuilder();
        String firstPart = line.split("=")[0];

        Pattern pattern = Pattern.compile(NAME_REGEX);
        Matcher matcher = pattern.matcher(firstPart);
        while (matcher.find()) {
            nameBuilder.append(matcher.group());
        }

        return nameBuilder.toString().length();
    }
}
