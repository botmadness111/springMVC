package org.example.util.validators;

import org.example.dao.HumanDAO;
import org.example.models.Human;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class HumanValidator implements Validator {

    HumanDAO humanDAO;

    @Autowired
    public HumanValidator(HumanDAO humanDAO) {
        this.humanDAO = humanDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Human.class == clazz;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Human human = (Human) target;

        if (humanDAO.getHuman(human.getFio()).isPresent()) {
            errors.rejectValue("fio", "", "FIO should be UNIQUE!");
        }
    }
}
