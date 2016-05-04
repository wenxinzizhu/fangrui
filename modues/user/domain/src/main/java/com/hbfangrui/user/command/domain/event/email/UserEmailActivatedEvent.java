package com.hbfangrui.user.command.domain.event.email;

import com.hbfangrui.base.ddd.domain.model.support.Email;
import com.hbfangrui.user.base.model.UserId;

/**
 * Created by taoli on 15/11/1.
 */
public class UserEmailActivatedEvent extends UserEmailsEvent{
    private UserEmailActivatedEvent(UserId id, int version, Email email) {
        super(id, version, email);
    }

    public static UserEmailActivatedEvent apply(UserId owner, int version, Email email) {
        return new UserEmailActivatedEvent(owner, version, email);
    }
}
