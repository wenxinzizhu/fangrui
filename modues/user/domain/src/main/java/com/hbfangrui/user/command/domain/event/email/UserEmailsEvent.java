package com.hbfangrui.user.command.domain.event.email;

import com.hbfangrui.base.ddd.domain.model.event.DomainEvent;
import com.hbfangrui.base.ddd.domain.model.event.support.AbstractDomainEvent;
import com.hbfangrui.base.ddd.domain.model.support.Email;
import com.hbfangrui.user.base.model.UserId;
import lombok.Data;

import static com.hbfangrui.user.base.model.UserEmailsChecker.checkEmail;
import static com.hbfangrui.user.base.model.UserEmailsChecker.checkOwner;

/**
 * Created by taoli on 15/11/1.
 */
@Data
public abstract class UserEmailsEvent extends AbstractDomainEvent<UserId>
    implements DomainEvent<UserId> {
    private final UserId id;
    private final Email email;
    public UserEmailsEvent(UserId id, int version, Email email) {
        super(id, version);
        checkOwner(id);
        checkEmail(email);
        this.id = id;
        this.email = email;
    }
}
