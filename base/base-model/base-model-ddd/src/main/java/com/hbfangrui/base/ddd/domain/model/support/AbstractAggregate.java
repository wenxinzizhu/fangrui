package com.hbfangrui.base.ddd.domain.model.support;

import com.hbfangrui.base.ddd.domain.model.Aggregate;
import com.hbfangrui.base.ddd.domain.model.Entity;
import com.hbfangrui.base.ddd.domain.model.Identifier;
import com.hbfangrui.base.ddd.domain.model.event.DomainEvent;

import javax.persistence.MappedSuperclass;

/**
 * Created by tao.li on 2015/10/26.
 */
@MappedSuperclass
public abstract class AbstractAggregate<ID extends Identifier> extends Model implements Entity, Aggregate<ID> {

    protected  <EVENT extends DomainEvent>void publish(EVENT event){
        doPublish(event);
    }

    protected abstract <EVENT extends DomainEvent>void doPublish(EVENT event);
}
