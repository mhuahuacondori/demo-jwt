package org.hibernate.demojwt.dto.response;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BodyResponse<T> {

    private AuditResponse audit;
    private T response;

}
