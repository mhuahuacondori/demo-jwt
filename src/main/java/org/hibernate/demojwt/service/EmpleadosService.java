package org.hibernate.demojwt.service;

import org.hibernate.demojwt.entity.Empleados;
import java.util.List;

public interface EmpleadosService {
    List<Empleados> getAll();

    Empleados getById(Long id) throws Exception;

    void save(Empleados empleados);
    Empleados update(Long id,Empleados empleados) throws Exception;

    void delete(Long id) throws Exception;
}
