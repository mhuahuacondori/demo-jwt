package org.hibernate.demojwt.service;

import org.hibernate.demojwt.dto.EmpleadosDTO;
import org.hibernate.demojwt.exception.ValidationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface EmpleadosService {

    public Page<EmpleadosDTO> getAll(Pageable pageable) throws ValidationException;
    public EmpleadosDTO getById(Long id) throws ValidationException;
    public EmpleadosDTO save(EmpleadosDTO empleadosDTO) throws ValidationException;
    public EmpleadosDTO update(EmpleadosDTO empleadosDTO) throws ValidationException;
    public String delete(Long id ) throws ValidationException;

}
