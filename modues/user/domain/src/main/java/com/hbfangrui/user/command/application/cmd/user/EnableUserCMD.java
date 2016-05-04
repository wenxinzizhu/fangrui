package com.hbfangrui.user.command.application.cmd.user;

import com.hbfangrui.user.base.model.UserId;
import lombok.Value;

/**
 * Created by taoli on 15/10/31.
 */
@Value
public class EnableUserCMD extends UserCMD {
    private EnableUserCMD(UserId userId) {
        super(userId);
    }

    public static EnableUserCMD apply(UserId id){
        return new EnableUserCMD(id);
    }
}
