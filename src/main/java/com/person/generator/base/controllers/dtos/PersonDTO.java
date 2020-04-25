package com.person.generator.base.controllers.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Data
public class PersonDTO extends BaseDTO{

    @NotNull(message = "This field is required!")
    @Size(min = 3, message = "First name must have at least 3 characters!")
    private String firstName;

    @NotNull(message = "This field is required!")
    @Size(min = 3, message = "First name must have at least 3 characters!")
    private String lastName;

    @NotNull(message = "This field is required!")
    @Min(value = 18, message = "The age must be equal of greater to 18 and equal or greater to 65!")
    private Integer age;

    @NotNull(message = "This field is required!")
    @Min(value = 1, message="The salary must a positive number!")
    private Double salary;

    public PersonDTO(){
        super(null);
    }

    @Builder
    public PersonDTO(String id,
                     String firstName,
                     String lastName,
                     Integer age,
                     Double salary) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.salary = salary;
    }
}
