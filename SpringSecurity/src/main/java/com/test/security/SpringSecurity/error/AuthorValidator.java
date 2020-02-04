package com.test.security.SpringSecurity.error;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class AuthorValidator implements ConstraintValidator<Author, String> {

   List<String> authors = Arrays.asList("Thant Sin Aung", "Min Khant Kyaw", "Zar Ni Maung");
   public boolean isValid(String obj, ConstraintValidatorContext context) {
      return authors.contains(obj);
   }
}
