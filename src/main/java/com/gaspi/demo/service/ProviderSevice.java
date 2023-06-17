package com.gaspi.demo.service;

import com.gaspi.demo.model.Provider;
import com.gaspi.demo.model.ResponseListProvider;

public interface ProviderSevice {

    ResponseListProvider getProvidersByPage(Long page);

    Provider getProviderById(String idProvider);

    void saveProvider(Provider provider);

    void updateProvider(Provider updateProvider, String id);

    void deleteProvider(String id);
}
