package com.hbfangrui.user.command.domain.event.user;

import com.hbfangrui.user.base.model.UserId;
import com.hbfangrui.user.base.model.UserPassword;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Created by taoli on 15/10/31.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PasswordChangedEvent extends UserEvent {
    private UserPassword userPassword;
    public PasswordChangedEvent(UserId id, int version,  UserPassword userPassword) {
        super(id, version);
        this.userPassword = userPassword;
    }

    public static PasswordChangedEvent apply(UserId userId, int version, UserPassword password){
        return new PasswordChangedEvent(userId, version, password);
    }
}
