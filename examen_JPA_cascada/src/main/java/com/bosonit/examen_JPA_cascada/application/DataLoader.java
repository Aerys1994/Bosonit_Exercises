package com.bosonit.examen_JPA_cascada.application;

import com.bosonit.examen_JPA_cascada.domain.Cliente;
import com.bosonit.examen_JPA_cascada.repository.ClienteRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataLoader implements InitializingBean {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private FacturaService facturaService;

    @Override
    public void afterPropertiesSet() {
        Cliente nuevoCliente = new Cliente(0, "Andreu");

        Cliente clienteGuardado = clienteRepository.save(nuevoCliente);

        facturaService.insertarFacturaDePrueba(clienteGuardado);
    }
}
