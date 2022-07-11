package com.dh.clinica.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class TurnoDTO {

    private Integer id;
    private Paciente paciente;
    private Odontologo odontologo;
    private Date date;
}
