package car.dealer.demo.model.dto.viewModel.query1;

import car.dealer.demo.model.dto.bindingModel.query1Dtos.SaleDto;
import car.dealer.demo.model.entity.Sale;
import com.google.gson.annotations.Expose;

import java.util.Date;
import java.util.Set;

public class CustomerViewModelQuery1 {

    @Expose
    private Long id;
    @Expose
    private String name;
    @Expose
    private Date birthDate;
    @Expose
    private boolean isYoungDriver;
    @Expose
    private Set<SaleDto> sales;

    public CustomerViewModelQuery1() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isYoungDriver() {
        return isYoungDriver;
    }

    public void setYoungDriver(boolean youngDriver) {
        isYoungDriver = youngDriver;
    }

    public Set<SaleDto> getSales() {
        return sales;
    }

    public void setSales(Set<SaleDto> sales) {
        this.sales = sales;
    }
}
