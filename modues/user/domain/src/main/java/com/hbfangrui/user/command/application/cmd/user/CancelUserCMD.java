package com.hbfangrui.user.command.application.cmd.user;

import com.hbfangrui.user.base.model.UserId;
import lombok.Value;

/**
 * Created by taoli on 15/10/31.
 */
@Value
public class CancelUserCMD extends UserCMD{
    private CancelUserCMD(UserId userId) {
        super(userId);
    }

    public static CancelUserCMD apply(UserId userId){
        return new CancelUserCMD(userId);
    }
}
