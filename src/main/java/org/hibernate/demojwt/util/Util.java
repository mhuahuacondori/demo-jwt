package org.hibernate.demojwt.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.TimeZone;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Util {

    private static final String FORMAT_DATE = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    public static String validateOrDefault(String value, String fieldName, String defaultValue) {
        return Optional.ofNullable(value)
                .filter(v -> !v.isEmpty())
                .orElseGet(() -> {
                    log.warn("{} no está definido en archivo yml, usando valor predeterminado: {}", fieldName, defaultValue);
                    return defaultValue;
                });
    }

    public static DateFormat getLocalFormat() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE);
        dateFormat.setTimeZone(TimeZone.getDefault());
        return dateFormat;
    }

    public static String printPrettyJSONString(Object o) {
        return convertToJson(o, true);
    }

    public static String printJSONString(Object o) {
        return convertToJson(o, false);
    }

    private static String convertToJson(Object o, boolean prettyPrint) {
        try {
            ObjectMapper mapper = new ObjectMapper()
                    .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                    .setDateFormat(getLocalFormat());

            return prettyPrint ? mapper.writerWithDefaultPrettyPrinter().writeValueAsString(o) :
                    mapper.writeValueAsString(o);
        } catch (Exception e) {
            log.error("Error al convertir a JSON: {}", e.getMessage(), e);
            return "{}";
        }
    }
}