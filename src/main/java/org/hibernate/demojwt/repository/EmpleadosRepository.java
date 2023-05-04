package org.hibernate.demojwt.repository;

import org.hibernate.demojwt.entity.Empleados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadosRepository extends JpaRepository<Empleados, Long> {
}
