package org.hibernate.demojwt.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HeadersRequest {

    private String transactionId;
    private String authorization;
    private String Accept;

}
