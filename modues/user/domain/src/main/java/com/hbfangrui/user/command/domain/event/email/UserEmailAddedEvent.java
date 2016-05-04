package com.hbfangrui.user.command.domain.event.email;

import com.hbfangrui.base.ddd.domain.model.support.Email;
import com.hbfangrui.user.base.model.UserId;
import lombok.Data;

/**
 * Created by taoli on 15/11/1.
 */
@Data
public class UserEmailAddedEvent extends UserEmailsEvent{
    private UserEmailAddedEvent(UserId id, int version, Email email) {
        super(id, version, email);
    }

    public static UserEmailAddedEvent apply(UserId owner, int version, Email email){
        return new UserEmailAddedEvent(owner, version, email);
    }
}
