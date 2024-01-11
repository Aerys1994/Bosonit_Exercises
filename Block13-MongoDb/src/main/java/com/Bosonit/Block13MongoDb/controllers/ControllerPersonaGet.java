package com.Bosonit.Block13MongoDb.controllers;

import com.Bosonit.Block13MongoDb.application.PersonaService;
import com.Bosonit.Block13MongoDb.controllers.dto.PersonaOutputDto;
import com.Bosonit.Block13MongoDb.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/persona")
public class ControllerPersonaGet {

    @Autowired
    PersonaService personaService;
    @Autowired
    PersonaRepository personaRepository;

    public static final String GREATER_THAN="greater";
    public static final String LESS_THAN="less";
    public static final String EQUAL="equal";

    @GetMapping("/{idPersona}")
    public ResponseEntity<PersonaOutputDto> getPersonaById(
            @PathVariable String idPersona) {

            return ResponseEntity.ok().body(personaService.getPersonaById(idPersona));

    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<PersonaOutputDto>> getPersonasPorUsuario(
            @PathVariable String usuario) {

        List<PersonaOutputDto> personas = personaService.getPersonaByUsuario(
                    usuario);
        return ResponseEntity.ok().body(personas);

    }

    @GetMapping({"/getall"})
    public Iterable<PersonaOutputDto> getAllPersona(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "5", required = false) int pageSize
            ) {

        return personaService.getAllPersona(pageNumber, pageSize);
    }

    @GetMapping("/customQuery")
    public ResponseEntity<List<PersonaOutputDto>> getPersonaByQuery(
            @RequestParam(required = false) String usuario,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String surname,
            @RequestParam(required = false) String companyEmail,
            @RequestParam(required = false) String personalEmail,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) Boolean active,
            @RequestParam(required = false) Date createdDate,
            @RequestParam(required = false) String imageUrl,
            @RequestParam(required = false) Date terminationDate,
            @RequestParam(required = false, defaultValue = "idPersona") String orderByField,
            @RequestParam(required = false, defaultValue = "true") boolean asc,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {

        List<PersonaOutputDto> personas = personaService.getPersonaByQuery(
                usuario, name, surname, companyEmail, personalEmail, city,
                active, createdDate, imageUrl, terminationDate,
                orderByField, asc, pageNumber, pageSize);

        return ResponseEntity.ok().body(personas);
    }
}
