package com.gaspi.demo.exceptions;

import org.springframework.http.HttpStatus;

import com.gaspi.demo.utilities.EnumSeverity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestException extends RuntimeException {
    private EnumSeverity severity;
    private String title;
    private HttpStatus status;

    public RequestException(HttpStatus status, String title, EnumSeverity serverity, String message) {
        super(message);
        setStatus(status);
        setTitle(title);
        setSeverity(serverity);
    }
}
