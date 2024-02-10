package org.example.util.validators;


import org.example.models.Human;
import org.example.services.HumanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class HumanValidator implements Validator {

    HumanService humanService;

    @Autowired
    public HumanValidator(HumanService humanService) {
        this.humanService = humanService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Human.class == clazz;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Human human = (Human) target;

        if (humanService.getHuman(human.getFio()).isPresent()) {
            errors.rejectValue("fio", "", "FIO should be UNIQUE!");
        }
    }
}
