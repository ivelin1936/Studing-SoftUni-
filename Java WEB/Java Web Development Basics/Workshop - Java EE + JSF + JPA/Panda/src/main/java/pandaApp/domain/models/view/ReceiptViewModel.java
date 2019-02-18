package pandaApp.domain.models.view;

import java.math.BigDecimal;

public class ReceiptViewModel {

    private String id;
    private BigDecimal fee;
    private String issuedOn;
    private String recipient;

    public ReceiptViewModel() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getFee() {
        return this.fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public String getIssuedOn() {
        return this.issuedOn;
    }

    public void setIssuedOn(String issuedOn) {
        this.issuedOn = issuedOn;
    }

    public String getRecipient() {
        return this.recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }
}
