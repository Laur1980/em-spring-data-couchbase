package com.person.generator.base.controllers;

import com.person.generator.base.controllers.dtos.PersonDTO;
import com.person.generator.base.services.PersonServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/persons")
public class PersonController {

    private final PersonServiceImpl personService;

    @GetMapping
    public ResponseEntity<?> handleGetAll(){
        List<PersonDTO> persons = personService.findAll();
        return new ResponseEntity<>(persons, persons.isEmpty()? HttpStatus.NO_CONTENT: HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> handleGetOne(@PathVariable(value = "id", required = true)  String id){
        PersonDTO dto = personService.findOne(id);
        return new ResponseEntity<>(dto, dto == null? HttpStatus.NO_CONTENT: HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> handlePost(@RequestBody @Valid PersonDTO person,
                                        BindingResult bindingResult){
        PersonDTO dto = personService.saveOne(person);
        return new ResponseEntity<>(dto, dto == null ? HttpStatus.INTERNAL_SERVER_ERROR: HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<?> handlePut(@RequestBody @Valid PersonDTO person,
                                       BindingResult bindingResult){
        PersonDTO dto = personService.updateOne(person);
        return new ResponseEntity<>(dto, dto == null ? HttpStatus.INTERNAL_SERVER_ERROR: HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> handleDeleteMapping(@PathVariable(value = "id", required = true) String id){
        boolean result = personService.deleteOne(id);
        return new ResponseEntity<>(result, result? HttpStatus.OK: HttpStatus.NOT_FOUND);
    }

}
