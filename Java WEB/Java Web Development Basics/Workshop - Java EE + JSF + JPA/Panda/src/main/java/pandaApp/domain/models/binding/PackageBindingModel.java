package pandaApp.domain.models.binding;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PackageBindingModel {

    @NotNull
    @NotBlank(message = "May not be blank")
    private String description;

    @NotNull
    private Double weight;

    @NotNull
    @NotBlank(message = "May not be blank")
    private String shippingAddress;

    @NotNull
    @NotBlank(message = "May not be blank")
    private String recipient;

    public PackageBindingModel() {
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getWeight() {
        return this.weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getShippingAddress() {
        return this.shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getRecipient() {
        return this.recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }
}
