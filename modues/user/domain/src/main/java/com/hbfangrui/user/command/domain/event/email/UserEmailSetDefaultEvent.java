package com.hbfangrui.user.command.domain.event.email;

import com.hbfangrui.base.ddd.domain.model.support.Email;
import com.hbfangrui.user.base.model.UserId;
import lombok.Data;

/**
 * Created by taoli on 15/11/2.
 */
@Data
public class UserEmailSetDefaultEvent extends UserEmailsEvent{
    private UserEmailSetDefaultEvent(UserId id, int version, Email email) {
        super(id, version, email);
    }

    public static UserEmailSetDefaultEvent apply(UserId id, int version, Email email) {
        return new UserEmailSetDefaultEvent(id, version, email);
    }
}
