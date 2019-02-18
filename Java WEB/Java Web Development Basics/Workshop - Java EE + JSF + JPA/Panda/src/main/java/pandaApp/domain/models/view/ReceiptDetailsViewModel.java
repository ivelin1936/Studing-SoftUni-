package pandaApp.domain.models.view;

import java.math.BigDecimal;

public class ReceiptDetailsViewModel {

    private String id;
    private BigDecimal fee;
    private String issuedOn;
    private ReceiptPackageViewModel aPackage;

    public ReceiptDetailsViewModel() {
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

    public ReceiptPackageViewModel getaPackage() {
        return this.aPackage;
    }

    public void setaPackage(ReceiptPackageViewModel aPackage) {
        this.aPackage = aPackage;
    }
}
