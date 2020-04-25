package com.person.generator.base.services.mappers;

import com.person.generator.base.controllers.dtos.PersonDTO;
import com.person.generator.base.documents.PersonDocument;
import org.springframework.stereotype.Component;

@Component
public class PersonMapperImpl implements Mapper<PersonDocument, PersonDTO>{

    @Override
    public PersonDTO documentToDTO(PersonDocument document) {
        if(document == null){
            return null;
        }
        return PersonDTO.builder()
                .id(document.getId())
                .firstName(document.getFirstName())
                .lastName(document.getLastName())
                .age(document.getAge())
                .salary(document.getSalary())
                .build();
    }

    @Override
    public PersonDocument dtoToDocument(PersonDTO dto) {
        if(dto == null){
            return null;
        }
        return PersonDocument.builder()
                .id(dto.getId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .age(dto.getAge())
                .salary(dto.getSalary())
                .build();
    }
}
