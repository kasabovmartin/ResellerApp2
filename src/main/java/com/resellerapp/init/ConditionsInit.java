package com.resellerapp.init;

import com.resellerapp.service.ConditionsInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConditionsInit implements CommandLineRunner {

    private final ConditionsInitService conditionsInitService;

    @Autowired
    public ConditionsInit(ConditionsInitService conditionsInitService) {
        this.conditionsInitService = conditionsInitService;
    }

    @Override
    public void run(String... args) {
        this.conditionsInitService.initConditions();
    }
}
