package com.hbfangrui.user.command.domain.event.subscriber;

import com.hbfangrui.base.ddd.domain.model.event.DomainEvent;
import com.hbfangrui.base.ddd.domain.model.event.DomainEventSubscriber;
import com.hbfangrui.user.command.domain.event.phone.UserPhonesEvent;

/**
 * Created by taoli on 15/11/1.
 */
public interface UserPhonesEventSubscriber extends DomainEventSubscriber {
    default boolean accept(DomainEvent event){
        return event instanceof UserPhonesEvent;
    }
}
