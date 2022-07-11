package com.dh.clinica;

import com.dh.clinica.exceptions.BadRequestException;
import com.dh.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.model.Odontologo;
import com.dh.clinica.model.OdontologoDTO;
import com.dh.clinica.service.OdontologoService;
import org.junit.jupiter.api.Assertions.*;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class OdontologoServiceTests {
    @Autowired
    private OdontologoService odontologoService;

    public void cargarDataSet() throws BadRequestException {
        this.odontologoService.registrarOdontologo(new Odontologo("Matias", "Andrade", 3475657));
    }

    @Test
    public void agregarOdontologo() throws BadRequestException {
        this.cargarDataSet();
        Odontologo odontologo = odontologoService.registrarOdontologo(new Odontologo("Marcelo", "Perez", 349571660));
        Assert.assertTrue(odontologo.getId() != null);

    }

    @Test
    public void traerTodos() {
        List<Odontologo> odontologos = odontologoService.buscarTodos();
        Assert.assertTrue(!odontologos.isEmpty());
        //Assert.assertTrue(odontologos.size() == 1);
    }

    @Test
    public void buscarOdontologoTest() throws BadRequestException {
        Assert.assertNotNull(odontologoService.buscar(1));
    }

    @Test
    public void eliminarOdontologoTest() throws BadRequestException, ResourceNotFoundException {

        Odontologo odontologo = odontologoService.registrarOdontologo(new Odontologo("Marcelo", "Perez", 349571660));
        odontologoService.eliminarOdontologo(odontologo.getId());
        assertThrows(BadRequestException.class, () -> odontologoService.buscar(odontologo.getId()), "No se encontró el odontólogo");
    }
}
