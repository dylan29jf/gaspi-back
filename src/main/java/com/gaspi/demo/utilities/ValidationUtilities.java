package com.gaspi.demo.utilities;

import java.util.List;

import com.gaspi.demo.model.Provider;

public class ValidationUtilities {
    
    private ValidationUtilities() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean existProvider(List<Provider> listProvider, Provider provider) {

        return listProvider.stream()
                .anyMatch(element -> element.getName().equals(provider.getName()));
    }
}
