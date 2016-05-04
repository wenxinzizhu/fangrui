package com.hbfangrui.user.command.application.cmd.phone;

import com.hbfangrui.base.ddd.domain.model.support.Phone;
import com.hbfangrui.user.base.model.UserId;
import lombok.Data;

/**
 * Created by taoli on 15/11/14.
 */
@Data
public class AddUserPhoneCMD extends UserPhoneCMD{
    private AddUserPhoneCMD(UserId userId, Phone phone) {
        super(userId, phone);
    }

    public static  AddUserPhoneCMD apply(UserId userId, Phone phone){
        return new AddUserPhoneCMD(userId, phone);
    }

    public static AddUserPhoneCMD apply(UserId userId, String phone){
        return apply(userId, Phone.apply(phone));
    }
}
