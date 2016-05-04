package com.hbfangrui.user.command.application.cmd.phone;

import com.hbfangrui.base.ddd.domain.model.support.Phone;
import com.hbfangrui.user.base.model.UserId;
import lombok.Data;

/**
 * Created by taoli on 15/11/14.
 */
@Data
public class DeleteUserPhoneCMD extends UserPhoneCMD{
    private DeleteUserPhoneCMD(UserId userId, Phone phone){
        super(userId, phone);
    }

    public static DeleteUserPhoneCMD apply(UserId userId, Phone phone){
        return new DeleteUserPhoneCMD(userId, phone);
    }

    public static DeleteUserPhoneCMD apply(UserId userId, String phone){
        return apply(userId, Phone.apply(phone));
    }
}
