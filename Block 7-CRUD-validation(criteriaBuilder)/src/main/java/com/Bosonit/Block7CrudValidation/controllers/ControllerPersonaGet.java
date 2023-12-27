package com.Bosonit.Block7CrudValidation.controllers;

import com.Bosonit.Block7CrudValidation.application.PersonaService;
import com.Bosonit.Block7CrudValidation.controllers.dto.PersonaOutputDto;
import com.Bosonit.Block7CrudValidation.repository.PersonaRepository;
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
import java.util.Objects;

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
            @PathVariable int idPersona) {

            return ResponseEntity.ok().body(personaService.getPersonaById(
                    idPersona));

    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<PersonaOutputDto>> getPersonasByUsuario(
            @PathVariable String usuario) {

        List<PersonaOutputDto> personas = personaService.getPersonaByUsuario(
                    usuario);
        return ResponseEntity.ok().body(personas);

    }

    @CrossOrigin(origins = "https://cdpn.io")
    @GetMapping({"/getall"})
    public Iterable<PersonaOutputDto> getAllPersona(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "5", required = false) int pageSize
            ) {

        return personaService.getAllPersona(pageNumber, pageSize);
    }

    @GetMapping("/customQuery")
    public Page<PersonaOutputDto> findPersonaByQuery(
            @RequestParam(required = false) Integer idPersona,
            @RequestParam(required = false) String usuario,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String surname,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy")Date createdDate,
            @RequestParam(required = false) String dateCondition,
            @RequestParam(required = false) String orderBy,
            @RequestParam int page,
            @RequestParam(defaultValue = "10") int size
            ) {
        HashMap<String, Object> data = new HashMap<>();

        if (idPersona != null) data.put("id_persona", idPersona);
        if (usuario != null) data.put("usuario", usuario);
        if (name != null) data.put("name", name);
        if (surname != null) data.put("surname", surname);
        if (createdDate == null)
            dateCondition = GREATER_THAN;
        if (!dateCondition.equals(GREATER_THAN) && !dateCondition.equals(LESS_THAN) && !dateCondition.equals((EQUAL)))
            dateCondition = GREATER_THAN;
        if (createdDate != null) {
            data.put("created_date", createdDate);
            data.put("dateCondition", dateCondition);
        }

        Sort sort = StringUtils.hasText(orderBy) ? Sort.by(orderBy) : Sort.unsorted();
        Pageable pageable = PageRequest.of(page - 1, size, sort);

        return personaRepository.findPersonaByQuery(data, pageable);
    }
}
