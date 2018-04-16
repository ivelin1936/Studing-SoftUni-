package json.processing.service.categoryService;

import json.processing.model.dto.binding.CategoryBindingModel;
import json.processing.model.dto.view.CategoryByProductsCountViewModel;
import json.processing.model.entity.Category;

import java.util.LinkedList;
import java.util.List;

public interface CategoryService {

    void persist(CategoryBindingModel categoryBindingModel);

    CategoryBindingModel getCategoryById(Long categoryID);

    List<CategoryByProductsCountViewModel> categoriesByProductCount();
}
