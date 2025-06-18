package org.hibernate.demojwt.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
public class Response<T> {

    private T data;
    private String code;
    private String message;
    private HttpStatus status;
    private String transactionId;

}
