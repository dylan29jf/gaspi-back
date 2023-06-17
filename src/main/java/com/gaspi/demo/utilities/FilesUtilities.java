package com.gaspi.demo.utilities;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.springframework.http.HttpStatus;

import com.gaspi.demo.exceptions.RequestException;
import com.gaspi.demo.model.Provider;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FilesUtilities {

    private FilesUtilities() {
        throw new IllegalStateException("Utility class");
    }

    
    public static String getVersion() {
        try {
            MavenXpp3Reader reader = new MavenXpp3Reader();
            Model model = reader.read(new FileReader("pom.xml"));
            return model.getVersion();
        } catch (IOException e) {
            log.error("Ocurrio un error al leer el archivo {}", e);
        } catch (XmlPullParserException e) {
            log.error("Ocurrio un error al leer el archivo {}", e);
        }

        return "0.0.0";
    }

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
