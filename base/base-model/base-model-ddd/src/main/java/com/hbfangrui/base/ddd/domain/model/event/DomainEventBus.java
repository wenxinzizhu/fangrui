package com.hbfangrui.base.ddd.domain.model.event;

/**
 * Created by taoli on 15/10/31.
 */
public interface DomainEventBus extends DomainEventPublisher {
    void registerSubscriber(DomainEventSubscriber subscriber);

    void unregisterSubscriber(DomainEventSubscriber subscriber);
}
