package com.Bosonit.Block13MongoDb.domain;

import com.Bosonit.Block13MongoDb.controllers.dto.PersonaInputDto;
import com.Bosonit.Block13MongoDb.controllers.dto.PersonaOutputDto;
import jakarta.persistence.GeneratedValue;
import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "persona")
//@Data
@NoArgsConstructor
@AllArgsConstructor
public class Persona {

    @Id
    private String idPersona;

    private String usuario;
    private String password;
    private String name;
    private String surname;
    private String companyEmail;
    private String personalEmail;
    private String city;
    private boolean active;
    private Date createdDate;
    private String imageUrl;
    private Date terminationDate;



    public Persona(PersonaInputDto personaInputDto) throws Exception {


        //this.idPersona = personaInputDto.getId();
        this.usuario = personaInputDto.getUsuario();
        this.password = personaInputDto.getPassword();
        this.name = personaInputDto.getName();
        this.surname = personaInputDto.getSurname();
        this.companyEmail = personaInputDto.getUsuario();
        this.personalEmail = personaInputDto.getPersonalEmail();
        this.city = personaInputDto.getCity();
        this.active = personaInputDto.isActive();
        this.createdDate = personaInputDto.getCreatedDate();
        this.imageUrl = personaInputDto.getImageUrl();
        this.terminationDate = personaInputDto.getTerminationDate();
    }

    public PersonaOutputDto personaToPersonaOutputDto() {
        return new PersonaOutputDto(
                this.idPersona,
                this.usuario,
                this.password,
                this.name,
                this.surname,
                this.companyEmail,
                this.personalEmail,
                this.city,
                this.active,
                this.createdDate,
                this.imageUrl,
                this.terminationDate

        );
    }


}
