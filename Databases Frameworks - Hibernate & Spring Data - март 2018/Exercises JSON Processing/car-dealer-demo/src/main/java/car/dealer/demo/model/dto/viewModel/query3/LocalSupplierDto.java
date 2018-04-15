package car.dealer.demo.model.dto.viewModel.query3;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LocalSupplierDto {

    @Expose
    @SerializedName(value = "Id")
    private Long id;

    @Expose
    @SerializedName(value = "Name")
    private String name;

    @Expose
    private Long partsCount;

    public LocalSupplierDto() {
    }

    public LocalSupplierDto(Long id, String name, Long partsCount) {
        this.id = id;
        this.name = name;
        this.partsCount = partsCount;
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

    public long getPartsCount() {
        return partsCount;
    }

    public void setPartsCount(long partsCount) {
        this.partsCount = partsCount;
    }
}
