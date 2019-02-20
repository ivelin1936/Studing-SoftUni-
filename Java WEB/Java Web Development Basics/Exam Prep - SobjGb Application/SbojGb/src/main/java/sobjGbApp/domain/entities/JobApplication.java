package sobjGbApp.domain.entities;

import sobjGbApp.domain.entities.enumerations.Sector;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "job_application")
public class JobApplication extends BaseEntity {

    private Sector sector;
    private String profession;
    private BigDecimal salary;
    private String description;

    public JobApplication() {
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "sector", nullable = false)
    public Sector getSector() {
        return this.sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    @Column(name = "profession", nullable = false)
    public String getProfession() {
        return this.profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    @Column(name = "salary", nullable = false)
    public BigDecimal getSalary() {
        return this.salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Column(name = "description", nullable = false)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
