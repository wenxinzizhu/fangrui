package com.hbfangrui.user.command.domain.event.phone;

import com.hbfangrui.base.ddd.domain.model.support.Phone;
import com.hbfangrui.user.base.model.UserId;
import lombok.Data;

/**
 * Created by taoli on 15/11/2.
 */
@Data
public class UserPhoneSetDefaultEvent extends UserPhonesEvent{
    private UserPhoneSetDefaultEvent(UserId id, int version, Phone phone) {
        super(id, version, phone);
    }

    public static UserPhoneSetDefaultEvent apply(UserId id, int version, Phone phone) {
        return new UserPhoneSetDefaultEvent(id, version, phone);
    }
}
