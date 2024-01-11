package com.bosonit.Backend.application;

import com.bosonit.Backend.controllers.dto.ClientInputDto;
import com.bosonit.Backend.controllers.dto.ClientOutputDto;
import com.bosonit.Backend.controllers.dto.ClientSimpleOutputDto;

import java.util.List;

public interface ClientService {
    ClientSimpleOutputDto getClientById(int id);
    List<ClientOutputDto> getAllClient(int pageNumber, int pageSize);
    ClientSimpleOutputDto addClient(ClientInputDto clientInputDto) throws Exception;

    ClientSimpleOutputDto updateClient(int idClient, ClientInputDto clientInputDto) throws Exception;
    void deleteClient(int idClient);

}



