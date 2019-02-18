package pandaApp.repository.receiptRepo;

import pandaApp.domain.entities.Receipt;
import pandaApp.repository.genericRepo.GenericRepository;

import java.util.List;

public interface ReceiptRepository extends GenericRepository<Receipt, String> {

    List<Receipt> findAllByUsername(String username);

}
