package com.dh.clinica.service;

import com.dh.clinica.exceptions.BadRequestException;
import com.dh.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.model.Odontologo;
import com.dh.clinica.model.OdontologoDTO;
import com.dh.clinica.repository.impl.OdontologoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class OdontologoService {

    private final OdontologoRepository odontologoRepository;

    @Autowired
    public OdontologoService(OdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    @Autowired
    ObjectMapper mapper;


    private void saveOdontologo(OdontologoDTO odontologoDTO) {
        Odontologo newOdontologo = mapper.convertValue(odontologoDTO, Odontologo.class);
        odontologoRepository.save(newOdontologo);
    }

    public void registrarOdontologo(OdontologoDTO odontologoDTO) { // llama a la funcion de saveOdontologo
        saveOdontologo(odontologoDTO);
    }

   public Odontologo registrarOdontologo(Odontologo odontologo) { // llama a la funcion de saveOdontologo
        return odontologoRepository.save(odontologo);
    }


    public void eliminarOdontologo(Integer id) throws BadRequestException, ResourceNotFoundException{
        if (buscar(id).isEmpty())
            throw new ResourceNotFoundException("No existe el odontólogo con ID " + id);
        odontologoRepository.deleteById(id);
    }


    public Odontologo actualizar(Odontologo odontologo) throws ResourceNotFoundException, BadRequestException {
        if(buscar(odontologo.getId()).isEmpty())
            throw new ResourceNotFoundException("No existe el odontólogo con ID " + odontologo.getId());
        return odontologoRepository.save(odontologo);
    }


    public Optional<Odontologo> buscar(Integer id) throws BadRequestException {
        if(!odontologoRepository.existsById(id))
            throw new BadRequestException("No existe el odontólogo con ID " + id);
        return odontologoRepository.findById(id);
    }


    public List <Odontologo> buscarOdontologoPorApellido (String apellido){
        return odontologoRepository.odontologoApellido(apellido);
   }


    public List<Odontologo> buscarTodos() {
        return odontologoRepository.findAll();
    }

}
