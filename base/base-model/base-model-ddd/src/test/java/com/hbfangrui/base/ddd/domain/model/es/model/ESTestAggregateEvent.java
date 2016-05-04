package com.hbfangrui.base.ddd.domain.model.es.model;

import com.hbfangrui.base.ddd.domain.model.event.support.AbstractDomainEvent;

/**
 * Created by tao.li on 2015/10/26.
 */
public class ESTestAggregateEvent extends AbstractDomainEvent<ESTestAggregateId> {
    public ESTestAggregateEvent() {
        super(ESTestAggregateId.apply("1223"), 1);
    }
}
