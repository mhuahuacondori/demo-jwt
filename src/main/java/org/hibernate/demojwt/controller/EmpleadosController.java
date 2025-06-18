package org.hibernate.demojwt.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.demojwt.dto.EmpleadosDTO;
import org.hibernate.demojwt.dto.response.AuditResponse;
import org.hibernate.demojwt.dto.response.Response;
import org.hibernate.demojwt.dto.request.HeadersRequest;
import org.hibernate.demojwt.dto.response.BodyResponse;
import org.hibernate.demojwt.common.Constantes;
import org.hibernate.demojwt.exception.ValidationException;
import org.hibernate.demojwt.service.EmpleadosService;
import org.hibernate.demojwt.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import java.util.function.Supplier;

@RestController
@RequestMapping(Constantes.PATH)
@Slf4j
@RequiredArgsConstructor
public class EmpleadosController {

    @Autowired
    private EmpleadosService empleadosService;

    @GetMapping
    public ResponseEntity<BodyResponse<Page<EmpleadosDTO>>> getAll(
            Pageable pageable,
            @RequestHeader String transactionId) {
        return handleRequest(transactionId, () -> empleadosService.getAll(pageable));
    }

    @GetMapping(Constantes.PATH_REQ_ID)
    public ResponseEntity<BodyResponse<EmpleadosDTO>> getById(
            @RequestHeader String transactionId,
            @PathVariable Long id) {
        return handleRequest(transactionId, () -> empleadosService.getById(id));
    }

    @PostMapping
    public ResponseEntity<BodyResponse<EmpleadosDTO>> save(
            @RequestHeader String transactionId,
            @RequestBody EmpleadosDTO empleados) {
        return handleRequest(transactionId, () -> empleadosService.save(empleados));
    }

    @PutMapping(Constantes.PATH_REQ_ID)
    public ResponseEntity<BodyResponse<EmpleadosDTO>> update(
            @RequestHeader String transactionId,
            @RequestBody EmpleadosDTO empleados) {
        return handleRequest(transactionId, () -> empleadosService.update(empleados));
    }

    @DeleteMapping(Constantes.PATH_REQ_ID)
    public ResponseEntity<BodyResponse<String>> deleteById(
            @RequestHeader String transactionId,
            @PathVariable Long id) {
        return handleRequest(transactionId, () -> empleadosService.delete(id));
    }

    private <T> ResponseEntity<BodyResponse<T>> handleRequest(String transactionId, Supplier<T> supplier) {
        long start = System.currentTimeMillis();
        Response<T> response = new Response<>();
        try {
            log.info(Constantes.LOG_INIT, transactionId);
            HeadersRequest headerRequest = new HeadersRequest();
            headerRequest.setTransactionId(transactionId);
            log.info(Constantes.LOG_HEADER, Util.printPrettyJSONString(headerRequest));

            T result = supplier.get();
            response.setData(result);
            response.setTransactionId(transactionId);
            response.setCode(Constantes.CODE_SUCCESS);
            response.setMessage(Constantes.MSG_SUCCESS);
            response.setStatus(HttpStatus.OK);
            log.info(Constantes.LOG_SSUCCESS_OBTAINED, transactionId);
        } catch (ValidationException e) {
            response.setData(null);
            response.setTransactionId(transactionId);
            response.setCode(e.getErrorCode());
            response.setMessage(Constantes.MSG_VALIDATION + e.getMessage());
            response.setStatus(HttpStatus.BAD_REQUEST);
            log.warn(Constantes.LOG_VALIDATION_ERROR, transactionId, e.getMessage());
        } catch (Exception e) {
            response.setData(null);
            response.setTransactionId(transactionId);
            response.setCode(Constantes.CODE_ERROR);
            response.setMessage(Constantes.MSG_ERROR + e.getMessage());
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            log.error(Constantes.LOG_UNEXPECTED_ERROR, transactionId, e.toString(), e);
        } finally {
            long end = System.currentTimeMillis();
            log.info(Constantes.LOG_TOTAL_TIME, transactionId, end - start);
        }
        return buildResponse(response);
    }

    private <T> ResponseEntity<BodyResponse<T>> buildResponse(Response<T> response) {
        AuditResponse audit = new AuditResponse();
        audit.setCode(response.getCode());
        audit.setMessage(response.getMessage());
        BodyResponse<T> bodyResponse = new BodyResponse<>();
        bodyResponse.setAudit(audit);
        bodyResponse.setResponse(response.getData());
        log.info(Constantes.LOG_RESPONSE, Util.printPrettyJSONString(bodyResponse));
        return ResponseEntity.status(response.getStatus()).body(bodyResponse);
    }

}
