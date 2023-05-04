package org.hibernate.demojwt.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Empleados implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    @Type(type="date")
    @Column(name = "fecha_nac")
    private Date fechaNac;
    @Column(name = "reporta_a")
    private Integer reportaA;
    private Integer extension;


}