package pandaApp.repository.receiptRepo;

import pandaApp.domain.entities.Receipt;
import pandaApp.repository.genericRepo.GenericRepositoryImpl;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class ReceiptRepositoryImpl extends GenericRepositoryImpl<Receipt, String> implements ReceiptRepository {

    private EntityManager entityManager;

    @Inject
    public ReceiptRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Receipt> findAllByUsername(String username) {
        try {
            List<Receipt> Receipt = this.entityManager
                    .createQuery("SELECT r FROM Receipt r WHERE r.recipient.username = :username", Receipt.class)
                    .setParameter("username", username)
                    .getResultList();

            return Receipt;
        } catch (IllegalStateException | IllegalArgumentException ex) {
            //LOG here...
            return List.of();
        }
    }
}
