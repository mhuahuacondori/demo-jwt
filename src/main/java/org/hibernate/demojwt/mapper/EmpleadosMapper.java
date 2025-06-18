package org.hibernate.demojwt.mapper;

import org.hibernate.demojwt.dto.EmpleadosDTO;
import org.hibernate.demojwt.entity.Empleados;
import org.springframework.stereotype.Component;

@Component
public class EmpleadosMapper {

    public EmpleadosDTO toDTO(Empleados empleados) {
        EmpleadosDTO empleadosDTO = new EmpleadosDTO();
        empleadosDTO.setId(empleados.getId());
        empleadosDTO.setNombre(empleados.getNombre());
        empleadosDTO.setApellido(empleados.getApellido());
        empleadosDTO.setFechaNac(empleados.getFechaNac());
        empleadosDTO.setReportaA(empleados.getReportaA());
        empleadosDTO.setExtension(empleados.getExtension());
        return empleadosDTO;
    }

    public Empleados toEntity(EmpleadosDTO empleadosDTO) {
        Empleados empleados = new Empleados();
        empleados.setId(empleadosDTO.getId());
        empleados.setNombre(empleadosDTO.getNombre());
        empleados.setApellido(empleadosDTO.getApellido());
        empleados.setFechaNac(empleadosDTO.getFechaNac());
        empleados.setReportaA(empleadosDTO.getReportaA());
        empleados.setExtension(empleadosDTO.getExtension());
        return empleados;
    }

    public void updateEntityFromDTO(EmpleadosDTO dto, Empleados entity) {
        entity.setNombre(dto.getNombre());
        entity.setApellido(dto.getApellido());
        entity.setFechaNac(dto.getFechaNac());
        entity.setReportaA(dto.getReportaA());
        entity.setExtension(dto.getExtension());
    }
}
