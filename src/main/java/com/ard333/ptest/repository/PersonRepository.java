package com.ard333.ptest.repository;

import java.util.List;

import com.ard333.ptest.entity.Person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findByPostalCodeBetweenOrderByNameAsc(String from, String to);
}