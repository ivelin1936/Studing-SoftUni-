import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Internships {

    private static final String VALID_NAME_REGEX = "\\b[A-Z][a-z]+[ ][A-Z][a-z]+\\b";

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int numberOfProblems = Integer.parseInt(reader.readLine());
        int numberOfCandidates = Integer.parseInt(reader.readLine());

        Deque<String> problems = new ArrayDeque<>();
        for (int i = 0; i < numberOfProblems; i++) {
            String problem = reader.readLine();
            problems.push(problem);
        }

        Deque<String> candidates = new ArrayDeque<>();
        for (int i = 0; i < numberOfCandidates; i++) {
            String candidat = reader.readLine();
            if (candidat.matches(VALID_NAME_REGEX)) {
                candidates.offer(candidat);
            }
        }

        while (problems.size() != 0) {

            if (candidates.size() == 1) {
                System.out.println(candidates.poll() + " gets the job!");
                return;
            }

            String problem = problems.pop();
            String ppl = candidates.poll();

            if (checkIfTheCandidateSolvedIt(problem, ppl)) {
                /**If a Problem is solved,
                 * it is removed from the stack with problems,
                 * and the Candidate who solved it goes to the
                 * end of the queue. You should write on the console:
                 * "{candidate} solved {problem}."*/
                candidates.offer(ppl);
                System.out.println(ppl + " solved " + problem + ".");

            } else {
                /**If a Problem is unsolved, it goes to the bottom
                 * of the stack and the candidate fails the interview,
                 * so he leaves in tears. You should write on the console:
                 * "{candidate} failed {problem}."*/
                problems.addLast(problem);
                System.out.println(ppl + " failed " + problem + ".");
            }
        }

        if (problems.size() == 0) {
            System.out.println(String.join(", ", candidates));
        }
    }

    private static boolean checkIfTheCandidateSolvedIt(String problem, String ppl) {
        long candidateNameLettersSum = ppl.chars().mapToLong(ch -> ch).sum();
        long problemLettersSum = problem.chars().mapToLong(ch -> ch).sum();

        return candidateNameLettersSum > problemLettersSum;
    }
}
