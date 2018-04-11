package json.processing;

import com.google.gson.Gson;
import json.processing.model.dto.binding.UsersBindingModel;
import json.processing.model.entity.User;
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
import java.util.Arrays;

@Component
@Transactional
public class CmdRunner implements CommandLineRunner {

    private static final String USERS_INPUT_JSON = "/files/input/json/users.json";
    private static final String PRODUCTS_INPUT_JSON = "/files/input/json/products.json";
    private static final String CATEGORIES_INPUT_JSON = "/files/input/json/categories.json";

    private final Serializer serializerJson;
    private final UserService userService;
    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public CmdRunner(@Qualifier(value = "JsonSerializer") Serializer serializerJson,
                     UserService userService,
                     ProductService productService,
                     CategoryService categoryService) {
        this.serializerJson = serializerJson;
        this.userService = userService;
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
        seedDataIntoDB();
    }

    private void seedDataIntoDB() throws IOException {
        seedUsers();
    }

    private void seedUsers() throws IOException {
//      UsersBindingModel[] usersDtos = serializerJson.deserialize(UsersBindingModel[].class, USERS_INPUT_JSON);
//      String debug = "";
        FileIO fileIO = new FileIO();
        Gson gson = new Gson();
        String content = fileIO.read(USERS_INPUT_JSON);
        UsersBindingModel[] users = gson.fromJson(content, UsersBindingModel[].class);

        Arrays.stream(users).forEach(this.userService::persist);
    }
}
