package com.gaspi.demo.model;

import java.util.List;

import lombok.Data;

@Data
public class ResponseListProvider {
    private int allProviders; 
    private List<Provider> listProviders;
}
