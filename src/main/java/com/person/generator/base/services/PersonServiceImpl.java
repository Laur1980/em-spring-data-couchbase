package com.person.generator.base.services;

import com.person.generator.base.controllers.dtos.PersonDTO;
import com.person.generator.base.controllers.exceptions.BusinessException;
import com.person.generator.base.documents.PersonDocument;
import com.person.generator.base.repositories.PersonRepository;
import com.person.generator.base.services.mappers.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl {

    private final PersonRepository personRepository;

    @Qualifier("personMapperImpl")
    private final Mapper<PersonDocument,PersonDTO> personMapper;

    public List<PersonDTO> findAll(){
        List<PersonDocument> persons = (List<PersonDocument>) personRepository.findAll();
        if(persons == null || persons.isEmpty()){
            return Collections.emptyList();
        }
        return persons.stream().map(personMapper::documentToDTO).collect(Collectors.toList());
    }

    public PersonDTO findOne(String id){
        if(id == null){
            throw new IllegalArgumentException(String.format("Invalid id given : %s",id));
        }
        Optional<PersonDocument> personOpt = personRepository.findById(id);
        return personOpt.isEmpty()? null: personMapper.documentToDTO(personOpt.get());
    }

    public PersonDTO updateOne(PersonDTO dto){
        if(dto.getId() == null){
            throw new IllegalArgumentException("No id provided for update operation");
        }
        boolean isPresent = personRepository.existsById(dto.getId());
        if(isPresent) {
            return saveOne(dto);
        }else{
            throw new BusinessException(String.format("No document found for id %s",dto.getId()));
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public PersonDTO saveOne(PersonDTO dto){
        if(dto == null){
            throw new IllegalArgumentException("Invalid data given!");
        }
        PersonDocument document = personMapper.dtoToDocument(dto);
        PersonDocument savedResult = personRepository.save(document);
        return personMapper.documentToDTO(savedResult);
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean deleteOne(String id) {
        if (id == null) {
            throw new IllegalArgumentException(String.format("Invalid id given : %s", id));
        }
        try {
            boolean isPresent = personRepository.existsById(id);
            if (isPresent) {
                personRepository.deleteById(id);
            }
            return isPresent;
        } catch (Exception e) {
            return false;
        }
    }

}
