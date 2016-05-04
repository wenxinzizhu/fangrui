package com.hbfangrui.user.command.application.cmd.user;

import com.hbfangrui.user.base.model.UserId;
import lombok.Value;

/**
 * Created by taoli on 15/10/31.
 */
@Value
public class DisableUserCMD extends UserCMD{
    private DisableUserCMD(UserId userId){
        super(userId);
    }

    public static DisableUserCMD apply(UserId userId){
        return new DisableUserCMD(userId);
    }
}
