package com.hbfangrui.user.command.application.cmd.phone;

import com.hbfangrui.base.ddd.domain.model.support.Phone;
import com.hbfangrui.user.base.model.UserId;
import lombok.Data;

/**
 * Created by taoli on 15/11/14.
 */
@Data
public class SetDefaultPhoneCMD extends UserPhoneCMD{
    private SetDefaultPhoneCMD(UserId userId, Phone phone) {
        super(userId, phone);
    }

    public static SetDefaultPhoneCMD apply(UserId userId, Phone phone){
        return new SetDefaultPhoneCMD(userId, phone);
    }

    public static SetDefaultPhoneCMD apply(UserId userId, String phone){
        return apply(userId, Phone.apply(phone));
    }
}
