package pandaApp.service.receiptService;

import pandaApp.domain.models.service.ReceiptServiceModel;

import java.util.List;

public interface ReceiptService {

    List<ReceiptServiceModel> findAllByUsername(String username);

    ReceiptServiceModel createReceipt(String username, String packageId);

    ReceiptServiceModel findById(String receiptId);
}
