package com.hbfangrui.user.command.domain.event.user;

import com.hbfangrui.user.base.model.UserId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Created by taoli on 15/10/31.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserEnabledEvent extends UserEvent {
    public UserEnabledEvent(UserId id, int version) {
        super(id, version);
    }

    public static UserEnabledEvent apply(UserId userId, int version){
        return new UserEnabledEvent(userId, version);
    }
}
