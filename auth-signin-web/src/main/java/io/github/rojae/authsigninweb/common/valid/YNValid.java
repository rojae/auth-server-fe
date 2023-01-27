package io.github.rojae.authsigninweb.common.valid;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = YNValidator.class)
public @interface YNValid {
    String message() default "정의된 Y, N이 아닙니다";
    Class[] groups() default {};
    Class[] payload() default {};
}