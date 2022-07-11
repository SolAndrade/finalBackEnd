package com.dh.clinica.service;

import com.dh.clinica.exceptions.BadRequestException;
import com.dh.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.model.Paciente;
import com.dh.clinica.repository.impl.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }


    public Paciente guardar(Paciente p) {
        return pacienteRepository.save(p);
    }


    public Optional<Paciente> buscar(Integer id) throws BadRequestException {
        if(!pacienteRepository.existsById(id))
            throw new BadRequestException("No existe el paciente con ID " + id);
        return pacienteRepository.findById(id);
    }


    public List<Paciente> buscarTodos() {
        return pacienteRepository.findAll();
    }


    public void eliminar(Integer id) throws ResourceNotFoundException, BadRequestException {
        if (buscar(id).isEmpty())
            throw new ResourceNotFoundException("No existe el paciente con ID " + id);
        pacienteRepository.deleteById(id);
    }


    public Paciente actualizar(Paciente paciente) throws ResourceNotFoundException, BadRequestException {
        if(buscar(paciente.getId()).isEmpty())
            throw new ResourceNotFoundException("No existe el paciente con ID " + paciente.getId());
        return pacienteRepository.save(paciente);
    }

}
