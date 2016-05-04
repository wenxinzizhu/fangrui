package com.hbfangrui.base.ddd.domain.model.event.support;

import com.google.common.collect.Lists;
import com.hbfangrui.base.ddd.domain.model.event.DomainEvent;
import com.hbfangrui.base.ddd.domain.model.event.DomainEventBus;
import com.hbfangrui.base.ddd.domain.model.event.DomainEventSubscriber;

import java.util.List;

/**
 * Created by taoli on 15/10/31.
 */
public class DefaultDomainEventBus implements DomainEventBus {
    private List<DomainEventSubscriber> subscribers = Lists.newArrayList();

    @Override
    public void registerSubscriber(DomainEventSubscriber subscriber) {
        this.subscribers.add(subscriber);
    }

    @Override
    public void unregisterSubscriber(DomainEventSubscriber subscriber) {
        this.subscribers.remove(subscriber);
    }

    @Override
    public void publish(DomainEvent event) {
        this.subscribers.forEach(subscriber -> {
            subscriber.handle(event);
        });
    }
}
