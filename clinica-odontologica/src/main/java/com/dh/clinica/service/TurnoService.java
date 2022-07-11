package com.dh.clinica.service;

import com.dh.clinica.exceptions.BadRequestException;
import com.dh.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.model.Odontologo;
import com.dh.clinica.model.Turno;
import com.dh.clinica.repository.impl.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {

    private final TurnoRepository turnoRepository;

    @Autowired
    public TurnoService(TurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    public Turno registrarTurno(Turno turno) {
        return turnoRepository.save(turno);
    }

    public List<Turno> listar() {
        return turnoRepository.findAll();
    }

    public void eliminar(Integer id) throws ResourceNotFoundException, BadRequestException {
        if (buscar(id).isEmpty())
            throw new ResourceNotFoundException("No existe el turno con ID: " + id);
        turnoRepository.deleteById(id);
    }


    public Turno actualizar(Turno turno) throws ResourceNotFoundException, BadRequestException {
        if(buscar(turno.getId()).isEmpty())
            throw new ResourceNotFoundException("No existe el turno con ID: " + turno.getId());
        return turnoRepository.save(turno);
    }


    public Optional<Turno> buscar(Integer id) throws BadRequestException {
        if(!turnoRepository.existsById(id))
            throw new BadRequestException("No existe el turno con ID " + id);
        return turnoRepository.findById(id);
    }

}
