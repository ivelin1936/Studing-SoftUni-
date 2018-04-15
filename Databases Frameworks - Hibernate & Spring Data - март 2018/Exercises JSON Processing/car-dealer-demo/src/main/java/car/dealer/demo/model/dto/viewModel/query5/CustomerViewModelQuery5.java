package car.dealer.demo.model.dto.viewModel.query5;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class CustomerViewModelQuery5 {

    @Expose
    private String fullName;
    @Expose
    private Integer boughtCars;
    @Expose
    private BigDecimal spentMoney;

    public CustomerViewModelQuery5() {
    }

    public CustomerViewModelQuery5(String fullName, Integer boughtCars, BigDecimal spentMoney) {
        this.fullName = fullName;
        this.boughtCars = boughtCars;
        this.spentMoney = spentMoney;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getBoughtCars() {
        return boughtCars;
    }

    public void setBoughtCars(Integer boughtCars) {
        this.boughtCars = boughtCars;
    }

    public BigDecimal getSpentMoney() {
        return spentMoney;
    }

    public void setSpentMoney(BigDecimal spentMoney) {
        this.spentMoney = spentMoney;
    }
}
