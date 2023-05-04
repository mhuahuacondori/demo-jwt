package org.hibernate.demojwt.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.demojwt.entity.Empleados;
import org.hibernate.demojwt.common.Constantes;
import org.hibernate.demojwt.service.EmpleadosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constantes.PATH)
@Slf4j
@RequiredArgsConstructor
public class EmpleadosController {
    @Autowired
    EmpleadosService empleadosService;

    @GetMapping
    public List<Empleados> getAll(){
        return this.empleadosService.getAll();
    }

    @GetMapping(Constantes.PATH_REQ_ID)
    public Empleados getById(@PathVariable(Constantes.REQ_ID) Long id) throws Exception {
        return this.empleadosService.getById(id);
    }

    @PostMapping
    public void save(@RequestBody Empleados empleados){
        this.empleadosService.save(empleados);
    }

    @PutMapping(Constantes.PATH_REQ_ID)
    public Empleados update(@PathVariable(Constantes.REQ_ID) Long id,@RequestBody Empleados empleados) throws Exception {
        return empleadosService.update(id,empleados);
    }

    @DeleteMapping(Constantes.PATH_REQ_ID)
    public void deleteById(@PathVariable(Constantes.REQ_ID) Long id) throws Exception {
        this.empleadosService.delete(id);
    }
}
