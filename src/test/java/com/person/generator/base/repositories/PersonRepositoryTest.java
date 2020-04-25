package com.person.generator.base.repositories;

import com.person.generator.base.documents.PersonDocument;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestPropertySource(locations = {"/test.properties"})
class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;


    private List<PersonDocument> savedPersons;

    @BeforeEach
    void setUp() {
        personRepository.deleteAll();
        List<PersonDocument> persons = Arrays.asList(
                PersonDocument.builder()
                        .salary(2500.0)
                        .age(27)
                        .firstName("Marian")
                        .lastName("Marinesc")
                .build(),
                PersonDocument.builder()
                        .salary(3400.0)
                        .age(44)
                        .firstName("Dorel")
                        .lastName("Dorinescu")
                        .build(),
                PersonDocument.builder()
                        .salary(4400.0)
                        .age(32)
                        .firstName("Mihaela")
                        .lastName("Mihaelescu")
                        .build(),
                PersonDocument.builder()
                        .salary(6700.0)
                        .age(36)
                        .firstName("Andreea")
                        .lastName("Andreescu")
                        .build(),
                PersonDocument.builder()
                        .salary(5200.0)
                        .age(232)
                        .firstName("Bogdan")
                        .lastName("Bogdanescu")
                        .build(),
                PersonDocument.builder()
                        .salary(4100.0)
                        .age(34)
                        .firstName("Marcel")
                        .lastName("Marcelescu")
                        .build(),
                PersonDocument.builder()
                        .salary(4400.0)
                        .age(52)
                        .firstName("Marina")
                        .lastName("Marinescu")
                        .build(),
                PersonDocument.builder()
                        .salary(4700.0)
                        .age(46)
                        .firstName("Roxana")
                        .lastName("Roxanescu")
                        .build()
        );
        savedPersons = (List<PersonDocument>) personRepository.saveAll(persons);
    }

    @AfterEach
    void tearDown() {
        if(savedPersons != null && !savedPersons.isEmpty() && savedPersons.get(0).getId() != null){
            personRepository.deleteAll(savedPersons);
        }
    }


    @Test
    public void testNotNull(){
        assertNotNull(personRepository);
    }

    @Test
    public void testSaveOne(){
        PersonDocument givenPerson = PersonDocument.builder()
                .salary(2500.0)
                .age(27)
                .firstName("Marian")
                .lastName("Marinesc")
                .build();
        PersonDocument savedPerson = personRepository.save(givenPerson);
        assertAll("It saved one person document", ()->{
            assertNotNull(savedPerson);
            assertNotNull(savedPerson.getId());
            assertThat(savedPerson.getFirstName()).isEqualTo(givenPerson.getFirstName());
            assertThat(savedPerson.getLastName()).isEqualTo(givenPerson.getLastName());
            assertThat(savedPerson.getAge()).isEqualTo(givenPerson.getAge());
            assertThat(savedPerson.getSalary()).isEqualTo(givenPerson.getSalary());
        });
    }

    @Test
    public void testFindOne(){
        assertAll("It checks that savedPersons is populated", ()->{
            assertNotNull(savedPersons);
            assertFalse(savedPersons.isEmpty());
            assertThat(savedPersons.get(0)).isNotNull();
            assertThat(savedPersons.get(0).getId()).isNotNull();
        });
        PersonDocument givenPerson = savedPersons.get(0);
        Optional<PersonDocument> findOneOpt = personRepository.findById(givenPerson.getId());
        assertAll("It saved one person document", ()->{
            assertNotNull(findOneOpt);
            assertTrue(findOneOpt.isPresent());
            assertThat(findOneOpt.get().getId()).isEqualTo(givenPerson.getId());
            assertThat(findOneOpt.get().getLastName()).isEqualTo(givenPerson.getLastName());
            assertThat(findOneOpt.get().getAge()).isEqualTo(givenPerson.getAge());
            assertThat(findOneOpt.get().getSalary()).isEqualTo(givenPerson.getSalary());
        });
    }

    @Test
    public void testSaveAll(){
        List<PersonDocument> findResult = (List<PersonDocument>) personRepository.findAll();
        assertAll("",() -> {
            assertNotNull(findResult);
            assertFalse(findResult.isEmpty());
            assertThat(findResult.size()).isEqualTo(8);
        });
    }
}