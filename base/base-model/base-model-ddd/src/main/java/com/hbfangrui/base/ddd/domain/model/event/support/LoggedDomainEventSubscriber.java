package com.hbfangrui.base.ddd.domain.model.event.support;

import com.hbfangrui.base.ddd.domain.model.event.DomainEvent;
import com.hbfangrui.base.ddd.domain.model.event.DomainEventSubscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by taoli on 15/10/31.
 */
public class LoggedDomainEventSubscriber implements DomainEventSubscriber {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggedDomainEventSubscriber.class);

    @Override
    public void doHandle(DomainEvent event) {
        LOGGER.info("handle {}.", event);
    }
}
