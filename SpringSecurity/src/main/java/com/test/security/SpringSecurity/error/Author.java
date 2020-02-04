package com.test.security.SpringSecurity.error;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AuthorValidator.class)
@Documented
public @interface Author {
    String message() default "Author is not allow";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
