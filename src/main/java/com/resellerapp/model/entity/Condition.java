package com.resellerapp.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "conditions")
public class Condition extends BaseEntity {

    @NotNull
    @Enumerated(EnumType.STRING)
    private ConditionName conditionName;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "condition")
    private Set<Offer> offersWithCondition;

}
