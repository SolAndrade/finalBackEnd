package com.dh.clinica;



import com.dh.clinica.exceptions.BadRequestException;
import com.dh.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.model.Domicilio;
import com.dh.clinica.model.Odontologo;
import com.dh.clinica.model.Paciente;
import com.dh.clinica.service.DomicilioService;
import com.dh.clinica.service.PacienteService;

import org.junit.Assert;


import org.junit.FixMethodOrder;

import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;

import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class PacienteServiceTest {
    @Autowired
    private PacienteService pacienteService;


    public void cargarDataSet() {
        Domicilio domicilio = new Domicilio("Hipolito Yrigoyen", "610", "Cordoba", "Cordoba");
        Paciente p = pacienteService.guardar(new Paciente("Matias", "Andrade", "65656565", new Date(), domicilio));
        Domicilio domicilio1 = new Domicilio("Chacabuco", "123", "Cordoba", "Cordoba");
        Paciente p1 = pacienteService.guardar(new Paciente("Maria", "Alfonzo", "84848484", new Date(), domicilio1));

    }

    @Test
    public void agregarYBuscarPacienteTest() throws BadRequestException {
        this.cargarDataSet();
        Domicilio domicilio = new Domicilio("Entre Rios", "123", "Cordoba", "Cordoba");
        Paciente p = pacienteService.guardar(new Paciente("Martin", "Gomez", "42424242", new Date(), domicilio));

        Assert.assertNotNull(pacienteService.buscar(p.getId()));
    }

    @Test
    public void traerTodos() {
        List<Paciente> pacientes = pacienteService.buscarTodos();
        Assert.assertTrue(!pacientes.isEmpty());
        //Assert.assertEquals(2, pacientes.size());
    }

    @Test
    public void buscarTurnoTest() throws BadRequestException {
        Assert.assertNotNull(pacienteService.buscar(2));
    }

    @Test
    public void eliminarPacienteTest() throws ResourceNotFoundException, BadRequestException {
        pacienteService.eliminar(1);
        assertThrows(BadRequestException.class, () -> pacienteService.buscar(1), "No se encontró el paciente.");
    }

}
