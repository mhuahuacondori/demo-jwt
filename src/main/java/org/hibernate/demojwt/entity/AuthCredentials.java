package org.hibernate.demojwt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import java.io.Serializable;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthCredentials implements Serializable {

    private static final long serialVersionUID = 1L;

    private String email;
    private String password;
}
