package com.gaspi.demo.model;

import com.gaspi.demo.utilities.EnumSeverity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseAlert {
    private EnumSeverity severity;
    private String title;
    private String message;
}
