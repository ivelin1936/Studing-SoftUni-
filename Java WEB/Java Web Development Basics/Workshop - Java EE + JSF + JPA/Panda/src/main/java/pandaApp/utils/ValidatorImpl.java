package pandaApp.utils;

import javax.validation.Validation;
import javax.validation.Validator;

public class ValidatorImpl implements IValidator {

    private Validator validator;

    public ValidatorImpl() {
        this.validator = Validation.buildDefaultValidatorFactory()
                .getValidator();
    }

    @Override
    public <M> boolean isValid(M model) {
        return this.validator.validate(model).size() == 0;
    }

//    @Override
//    public <M> List<String> getMessages(M model) {
//        return this.validator.validate(model)
//                .stream()
//                .map(ConstraintViolation::getMessage)
//                .collect(Collectors.toList());
//    }
}
