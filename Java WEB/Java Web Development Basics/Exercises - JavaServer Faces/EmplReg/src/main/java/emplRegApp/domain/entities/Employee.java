package emplRegApp.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "emplayees")
public class Employee extends BaseEntity {

    private String firstName;
    private String lastName;
    private String position;
    private BigDecimal salary;
    private Integer age;

    public Employee() {
    }

    @Column(name = "first_name", nullable = false)
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "position", nullable = false)
    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Column(name = "salary", nullable = false)
    public BigDecimal getSalary() {
        return this.salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Column(name = "age", nullable = false)
    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
