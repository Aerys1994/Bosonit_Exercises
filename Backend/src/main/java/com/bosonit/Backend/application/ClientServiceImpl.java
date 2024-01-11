package com.bosonit.Backend.application;

import com.bosonit.Backend.controllers.dto.ClientInputDto;
import com.bosonit.Backend.controllers.dto.ClientOutputDto;
import com.bosonit.Backend.controllers.dto.ClientSimpleOutputDto;
import com.bosonit.Backend.domain.Client;
import com.bosonit.Backend.repository.ClientRepository;

import com.bosonit.Backend.repository.TripRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    TripRepository tripRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ClientSimpleOutputDto getClientById(int idClient)
    {
        Client client = clientRepository.findById(idClient).orElseThrow(
                () -> new EntityNotFoundException("Client not found with ID: " + idClient));

        ClientSimpleOutputDto clientOutputDto = client.clientToClientOutputDto();
        return clientOutputDto;
    }

    @Override
    public ClientSimpleOutputDto addClient(ClientInputDto clientInputDto) throws Exception
    {
        return clientRepository.save(new Client(clientInputDto))
                .clientToClientOutputDto();
    }

    @Override
    public ClientSimpleOutputDto updateClient(int idClient, ClientInputDto clientInputDto) throws Exception
    {
        Client client = clientRepository.findById(idClient).orElseThrow(() -> new EntityNotFoundException("Client not found with ID: " + idClient));

        client.setName(clientInputDto.getName());
        client.setSurname(clientInputDto.getSurname());
        client.setAge(clientInputDto.getAge());
        client.setEmail(clientInputDto.getEmail());
        client.setPhone(clientInputDto.getPhone());

        return client.clientToClientOutputDto();
    }

    @Override
    public List<ClientOutputDto> getAllClient(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return clientRepository.findAll(pageRequest).getContent()
                .stream()
                .map(client -> {
                    return client.clientToClientOutputDto();
                })
                .toList();
    }

    @Override
    public void deleteClient(int idClient){
        clientRepository.deleteById(idClient);
    }
}
