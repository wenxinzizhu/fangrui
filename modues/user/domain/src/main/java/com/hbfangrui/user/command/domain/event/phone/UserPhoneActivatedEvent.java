package com.hbfangrui.user.command.domain.event.phone;

import com.hbfangrui.base.ddd.domain.model.support.Phone;
import com.hbfangrui.user.base.model.UserId;

/**
 * Created by taoli on 15/11/1.
 */
public class UserPhoneActivatedEvent extends UserPhonesEvent{
    private UserPhoneActivatedEvent(UserId id, int version, Phone phone) {
        super(id, version, phone);
    }

    public static UserPhoneActivatedEvent apply(UserId owner, int version, Phone phone) {
        return new UserPhoneActivatedEvent(owner, version, phone);
    }
}
