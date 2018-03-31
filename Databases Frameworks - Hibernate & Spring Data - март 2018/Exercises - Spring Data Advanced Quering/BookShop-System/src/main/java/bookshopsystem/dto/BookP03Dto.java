package bookshopsystem.dto;

import java.math.BigDecimal;

public class BookP03Dto {

    private String title;
    private BigDecimal price;

    public BookP03Dto() {
    }

    public BookP03Dto(String title, BigDecimal price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
