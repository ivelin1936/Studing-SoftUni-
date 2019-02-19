package pandaApp.repository.receiptRepo;

import pandaApp.domain.entities.Receipt;
import pandaApp.repository.genericRepo.GenericRepositoryImpl;
import pandaApp.utils.AppConstants;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReceiptRepositoryImpl extends GenericRepositoryImpl<Receipt, String> implements ReceiptRepository {

    private static final Logger LOG = Logger.getLogger(ReceiptRepositoryImpl.class.getName());

    @Override
    protected Logger logger() {
        return LOG;
    }

    @Override
    public List<Receipt> findAllByUsername(String username) {
        try {
            List<Receipt> Receipt = super.entityManager
                    .createQuery("SELECT r FROM Receipt r WHERE r.recipient.username = :username", Receipt.class)
                    .setParameter(AppConstants.USERNAME, username)
                    .getResultList();

            return Receipt;
        } catch (IllegalStateException | IllegalArgumentException ex) {
            //LOG here...
            LOG.log(Level.SEVERE, "No Receipts found by username: " + username, ex);
            return List.of();
        }
    }
}
