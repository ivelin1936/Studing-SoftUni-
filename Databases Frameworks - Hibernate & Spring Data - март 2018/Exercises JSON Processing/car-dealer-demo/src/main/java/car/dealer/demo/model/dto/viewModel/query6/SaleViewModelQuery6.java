package car.dealer.demo.model.dto.viewModel.query6;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class SaleViewModelQuery6 {

    @Expose
    private CarViewModelQuery6 car;

    @Expose
    private String customerName;

    @Expose
    @SerializedName("Discount")
    private Double discount;

    @Expose
    private BigDecimal price;

    @Expose
    private BigDecimal priceWithDiscount;

    public SaleViewModelQuery6() {
    }

    public CarViewModelQuery6 getCar() {
        return car;
    }

    public void setCar(CarViewModelQuery6 car) {
        this.car = car;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPriceWithDiscount() {
        return priceWithDiscount;
    }

    public void setPriceWithDiscount(BigDecimal priceWithDiscount) {
        this.priceWithDiscount = priceWithDiscount;
    }
}
