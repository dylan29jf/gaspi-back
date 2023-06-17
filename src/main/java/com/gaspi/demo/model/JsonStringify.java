package com.gaspi.demo.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gaspi.demo.utilities.EnumSeverity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonStringify {
    public static String parseAlert(String title, EnumSeverity enumSeverity, String message) {
        ObjectMapper mapper = new ObjectMapper();
        ResponseAlert obj = new ResponseAlert(enumSeverity, title, message);

        try {
            return mapper.writeValueAsString(obj);

        } catch (JsonProcessingException e) {
            log.info("Ocurrio un erro en el parseAlert ", e.getMessage());
            return "";
        }
    }
}
