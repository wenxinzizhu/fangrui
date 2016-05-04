package com.hbfangrui.base.ddd.domain.unitofwork;


import com.hbfangrui.base.ddd.domain.model.event.DomainEvent;
import com.hbfangrui.base.ddd.domain.model.event.DomainEventBus;
import com.hbfangrui.base.ddd.domain.model.event.DomainEventPublisher;
import com.hbfangrui.base.ddd.domain.model.event.DomainEventSubscriber;
import com.hbfangrui.base.ddd.domain.model.event.support.DefaultDomainEventBus;

import java.util.Collection;


/**
 * Created by taoli on 15/10/31.
 */
public class DefaultUnitOfWork implements DomainEventPublisher, DomainEventBus {
    private static final ThreadLocal<DefaultUnitOfWork> threadLocal =
            ThreadLocal.withInitial(()->new DefaultUnitOfWork());

    private final DefaultDomainEventBus eventSubscribers;
    private DefaultUnitOfWork(){
        this.eventSubscribers = new DefaultDomainEventBus();
    }

    @Override
    public void registerSubscriber(DomainEventSubscriber subscriber){
        this.eventSubscribers.registerSubscriber(subscriber);
    }

    public void registerSubscribers(Collection<? extends DomainEventSubscriber> subscribers){
        subscribers.forEach(this::registerSubscriber);
    }

    @Override
    public void unregisterSubscriber(DomainEventSubscriber subscriber){
        this.eventSubscribers.unregisterSubscriber(subscriber);
    }

    @Override
    public void publish(DomainEvent event){
        this.eventSubscribers.publish(event);
    }

    public static DefaultUnitOfWork getContext(){
        return threadLocal.get();
    }

    public static void cleanContext(){
        threadLocal.remove();
    }
}
