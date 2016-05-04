package com.hbfangrui.base.ddd.domain.model.event;

import com.hbfangrui.base.ddd.domain.unitofwork.DefaultUnitOfWork;

/**
 * Created by tao.li on 2015/10/26.
 */
public interface DomainEventPublisher {
    default void publish(DomainEvent event){
        DefaultUnitOfWork.getContext().publish(event);
    }
}
