package com.hbfangrui.base.ddd.domain.model.event;

/**
 * Created by tao.li on 2015/10/26.
 */
public interface DomainEventSubscriber {

    default void handle(DomainEvent event) {
        if (accept(event)) {
            doHandle(event);
        }
    }

    default boolean accept(DomainEvent event) {
        return true;
    }

    void doHandle(DomainEvent event);
}
