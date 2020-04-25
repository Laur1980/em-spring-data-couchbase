package com.person.generator.base.services.mappers;

import com.person.generator.base.controllers.dtos.BaseDTO;

public interface Mapper<D, DT extends BaseDTO> {

    DT documentToDTO(D document);

    D dtoToDocument(DT dto);
}
