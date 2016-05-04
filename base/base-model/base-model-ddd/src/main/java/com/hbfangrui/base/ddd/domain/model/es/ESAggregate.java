package com.hbfangrui.base.ddd.domain.model.es;

import com.hbfangrui.base.ddd.domain.model.Identifier;
import com.hbfangrui.base.ddd.domain.model.event.DomainEvent;
import com.hbfangrui.base.ddd.domain.model.event.DomainEventPublisher;

import java.lang.reflect.Method;
import java.util.function.Consumer;


/**
 * Created by taoli on 15/10/30.
 */
public interface ESAggregate<ID extends Identifier> extends com.hbfangrui.base.ddd.domain.model.Aggregate<ID>, DomainEventPublisher {

    default <EVENT extends DomainEvent> void publishAndApply(EVENT event){
        publish(event);
        applyEvent(event);
    }

    default  <EVENT extends DomainEvent> void publishAndApply(EVENT event, Consumer<EVENT> apply){
        publish(event);
        apply.accept(event);
    }


    default void recover(EventStream eventStream){
        if (eventStream.isNotEmpty()) {
            eventStream.events().forEach((event -> {
                applyEvent(event);
            }));
        }
    }

    default void applyEvent(DomainEvent event){
        Class cls = getClass();
        for (Method method : cls.getDeclaredMethods()) {
            if (method.getParameterCount() == 1 && method.getParameterTypes()[0] == event.getClass()) {
                method.setAccessible(true);
                try {
                    method.invoke(this, event);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return;
            }
        }
        throw new RuntimeException("can not find event apply for " + event.getClass());
    }
}
