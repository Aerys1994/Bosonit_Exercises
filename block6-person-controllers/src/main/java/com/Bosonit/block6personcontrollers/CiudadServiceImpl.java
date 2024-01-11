package com.Bosonit.block6personcontrollers;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CiudadServiceImpl implements CiudadService
{
    private final List<Ciudad> listaCiudades = new ArrayList<>();

    @Override
    public void agregarCiudad(Ciudad ciudad) {
        listaCiudades.add(ciudad);
    }

    @Override
    public List<Ciudad> obtenerCiudades() {
        return listaCiudades;
    }


}
