package exercises;

import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class ContainsEmployee {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager em = factory.createEntityManager();

        String[] fullName = reader.readLine().split(" ");

        List<Employee> employees = em.createQuery("SELECT e FROM Employee AS e").getResultList();

//        Employee inputEmployee = new Employee();
//        inputEmployee.setFirstName(fullName[0]);
//        inputEmployee.setLastName(fullName[1]);
//        if (em.contains(inputEmployee)) {
//            System.out.println("Yes");
//        } else {
//            System.out.println("No");
//        }

        if (isContained(fullName, employees)) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }

        em.close();
        factory.close();
    }

    private static boolean isContained(String[] fullName, List<Employee> employees) {
        String firstName = fullName[0];
        String lastName = fullName[1];

        for (Employee employee : employees) {
            if (employee.getFirstName().equals(firstName) && employee.getLastName().equals(lastName)) {
                return true;
            }
        }
        return false;
    }
}
