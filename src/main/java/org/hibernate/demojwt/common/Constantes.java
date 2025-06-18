package org.hibernate.demojwt.common;

public class Constantes {

    public static final String PATH = "/empleados";
    public static final String PATH_REQ_ID = "/{id}";
    public static final String REQ_ID = "id";

    public static final String RESPONSE_200 = "200";
    public static final String RESPONSE_400 = "400";
    public static final String RESPONSE_404 = "404";
    public static final String RESPONSE_500 = "500";

    public static final String DESC_SUCCESS = "Operación exitosa.";
    public static final String DESC_INVALID_REQUEST = "Solicitud inválida.";
    public static final String DESC_NOT_FOUND = "No encontrado.";
    public static final String DESC_INTERNAL_SERVER_ERROR = "Error interno del servidor.";

    public static final String HEADER_TRANSACTION_ID = "ID de la transaccion.";
    public static final String HEADER_SEARCH_ID = "ID para buscar el Objeto.";
    public static final String REQUEST_BODY = "Cuerpo de la solicitud.";

    public static final String CODE_SUCCESS = "0";
    public static final String CODE_VALIDATION = "-1";
    public static final String CODE_ERROR = "-2";

    public static final String MSG_SUCCESS = "Proceso exitoso.";
    public static final String MSG_VALIDATION = "Error de validación: ";
    public static final String MSG_ERROR = "Error inesperado: ";

    public static final String LOG_INIT = "Iniciando proceso - TransactionId: {}";
    public static final String LOG_HEADER = "Header: {}";
    public static final String LOG_REQUEST = "Request: {}";
    public static final String LOG_SSUCCESS_OBTAINED = "Proceso procesado exitosamente - TransactionId: {}";
    public static final String LOG_SUCCESS_CREATED = "Proceso creado exitosamente - TransactionId: {}, SaleID: {}";
    public static final String LOG_RESPONSE = "Response: {}";
    public static final String LOG_VALIDATION_ERROR = "Error de validación - TransactionId: {} - Detalle: {}";
    public static final String LOG_UNEXPECTED_ERROR = "Error inesperado - TransactionId: {} - Detalle: {}";
    public static final String LOG_TOTAL_TIME = "Tiempo total de procesamiento - TransactionId: {} - Tiempo: {} ms";

}
