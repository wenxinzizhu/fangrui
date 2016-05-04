package com.hbfangrui.user.command.domain.event.phone;

import com.hbfangrui.base.ddd.domain.model.support.Phone;
import com.hbfangrui.user.base.model.UserId;
import lombok.Data;

/**
 * Created by taoli on 15/11/1.
 */
@Data
public class UserPhoneAddedEvent extends UserPhonesEvent{
    private UserPhoneAddedEvent(UserId id, int version, Phone phone) {
        super(id, version, phone);
    }

    public static UserPhoneAddedEvent apply(UserId owner, int version, Phone phone){
        return new UserPhoneAddedEvent(owner, version, phone);
    }
}
