package com.hbfangrui.user.command.application.cmd.user;

import com.hbfangrui.user.base.model.UserId;
import lombok.Value;

/**
 * Created by taoli on 15/10/31.
 */
@Value
public class ActiveUserCMD extends UserCMD{
    private ActiveUserCMD(UserId userId) {
        super(userId);
    }

    public static ActiveUserCMD apply(UserId userId){
        return new ActiveUserCMD(userId);
    }
}
