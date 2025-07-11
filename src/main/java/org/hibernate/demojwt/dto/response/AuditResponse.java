package org.hibernate.demojwt.dto.response;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuditResponse {

    private String code;
    private String message;

}
