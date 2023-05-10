package com.example.springsecurity.repository;

import com.example.springsecurity.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface PeopleRepository extends JpaRepository<Person, Long> {

    List<Person> findAllByRole(String role);

    Optional<Person> findByName(String name);

    Optional<Person> findByEmail(String email);
}
