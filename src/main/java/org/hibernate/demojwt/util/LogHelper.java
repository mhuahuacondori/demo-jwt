package org.hibernate.demojwt.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogHelper {

    public static void logStart(String transactionId) {
        log.info("Inicio de transacción [{}]", transactionId);
    }

    public static void logHeader(String headerJson) {
        log.info("Cabecera de la solicitud: {}", headerJson);
    }

    public static void logSuccess(String transactionId, Object result) {
        log.info("Transacción [{}] completada con éxito. Resultado: {}", transactionId, result);
    }

    public static void logValidationError(String transactionId, String message) {
        log.warn("Error de validación en transacción [{}]: {}", transactionId, message);
    }

    public static void logUnexpectedError(String transactionId, Exception e) {
        log.error("Error inesperado en transacción [{}]: {}", transactionId, e.toString(), e);
    }

    public static void logTotalTime(String transactionId, long durationMs) {
        log.info("Tiempo total de transacción [{}]: {} ms", transactionId, durationMs);
    }
}