package com.gaspi.demo.exceptions;

import com.gaspi.demo.utilities.EnumSeverity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorRest {
    private EnumSeverity severity;
    private String title;
    private String message;
}
