package ru.malik.spring.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import ru.malik.spring.dao.PersonDAO;
import ru.malik.spring.models.Person;

@Component
public class PersonValidator {

    private final PersonDAO personDAO;

    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        if(personDAO.getPersonByFullName(person.getFullName()).isPresent())
               errors.rejectValue("fullName", "", "Человек с таким ФИ существует");
    }
}
