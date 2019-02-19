package pandaApp.domain.models.service;

import pandaApp.domain.entities.enumerations.Status;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class PackageServiceModel {

    private String id;

    @NotNull
    @NotBlank
    private String description;

    @NotNull
    private Double weight;

    @NotNull
    @NotBlank
    private String shippingAddress;

    private Status status;

    private LocalDateTime estimatedDeliveryDate;

    private UserServiceModel recipient;

    public PackageServiceModel() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getEstimatedDeliveryDate() {
        return this.estimatedDeliveryDate;
    }

    public void setEstimatedDeliveryDate(LocalDateTime estimatedDeliveryDate) {
        this.estimatedDeliveryDate = estimatedDeliveryDate;
    }

    public UserServiceModel getRecipient() {
        return this.recipient;
    }

    public void setRecipient(UserServiceModel recipient) {
        this.recipient = recipient;
    }
}
