package com.example.springsecurity.contollers;

import com.example.springsecurity.dto.PersonDTO;
import com.example.springsecurity.expception.RegistrationException;
import com.example.springsecurity.models.Person;
import com.example.springsecurity.service.RegistrationService;
import com.example.springsecurity.util.PersonValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/auth")
public class AuthController {

    private final RegistrationService registrationService;
    private final PersonValidator personValidator;
    private final ModelMapper modelMapper;

    @Autowired
    public AuthController(RegistrationService registrationService, PersonValidator personValidator, ModelMapper modelMapper) {
        this.registrationService = registrationService;
        this.personValidator = personValidator;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("personDTO") PersonDTO personDTO){
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("personDTO") @Valid PersonDTO personDTO,
                                      BindingResult bindingResult, Model model) {
        personValidator.validate(personDTO, bindingResult);

        if (!personDTO.getPassword().equals(personDTO.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword", "passwords.mismatch","Пароль сәйкес келмейді");
        }

            if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "/auth/registration";
        }

        try {
            registrationService.register(convertToPerson(personDTO));
        } catch (RegistrationException e) {
            model.addAttribute("error", e.getMessage());
            return "/auth/registration";
        }

        return "redirect:/auth/login";
    }


    private Person convertToPerson(PersonDTO personDTO){
        return modelMapper.map(personDTO,Person.class);
    }
}
