package com.example.springsecurity.util;


import com.example.springsecurity.dto.PersonDTO;
import com.example.springsecurity.service.PeopleDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    private final PeopleDetailsService peopleDetailsService;

    @Autowired
    public PersonValidator(PeopleDetailsService peopleDetailsService) {
        this.peopleDetailsService =peopleDetailsService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return PersonDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        PersonDTO personDTO = (PersonDTO) o;


        try {
            peopleDetailsService.loadUserByUsername(personDTO.getEmail());
        } catch (UsernameNotFoundException ignored){
            return;
        }

        errors.rejectValue("name","","Бұндай есімді пайдалануышы бар");
    }

}
