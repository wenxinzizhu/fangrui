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
public class UserDisabledEvent extends UserEvent {
    public UserDisabledEvent(UserId id, int version) {
        super(id, version);
    }

    public static UserDisabledEvent apply(UserId userId, int version){
        return new UserDisabledEvent(userId, version);
    }
}
