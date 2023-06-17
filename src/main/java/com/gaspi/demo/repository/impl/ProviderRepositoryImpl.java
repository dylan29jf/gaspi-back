package com.gaspi.demo.repository.impl;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.lang.reflect.Type;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.gaspi.demo.exceptions.RequestException;
import com.gaspi.demo.model.Provider;
import com.gaspi.demo.repository.ProviderRepository;
import com.gaspi.demo.utilities.EnumSeverity;
import com.gaspi.demo.utilities.FileJson;
import com.gaspi.demo.utilities.ValidationUtilities;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class ProviderRepositoryImpl implements ProviderRepository {

    @Override
    public List<Provider> getAllProviders() {
        String pathJsonFile = "src/main/resources/bd/bd.json";
        List<Provider> existingProviders = new ArrayList<>();
        File jsonFile = new File(pathJsonFile); // Creamos la instancia del archivo
        if (jsonFile.exists() && jsonFile.length() > 0) { // Validamos si existe el archivo y si es mayor a 0
            try (Reader reader = new FileReader(pathJsonFile)) { // Leemos el archivo
                Type type = new TypeToken<List<Provider>>() { // Creamos un Tipo de dato que va hacer referencia a la
                                                              // lista de proveedores
                }.getType();
                existingProviders = new Gson().fromJson(reader, type); // Agregamos los elemetos que se leen del archivo
                                                                       // a la lista
            } catch (IOException e) {
                log.info("Error al abrir el archivo {}", e.getMessage());
            }
        }

        return existingProviders;
    }

    @Override
    public void saveProvider(Provider provider) {

        List<Provider> existingProviders = getAllProviders(); // Obtenemos todos los proveedores

        if (ValidationUtilities.existProvider(existingProviders, provider)) { // Validamos que el proveedor exista,
                                                                              // de ser asi entonces creamos una
                                                                              // nueva excepción
            throw new RequestException(HttpStatus.valueOf(400), "Oopss!", EnumSeverity.ERROR,
                    "El nombre del proveedor ya existe");
        }
        existingProviders.add(provider); // Agregamos el nuevo proveedor

        FileJson.modifyFileJson(existingProviders);
    }

    // currentPage =
    @Override
    public List<Provider> getProvidersByPage(Long currentPage) {

        int elementsPerPage = 5;
        

        int lastIndex = (int) (currentPage * elementsPerPage);
        int firstIndex = lastIndex - elementsPerPage; 

        List<Provider> listProviders = getAllProviders();

        if (lastIndex > listProviders.size()) {
            lastIndex = listProviders.size();
        }

        return listProviders.subList(firstIndex, lastIndex);
    }

    @Override
    public Provider getProvider(String idProvider) {
        List<Provider> listProviders = getAllProviders();

        Optional<Provider> provider = listProviders.stream()
                .filter(prov -> prov.getId().equals(idProvider))
                .findFirst();

        return provider.isPresent() ? provider.get() : null;
    }

    @Override
    public void updateProvider(Provider provider, String id) {
        List<Provider> listProviders = getAllProviders();

        List<Provider> updateList = listProviders.stream()
                .map(prov -> prov.getId().equals(id) ? provider : prov)
                .collect(Collectors.toList());
        FileJson.modifyFileJson(updateList);
    }

    @Override
    public void deleteProvider(String idProvider) {

        List<Provider> listProviders = getAllProviders();

        List<Provider> deleteProviders = listProviders.stream()
                .filter(prov -> !prov.getId().equals(idProvider)).collect(Collectors.toList());

        FileJson.modifyFileJson(deleteProviders);
    }

}
