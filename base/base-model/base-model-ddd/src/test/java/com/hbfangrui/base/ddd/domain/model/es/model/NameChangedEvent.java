package com.hbfangrui.base.ddd.domain.model.es.model;

import lombok.Data;

/**
 * Created by tao.li on 2015/10/26.
 */
@Data
public class NameChangedEvent extends ESTestAggregateEvent {
    private final String name;
    public NameChangedEvent(String name) {
        this.name = name;
    }
}
