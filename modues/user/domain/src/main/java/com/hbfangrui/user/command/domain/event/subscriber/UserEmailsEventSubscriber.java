package com.hbfangrui.user.command.domain.event.subscriber;

import com.hbfangrui.base.ddd.domain.model.event.DomainEvent;
import com.hbfangrui.base.ddd.domain.model.event.DomainEventSubscriber;
import com.hbfangrui.user.command.domain.event.email.UserEmailsEvent;

/**
 * Created by taoli on 15/11/1.
 */
public interface UserEmailsEventSubscriber extends DomainEventSubscriber {
    default boolean accept(DomainEvent event){
        return event instanceof UserEmailsEvent;
    }
}
