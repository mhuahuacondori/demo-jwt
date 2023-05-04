package org.hibernate.demojwt.service;

import lombok.RequiredArgsConstructor;
import org.hibernate.demojwt.entity.Empleados;
import org.hibernate.demojwt.repository.EmpleadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmpleadosServiceImpl implements EmpleadosService{
    @Autowired
    EmpleadosRepository empleadosRepository;


    @Override
    public List<Empleados> getAll() {
        return this.empleadosRepository.findAll();
    }

    @Override
    public Empleados getById(Long id) throws Exception {
        Optional<Empleados> optionalEmpleados = this.empleadosRepository.findById(id);
        if(optionalEmpleados.isPresent()) {
            return optionalEmpleados.get();
        }else {
            throw new Exception("Registro no encontrado con id "+id);
        }
    }

    @Override
    public void save(Empleados empleados) {
        this.empleadosRepository.save(empleados);
    }

    @Override
    public Empleados update(Long id,Empleados empleados) throws Exception {
        Optional<Empleados> optionalEmpleados = this.empleadosRepository.findById(id);

        if(optionalEmpleados.isPresent()) {
            Empleados empleadosUpdate = optionalEmpleados.get();
            //empleadosUpdate.setId(id);
            empleadosUpdate.setNombre(empleados.getNombre());
            empleadosUpdate.setApellido(empleados.getApellido());
            empleadosUpdate.setFechaNac(empleados.getFechaNac());
            empleadosUpdate.setReportaA(empleados.getReportaA());
            empleadosUpdate.setExtension(empleados.getExtension());
            this.empleadosRepository.save(empleadosUpdate);
            return empleadosUpdate;
        }else {
            throw new Exception("Registro no encontrado con id "+id);
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        Optional<Empleados> empleados = this.empleadosRepository.findById(id);

        if(empleados.isPresent()) {
            this.empleadosRepository.delete(empleados.get());
        }else {
            throw new Exception("Registro no encontrado con id "+id);
        }
    }
}
