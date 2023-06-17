package com.gaspi.demo.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gaspi.demo.model.JsonStringify;
import com.gaspi.demo.model.Provider;
import com.gaspi.demo.service.ProviderSevice;
import com.gaspi.demo.utilities.EnumSeverity;

import lombok.AllArgsConstructor;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@AllArgsConstructor
@RequestMapping("/api/proveedores")
public class ProviderController {

    private ProviderSevice providerSevice;

    @CrossOrigin(origins = "*")
    @GetMapping(path = "{id}")
    public ResponseEntity<Object> getProviderById(@PathVariable String id) {
        Provider provider = providerSevice.getProviderById(id);
        return ResponseEntity.ok().body(provider);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "pagina/{currentPage}")
    public ResponseEntity<Object> getProviderById(@PathVariable Long currentPage) {
        List<Provider> listProvider = providerSevice.getProvidersByPage(currentPage);
        return ResponseEntity.ok().body(listProvider);
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "guardar")
    public ResponseEntity<Object> save(@RequestBody @Valid Provider provider) {
        providerSevice.saveProvider(provider);
        return ResponseEntity.ok().body(
                JsonStringify.parseAlert("Registrado", EnumSeverity.SUCCESS, "Proveedor registrado correctamente."));
    }

    @CrossOrigin(origins = "*")
    @PutMapping(path = "modificar/{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @RequestBody Provider updateProvider) {
        providerSevice.updateProvider(updateProvider, id);
        return ResponseEntity.ok().body(
                JsonStringify.parseAlert("Actualizado", EnumSeverity.SUCCESS, "Proveedor actualizado correctamente."));

    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "eliminar/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        providerSevice.deleteProvider(id);
        return ResponseEntity.ok().body(
                JsonStringify.parseAlert("Eliminado", EnumSeverity.SUCCESS, "Proveedor eliminado correctamente."));

    }

}
