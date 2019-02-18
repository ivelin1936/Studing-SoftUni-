package pandaApp.service.receiptService;

import org.modelmapper.ModelMapper;
import pandaApp.domain.entities.Receipt;
import pandaApp.domain.models.service.PackageServiceModel;
import pandaApp.domain.models.service.ReceiptServiceModel;
import pandaApp.domain.models.service.UserServiceModel;
import pandaApp.repository.receiptRepo.ReceiptRepository;
import pandaApp.service.packageService.PackageService;
import pandaApp.service.userService.UserService;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ReceiptServiceImpl implements ReceiptService {

    private ReceiptRepository receiptRepository;
    private PackageService packageService;
    private UserService userService;
    private ModelMapper modelMapper;

    @Inject
    public ReceiptServiceImpl(ReceiptRepository receiptRepository,
                              PackageService packageService,
                              UserService userService,
                              ModelMapper modelMapper) {
        this.receiptRepository = receiptRepository;
        this.packageService = packageService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<ReceiptServiceModel> findAllByUsername(String username) {
        List<Receipt> receipts = this.receiptRepository.findAllByUsername(username);

        return receipts.stream()
                .map(r -> this.modelMapper.map(r, ReceiptServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public ReceiptServiceModel findById(String receiptId) {
        Receipt receiptEntity = this.receiptRepository.findById(receiptId);

        if (receiptEntity == null) {
            throw new IllegalArgumentException("Receipt with passed id doesn't exist.");
        } else {
            return this.modelMapper.map(receiptEntity, ReceiptServiceModel.class);
        }
    }

    @Override
    public ReceiptServiceModel createReceipt(String username, String packageId) {
        //Update package status by passed id to Acquired
        this.packageService.updateStatus(packageId);

        //Get the package from DB with the passed id
        PackageServiceModel packageServiceModel = this.packageService.findById(packageId);

        //Build new receipt for this delivered package
        ReceiptServiceModel receiptServiceModel = buildReceipt(username, packageServiceModel);

        //Save resulted receipt into database
        Receipt receipt = this.receiptRepository
                .save(this.modelMapper.map(receiptServiceModel, Receipt.class));

        return this.modelMapper.map(receipt, ReceiptServiceModel.class);
    }

    private ReceiptServiceModel buildReceipt(String username, PackageServiceModel packageServiceModel) {
        ReceiptServiceModel receiptServiceModel = new ReceiptServiceModel();

        //Receipt’s Fee should be set to the Package’s Weight multiplied (*) by 2.67
        BigDecimal fee = BigDecimal.valueOf(packageServiceModel.getWeight())
                .multiply(BigDecimal.valueOf(2.67));
        receiptServiceModel.setFee(fee);

        //Set receipt's package
        receiptServiceModel.setaPackage(packageServiceModel);

        //Set recipient
        UserServiceModel userServiceModel = this.userService.findByUsername(username);
        receiptServiceModel.setRecipient(userServiceModel);

        //Set issuedOn date
        receiptServiceModel.setIssuedOn(LocalDateTime.now());

        return receiptServiceModel;
    }

}
