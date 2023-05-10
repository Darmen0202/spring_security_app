package com.example.springsecurity.service;

import com.example.springsecurity.models.Person;
import com.example.springsecurity.repository.PeopleRepository;
import com.example.springsecurity.security.PersonDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class PeopleDetailsService implements UserDetailsService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleDetailsService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Person> person= peopleRepository.findByEmail(s);


        if(person.isEmpty())
            throw new UsernameNotFoundException("Пайдаланушы табылмады!");

        return new PersonDetails(person.get());
    }


}