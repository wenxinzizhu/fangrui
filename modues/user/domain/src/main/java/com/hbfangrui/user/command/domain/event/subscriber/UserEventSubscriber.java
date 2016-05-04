package com.hbfangrui.user.command.domain.event.subscriber;

import com.hbfangrui.base.ddd.domain.model.event.DomainEvent;
import com.hbfangrui.base.ddd.domain.model.event.DomainEventSubscriber;
import com.hbfangrui.user.command.domain.event.user.UserEvent;

/**
 * Created by taoli on 15/10/31.
 */
public interface UserEventSubscriber extends DomainEventSubscriber {
    default boolean accept(DomainEvent event){
        return event instanceof UserEvent;
    }
}
