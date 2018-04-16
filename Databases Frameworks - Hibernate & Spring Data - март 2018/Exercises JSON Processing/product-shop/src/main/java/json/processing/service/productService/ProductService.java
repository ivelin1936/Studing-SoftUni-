package json.processing.service.productService;

import json.processing.model.dto.binding.ProductCreateBindingModel;
import json.processing.model.dto.view.ProductInRangeViewModel;
import json.processing.model.dto.view.UserWithSoldItemViewModel;
import json.processing.model.entity.User;

import java.util.Collection;
import java.util.List;

public interface ProductService {

    void persist(Collection<ProductCreateBindingModel> models);

    List<ProductInRangeViewModel> getAllByRangeWithoutBuyer(int from, int to);

    List<UserWithSoldItemViewModel> getAllUsersQuery2();
}
