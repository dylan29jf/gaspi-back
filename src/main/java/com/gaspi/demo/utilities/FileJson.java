package com.gaspi.demo.utilities;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.gaspi.demo.exceptions.RequestException;
import com.gaspi.demo.model.Provider;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileJson {
    public static void modifyFileJson(List<Provider> listProviders) {
        try {
            String pathJsonFile = "src/main/resources/bd/bd.json";

            Gson gson = new GsonBuilder().setPrettyPrinting().create(); // Le damos formato al json
            Writer file = new FileWriter(pathJsonFile); // Abrimos el archivo para poder escribir
            gson.toJson(listProviders, file); // Escribimos en el archivo
            file.close(); // Cerramos el archivo
        } catch (IOException e) {
            log.info("Error al abrir el archivo {}", e.getMessage());
            throw new RequestException(HttpStatus.valueOf(400), "Oopss!", EnumSeverity.ERROR,
                    "No se pudo almacenar el provedoor");
        }
    }

}
