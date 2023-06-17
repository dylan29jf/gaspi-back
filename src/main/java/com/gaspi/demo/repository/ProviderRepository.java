package com.gaspi.demo.repository;

import java.util.List;

import com.gaspi.demo.model.Provider;

public interface ProviderRepository {
    
    List<Provider> getAllProviders();

    List<Provider> getProvidersByPage(Long page);

    Provider getProvider(String idProvider);

    void saveProvider(Provider provider);

    void updateProvider(Provider provider, String id);

    void deleteProvider(String idProvider);
}
