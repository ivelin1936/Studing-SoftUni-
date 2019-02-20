package sobjGbApp.domain.models.binding;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class JobBindingModel {

    @NotNull(message = "Cannot be null.")
    private String sector;

    @NotNull
    @NotEmpty(message = "Cannot be empty.")
    private String profession;

    @NotNull(message = "Cannot be null.")
    private BigDecimal salary;

    @NotNull
    @NotEmpty(message = "Cannot be empty.")
    private String description;

    public JobBindingModel() {
    }

    public String getSector() {
        return this.sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getProfession() {
        return this.profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public BigDecimal getSalary() {
        return this.salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
