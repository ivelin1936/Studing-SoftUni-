
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class Ranking {

    private static final String STOPER_CONTESTS = "end of contests";
    private static final String STOPER_SUBMISSIONS = "end of submissions";

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Map<String, String> contestPassDB = new LinkedHashMap<>();
        fillContestDB(reader, contestPassDB);

        Map<String, Map<String, Integer>> studentsDB = new TreeMap<>();
        String lineSub;
        while (!STOPER_SUBMISSIONS.equalsIgnoreCase(lineSub = reader.readLine())) {
            String[] tokens = lineSub.split("=>");
            //“{contest}=>{password}=>{username}=>{points}”
            String contest = tokens[0];
            String password = tokens[1];
            String userName = tokens[2];
            int points = Integer.parseInt(tokens[3]);

            if (!contestPassDB.containsKey(contest) || !contestPassDB.get(contest).equals(password)) {
                continue;
            }

            if (!studentsDB.containsKey(userName)) {
                studentsDB.putIfAbsent(userName, new LinkedHashMap<>());
                studentsDB.get(userName).put(contest, points);
            } else {
                if (studentsDB.get(userName).containsKey(contest)) {
                    int oldPt = studentsDB.get(userName).get(contest);
                    if (points > oldPt) {
                        studentsDB.get(userName).replace(contest, points);
                    }
                } else {
                    studentsDB.get(userName).putIfAbsent(contest, points);
                }
            }
        }

        printBestCandidate(studentsDB);
        printRanking(studentsDB);
    }

    private static void printRanking(Map<String,Map<String,Integer>> studentsDB) {
        System.out.println("Ranking:");
        for (String student : studentsDB.keySet()) {
            System.out.println(student);

            studentsDB.get(student)
                    .entrySet()
                    .stream()
                    .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                    .forEach(exam -> {
                        System.out.println(String.format("#  %s -> %d", exam.getKey(), exam.getValue()));
                    });
        }
    }

    private static void printBestCandidate(Map<String,Map<String,Integer>> studentsDB) {
        String bestCandidateName = "";
        int totalPoints = 0;
        for (String stud : studentsDB.keySet()) {
            int buffSum = studentsDB.get(stud).values().stream().mapToInt(Integer::valueOf).sum();
            if (buffSum > totalPoints) {
                totalPoints = buffSum;
                bestCandidateName = stud;
            }
        }

        System.out.println(String.format("Best candidate is %s with total %d points.", bestCandidateName, totalPoints));
    }

    private static void fillContestDB(BufferedReader reader, Map<String, String> contestPassDB) throws IOException {
        String lineContests;
        while (!STOPER_CONTESTS.equalsIgnoreCase(lineContests = reader.readLine())) {
            String[] tokens = lineContests.split(":");
            String contest = tokens[0];
            String password = tokens[1];

            contestPassDB.putIfAbsent(contest, password);
        }
    }
}
