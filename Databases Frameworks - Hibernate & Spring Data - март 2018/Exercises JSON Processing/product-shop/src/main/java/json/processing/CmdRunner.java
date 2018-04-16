package json.processing;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import json.processing.model.dto.binding.CategoryBindingModel;
import json.processing.model.dto.binding.ProductCreateBindingModel;
import json.processing.model.dto.binding.UsersBindingModel;
import json.processing.model.dto.view.CategoryByProductsCountViewModel;
import json.processing.model.dto.view.ProductInRangeViewModel;
import json.processing.model.dto.view.UserWithSoldItemViewModel;
import json.processing.model.dto.view.usersAndProductsQuery4.UsersViewModelWrapper;
import json.processing.model.entity.Category;
import json.processing.service.categoryService.CategoryService;
import json.processing.service.productService.ProductService;
import json.processing.service.userService.UserService;
import json.processing.util.io.FileIO;
import json.processing.util.serialize.Serializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

@Component
@Transactional
public class CmdRunner implements CommandLineRunner {

    private static final String USERS_INPUT_JSON = "/files/input/json/users.json";
    private static final String PRODUCTS_INPUT_JSON = "/files/input/json/products.json";
    private static final String CATEGORIES_INPUT_JSON = "/files/input/json/categories.json";
    private static final String OUTPUT_JSON_DIRECTORY_PATH = "src/main/resources/files/output/json/";

    private final Serializer serializerJson;
    private final UserService userService;
    private final ProductService productService;
    private final CategoryService categoryService;
    private final FileIO fileIO;
    private final Gson gson;

    @Autowired
    public CmdRunner(@Qualifier(value = "JsonSerializer") Serializer serializerJson,
                     UserService userService,
                     ProductService productService,
                     CategoryService categoryService,
                     FileIO fileIO,
                     Gson gson) {
        this.serializerJson = serializerJson;
        this.userService = userService;
        this.productService = productService;
        this.categoryService = categoryService;
        this.fileIO = fileIO;
        this.gson = gson;
    }

    @Override
    public void run(String... args) throws Exception {
//        seedDataIntoDB();
        queryAndExportData();
    }

    private void queryAndExportData() throws IOException {
        queryProductsInRange();
        querySuccessfullySoldProducts();
        queryCategoriesByProductsCount();
        usersAndProducts();
    }

    private void usersAndProducts() {
        UsersViewModelWrapper modelWrappers = this.userService.getAllUsersWithSoldProduct();
        serializerJson.serialize(modelWrappers, OUTPUT_JSON_DIRECTORY_PATH + "users-and-products.json");
    }

    private void queryCategoriesByProductsCount() {
        List<CategoryByProductsCountViewModel> categories = this.categoryService.categoriesByProductCount();
        serializerJson.serialize(categories, OUTPUT_JSON_DIRECTORY_PATH + "categories-by-products.json");
    }

    private void querySuccessfullySoldProducts() {
        List<UserWithSoldItemViewModel> viewModels = this.productService.getAllUsersQuery2();
        serializerJson.serialize(viewModels, OUTPUT_JSON_DIRECTORY_PATH + "users-sold-products.json");
    }

    private void queryProductsInRange() {
        List<ProductInRangeViewModel> viewModels = this.productService.getAllByRangeWithoutBuyer(500, 1000);
        serializerJson.serialize(viewModels, OUTPUT_JSON_DIRECTORY_PATH + "products-in-range.json");
    }

    private void seedDataIntoDB() throws IOException {
        seedUsers();
        seedCategories();
        seedProducts();
    }

    private void seedProducts() throws IOException {
        String content = fileIO.read(PRODUCTS_INPUT_JSON);
        Type listType = new TypeToken<List<ProductCreateBindingModel>>() {
        }.getType();
        List<ProductCreateBindingModel> products = this.gson.fromJson(content, listType);
        products.forEach(this::randomizeData);
        this.productService.persist(products);
    }

    private void randomizeData(ProductCreateBindingModel model) {
        Random random = new Random();
        int buyer = 0;
        do {
            buyer = random.nextInt(69);
            if (buyer <= 56 && buyer != 0) model.setBuyer(buyer);
        } while (buyer == 0);

        int seller = 0;
        do {
            seller = random.nextInt(56);
            if (seller != buyer && seller != 0) {
                model.setSeller(seller);
            }
        } while (seller == buyer || seller == 0);
    }

    private void seedCategories() throws IOException {
        String content = fileIO.read(CATEGORIES_INPUT_JSON);
        CategoryBindingModel[] categories = gson.fromJson(content, CategoryBindingModel[].class);

        Arrays.stream(categories).forEach(this.categoryService::persist);
    }

    private void seedUsers() throws IOException {
//      UsersBindingModel[] usersDtos = serializerJson.deserialize(UsersBindingModel[].class, USERS_INPUT_JSON);
//      String debug = "";
        String content = fileIO.read(USERS_INPUT_JSON);
        UsersBindingModel[] users = gson.fromJson(content, UsersBindingModel[].class);

        Arrays.stream(users).forEach(this.userService::persist);
    }
}
