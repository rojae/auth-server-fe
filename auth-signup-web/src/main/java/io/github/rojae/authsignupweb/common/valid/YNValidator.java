package io.github.rojae.authsignupweb.common.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class YNValidator implements ConstraintValidator<YNValid, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null || value.equals(""))
            return false;
        else
            return value.equals("Y") || value.equals("N");
    }
}

