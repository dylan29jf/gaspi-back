package com.gaspi.demo.service;

import java.util.List;

import com.gaspi.demo.model.Provider;

public interface ProviderSevice {

    List<Provider> getProvidersByPage(Long page);

    Provider getProviderById(String idProvider);

    void saveProvider(Provider provider);

    void updateProvider(Provider updateProvider, String id);

    void deleteProvider(String id);
}
