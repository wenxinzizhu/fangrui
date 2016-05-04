package com.hbfangrui.user.command.domain.event.user;

import com.hbfangrui.base.ddd.domain.model.event.DomainEvent;
import com.hbfangrui.base.ddd.domain.model.event.support.AbstractDomainEvent;
import com.hbfangrui.user.base.model.UserId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Created by taoli on 15/10/30.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public abstract class UserEvent extends AbstractDomainEvent implements DomainEvent {
    private final UserId id;

    public UserEvent(UserId id, int version) {
        super(id, version);
        this.id = id;
    }
}
