package com.gaspi.demo.service.impl;

import org.springframework.stereotype.Service;

import com.gaspi.demo.model.Profile;
import com.gaspi.demo.service.ProfileService;
import com.gaspi.demo.utilities.FilesUtilities;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    @Override
    public Profile getProfile() {

        Profile profile = new Profile();

        String version = FilesUtilities.getVersion();

        profile.setName("Candidato 1");
        profile.setVersion(version);
        profile.setImage("https://github.com/mdo.png");

        return profile;
    }

}
