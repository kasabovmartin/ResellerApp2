package com.resellerapp.service;

import com.resellerapp.model.entity.Condition;
import com.resellerapp.model.entity.ConditionName;
import com.resellerapp.repository.ConditionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ConditionsInitService {

    ConditionRepository conditionRepository;

    @Autowired
    public ConditionsInitService(ConditionRepository conditionRepository) {
        this.conditionRepository = conditionRepository;
    }

    public void initConditions() {
        if (conditionRepository.count() != 0) {
            return;
        }

        Arrays.stream(ConditionName.values())
                .forEach(value -> {
                    Condition condition = new Condition();
                    condition.setConditionName(value);
                    condition.setDescription("...");

                    conditionRepository.save(condition);
                });

    }

    public Condition findCondition(ConditionName conditionName) {
        return conditionRepository.findConditionByConditionName(conditionName).orElse(null);
    }
}
