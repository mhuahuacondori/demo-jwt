package org.hibernate.demojwt.service;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.demojwt.common.Constantes;
import org.hibernate.demojwt.dto.EmpleadosDTO;
import org.hibernate.demojwt.entity.Empleados;
import org.hibernate.demojwt.exception.ValidationException;
import org.hibernate.demojwt.mapper.EmpleadosMapper;
import org.hibernate.demojwt.repository.EmpleadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Slf4j
@Service
public class EmpleadosServiceImpl implements EmpleadosService {

    @Autowired
    private EmpleadosRepository empleadosRepository;

    @Autowired
    private EmpleadosMapper empleadosMapper;

    @Override
    public Page<EmpleadosDTO> getAll(Pageable pageable) {
        log.info("Obteniendo todos los empleados con paginación: {}", pageable);
        return empleadosRepository.findAll(pageable)
                .map(empleadosMapper::toDTO);
    }

    @Override
    public EmpleadosDTO getById(Long id) {
        validarId(id);
        log.info("Buscando empleado con ID: {}", id);
        Empleados empleado = findEmpleadoById(id);
        return empleadosMapper.toDTO(empleado);
    }

    @Override
    public EmpleadosDTO save(EmpleadosDTO empleadosDTO) {
        validarDTO(empleadosDTO, false);
        log.info("Guardando nuevo empleado: {}", empleadosDTO);
        Empleados empleado = empleadosMapper.toEntity(empleadosDTO);
        Empleados guardado = empleadosRepository.save(empleado);
        log.info("Empleado guardado con ID: {}", guardado.getId());
        return empleadosMapper.toDTO(guardado);
    }

    @Override
    public EmpleadosDTO update(EmpleadosDTO empleadosDTO) {
        validarDTO(empleadosDTO, true);
        Long id = empleadosDTO.getId();
        log.info("Actualizando empleado con ID: {}", id);
        Empleados existente = findEmpleadoById(id);
        empleadosMapper.updateEntityFromDTO(empleadosDTO, existente);
        Empleados actualizado = empleadosRepository.save(existente);
        log.info("Empleado actualizado con ID: {}", actualizado.getId());
        return empleadosMapper.toDTO(actualizado);
    }

    @Override
    public String delete(Long id) {
        validarId(id);
        log.info("Eliminando empleado con ID: {}", id);
        Empleados empleado = findEmpleadoById(id);
        empleadosRepository.deleteById(empleado.getId());
        log.info("Empleado eliminado con ID: {}", empleado.getId());
        return "Empleado eliminado con ID [" + empleado.getId() + "]";

    }

    private Empleados findEmpleadoById(Long id) {
        return empleadosRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Empleado no encontrado con ID: {}", id);
                    return new ValidationException("Registro no encontrado con id [" + id + "]", Constantes.CODE_VALIDATION);
                });
    }

    private void validarId(Long id) {
        if (id == null || id <= 0) {
            log.warn("ID inválido: {}", id);
            throw new ValidationException("ID inválido: " + id, Constantes.CODE_VALIDATION);
        }
    }

    private void validarDTO(EmpleadosDTO dto, boolean requiereId) {
        if (dto == null) {
            log.warn("DTO recibido es nulo");
            throw new ValidationException("El objeto EmpleadosDTO no puede ser nulo", Constantes.CODE_VALIDATION);
        }
        if (requiereId && (dto.getId() == null || dto.getId() <= 0)) {
            log.warn("ID requerido pero inválido en DTO: {}", dto);
            throw new ValidationException("ID inválido para actualización", Constantes.CODE_VALIDATION);
        }
    }
}
