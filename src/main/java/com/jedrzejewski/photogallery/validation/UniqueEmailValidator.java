package com.jedrzejewski.photogallery.validation;

import com.jedrzejewski.photogallery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmailConstraint, String> {

    @Autowired
    private UserRepository userRepository;

    public UniqueEmailValidator() {
    }

    @Override
    public void initialize(UniqueEmailConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return !userRepository.existsUserByEmail(email);
    }
}
