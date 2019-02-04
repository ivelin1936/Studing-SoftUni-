package meTube.utils;

import javax.validation.Validation;
import javax.validation.Validator;

public class ValidatorImpl {

    private Validator validator;

    public ValidatorImpl() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public<M> boolean isValid(M model) {
        return this.validator.validate(model).size() == 0;
    }
}
