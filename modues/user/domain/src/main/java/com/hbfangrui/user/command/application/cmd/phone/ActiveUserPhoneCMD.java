package com.hbfangrui.user.command.application.cmd.phone;

import com.hbfangrui.base.ddd.domain.model.support.Phone;
import com.hbfangrui.user.base.model.UserId;
import lombok.Data;

/**
 * Created by taoli on 15/11/14.
 */
@Data
public class ActiveUserPhoneCMD extends UserPhoneCMD {
    private ActiveUserPhoneCMD(UserId userId, Phone phone) {
        super(userId, phone);
    }

    public static ActiveUserPhoneCMD apply(UserId userId, Phone phone){
        return new ActiveUserPhoneCMD(userId, phone);
    }

    public static ActiveUserPhoneCMD apply(UserId userId, String phone){
        return apply(userId, Phone.apply(phone));
    }
}
