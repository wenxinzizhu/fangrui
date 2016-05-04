package com.hbfangrui.user.command.application;

import com.hbfangrui.base.ddd.domain.model.event.support.LoggedDomainEventSubscriber;
import com.hbfangrui.user.command.domain.event.subscriber.UserEventSubscriber;

class UserLoggedEventSubscriber extends LoggedDomainEventSubscriber implements UserEventSubscriber {

}