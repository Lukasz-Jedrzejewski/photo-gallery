package com.jedrzejewski.photogallery.fixture;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
@AllArgsConstructor
public class CommandLineRunner implements org.springframework.boot.CommandLineRunner {

    private final Init init;

    @Override
    public void run(String... args) throws Exception {
        init.initRoles();
        init.initAdmin();
    }
}
