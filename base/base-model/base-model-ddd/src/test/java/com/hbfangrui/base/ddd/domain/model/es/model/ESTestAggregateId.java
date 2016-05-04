package com.hbfangrui.base.ddd.domain.model.es.model;

import com.hbfangrui.base.ddd.domain.model.support.AbstractIdentifier;

/**
 * Created by tao.li on 2015/10/26.
 */
public class ESTestAggregateId extends AbstractIdentifier {
    private ESTestAggregateId() {
    }

    private ESTestAggregateId(String id) {
        super(id);
    }

    public static ESTestAggregateId apply(String id){
        return new ESTestAggregateId(id);
    }
}
