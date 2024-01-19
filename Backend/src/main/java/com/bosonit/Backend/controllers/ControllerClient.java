package com.bosonit.Backend.controllers;

import com.bosonit.Backend.application.ClientServiceImpl;
import com.bosonit.Backend.controllers.dto.ClientInputDto;
import com.bosonit.Backend.controllers.dto.ClientOutputDto;
import com.bosonit.Backend.controllers.dto.ClientSimpleOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/client")
public class ControllerClient {

    @Autowired
    ClientServiceImpl clientServiceImpl;

    @PostMapping
    public ResponseEntity<ClientSimpleOutputDto> addClient(@RequestBody
                                                       ClientInputDto clientInputDto) throws Exception {
        URI location = URI.create("/client");
        return ResponseEntity.created(location)
                .body(clientServiceImpl.addClient(clientInputDto));
    }

    @GetMapping("/allClient")
    public List<ClientOutputDto> getAllClient(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "5", required = false) int pageSize) {

        return clientServiceImpl.getAllClient(pageNumber, pageSize);
    }

    @GetMapping("/{idClient}")
    public ResponseEntity<ClientSimpleOutputDto> getClientById(@PathVariable int idClient) {
        try {
            return ResponseEntity.ok().body(clientServiceImpl.getClientById(
                    idClient));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public ResponseEntity<ClientSimpleOutputDto> updateClient(@RequestBody int idClient, @RequestBody ClientInputDto clientInputDto) {
        try {
            clientServiceImpl.getClientById(idClient);
            return  ResponseEntity.ok().body(clientServiceImpl.updateClient(idClient, clientInputDto));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteClient(@RequestBody int idClient) {
        deleteClient(idClient);
        return ResponseEntity.ok().build();
    }
}
