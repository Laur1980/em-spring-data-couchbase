package com.person.generator.base.controllers.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Data
public abstract class BaseDTO {

    protected String id;

}
