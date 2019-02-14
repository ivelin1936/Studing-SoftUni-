package fdmcApp.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "cats")
public class Cat extends BaseEntity {

    private String name;
    private String breed;
    private String color;
    private Integer age;
    private String gender;
    private BigDecimal price;
    private Date addedOn;
    private boolean hasPassport;

    public Cat() {
    }

    @Column(name = "name", nullable = false)
    @Size.List ({
            @Size(min=2, message="Name must be at least 2 characters"),
            @Size(max=10, message="Name must be less than 10 characters")
    })
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "breed", nullable = false)
    @Size.List ({
            @Size(min=5, message="Breed must be at least 5 characters"),
            @Size(max=20, message="Breed must be less than 20 characters")
    })
    public String getBreed() {
        return this.breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    @Column(name = "color")
    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Column(name = "age", nullable = false)
    @Min(value = 1, message = "Age must be an integer between 1 and 31.")
    @Max(value = 31, message = "Age must be an integer between 1 and 31.")
    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Column(name = "gender")
    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Column(name = "price", nullable = false)
    @DecimalMin(value = "0.01", message = "Price value, must be at least 0.01")
    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(name = "added_on", nullable = false)
    @Temporal(TemporalType.DATE)
    public Date getAddedOn() {
        return this.addedOn;
    }

    public void setAddedOn(Date addedOn) {
        this.addedOn = addedOn;
    }

    @Column(name = "has_passport", nullable = false)
    public boolean isHasPassport() {
        return this.hasPassport;
    }

    public void setHasPassport(boolean hasPassport) {
        this.hasPassport = hasPassport;
    }
}
