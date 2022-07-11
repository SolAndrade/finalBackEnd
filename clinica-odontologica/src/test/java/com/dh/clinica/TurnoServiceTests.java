package com.dh.clinica;

import com.dh.clinica.exceptions.BadRequestException;
import com.dh.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.model.Domicilio;
import com.dh.clinica.model.Odontologo;
import com.dh.clinica.model.Paciente;
import com.dh.clinica.model.Turno;
import com.dh.clinica.service.OdontologoService;
import com.dh.clinica.service.PacienteService;
import com.dh.clinica.service.TurnoService;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertThrows;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class TurnoServiceTests {

    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private TurnoService turnoService;

    public void cargarDataSet() throws BadRequestException {
        Domicilio domicilio = new Domicilio("Hipolito Yrigoyen", "610", "Cordoba", "Cordoba");
        Paciente p = pacienteService.guardar(new Paciente("Matias", "Andrade", "65656565", new Date(), domicilio));
        this.odontologoService.registrarOdontologo(new Odontologo("Matias", "Andrade", 3475657));

    }

    @Test
    public void altaTurnoTest() throws BadRequestException {

        this.cargarDataSet();
        turnoService.registrarTurno(new Turno(pacienteService.buscar(1).get(),odontologoService.buscar(1).get(),new Date()));
        Assert.assertNotNull(turnoService.buscar(1));
    }


    @Test
    public void buscarTurnoTest() throws BadRequestException {
        Assert.assertNotNull(turnoService.buscar(1));
    }


    @Test
    public void eliminarTurnoTest() throws ResourceNotFoundException, BadRequestException {
        turnoService.eliminar(1);
        assertThrows(BadRequestException.class, () -> turnoService.buscar(1), "No se encontró el turno.");
    }

}
