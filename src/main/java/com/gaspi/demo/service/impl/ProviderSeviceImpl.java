package com.gaspi.demo.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.gaspi.demo.exceptions.RequestException;
import com.gaspi.demo.model.Provider;
import com.gaspi.demo.model.ResponseListProvider;
import com.gaspi.demo.repository.ProviderRepository;
import com.gaspi.demo.service.ProviderSevice;
import com.gaspi.demo.utilities.EnumSeverity;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProviderSeviceImpl implements ProviderSevice {

    private static final String TITLE = "Faltan datos";

    private ProviderRepository providerRepository;

    @Override
    public void saveProvider(Provider provider) {
        if (provider.getName().equals("")) {
            throw new RequestException(HttpStatus.BAD_REQUEST, TITLE, EnumSeverity.INFO,
                    "Falta el nombre del proveedor");
        }
        if (provider.getAddress().equals("")) {
            throw new RequestException(HttpStatus.BAD_REQUEST, TITLE, EnumSeverity.INFO,
                    "Falta la dirección del proveedor");
        }
        if (provider.getCompanyName().equals("")) {
            throw new RequestException(HttpStatus.BAD_REQUEST, TITLE, EnumSeverity.INFO,
                    "Falta la Razón Social del proveedor");
        }

        String id = UUID.randomUUID().toString();

        provider.setId(id);

        providerRepository.saveProvider(provider);
    }

    @Override
    public void updateProvider(Provider updateProvider, String id) {
        if (updateProvider.getName().equals("")) {
            throw new RequestException(HttpStatus.BAD_REQUEST, TITLE, EnumSeverity.INFO,
                    "Falta el nombre del proveedor");
        }
        if (updateProvider.getAddress().equals("")) {
            throw new RequestException(HttpStatus.BAD_REQUEST, TITLE, EnumSeverity.INFO,
                    "Falta la dirección del proveedor");
        }
        if (updateProvider.getCompanyName().equals("")) {
            throw new RequestException(HttpStatus.BAD_REQUEST, TITLE, EnumSeverity.INFO,
                    "Falta la Razón Social del proveedor");
        }

        if (!updateProvider.getId().equals(id)) {
            throw new RequestException(HttpStatus.BAD_REQUEST, "Oops!", EnumSeverity.INFO,
                    "No podemos actualizar el proveedor.");
        }

        providerRepository.updateProvider(updateProvider, id);
    }

    @Override
    public void deleteProvider(String id) {
        if (id.equals("")) {
            throw new RequestException(HttpStatus.BAD_REQUEST, TITLE, EnumSeverity.INFO, "El id no puede ir vacio.");
        }

        providerRepository.deleteProvider(id);
    }

    @Override
    public Provider getProviderById(String idProvider) {
        if (idProvider.equals("")) {
            throw new RequestException(HttpStatus.BAD_REQUEST, TITLE, EnumSeverity.INFO, "El id no puede ir vacio.");
        }

        return providerRepository.getProvider(idProvider);
    }

    @Override
    public ResponseListProvider getProvidersByPage(Long page) {

        if (page <= 0) {
            throw new RequestException(HttpStatus.BAD_REQUEST, "Oops!", EnumSeverity.ERROR,
                    "Número de pagina invalida");
        }

        ResponseListProvider response = new ResponseListProvider();

        List<Provider> listProviders = providerRepository.getProvidersByPage(page);

        int allProviders = providerRepository.getAllProviders().size();

        response.setAllProviders(allProviders);
        response.setListProviders(listProviders);

        return response;

    }

}
