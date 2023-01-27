package io.github.rojae.authsigninweb.common.valid;

import io.github.rojae.authsigninweb.common.enums.PlatformType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PlatformTypeValidator implements ConstraintValidator<YNValid, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !PlatformType.valueOfName(value).equals(PlatformType.UNKNOWN);
    }
}
