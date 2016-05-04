package com.hbfangrui.user.command.application.cmd.email;

import com.hbfangrui.base.ddd.domain.model.support.Email;
import com.hbfangrui.user.base.model.UserId;

/**
 * Created by taoli on 15/11/1.
 */
public class ActiveUserEmailCMD extends UserEmailCMD{
    private ActiveUserEmailCMD(UserId userId, Email email) {
        super(userId, email);
    }

    public static ActiveUserEmailCMD apply(UserId owner, Email email) {
        return new ActiveUserEmailCMD(owner, email);
    }

    public static ActiveUserEmailCMD apply(UserId owner, String email){
        return apply(owner, Email.apply(email));
    }
}
