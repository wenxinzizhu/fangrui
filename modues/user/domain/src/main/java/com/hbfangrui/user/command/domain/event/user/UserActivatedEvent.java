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
public class UserActivatedEvent extends UserEvent {
    private UserActivatedEvent(UserId id, int version) {
        super(id, version);
    }

    public static UserActivatedEvent apply(UserId id, int version){
        return new UserActivatedEvent(id, version);
    }
}
