package com.hbfangrui.user.command.application.cmd.user;

import com.hbfangrui.user.base.model.UserChecker;
import com.hbfangrui.user.base.model.UserId;
import lombok.Value;

/**
 * Created by taoli on 15/10/30.
 */
@Value
public class ChangePasswordCMD extends UserCMD{
    private final String pwd;

    private ChangePasswordCMD(UserId id, String pwd){
        super(id);
        UserChecker.checkPassword(pwd);
        this.pwd = pwd;
    }

    public static ChangePasswordCMD apply(UserId userId, String pwd){
        return new ChangePasswordCMD(userId, pwd);
    }
}
